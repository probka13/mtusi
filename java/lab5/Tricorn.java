import java.awt.geom.Rectangle2D;


public class Tricorn extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double rectangle)
    {
        rectangle.x = -2;
        rectangle.y = -2;
        rectangle.width = 4;
        rectangle.height = 4;
    }

    public int numIterations(double x, double y)
    {
        int count = 0;

        double z_2 = 0;
        double real = 0;
        double image = 0;

        while (count < MAX_ITERATIONS && z_2 < 4) {
            count++;

            double nextReal = real * real - image * image + x;
            double nextImage = -2 * real * image + y;

            real = nextReal;
            image = nextImage;

            z_2 = real * real + image * image;
	    }

	    return count < MAX_ITERATIONS ? count : -1;
    }

    public static String getString()
    {
        return "Tricorn";
    }
}