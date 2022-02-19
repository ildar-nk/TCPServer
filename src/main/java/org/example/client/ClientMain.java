package org.example.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientMain {
    public static void main(String[] args) {
        try (
                final Socket socket = new Socket("127.0.0.1", 9999);
                final InputStream in = socket.getInputStream();
                final OutputStream out = socket.getOutputStream();
                final ByteArrayOutputStream buffer = new ByteArrayOutputStream()

        ) {
            buffer.write(
                    """
                            POST / HTTP/1.1\r
                            Host: 127.0.0.1\r
                            Content-Type: image/png\r
                            Content-Length: 4892\r
                            \r                                                
                            """.getBytes(StandardCharsets.UTF_8)
            );
            Files.copy(Paths.get("java.png"), buffer);
            out.write(buffer.toByteArray());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
