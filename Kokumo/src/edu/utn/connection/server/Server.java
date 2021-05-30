package edu.utn.connection.server;

import com.sun.net.httpserver.HttpServer;
import edu.utn.connection.server.requestHandler.Attack;
import edu.utn.connection.server.requestHandler.Move;

import java.io.IOException;
import java.net.InetSocketAddress;


public class Server {


    private String IP;
    private int port;
    private HttpServer server;

    public Server(String IP, int port) {
        this.IP = IP;
        this.port = port;
    }

    private HttpServer getServer() throws IOException {
        if(server==null){
            server=HttpServer.create(new InetSocketAddress(IP,port),0);
        }
        return server;
    }

    public String getIP() {
        return IP;
    }
    public int getPort() {
        return port;
    }

    public void startConnection() throws IOException {
        getServer().createContext("/attack",new Attack());
        getServer().createContext("/move",new Move());
        getServer().setExecutor(null);
        getServer().start();
    }
    public void closeConnection() throws IOException {
        getServer().stop(60);
    }

}
