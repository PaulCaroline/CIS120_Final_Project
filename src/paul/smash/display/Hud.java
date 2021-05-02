package paul.smash.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import paul.smash.framework.GameObject;
import paul.smash.framework.ObjectType;
import paul.smash.framework.PlayerType;
import paul.smash.framework.Spritesheet;
import paul.smash.objects.Player;

public class Hud extends GameObject {
	private boolean isPlayerTwo;
	private PlayerType playerType;
	private BufferedImage stockSheetImage;
	private Spritesheet stockSheet;
	private BufferedImage stockIcon;
	private BufferedImage hudImage;
	private Player player;
	private boolean hudShowing;
	private int stockOneX = x + getWidth() / 2 - 35;
	private int stockOneY = y + getHeight() / 2 + 35;

	public Hud(int x, int y, int width, int height, Player player) {
		super(x, y, width, height);
		if (player.getType() == ObjectType.PLAYER_TWO) {
			isPlayerTwo = true;
		} else {
			isPlayerTwo = false;
		}
		this.player = player;
		playerType = player.getCharacter();
		try {
			stockSheetImage = ImageIO.read(new File("res/stock_sheet.png"));
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
		stockSheet = new Spritesheet(stockSheetImage);

		switch (player.getCharacter()) {
		case GANONDORF:
			hudImage = stockSheet.getImage(3, 2, 300, 300);
			stockIcon = stockSheet.getImage(4, 1, 300, 300);
			break;
		case KIRBY:
			hudImage = stockSheet.getImage(1, 2, 300, 300);
			stockIcon = stockSheet.getImage(2, 1, 300, 300);
			break;
		case MARIO:
			hudImage = stockSheet.getImage(4, 2, 300, 300);
			stockIcon = stockSheet.getImage(3, 1, 300, 300);
			break;
		case PIKACHU:
			hudImage = stockSheet.getImage(2, 2, 300, 300);
			stockIcon = stockSheet.getImage(1, 1, 300, 300);
			break;
		}

	}

	@Override
	public void tick() {
	
	}

	@Override
	public void draw(Graphics g) {
	return;  //May need to change this later
	}

	public void show(Graphics g) {
//		g.setColor(Color.MAGENTA);
//		g.drawRect(x, y, getWidth(), getHeight());
//		g.setColor(Color.GREEN);
//		g.drawRect(x, y + 48, getWidth(), getHeight() / 2 - 20);

		if (!hudShowing || player.stockLost()) {
			g.drawImage(hudImage, x, y, getWidth(), getHeight(), null);
			player.findRemainingStocks();
			switch (player.getNumStocks()) {
			case 3:
				g.drawImage(stockIcon, stockOneX, stockOneY, 32, 32, null);
				g.drawImage(stockIcon, stockOneX + 37, stockOneY, 32, 32, null);
				g.drawImage(stockIcon, stockOneX + 74, stockOneY, 32, 32, null);
				break;
			case 2:
				g.drawImage(stockIcon, stockOneX, stockOneY, 32, 32, null);
				g.drawImage(stockIcon, stockOneX + 37, stockOneY, 32, 32, null);
				break;
			case 1:
				g.drawImage(stockIcon, stockOneX, stockOneY, 32, 32, null);
				break;
			default:
				break;
			}
			hudShowing = true;
		} else {
			System.out.println("");
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		if (isPlayerTwo) {
			g.drawString("PLAYER 2", x + getWidth() / 2, y + getHeight() / 2 + 23);
		} else {
			g.drawString("PLAYER 1", x + getWidth() / 2, y + getHeight() / 2 + 23);

		}

		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
		g.drawString("" + player.getDamage() + "%", x + 80, y + 75);
		g.setColor(Color.WHITE);
		g.drawString("" + player.getDamage() + "%", x + 82, y + 77);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y + 48, getWidth(), getHeight() / 2 - 20);
	}

	public void showAgain() {
		hudShowing = false;
	}
}
