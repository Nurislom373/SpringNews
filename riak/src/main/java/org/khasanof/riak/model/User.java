package org.khasanof.riak.model;

public record User(
        Long id,
        String name,
        String email
) {
}
