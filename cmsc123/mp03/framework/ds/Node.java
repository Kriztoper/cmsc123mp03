package cmsc123.mp03.framework.ds;

import cmsc123.mp03.game.BoardNode;

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

    public void displayChildren() {
    	int x = 1;
    	for (NodeInterface<E> child: children) {
    		System.out.println("child "+(x++)+" value "+((BoardNode) child.getValue()).getValue());
    		((BoardNode) child.getValue()).displayBoard();
    	}
    }
}
