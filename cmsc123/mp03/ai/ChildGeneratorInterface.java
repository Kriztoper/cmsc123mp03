package cmsc123.mp03.ai;

import cmsc123.mp03.framework.ds.NodeInterface;

public interface ChildGeneratorInterface<E> {

    public NodeInterface[] generateChildren(NodeInterface<E> node);
    
}
