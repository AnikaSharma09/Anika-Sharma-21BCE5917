import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;

@ClientEndpoint
public class GameClient {

    private Session userSession = null;

    public GameClient(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received: " + message);
    }

    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    public static void main(String[] args) {
        URI uri = URI.create("ws://localhost:8080/game");
        GameClient client = new GameClient(uri);

        // Simulate sending a game move
        client.sendMessage("A-P1:L");
    }
}
