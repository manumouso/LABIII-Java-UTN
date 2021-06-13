package edu.utn.factory;

import edu.utn.connection.client.Client;
import edu.utn.connection.server.Server;

public class NetworkFactory {

    public Server createServer(String IP,int port){

        return new Server(IP,port);
    }

    public Client createClient(){

        return new Client();
    }
}
