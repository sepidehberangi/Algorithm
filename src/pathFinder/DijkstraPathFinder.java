package pathFinder;

import map.Coordinate;
import map.PathMap;

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
        Queue<Coordinate> queue = new LinkedList<Coordinate>();

        List<Coordinate> originCells = map.originCells;
        List<Coordinate> destCells = map.destCells;
        List<Coordinate> visitedCells = new ArrayList<Coordinate>();

        Coordinate origin = originCells.get(0);
        Coordinate dest = destCells.get(0);

        List<Coordinate> path = new ArrayList<Coordinate>();

        // Add the origin cell to the queue
        queue.add(origin);

        // Explore all possible cells queued
        while (queue.size() != 0) {
          Coordinate location = queue.remove();
          Iterable<Coordinate> adjacent = getNeighbours(location);
          Iterator<Coordinate> iterator = adjacent.iterator();

          // Stop once we have reached the destination
          if (location.getRow() == dest.getRow() && location.getColumn() == dest.getColumn()) {
              path.add(location);
              break;
          }

          // Visit the next item in the queue
          visitedCells.add(location);
          path.add(location);
          coordinatesExplored++;

          // If there are unexplored neighbours add them to the queue
          while (iterator.hasNext()) {
              Coordinate nextCell = iterator.next();
              if (!visitedCells.contains(nextCell) && !queue.contains(nextCell)) {
                  queue.add(nextCell);
              }
          }
        }

        return path;
    } // end of findPath()

    public List<Coordinate> getNeighbours(Coordinate origin) {
        List<Coordinate> neighbours = new ArrayList<Coordinate>();
        int row = origin.getRow();
        int col = origin.getColumn();

        // Check if we can go up
        if (row != map.sizeR - 1) {
            if (map.isPassable(row + 1, col)) {
                neighbours.add(new Coordinate(row + 1, col, false));
            }
        }

        // Check if we can go down
        if (row != 0) {
            if (map.isPassable(row - 1, col)) {
                neighbours.add(new Coordinate(row - 1, col, false));
            }
        }

        // Check if we can go left
        if (col != 0) {
            if (map.isPassable(row, col - 1)) {
                neighbours.add(new Coordinate(row, col - 1, false));
            }
        }

        // Check if we can go right
        if (col != map.sizeC - 1) {
            if (map.isPassable(row, col + 1)) {
                neighbours.add(new Coordinate(row, col + 1, false));
            }
        }

        return neighbours;
    }


    @Override
    public int coordinatesExplored() {
        return coordinatesExplored;
    } // end of cellsExplored()



} // end of class DijsktraPathFinder
