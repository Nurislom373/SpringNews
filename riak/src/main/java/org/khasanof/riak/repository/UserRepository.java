package org.khasanof.riak.repository;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.khasanof.riak.model.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private static final String BUCKET = "users";

    private final RiakClient client;
    private final ObjectMapper objectMapper;

    public void save(User user) {
        try {
            String json = objectMapper.writeValueAsString(user);
            Namespace namespace = new Namespace(BUCKET);
            Location location = new Location(namespace, user.id().toString());

            RiakObject object = new RiakObject()
                    .setContentType("application/json")
                    .setValue(BinaryValue.create(json));

            StoreValue operation = new StoreValue.Builder(object)
                            .withLocation(location)
                            .build();

            client.execute(operation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }

    public User findById(Long id) {
        try {
            Namespace namespace = new Namespace(BUCKET);
            Location location = new Location(namespace, id.toString());
            FetchValue operation = new FetchValue.Builder(location).build();

            FetchValue.Response response = client.execute(operation);
            if (response.isNotFound()) {
                return null;
            }

            RiakObject object = response.getValue(RiakObject.class);
            String json = object.getValue().toString();
            return objectMapper.readValue(json, User.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get user", e);
        }
    }

    public void delete(Long id) {
        try {
            Namespace namespace = new Namespace(BUCKET);
            Location location = new Location(namespace, id.toString());
            DeleteValue operation = new DeleteValue.Builder(location).build();
            client.execute(operation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user", e);
        }
    }
}
