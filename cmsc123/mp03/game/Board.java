package cmsc123.mp03.game;

import java.util.HashMap;

import cmsc123.mp03.framework.BroadcasterInterface;
import cmsc123.mp03.framework.Event;
import cmsc123.mp03.framework.ListenerInterface;
import cmsc123.mp03.framework.ReactorInterface;
import cmsc123.mp03.framework.ds.Link;
import cmsc123.mp03.framework.ds.LinkList;

public class Board implements ReactorInterface, BroadcasterInterface {

	private static final int X = 7;
	private static final int Y = 6;
	
    private HashMap<String, LinkList<ListenerInterface>> listeners;
    private int[][] boardArray;
    
    public Board() {
        listeners = new HashMap<>();
        
        int[][] boardArrayTemp = {
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 0, 0, 0, 0}
        };
        
        boardArray = boardArrayTemp;
    }

    @Override
    public void react(Object event) {
        // TODO Auto-generated method stub
    }

    @Override
    public void broadcast(String eventName) {
        LinkList<ListenerInterface> list = listeners.get(eventName);
        
        if (list != null) {
            Link<ListenerInterface> link = list.getFirst();
            link.getValue().obey(new Event());
            
            while (link.hasNext()) {
                link = link.getNext();
                link.getValue().obey(new Event());
            }
        }
    }

    @Override
    public void addListener(String eventName, ListenerInterface listener) {
        LinkList<ListenerInterface> list;
        
        if (listeners.get(eventName) == null) {
            list = new LinkList<ListenerInterface>(new Link<ListenerInterface>(listener));
            listeners.put(eventName, list);
        } else {
            list = listeners.get(eventName);
            Link<ListenerInterface> link = new Link<ListenerInterface>(listener);
            link.setNext(list.getFirst());
            list.setFirst(link);
        }
    }

}
