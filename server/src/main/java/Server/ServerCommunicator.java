package Server;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

import Handler.CommandHandler;
import Handler.ParseIntegerHandler;
import Handler.ToLowerCaseHandler;
import Handler.TrimHandler;

public class ServerCommunicator {

    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;
    private void run(String portNumber) {

        System.out.println("Initializing HTTP Server");

        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        server.setExecutor(null);
        System.out.println("Creating contexts");

        server.createContext("/trim",new TrimHandler());
        server.createContext("/parseInteger",new ParseIntegerHandler());
        server.createContext("/toLowerCase",new ToLowerCaseHandler());
        server.createContext("/command",new CommandHandler());


        System.out.println("Starting server");
        server.start();
        System.out.println("ServerCommunicator started");
    }

    public static void main(String[] args) {
        String portNumber = "1234";
        new ServerCommunicator().run(portNumber);
    }
}