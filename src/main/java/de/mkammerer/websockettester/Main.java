package de.mkammerer.websockettester;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final URI[] URIS = new URI[]{
        URI.create("TODO"),
        URI.create("TODO")
    };

    public static void main(String[] args) throws Exception {
        WebSocketClient client = new WebSocketClient();
        client.start();
        try {
            List<Handler> handlers = new ArrayList<>();

            LOGGER.info("Starting handlers ...");
            for (URI uri : URIS) {
                handlers.add(run(client, uri));
            }
            LOGGER.info("Done");

            LOGGER.info("Await closing ...");
            for (Handler handler : handlers) {
                handler.awaitClose();
            }
            LOGGER.info("Done");
        } finally {
            client.stop();
        }
    }

    private static Handler run(WebSocketClient client, URI uri) throws IOException {
        Handler handler = new Handler();

        ClientUpgradeRequest request = new ClientUpgradeRequest();
        client.connect(handler, uri, request);

        return handler;
    }
}
