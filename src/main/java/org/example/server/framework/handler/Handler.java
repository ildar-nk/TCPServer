package org.example.server.framework.handler;

import org.example.server.framework.http.Request;

import java.io.OutputStream;

public interface Handler {
    void handle(final Request request, final OutputStream response);

}
