package assign4;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String message;

            while ((message = in.readLine()) != null) {
                if (message.trim().equalsIgnoreCase("quit")) {
                    System.out.println("Client requested to quit");
                    out.println("Bye!");
                    break;
                }

                System.out.println("Client sent: " + message);

                out.println(message);
            }

        } catch (IOException e) {
            System.out.println("Client has disconnected");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
