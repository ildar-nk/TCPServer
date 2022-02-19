package org.example.server.framework.http;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ResponseUtils {
    private ResponseUtils() {

    }

    public static void write(final OutputStream response, final int statusCode, final String statusText, final String contentType, final byte[] content) {
        try {
            response.write(
                    String.format(
                            """
                                    HTTP/1.1 %d %s\r
                                    Content-Length: %d\r
                                    Content-Type: %s\r
                                    Connection: close\r
                                    \r
                                    """,
                            statusCode,
                            statusText,
                            content.length,
                            contentType
                    ).getBytes(StandardCharsets.UTF_8)
            );
            response.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void write(final OutputStream response, final int statusCode, final String statusText, final String contentType, final String content) {
        write(response, statusCode, statusText, contentType, content.getBytes(StandardCharsets.UTF_8));

    }
    public static void write(final OutputStream response, final String contentType, final byte[] content){
        write(response, 200, "OK", contentType, content);

    }public static void write(final OutputStream response, final String contentType, final String content){
        write(response, contentType, content.getBytes(StandardCharsets.UTF_8));

    }
}
