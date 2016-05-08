package cmsc123.mp03.framework;

public interface BroadcasterInterface {

    public void broadcast(String eventName);
    
    public void addListener(String eventName, ListenerInterface listener);
    
}
