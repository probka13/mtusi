/**
 * 3D point class
 **/
public class Point3d
{
    /** X coordinate of the point **/
    private double xCoord;

    /** Y coordinate of the point **/
    private double yCoord;

    /** Z coordinate of the point **/
    private double zCoord;

    /** constructor to initialize point to (x, y, z) value **/
    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    /** No-argument constructor: defaults to a point at the origin **/
    public Point3d() {
        // call three-argument constructor and specify the origin
        this(0, 0, 0);
    }

    /** return the X coordinate of the point **/
    public double getX()
    {
        return xCoord;
    }

    /** return the Y coordinate of the point **/
    public double getY()
    {
        return yCoord;
    }

    /** return the Z coordinate of the point **/
    public double getZ()
    {
        return zCoord;
    }

    /** set the X coordinate of the point **/
    public void setX(double val)
    {
        xCoord = val;
    }

    /** set the Y coordinate of the point **/
    public void setY(double val)
    {
        yCoord = val;
    }

    /** set the Z coordinate of the point **/
    public void setZ(double val)
    {
        zCoord = val;
    }

    /** compare if the other point has the same coordinates **/
    public boolean equals(Point3d other)
    {
        return (xCoord == other.getX() && yCoord == other.getY() && zCoord == other.getZ());
    }

    /** calculate distance to another point **/
    public double distanceTo(Point3d other)
    {
        double result = Math.sqrt(Math.pow(xCoord - other.getX(), 2) + Math.pow(yCoord - other.getY(), 2) + Math.pow(zCoord - other.getZ(), 2));
        return Math.round(result * 100) / 100;
    }
}