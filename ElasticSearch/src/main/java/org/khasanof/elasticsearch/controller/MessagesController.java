package org.khasanof.elasticsearch.controller;

import org.khasanof.elasticsearch.model.Message;
import org.khasanof.elasticsearch.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/message")
public class MessagesController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Message message) {
        messageRepository.save(message);
        return new ResponseEntity<>("Successfully Created - Message", HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<String> create(@RequestBody Message message) {
//        Assert.notNull(message, "Message obj required is not null!");
//        elasticsearchOperations.save(message);
//        return new ResponseEntity<>("Successfully saved", HttpStatus.CREATED);
//    }
//
//    @GetMapping(value = "/get/{id}")
//    public ResponseEntity<Message> get(@PathVariable Integer id) {
//        Assert.notNull(id, "id required is not null!");
//        Query query = new NativeSearchQueryBuilder()
//                .withFilter(matchQuery("id", id)).build();
//        SearchHits<Message> search = elasticsearchOperations.search(query, Message.class, IndexCoordinates.of("my-application"));
//        return new ResponseEntity<>(search.getSearchHit(0).getContent(), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Message>> getAll() {
        return new ResponseEntity<>(StreamSupport.stream(
                messageRepository.findAll().spliterator(), false
        ).collect(Collectors.toList()), HttpStatus.OK);
    }
}
