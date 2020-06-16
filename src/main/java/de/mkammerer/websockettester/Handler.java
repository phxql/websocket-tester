package de.mkammerer.websockettester;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@WebSocket
public class Handler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    private final CountDownLatch closeLatch;

    public Handler() {
        this.closeLatch = new CountDownLatch(1);
    }

    public void awaitClose() throws InterruptedException {
        this.closeLatch.await();
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        LOGGER.debug("onClose({}, '{}')", statusCode, reason);
        this.closeLatch.countDown();
    }

    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        LOGGER.debug("onConnect({})", session);
    }

    @OnWebSocketMessage
    public void onMessage(String msg) {
        LOGGER.debug("onMessage('{}')", msg);
    }

    @OnWebSocketError
    public void onError(Throwable cause) {
        LOGGER.error("onError()", cause);
    }
}
