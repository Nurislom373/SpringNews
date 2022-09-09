package org.khasanof.elasticsearch.indices;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Service;

import static org.elasticsearch.client.RequestOptions.DEFAULT;

@Service
@RequiredArgsConstructor
public class ElasticIndexer {
    private final RestHighLevelClient client;

    public void reCreateIndex(String name) {
        dropIndexIfExists(name);
        createIndex(name);
    }

    @SneakyThrows
    private void createIndex(String name) {
        client.indices().create(new CreateIndexRequest(name), DEFAULT);
    }

    private void dropIndexIfExists(String name) {
        if (hasIndex(name)) {
            deleteIndex(name);
        }
    }

    @SneakyThrows
    private boolean hasIndex(String name) {
        return client.indices().exists(new GetIndexRequest(name), DEFAULT);
    }

    @SneakyThrows
    private void deleteIndex(String name) {
        client.indices().delete(new DeleteIndexRequest(name), DEFAULT);
    }
}
