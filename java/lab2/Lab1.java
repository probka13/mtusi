/**
 * Lab 1: get three points from user and compute area
 **/
public class Lab1
{
    /** Main method **/
    public static void main(String[] args) {
        Point3d firstPoint = new Point3d(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
        Point3d secondPoint = new Point3d(Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
        Point3d thirdPoint = new Point3d(Double.parseDouble(args[6]), Double.parseDouble(args[7]), Double.parseDouble(args[8]));
        if (firstPoint.equals(secondPoint) || firstPoint.equals(thirdPoint) || secondPoint.equals(thirdPoint))
        {
            System.out.println("Some points are equal, set different");
        }
        else
        {
            double result = computeArea(firstPoint, secondPoint, thirdPoint);
            System.out.println("Area is " + result);
        }
    }

    /** Computes area **/
    public static double computeArea(Point3d first, Point3d second, Point3d third)
    {
        double a = first.distanceTo(second);
        double b = second.distanceTo(third);
        double c = third.distanceTo(first);
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}