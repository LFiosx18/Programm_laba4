package server;

import java.io.IOException;
import java.sql.SQLException;

public class ServerStart {
    public static void main(String[] args) throws IOException, SQLException {
        Server server = new Server();
        server.run();
    }
}
