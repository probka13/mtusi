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

    /** Compare coordinates with another point. **/
    public boolean equals(Object obj)
    {
	if (obj == null) {
	    return false;
	}

	if (obj instanceof Location)
        {
            // Cast other object to Point3d type, then compare.
            Location other = (Location) obj;

            if (xCoord == other.xCoord &&
                yCoord == other.yCoord)
            {
                    return true;
            }
        }

        return false;
    }

    /** Returns hash code for location. **/
    public int hashCode()
    {
        int result = 17;
        result = result * 37 + xCoord;
        result = result * 37 + yCoord;
        return result;
    }
}