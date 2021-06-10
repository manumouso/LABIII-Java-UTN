package edu.utn.connection.server;

import com.sun.net.httpserver.HttpServer;
import edu.utn.connection.server.requestHandler.Attack;
import edu.utn.connection.server.requestHandler.Move;
import edu.utn.manager.ServiceManager;

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

    public void startConnection(ServiceManager service) throws IOException {
        try {
            getServer().createContext("/attack",new Attack(service));
            getServer().createContext("/move",new Move());
            getServer().setExecutor(null);
            getServer().start();

        }catch (IOException e){
            System.out.println("\t\t\tIO Exception: "+e.getMessage());
        }catch (Exception e){
            System.out.println("\t\t\tException: "+e.getMessage());
        }
    }
    public void closeConnection() throws IOException {
        if(getServer()!=null){
            getServer().stop(0);
        }
    }

}
