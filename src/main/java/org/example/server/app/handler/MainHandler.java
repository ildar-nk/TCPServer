package org.example.server.app.handler;

import org.example.reflection.json.JSONEncoder;
import org.example.server.app.domain.Transaction;
import org.example.server.framework.http.ContentTypes;
import org.example.server.framework.http.Request;
import org.example.server.framework.http.ResponseUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;

public class MainHandler {

    private final JSONEncoder encoder;

    public MainHandler(JSONEncoder encoder) {

        this.encoder = encoder;

    }
    public void getSingle(Request request, OutputStream response){
        ResponseUtils.write(response, ContentTypes.APPLICATION_JSON, encoder.encode(new Transaction(UUID.randomUUID().toString(), "45** **** **** **01", "45** **** **** **02", 10_000_00, Instant.now().getEpochSecond())));
    }

    public void hello(Request request, OutputStream response) {
        ResponseUtils.write(response, ContentTypes.TEXT_PLAIN, "Hello");
//        try {
//            response.write(
//                    """
//                            HTTP/1.1 200 OK\r
//                            Content-Length: 5\r
//                            Connection: close\r
//                            \r
//                            Hello
//                            """.getBytes(StandardCharsets.UTF_8)
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void hi(Request request, OutputStream response) {
        ResponseUtils.write(response, ContentTypes.TEXT_PLAIN, "Hi");
//        try {
//            response.write(
//                    """
//                            HTTP/1.1 200 OK\r
//                            Content-Length: 2\r
//                            Connection: close\r
//                            \r
//                            Hi
//                            """.getBytes(StandardCharsets.UTF_8)
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    public void favicon(Request request, OutputStream response){

        try {
            final byte[] favicon = Files.readAllBytes(Paths.get("favicon.ico"));
            ResponseUtils.write(response, "image/x-icon", favicon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
