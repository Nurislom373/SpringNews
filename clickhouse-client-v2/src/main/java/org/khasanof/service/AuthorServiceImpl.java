package org.khasanof.service;

import com.clickhouse.client.api.Client;
import com.clickhouse.client.api.data_formats.ClickHouseBinaryFormatReader;
import com.clickhouse.client.api.insert.InsertResponse;
import com.clickhouse.client.api.metrics.ServerMetrics;
import com.clickhouse.client.api.query.QueryResponse;
import org.khasanof.factory.ClickhouseClientFactory;
import org.khasanof.model.Author;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 6/9/25
 */
public class AuthorServiceImpl implements AuthorService {

    private Client client;
    private final ClickhouseClientFactory factory;

    public AuthorServiceImpl(ClickhouseClientFactory factory) {
        this.factory = factory;
        setClient();
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        CompletableFuture<QueryResponse> futureQueryResponse = client.query("select * from authors");
        try (QueryResponse queryResponse = futureQueryResponse.get(30, TimeUnit.SECONDS)) {
            ClickHouseBinaryFormatReader reader = client.newBinaryFormatReader(queryResponse);

            while (reader.hasNext()) {
                reader.next(); // Read the next record from stream and parse it

                Author author = createAuthor(reader);
                authors.add(author);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return authors;
    }

    private Author createAuthor(ClickHouseBinaryFormatReader reader) {
        UUID id = reader.getUUID("id");
        String name = reader.getString("name");
        String email = reader.getString("email");
        LocalDateTime createdAt = reader.getLocalDateTime("created_at");
        return new Author(id, name, email, createdAt);
    }

    @Override
    public void create(Author author) {
        CompletableFuture<InsertResponse> futureInsertResponse = client.insert("authors", List.of(author));

        futureInsertResponse.whenComplete((response, throwable) -> {
            if (throwable != null) {
                System.out.println("Error: " + throwable.getMessage());
                return;
            }
            System.out.println("Inserted count: " + response.getMetrics().getMetric(ServerMetrics.NUM_ROWS_WRITTEN).getLong());
        });
    }

    @Override
    public void update(Author author) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("name", author.getName());
        params.put("email", author.getEmail());
        params.put("createdAt", author.getCreatedAt());

        CompletableFuture<QueryResponse> futureQueryResponse = client.query("""
                ALTER TABLE authors 
                UPDATE name = {name:String}, email = {email:String}
                WHERE id = {id:UUID};
                """, params);

        futureQueryResponse.whenComplete((response, throwable) -> {
            if (throwable != null) {
                System.out.println("Error: " + throwable.getMessage());
                return;
            }
            System.out.println("Updated: " + response.getMetrics().getMetric(ServerMetrics.NUM_ROWS_WRITTEN).getLong());
        });
    }

    @Override
    public void delete(Author author) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());

        CompletableFuture<QueryResponse> futureQueryResponse = client.query("""
                ALTER TABLE authors DELETE WHERE id = {id:UUID}
                """, params);

        futureQueryResponse.whenComplete((response, throwable) -> {
            if (throwable != null) {
                System.out.println("Error: " + throwable.getMessage());
                return;
            }
            System.out.println("Deleted: " + response.getMetrics().getMetric(ServerMetrics.NUM_ROWS_WRITTEN).getLong());
        });
    }

    private void setClient() {
        this.client = factory.createClient();
        this.client.register(Author.class, client.getTableSchema("authors"));
    }
}
