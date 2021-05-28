package edu.utn.connection.server;

import com.sun.net.httpserver.HttpServer;
import edu.utn.connection.server.requestHandler.Attack;
import edu.utn.connection.server.requestHandler.Move;

import java.io.IOException;
import java.net.InetSocketAddress;


public class Server {

    private int port;
    private String IP;
    private HttpServer server;

    public Server(int port, String IP) {
        this.port = port;
        this.IP = IP;
    }

    public HttpServer getServer() throws IOException {
        if(server==null){
            server=HttpServer.create(new InetSocketAddress(IP,port),0);
        }
        return server;
    }

    public int getPort() {
        return port;
    }

    public String getIP() {
        return IP;
    }


    public void startConnection() throws IOException {
        getServer().createContext("/attack",new Attack());
        getServer().createContext("/move",new Move());
        getServer().start();
    }
    public void closeConnection() throws IOException {
        getServer().stop(60);
    }

}
