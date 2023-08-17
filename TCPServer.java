package tcpbs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerSocket server;

    public static void main(String[] args) throws Exception {
        String IP = "127.0.0.1"; // local loop back IP that is running on the same computer
        int Port = 7077; // sets the IP and Port number

        if (args.length > 0) {
            IP = args[0];
            Port = Integer.parseInt(args[1]); // assigns IP and Port
        }

        TCPServer server = new TCPServer(IP, Port); // passes the IP and port
        server.interact(); // calls interact
    }

    public TCPServer(String address, int port) throws Exception {
        if (address != null && !address.isEmpty()) {
            this.server = new ServerSocket(port, 1, InetAddress.getByName(address));
        } else {
            this.server = new ServerSocket(0, 1, InetAddress.getLocalHost()); // passes the port and address
        }
    }

    private void interact() throws Exception {
        String message;
        Socket client = this.server.accept(); // accepts client, goes to do clients' requests
        System.out.println("Client Connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        while ((message = in.readLine()) != null) {
            System.out.println("Client: " + message); // prints clients received bytes as data
            client.sendUrgentData(1); // sends one byte of urgent data to the socket
        }
    }

    public InetAddress getSocket() {
        return this.server.getInetAddress(); // gets address and sets as a global variable
    }

    public int getPort() {
        return this.server.getLocalPort(); // gets port number and sets as a global variable
    }
}


