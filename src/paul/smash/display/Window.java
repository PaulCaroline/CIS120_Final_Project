package paul.smash.display;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import paul.smash.framework.KeyboardInput;
import paul.smash.framework.PlayerType;
import paul.smash.framework.StageType;

public class Window {
	private Game game;
	private StageType stageType;
	private PlayerType characterOne;
	//private Player playerOne;
	private PlayerType characterTwo;
	//private Player playerTwo;
	private Helper helper;
	//private ImageComponent screen = new ImageComponent(0, 0, 800, 600, "res/start_screen.png");

	//StageType parameter used to be in the constructor; I recently removed it.
	public Window(int w, int h, String title, StageType stagetype, PlayerType characterOne, PlayerType characterTwo) {
		this.characterOne = characterOne;
		this.characterTwo = characterTwo;
		game = new Game();
		game.setHelper(new Helper());
		JFrame frame = new JFrame(title);
		try {
			Image smashIcon = ImageIO.read(new File("res/smash_icon.png"));
			frame.setIconImage(smashIcon);
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setPreferredSize(new Dimension(w, h));
		frame.setMaximumSize(new Dimension(w, h));
		frame.setMinimumSize(new Dimension(w, h));
		frame.setLocationRelativeTo(null);
		
		this.stageType = stageType;
		game.setStage(new Stage(stagetype));
		game.setCharacterOne(characterOne);
		game.setCharacterTwo(characterTwo);
	    helper = this.game.getHelper();	    

		////////////////////////////////////////////
		// Code to Begin the Game
		////////////////////////////////////////////
		
		
		frame.addKeyListener(new KeyboardInput(helper));
		
		frame.setFocusable(true);
		frame.requestFocus();
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		game.start();

		
	}
}
