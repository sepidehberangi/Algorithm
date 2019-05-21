package pathFinder;

import map.Coordinate;
import map.PathMap;
import pathFinder.TreeNode;

import java.util.*;

public class DijkstraPathFinder implements PathFinder
{
    private PathMap map;
    private static int coordinatesExplored;

    public DijkstraPathFinder(PathMap pathMap) {
        map = pathMap;
        coordinatesExplored = 0;
    } // end of DijkstraPathFinder()



    @Override
    public List<Coordinate> findPath() {
        PriorityQueue<Coordinate> priorityQueue = new PriorityQueue<Coordinate>();

        List<Coordinate> originCells = map.originCells;
        List<Coordinate> destCells = map.destCells;
        List<Coordinate> visitedCells = new ArrayList<Coordinate>();

        Coordinate origin = originCells.get(0);
        Coordinate dest = destCells.get(0);

        List<Coordinate> path = new ArrayList<Coordinate>();
        // Create a tree starting at the origin as the root node
        TreeNode<Coordinate> pathTree = new TreeNode<Coordinate>(origin);
        // Set the origin coordinate's distance to 0
        origin.setDistance(0);
        // Set the origin's tree node to the current one
        origin.setTreeNode(pathTree);
        // Store the resulting destination's tree so we can traverse it later
        TreeNode<Coordinate> destTree = null;

        // Add the origin cell to the queue
        priorityQueue.add(origin);


        // Explore all possible cells queued
        while (priorityQueue.size() != 0) {
            Coordinate location = priorityQueue.remove();
            Iterable<Coordinate> adjacent = getNeighbours(location);
            Iterator<Coordinate> iterator = adjacent.iterator();
            // Get the current coordinate's tree node
            TreeNode<Coordinate> currentTreeNode = location.getTreeNode();

            // Stop once we have reached the destination
            if (location.getRow() == dest.getRow() && location.getColumn() == dest.getColumn()) {
                destTree = location.getTreeNode();
                break;
            }

            // Visit the next item in the queue
            visitedCells.add(location);
            coordinatesExplored++;

            // If there are unexplored neighbours add them to the queue
            while (iterator.hasNext()) {
                Coordinate nextCell = iterator.next();
                if (!visitedCells.contains(nextCell) && !priorityQueue.contains(nextCell)) {
                    // Add the next coordinate as a child of the current tree node
                    TreeNode<Coordinate> newTree = new TreeNode<Coordinate>(nextCell);
                    currentTreeNode.addChild(newTree);
                    // Set the next coordinate's distance to the last coordinate's distance plus terrain cost
                    nextCell.setDistance(location.getDistance() + nextCell.getTerrainCost());
                    // Set the next coordinate's tree node to the current one
                    nextCell.setTreeNode(newTree);
                    priorityQueue.add(nextCell);
                }
            }
        }

        // Backtrack from the destination tree node to the origin tree node to find the path
        TreeNode<Coordinate> currentTreeNode = destTree;
        while (currentTreeNode != null) {
            path.add(currentTreeNode.getCoordinate());
            currentTreeNode = currentTreeNode.getParent();
        }

        Collections.reverse(path);
        return path;
    } // end of findPath()

    public List<Coordinate> getNeighbours(Coordinate origin) {
        List<Coordinate> neighbours = new ArrayList<Coordinate>();
        int row = origin.getRow();
        int col = origin.getColumn();

        // Check if we can go up
        if (row != map.sizeR - 1 && map.isPassable(row + 1, col)) {
            neighbours.add(new Coordinate(row + 1, col, false));
        }

        // Check if we can go down
        if (row != 0 && map.isPassable(row - 1, col)) {
            neighbours.add(new Coordinate(row - 1, col, false));
        }

        // Check if we can go left
        if (col != 0 && map.isPassable(row, col - 1)) {
            neighbours.add(new Coordinate(row, col - 1, false));
        }

        // Check if we can go right
        if (col != map.sizeC - 1 && map.isPassable(row, col + 1)) {
            neighbours.add(new Coordinate(row, col + 1, false));
        }

        return neighbours;
    }


    @Override
    public int coordinatesExplored() {
        return coordinatesExplored;
    } // end of cellsExplored()



} // end of class DijsktraPathFinder
