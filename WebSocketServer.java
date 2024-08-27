import org.glassfish.tyrus.server.Server;

public class WebSocketServer {

    public static void main(String[] args) {
        Server server = new Server("localhost", 8080, "/", null, GameServer.class);

        try {
            server.start();
            System.out.println("Server started on port 8080");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
