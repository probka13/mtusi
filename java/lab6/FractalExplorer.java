import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import javax.swing.*;

public class FractalExplorer
{
    private int displaySize;
    private JImageDisplay image;
    private FractalGenerator generator;
    private Rectangle2D.Double range;
    private JComboBox<String> fractalChooser;
    private JButton resetButton;
    private JButton saveButton;

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
	        String cmd = e.getActionCommand();

	        if (e.getSource() == fractalChooser)
	        {
		        String selectedItem = fractalChooser.getSelectedItem().toString();

		        if (selectedItem.equals(Mandelbrot.getString()))
		        {
			        generator = new Mandelbrot();
		        }
                else if (selectedItem.equals(Tricorn.getString()))
                {
			        generator = new Tricorn();
		        }
                else if (selectedItem.equals(BurningShip.getString()))
                {
			        generator = new BurningShip();
		        }

                range = new Rectangle2D.Double();
                generator.getInitialRange(range);
                drawFractal();
	        }
	        else if (cmd.equals("reset"))
	        {
                range = new Rectangle2D.Double();
	            generator.getInitialRange(range);
	            drawFractal();
	        }
	        else if (cmd.equals("save"))
	        {
	            JFileChooser chooser = new JFileChooser();
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
		        chooser.setFileFilter(filter);
		        chooser.setAcceptAllFileFilterUsed(false);

		        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
		        {
		            try
		            {
			            File f = chooser.getSelectedFile();
			            String filePath = f.getPath();
			            if (!filePath.toLowerCase().endsWith(".png"))
			            {
			                f = new File(filePath + ".png");
			            }

			            ImageIO.write(image.getImage(), "png", f);
		            }
		            catch (IOException exc)
		            {
			            JOptionPane.showMessageDialog(null, "Error: Couldn't save image ( "
						      + exc.getMessage() + " )");
		            }
	            }
	        }
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

    private void enableUI(boolean value)
    {
	    fractalChooser.setEnabled(value);
	    saveButton.setEnabled(value);
	    resetButton.setEnabled(value);
    }

    private class FractalWorker extends SwingWorker<Object, Object>
    {
        private int y;
        private int[] RGBValues;

        public FractalWorker(int coord) {
            y = coord;
        }

        public Object doInBackground()
        {
            RGBValues = new int[displaySize];
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height,
							       displaySize, y);

	        for (int x = 0; x < displaySize; x++)
	        {
        		double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width,
							       displaySize, x);
		        int numIterations;
		        int rgbColor = 0;
		        float hue;

		        numIterations = generator.numIterations(xCoord, yCoord);
		        if (numIterations > -1)
		        {
		            hue = 0.7f + numIterations / 200f;
		            rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
		        }

		        RGBValues[x] = rgbColor;
	        }

	        return null;
        }

        public void done()
        {
            for (int x = 0; x < displaySize; x++)
            {
                image.drawPixel(x, y, RGBValues[x]);
	        }

            image.repaint(0, 0, y, displaySize, 1);
	        enableUI(true);
        }
    }

    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Fractal Explorer");
        frame.getContentPane().setLayout( new BorderLayout());

        ActionHandler handler = new ActionHandler();

        JPanel fractalPanel = new JPanel();
	    JLabel label = new JLabel("Fractal: ");
	    fractalPanel.add(label);

	    fractalChooser = new JComboBox<String>();
        fractalChooser.addItem(Mandelbrot.getString());
        fractalChooser.addItem(Tricorn.getString());
        fractalChooser.addItem(BurningShip.getString());
        fractalChooser.addActionListener(handler);

	    fractalPanel.add(fractalChooser);
	    frame.getContentPane().add(fractalPanel, BorderLayout.NORTH);

        image = new JImageDisplay(displaySize, displaySize);
        frame.getContentPane().add(image, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();

        resetButton = new JButton("Reset");
        resetButton.setActionCommand("reset");
	    resetButton.addActionListener(handler);
        buttonsPanel.add(resetButton);

	    saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
	    saveButton.addActionListener(handler);
	    buttonsPanel.add(saveButton);

        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        frame.getContentPane().addMouseListener(new MouseHandler());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal()
    {
        enableUI(false);

        for (int i = 0; i < displaySize; i++)
        {
            FractalWorker worker = new FractalWorker(i);
            worker.execute();
	    }
	    image.repaint();
    }

    public static void main(String[] args) {
	    FractalExplorer explorer = new FractalExplorer(800);
	    explorer.createAndShowGUI();
	    explorer.drawFractal();
    }
}