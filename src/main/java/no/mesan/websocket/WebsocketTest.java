package no.mesan.websocket;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/test")
public class WebsocketTest {
    @OnOpen
    public void onOpen(final Session session) {
    }

    @OnMessage
    public String onMessage(final String message, final Session session) {
        return message;
    }

    @OnClose
    public void onClose(final Session session, final CloseReason closeReason) {
    }
}
