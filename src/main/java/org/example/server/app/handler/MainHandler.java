package org.example.server.app.handler;

import org.example.server.framework.handler.Handler;
import org.example.server.framework.http.Request;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainHandler implements Handler {
    @Override
    public void handle(Request request, OutputStream response) {
        try {
            response.write(
                    """
                            HTTP/1.1 200 OK\r
                            Content-Length: 5\r
                            Connection: close\r
                            \r
                            Hello
                            """.getBytes(StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
