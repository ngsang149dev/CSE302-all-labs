package assign4;

import java.io.*;
import java.net.*;

public class ClientMain {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server " + HOST + ":" + PORT);
            System.out.println("Type a message and press Enter. Type 'quit' to exit.");

            String message;

            while ((message = keyboard.readLine()) != null) {
                out.println(message);

                String response = in.readLine();

                if (response == null) {
                    break;
                }

                System.out.println("Server: " + response);

                if (message.trim().equalsIgnoreCase("quit")) {
                    break;
                }
            }

            System.out.println("Connection closed");

        } catch (ConnectException e) {
            System.out.println("Cannot connect: server is not running on port " + PORT);
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
