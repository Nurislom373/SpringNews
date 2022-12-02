package org.khasanof.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String from;
    private String to;
    private Long amount;

    public TransactionEntity(Integer id, String from, String to, Long amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public TransactionEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }
}
