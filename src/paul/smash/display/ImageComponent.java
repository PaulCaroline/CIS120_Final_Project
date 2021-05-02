package paul.smash.display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageComponent extends JPanel {
	private BufferedImage img;
	private int x;
	private int y;
	private int width;
	private int height;
	private String filename;

	public ImageComponent(int x, int y, int width, int height, String filename) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.filename = filename;

		try {
			if (img == null) {
				img = ImageIO.read(new File(filename));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	public void setImage(String name) {
		try {
			img = ImageIO.read(new File(name));
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ImageComponent test = new ImageComponent(0, 0, 700, 500, "res/start_screen.png");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(test);
		frame.setMaximumSize(new Dimension(800, 600));
		frame.setMinimumSize(new Dimension(800, 600));
		frame.pack();
		frame.setVisible(true);

	}
}

