package tcpbs;

import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;
import java.io.PrintWriter;

public class TCPClient {
    private Scanner sc; // Allows taking user input
    private Socket socket;
    private int port;
    private InetAddress address; // Used to encapsulate IP address
    
    public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getByName("127.0.0.1"); // Local loop back IP running on the same computer
        int port = 7077; // Sets IP and Port number
        
        if (args.length > 0) {
            ip = InetAddress.getByName(args[0]); // IP address from host's name
            port = Integer.parseInt(args[1]); // Assigns IP and Port
        }
        
        TCPClient client = new TCPClient(ip, port); // Passes IP and port number
        System.out.println("Enter message to send to server"); // User enters message to send to server
        client.send(); // Calls send method
    }
    
    private void send() throws IOException {
        String input;
        while (true) {
            input = sc.nextLine(); // Takes input from user
            System.out.println("Message sent");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Converts characters to bytes to be sent
            out.println(input);
        }
    }
    
    private TCPClient(InetAddress serverAddress, int serverPort) throws Exception {
        this.address = serverAddress;
        this.port = serverPort;
        
        // Connects with the server using socket
        this.socket = new Socket(this.address, this.port); // Creates socket and connects to the port at the given IP
        this.sc = new Scanner(System.in);
    }
}



