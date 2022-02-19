package org.example.server.app;

import org.example.json.JSONEncoder;
import org.example.server.app.handler.MainHandler;
import org.example.server.framework.Server;
import org.example.server.framework.handler.Handler;
import org.example.server.framework.http.Request;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        final JSONEncoder encoder = new JSONEncoder();

        final Server server = new Server();
        final MainHandler mainHandler = new MainHandler(encoder);

        //            try {
        //                response.write(
        //                        """
        //                                HTTP/1.1 200 OK\r
        //                                Content-Length: 5\r
        //                                Connection: close\r
        //                                \r
        //                                Hello
        //                                """.getBytes(StandardCharsets.UTF_8)
        //                );
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        server.register("/transaction", mainHandler::getSingle);
        server.register("/hello", mainHandler::hello);
        server.register("/hi", mainHandler::hi);
        server.register("/favicon.ico", mainHandler::favicon);

        server.serve(9999);
    }
}
