package org.khasanof.elasticsearch.repository;

import org.khasanof.elasticsearch.model.Message;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ElasticsearchRepository<Message, Integer> {
}
