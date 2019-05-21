package map;

import pathFinder.TreeNode;
import java.util.*;

/**
 * @author Jeffrey Chan, Youhan Xia, Phuc Chu
 * RMIT Algorithms & Analysis, 2019 semester 1
 * <p>
 * Class representing a coordinate.
 */
public class Coordinate implements Comparable<Coordinate> {
    /**
     * row
     */
    protected int r;

    /**
     * column
     */
    protected int c;

    /**
     * Whether coordinate is impassable or not.
     */
    protected boolean isImpassable;

    /**
     * Terrain cost.
     */
    protected int terrainCost;

    /**
     * Tree of the path from the origin to this coordinate
     */
    protected TreeNode<Coordinate> treeNode;

    /**
     * Distance from the source coordinate to this coordinate
     */
    protected int distance;



    /**
     * Construct coordinate (r, c).
     *
     * @param r Row coordinate
     * @param c Column coordinate
     */
    public Coordinate(int r, int c) {
        this(r, c, false);
    } // end of Coordinate()


    /**
     * Construct coordinate (r,c).
     *
     * @param r Row coordinate
     * @param c Column coordinate
     * @param b Whether coordiante is impassable.
     */
    public Coordinate(int r, int c, boolean b) {
        this.r = r;
        this.c = c;
        this.isImpassable = b;
        this.terrainCost = 1;
    } // end of Coordinate()


    /**
     * Default constructor.
     */
    public Coordinate() {
        this(0, 0);
    } // end of Coordinate()


    //
    // Getters and Setters
    //

    public int getRow() { return r; }

    public int getColumn() { return c; }


    public void setImpassable(boolean impassable) {
        isImpassable = impassable;
    }

    public boolean getImpassable() { return isImpassable; }

    public void setTerrainCost(int cost) {
        terrainCost = cost;
    }

    public int getTerrainCost() { return terrainCost; }

    public void setTreeNode(TreeNode<Coordinate> node) {
        treeNode = node;
    }

    public TreeNode<Coordinate> getTreeNode() {
        return treeNode;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }


    //
    // Override equals(), hashCode() and toString()
    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Coordinate coord = (Coordinate) o;
        return r == coord.getRow() && c == coord.getColumn();
    } // end of equals()


    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    } // end of hashCode()


    @Override
    public String toString() {
        return "(" + r + "," + c + "), " + isImpassable + ", " + terrainCost;
    } // end of toString()

    // Allow Coordinate objects to be compared by their terrain cost in a priority queue
    @Override
    public int compareTo(Coordinate other) {
        if (this.distance == other.distance) {
            return 0;
        } else if (this.distance > other.distance){
            return 1;
        } else {
            return -1;
        }
    }

} // end of class Coordinate
