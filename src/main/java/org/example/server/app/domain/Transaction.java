package org.example.server.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    private String id;
    private String from;
    private String to;
    private long amount;
    private long created;

}
