package paul.smash.display;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import paul.smash.framework.GameObject;
import paul.smash.framework.KeyboardInput;
import paul.smash.framework.ObjectType;
import paul.smash.framework.PlayerType;
import paul.smash.framework.StageType;
import paul.smash.objects.Platform;
import paul.smash.objects.Player;

public class Game extends Canvas implements Runnable {

	private boolean running = false;
	private Stage stage;
	public static int HEIGHT;
	public static int WIDTH;
	private Helper helper;
	private static final int INTERVAL = 35;
	public int count = 0;
	private PlayerType characterOne;
	private PlayerType characterTwo;
	private int playerOneWidth;
	private int playerTwoWidth;
	private int playerOneHeight;
	private int playerTwoHeight;
	private Player playerOne;
	private Player playerTwo;
	private Hud hudOne;
	private Hud hudTwo;
	private boolean gameOver;
	

	private void initialize() {
		// Define dimensions for characters in the game

		switch (characterOne) {
		case KIRBY:
			playerOneWidth = 32;
			playerOneHeight = 32;
			break;
		case PIKACHU:
			playerOneWidth = 32;
			playerOneHeight = 32;
			break;
		case GANONDORF:
			playerOneWidth = 32;
			playerOneHeight = 64;
			break;
		case MARIO:
			playerOneWidth = 32;
			playerOneHeight = 50;
			break;
		}
		switch (characterTwo) {
		case KIRBY:
			playerTwoWidth = 32;
			playerTwoHeight = 32;
			break;
		case PIKACHU:
			playerTwoWidth = 32;
			playerTwoHeight = 32;
			break;
		case GANONDORF:
			playerTwoWidth = 32;
			playerTwoHeight = 64;
			break;
		case MARIO:
			playerTwoWidth = 32;
			playerTwoHeight = 50;
			break;
		}

		WIDTH = getWidth();
		HEIGHT = getHeight();
		// helper = new Helper();
		for (Platform p : stage.platforms) {
			helper.addObject(p);
		}

		playerOne = new Player(340, 0, playerOneWidth, playerOneHeight, helper, ObjectType.PLAYER_ONE, characterOne);
		playerTwo = new Player(420, 0, playerTwoWidth, playerTwoHeight, helper, ObjectType.PLAYER_TWO, characterTwo);
		helper.addObject(playerOne);
		helper.addObject(playerTwo);
		hudOne = new Hud(100, 395, 150, 150, playerOne); // y was 365
		hudTwo = new Hud(528, 395, 150, 150, playerTwo);
		helper.addObject(hudOne);
		helper.addObject(hudTwo);

	}

	public int getPlayerTwoHeight() {
		return playerTwoHeight;
	}

	public void setPlayerTwoHeight(int playerTwoHeight) {
		this.playerTwoHeight = playerTwoHeight;
	}

	public PlayerType getCharacterOne() {
		return characterOne;
	}

	public PlayerType getCharacterTwo() {
		return characterTwo;
	}

	@Override
	public void run() {
		Graphics g = this.getGraphics();
		// this.addKeyListener(new KeyboardInput(helper));
		initialize();
		this.addKeyListener(new KeyboardInput(helper));
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				helper.tick();
				boolean playerOnHud = playerOne.getBounds().intersects(hudOne.getBounds())
						|| playerOne.getBounds().intersects(hudTwo.getBounds())
						|| playerTwo.getBounds().intersects(hudOne.getBounds())
						|| playerTwo.getBounds().intersects(hudTwo.getBounds());
				if (playerOnHud || playerOne.stockLost() || playerTwo.stockLost()) {
					stage.showAgain();
					hudOne.showAgain();
					hudTwo.showAgain();
				}
				draw(g);

				if (playerOne.getNumStocks() == 0 || playerTwo.getNumStocks() == 0) {
					gameOver = true;
				}
			}
		});
		timer.start();
		setFocusable(true); // THIS MAY NEED TO CHANGE LATER
	}

	public void start() {
		this.run();
		running = true;
	}

	public void draw(Graphics g) {
		
		this.createBufferStrategy(4);

		if (gameOver) {
			try {
				BufferedImage gameOver = ImageIO.read(new File("res/game_over.png"));
				g.drawImage(gameOver, WIDTH / 2 - 250, 180, 600, 180, null );
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}
		}

		// Display the stage background image
		else {
			stage.show(g);

			helper.draw(g);
			hudOne.show(g);
			hudTwo.show(g);
		}
	}

	public static void main(String[] args) {
		GameMenu m = new GameMenu(800, 578, "Super Smash Bros CIS 120 Main Menu");
		while (true) {
			if (m.getCharacterOne() != null && m.getCharacterTwo() != null && m.getStageType() != null
					&& m.pressedStart()) {

				new Window(800, 578, "Super Smash Bros CIS 120", m.getStageType(), m.getCharacterOne(),
						m.getCharacterTwo());
				break;

			} else {
				System.out.println("");
			}
		}

		// SwingUtilities.invokeLater(new Game());
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setCharacterOne(PlayerType characterOne) {
		this.characterOne = characterOne;
	}

	public void setCharacterTwo(PlayerType characterTwo) {
		this.characterTwo = characterTwo;
	}

	public Helper getHelper() {
		return this.helper;
	}

	public void setHelper(Helper helper) {
		this.helper = helper;
	}
}
