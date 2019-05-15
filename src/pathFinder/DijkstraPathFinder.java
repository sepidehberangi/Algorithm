package pathFinder;

import map.Coordinate;
import map.PathMap;

import java.util.*;

public class DijkstraPathFinder implements PathFinder
{
    private PathMap map;
    private static int coordinatesExplored;
    private List<Coordinate> impassableCells;

    public DijkstraPathFinder(PathMap pathMap) {
        map = pathMap;
        coordinatesExplored = 0;
        impassableCells = new ArrayList<Coordinate>();
    } // end of DijkstraPathFinder()



    @Override
    public List<Coordinate> findPath() {
        // You can replace this with your favourite list, but note it must be a
        // list type
        Queue<Coordinate> queue = new LinkedList<Coordinate>();
        List<Coordinate> originCells = map.originCells;
        List<Coordinate> destCells = map.destCells;
        List<Coordinate> visitedCells = new ArrayList<Coordinate>();
        Coordinate origin = originCells.get(0);
        Coordinate dest = destCells.get(0);
        List<Coordinate> path = new ArrayList<Coordinate>();

        queue.add(origin);

        while (queue.size() != 0) {
          Coordinate location = queue.remove();
          int distance = 1;
          Iterable<Coordinate> adjacent = getNeighbours(location);
          Iterator<Coordinate> iterator = adjacent.iterator();

          if (location == dest) {
            break;
          }

          visitedCells.add(location);
          path.add(location);
          coordinatesExplored++;

          while (iterator.hasNext()) {
              distance++;
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
            } else {
                impassableCells.add(new Coordinate(row + 1, col, true));
            }
        }

        // Check if we can go down
        if (row != 0) {
            if (map.isPassable(row - 1, col)) {
                neighbours.add(new Coordinate(row - 1, col, false));
            } else {
                impassableCells.add(new Coordinate(row - 1, col, true));
            }
        }

        // Check if we can go left
        if (col != 0) {
            if (map.isPassable(row, col - 1)) {
                neighbours.add(new Coordinate(row, col - 1, false));
            } else {
                impassableCells.add(new Coordinate(row, col - 1, true));
            }
        }

        // Check if we can go right
        if (col != map.sizeC - 1) {
            if (map.isPassable(row, col + 1)) {
                neighbours.add(new Coordinate(row, col + 1, false));
            } else {
                impassableCells.add(new Coordinate(row, col + 1, true));
            }
        }

        return neighbours;
    }


    @Override
    public int coordinatesExplored() {
        return coordinatesExplored;
    } // end of cellsExplored()



} // end of class DijsktraPathFinder
