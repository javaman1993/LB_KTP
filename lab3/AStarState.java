import java.util.HashMap;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map. This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints." In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState {
    /**
     * контейнер для хранения карты с навигацией
     **/
    private Map2D map;

    /** Хэш-карта для "открытых вершин" **/
    private HashMap<Location, Waypoint> openWaypoints;

    /** Хэш-карта для "закрытых вершин" **/
    private HashMap<Location, Waypoint> closedWaypoints;

    /**
     * создание нового объекта для поиска пути
     **/
    public AStarState(Map2D map) {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
        this.openWaypoints = new HashMap<Location, Waypoint>();
        this.closedWaypoints = new HashMap<Location, Waypoint>();
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap() {
        return map;
    }

    /**
     * данный метод првоеряет все открытые направления и возвращает путь с
     * наименьшим весом. В случае, если метод не найдет такового пути, то вернется
     * null
     **/
    public Waypoint getMinOpenWaypoint() {
        if (openWaypoints.isEmpty()) {
            return null;
        } else {
            Waypoint minCostWaypoint = null;
            float minCost = Float.MAX_VALUE;
            for (Waypoint wp : openWaypoints.values()) {
                float totalCost = wp.getTotalCost();
                if (totalCost < minCost) {
                    minCostWaypoint = wp;
                    minCost = totalCost;
                }
            }
            return minCostWaypoint;
        }
    }

    /**
     * Метод для добавления направлений в коллекцию для сохранения (возможна
     * ситуация обновления состояния).
     **/
    public boolean addOpenWaypoint(Waypoint newWP) {
        Location loc = newWP.getLocation();
        if (!openWaypoints.containsKey(loc)) {
            openWaypoints.put(loc, newWP);
            return true;
        } else {
            Waypoint oldWP = openWaypoints.get(loc);
            if (newWP.getPreviousCost() < oldWP.getPreviousCost()) {
                openWaypoints.put(loc, oldWP);
                return true;
            }
            return false;
        }
    }

    /** Возвращает количество точке в наборе открытых вершин **/
    public int numOpenWaypoints() {
        return openWaypoints.size();
    }

    /**
     * метод перемещает область в список открытык точек to the closed list.
     **/
    public void closeWaypoint(Location loc) {
        Waypoint wp = openWaypoints.remove(loc);
        closedWaypoints.put(loc, wp);
    }

    /**
     * возвращает true, если набор закрытых точек содержит указанную область.
     **/
    public boolean isLocationClosed(Location loc) {
        return (closedWaypoints.keySet().contains(loc));
    }
}
