package paul.smash.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import paul.smash.framework.StageType;

public class Menu extends JPanel {
	private BufferedImage configure;
	private BufferedImage instructions;
	private boolean showInstructions;

	public Menu() {
		try {
			if (configure == null) {
				configure = ImageIO.read(new File("res/start_screen_revised.png"));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
		try {
			if (instructions == null) {
				instructions = ImageIO.read(new File("res/instructions.png"));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (showInstructions) {
			g.drawImage(instructions, 0, 0, 800, 530, null);
		} else {
			g.drawImage(configure, 0, 0, 800, 518, null);
			g.setColor(Color.GRAY);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			g.drawString("Configure your game from the tab above.", 35, 50);
		}

	}

	public void toggleInstructions() {
		showInstructions = !showInstructions;
	}
}
