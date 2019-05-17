package pathFinder;

import map.Coordinate;
import java.util.*;

public class TreeNode<Coordinate> {
    private Coordinate coord;
    private TreeNode<Coordinate> parent;
    private List<TreeNode<Coordinate>> children;

    public TreeNode(Coordinate coord) {
        this.coord = coord;
        children = new ArrayList<TreeNode<Coordinate>>();
    }

    protected Coordinate getCoordinate() {
        return coord;
    }

    protected List<TreeNode<Coordinate>> getChildren() {
        return children;
    }

    protected void addChild(TreeNode<Coordinate> child) {
        child.setParent(this);
        children.add(child);
    }

    protected void setParent(TreeNode<Coordinate> parent) {
        this.parent = parent;
    }

    protected TreeNode<Coordinate> getParent() {
        return parent;
    }
}
