package org.example.reflection.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.json.JSONEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    private long id;
    private String name;

}
