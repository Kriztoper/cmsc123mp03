package cmsc123.mp03.framework.ds;

public interface NodeInterface<E> {

    public E getValue();
    public void setValue(E value);
    public NodeInterface<E>[] getChildren();
    public void setChildren(NodeInterface<E>[] children);
    
}
