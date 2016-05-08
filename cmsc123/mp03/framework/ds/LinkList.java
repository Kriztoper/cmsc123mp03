package cmsc123.mp03.framework.ds;

public class LinkList<E> {

    public Link<E> first;
    
    private int size;
    
    public LinkList(Link<E> first) {
        setSize(first == null ? 0 : 1);
        
        this.first = first;
    }
    
    public Link<E> getFirst() {
        return first;
    }
    
    public void setFirst(Link<E> first) {
        this.first = first;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
}
