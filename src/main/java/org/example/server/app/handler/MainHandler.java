package org.example.server.app.handler;

import org.example.server.framework.handler.Handler;
import org.example.server.framework.http.Request;
import org.example.server.framework.http.ResponseUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainHandler {

    public void hello(Request request, OutputStream response) {
        ResponseUtils.write(response, "text/plain", "Hello");
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
        ResponseUtils.write(response, "text/plain", "Hi");
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
}
