package cmsc123.mp03.framework.ds;

public class Node<E> implements NodeInterface<E> {

    private E value;
    private NodeInterface<E>[] children;
    
    public Node(E value) {
        this.value = value;
    }

    @Override
    public void setChildren(NodeInterface<E>[] children) {
        this.children = children;
    }

    @Override
    public E getValue() {
        return value;
    }

    @Override
    public NodeInterface<E>[] getChildren() {
        return children;
    }

    @Override
    public void setValue(E value) {
        this.value = value;
    }
}
