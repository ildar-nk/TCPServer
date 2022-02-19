package org.example.server.framework;

import org.example.server.framework.guava.Bytes;
import org.example.server.framework.exception.InvalidRequestException;
import org.example.server.framework.exception.InvalidRequestLineException;
import org.example.server.framework.exception.RequestHeaderTooLargeException;
import org.example.server.framework.handler.Handler;
import org.example.server.framework.http.Request;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static final byte[] CRLF = {'\r', '\n'};
    public static final byte[] CRLFCRLF = {'\r', '\n', '\r', '\n'};

    private final Map<String, Handler> handlers = new HashMap<>();

    public void register(final String path, final Handler handler){
        handlers.put(path, handler);


    }

    public void serve(final int port) throws IOException {

        try (
                final ServerSocket serverSocket = new ServerSocket(port);
        ) {
            while (true) {
                try (
                        final Socket socket = serverSocket.accept();
                        final InputStream in = new BufferedInputStream(socket.getInputStream());
                        final OutputStream out = socket.getOutputStream();

                ) {
                    final byte[] buffer = new byte[4096];
                    in.mark(buffer.length);
                    final int read = in.read(buffer);
                    final int requestLineEndIndex = Bytes.indexOf(buffer, CRLF, 0);
                    if (requestLineEndIndex == -1) {
                        throw new InvalidRequestException("no CRLF in request");
                    }


                    final String requestLine = new String(buffer, 0, requestLineEndIndex, StandardCharsets.UTF_8);
                    final String[] requestLineParts = requestLine.split(" ", 3);
                    if (requestLineParts.length != 3) {
                        throw new InvalidRequestLineException(requestLine);
                    }
                    final String requestPath = requestLineParts[1];

                    final int headersStartIndex = requestLineEndIndex + CRLF.length;

                    final int headersEndIndex = Bytes.indexOf(buffer, CRLFCRLF, 0);
                    if (headersEndIndex == -1) {
                        throw new RequestHeaderTooLargeException();
                    }

                    final Map<String, String> headers = new HashMap<>();
                    int contentLength = 0;

                    int currentIndex = headersStartIndex;
                    while (currentIndex < headersEndIndex) {
                        final int currentHeaderEndIndex = Bytes.indexOf(buffer, CRLF, currentIndex);
                        final String header = new String(buffer, currentIndex, (currentHeaderEndIndex - currentIndex), StandardCharsets.UTF_8);
                        currentIndex = currentHeaderEndIndex + CRLF.length;
                        final String[] headerParts = header.split(":", 2);
                        final String headerName = headerParts[0].trim();
                        final String headerValue = headerParts[1].trim();
                        headers.put(headerName, headerValue);
                        if (headerName.equals("Content-Length")) {
                            contentLength = Integer.parseInt(headerValue);
                        }
                    }
                    if (contentLength > 1024 * 1024) {
                        throw new RuntimeException("...");
                    }

                    in.reset();
                    final int bodyStartIndex = headersEndIndex + CRLFCRLF.length;
                    final int bodyEndIndex = bodyStartIndex + contentLength;
                    final long skipped = in.skip(bodyStartIndex);
                    if (skipped != bodyStartIndex) {
                        throw new RuntimeException("...");
                    }
                    final byte[] body = in.readNBytes(contentLength);

                    final Request request = new Request();
                    request.setPath(requestPath);
                    request.setContentLength(contentLength);
                    request.setHeaders(headers);
                    request.setBody(body);


                    // Files.write(Paths.get("upload.png"), body);

                    handlers.get(requestPath).handle(request, out);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
