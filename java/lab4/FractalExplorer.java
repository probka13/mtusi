import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;

public class FractalExplorer
{
    private int displaySize;
    private JImageDisplay image;
    private FractalGenerator generator;
    private Rectangle2D.Double range;

    public FractalExplorer(int size)
    {
        displaySize = size;
        generator = new Mandelbrot();
        range = new Rectangle2D.Double();
        generator.getInitialRange(range);
    }

    private class ActionHandler implements ActionListener
    {
	    public void actionPerformed(ActionEvent e)
	    {
	        range = new Rectangle2D.Double();
	        generator.getInitialRange(range);
	        drawFractal();
	    }
    }

    private class MouseHandler extends MouseAdapter
    {
	    public void mouseClicked(MouseEvent e)
	    {
	        double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, e.getX());
	        double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, e.getY());
	        generator.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
	        drawFractal();
	    }
    }

    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Fractal Explorer");
        frame.getContentPane().setLayout( new BorderLayout());

        image = new JImageDisplay(displaySize, displaySize);
        frame.getContentPane().add(image, BorderLayout.CENTER);

        ActionHandler handler = new ActionHandler();
        JButton newButton = new JButton("Reset");
        newButton.setActionCommand("reset");
	    newButton.addActionListener(handler);
        frame.getContentPane().add(newButton, BorderLayout.SOUTH);

        frame.getContentPane().addMouseListener(new MouseHandler());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal()
    {
        for (int i = 0; i < image.getWidth(); i++) {
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, i);
	        for (int j = 0; j < image.getHeight(); j++) {
		        double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, j);
		        int numIterations = generator.numIterations(xCoord, yCoord);

		        if (numIterations > -1) {
		            float hue = 0.7f + (float) numIterations / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    image.drawPixel(i, j, rgbColor);
                }
	        }
	    }
	    image.repaint();
    }

    public static void main(String[] args) {
	    FractalExplorer explorer = new FractalExplorer(800);
	    explorer.createAndShowGUI();
	    explorer.drawFractal();
    }
}