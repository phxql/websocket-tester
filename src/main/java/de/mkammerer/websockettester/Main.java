package de.mkammerer.websockettester;

import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;

public class Main {
    private static final String URL = "TODO";

    public static void main(String[] args) throws Exception {
        WebSocketClient client = new WebSocketClient();
        client.start();
        try {
            run(client);
        } finally {
            client.stop();
        }
    }

    private static void run(WebSocketClient client) throws IOException, InterruptedException {
        Handler handler = new Handler();

        client.connect(handler, URI.create(URL));

        handler.awaitClose();
    }
}
