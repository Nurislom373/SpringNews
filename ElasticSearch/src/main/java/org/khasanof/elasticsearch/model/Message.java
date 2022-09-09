package org.khasanof.elasticsearch.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "message")
public class Message implements Serializable {
    @Id
    private Integer id;
    @Field(type = FieldType.Text, name = "message")
    private String message;
    @Field(type = FieldType.Text, name = "from")
    private String from;
    @Field(type = FieldType.Text, name = "to")
    private String to;
}
