package org.example.reflection;

import org.example.reflection.json.JSONDecoder;
import org.example.reflection.json.JSONEncoder;
import org.example.reflection.domain.Patient;
import org.example.server.app.domain.Transaction;

public class Main {
    public static void main(String[] args) {
        final Patient patient = new Patient(1,"noname");
        final JSONEncoder encoder = new JSONEncoder();
        final String json = encoder.encode(patient);
        System.out.println(json);

        final JSONDecoder decoder = new JSONDecoder();
        final Patient result = decoder.decode(json, Patient.class);
        System.out.println("result = " + result);

    }
}
