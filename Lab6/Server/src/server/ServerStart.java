package server;

import java.io.IOException;

public class ServerStart {
    public static void main(String[] args) throws IOException {
        String inputFile = System.getenv("FILE_PATH");
        Server server = new Server(inputFile);
        server.run();
    }
}
