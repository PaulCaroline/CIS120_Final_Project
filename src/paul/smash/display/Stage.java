package paul.smash.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import paul.smash.framework.*;
import paul.smash.objects.Platform;

public class Stage {

	private StageType type;
	protected LinkedList<Platform> platforms;
	private String FILENAME;
	private String CUT_FILENAME;
	private boolean stageShowing;
	protected static BufferedImage img;
	protected static BufferedImage cut_img;

	public Stage(StageType type) {
		this.type = type;
		this.platforms = new LinkedList<Platform>();
		switch (type) {
		case BATTLEFIELD:
			this.FILENAME = "res/battlefield.png";
			this.CUT_FILENAME = "res/battlefield_cut2.png";
			platforms.add(new Platform(340, 78, 120, 5));
			platforms.add(new Platform(210, 168, 120, 5));
			platforms.add(new Platform(470, 168, 120, 5));
			platforms.add(new Platform(150, 255, 500, 30));
			break;

		case POKEMON_STADIUM:
			this.FILENAME = "res/pokemon_stadium.png";
			this.CUT_FILENAME = "res/pokemon_stadium_cut2.png";
			platforms.add(new Platform(185, 287, 122, 5));
			platforms.add(new Platform(496, 287, 122, 5));
			platforms.add(new Platform(65, 407, 665, 60));
			break;

		case GREEN_GREENS:
			this.FILENAME = "res/green_greens.png";
			this.CUT_FILENAME = "res/green_greens_cut2.png";
			platforms.add(new Platform(60, 214, 225, 10));
			platforms.add(new Platform(515, 214, 225, 10));
			platforms.add(new Platform(85, 435, 630, 60));
			break;

		case FOUNTAIN_OF_DREAMS:
			this.FILENAME = "res/fountain_of_dreams.png";
			this.CUT_FILENAME = "res/fountain_of_dreams_cut2.png";
			platforms.add(new Platform(140, 237, 130, 5));
			platforms.add(new Platform(530, 237, 130, 5));
			platforms.add(new Platform(85, 375, 630, 60));
			platforms.add(new Platform(335, 102, 130, 5));
			break;

		case FINAL_DESTINATION:
			this.FILENAME = "res/final_destination.png";
			this.CUT_FILENAME = "res/final_destination_cut2.png";
			platforms.add(new Platform(110, 300, 565, 5));
			break;

		case KALOS_POKEMON_LEAGUE:
			this.FILENAME = "res/kalos_pokemon_league.png";
			this.CUT_FILENAME = "res/kalos_pokemon_league_cut2.png";
			platforms.add(new Platform(60, 245, 130, 5));
			platforms.add(new Platform(615, 245, 130, 5));
			platforms.add(new Platform(125, 370, 565, 5));
			break;

		}

		try {
			if (img == null) {
				img = ImageIO.read(new File(FILENAME));
			}
			if (cut_img == null) {
				cut_img = ImageIO.read(new File(CUT_FILENAME));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	public void show(Graphics g) {
		if (!stageShowing) {
			g.drawImage(img, 0, 0, 800, 550, null);
			stageShowing = true;
		} else {
			g.drawImage(cut_img, 0, 0, 800, 550, null);

		}
	}

	public LinkedList<Platform> getPlatforms() {
		return platforms;
	}
	public void showAgain() {
		stageShowing = false;
	}
}
