package org.example.reflection;

import org.example.json.JSONEncoder;
import org.example.reflection.domain.Patient;

public class Main {
    public static void main(String[] args) {
        final Patient patient = new Patient(1,"noname");
        final JSONEncoder encoder = new JSONEncoder();
        final String json = encoder.encode(patient);
        System.out.println(json);
    }
}
