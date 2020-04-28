/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    /** Проверяет равенство с другим объектом. **/
    public boolean equals(Object object) {
        if (object instanceof Location) {
            Location l2 = (Location) object;
            return xCoord == l2.xCoord && yCoord == l2.yCoord;
        }
        else
            return false;
    }

    /** Создает хэш-код для объекта Location **/
    public int hashCode() {
        int hash = 11;
        hash = 31 * hash + xCoord;
        hash = 31 * hash + yCoord;
        return hash;
    }
}
