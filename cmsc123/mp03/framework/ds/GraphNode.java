package cmsc123.mp03.framework.ds;

public class GraphNode {
    
    private int value;
    private GraphNode north;
    private GraphNode northwest;
    private GraphNode west;
    private GraphNode southwest;
    private GraphNode south;
    private GraphNode southeast;
    private GraphNode east;
    private GraphNode northeast;
    
    public GraphNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public GraphNode getNorth() {
        return north;
    }

    public void setNorth(GraphNode north) {
        this.north = north;
    }

    public GraphNode getNortheast() {
        return northeast;
    }

    public void setNortheast(GraphNode northeast) {
        this.northeast = northeast;
    }

    public GraphNode getEast() {
        return east;
    }

    public void setEast(GraphNode east) {
        this.east = east;
    }

    public GraphNode getSoutheast() {
        return southeast;
    }

    public void setSoutheast(GraphNode southeast) {
        this.southeast = southeast;
    }

    public GraphNode getSouth() {
        return south;
    }

    public void setSouth(GraphNode south) {
        this.south = south;
    }

    public GraphNode getSouthwest() {
        return southwest;
    }

    public void setSouthwest(GraphNode southwest) {
        this.southwest = southwest;
    }

    public GraphNode getWest() {
        return west;
    }

    public void setWest(GraphNode west) {
        this.west = west;
    }

    public GraphNode getNorthwest() {
        return northwest;
    }

    public void setNorthwest(GraphNode northwest) {
        this.northwest = northwest;
    }
}
