package org.example.address;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        try {
            InetAddress yandex = InetAddress.getByName("ya.ru");
            System.out.println("yandex = " + yandex);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
