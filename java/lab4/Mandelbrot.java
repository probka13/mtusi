import java.awt.geom.Rectangle2D;


public class Mandelbrot extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double rectangle)
    {
        rectangle.x = -2;
        rectangle.y = -1.5;
        rectangle.width = 3;
        rectangle.height = 3;
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
            double nextImage = 2 * real * image + y;

            real = nextReal;
            image = nextImage;

            z_2 = real * real + image * image;
	    }

	    return count < MAX_ITERATIONS ? count : -1;

    }
}