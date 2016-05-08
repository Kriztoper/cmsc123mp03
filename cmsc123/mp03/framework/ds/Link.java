package cmsc123.mp03.framework.ds;

public class Link<E> {
    
    private E value;
    private Link<E> next;
    
    public Link(E data) {
        this.value = data;
    }
    
    public E getValue() {
        return value;
    }
    
    public void setValue(E data) {
        this.value = data;
    }
    
    public Link<E> getNext() {
        return next;
    }
    
    public void setNext(Link<E> next) {
        this.next = next;
    }
    
    public boolean hasNext() {
        return next != null;
    }
}
