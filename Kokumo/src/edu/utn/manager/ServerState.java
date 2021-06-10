package edu.utn.manager;

public class ServerState {
    private boolean serverWasCreated;
    private boolean running;
    private boolean connectedClient;

    public boolean serverWasCreated() {
        return serverWasCreated;
    }
    public void setServerWasCreated(boolean serverWasCreated) {
        this.serverWasCreated = serverWasCreated;
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public boolean connectedClient() {
        return connectedClient;
    }
    public void setConnectedClient(boolean connectedClient) {
        this.connectedClient = connectedClient;
    }
}
