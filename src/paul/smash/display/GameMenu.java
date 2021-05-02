package paul.smash.display;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import paul.smash.framework.PlayerType;
import paul.smash.framework.StageType;

public class GameMenu {
	private StageType stageType;
	private PlayerType characterOne;
	private PlayerType characterTwo;
	private boolean pressedStart;

	public GameMenu(int w, int h, String title) {
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

		Menu menu = new Menu();

		JMenuBar bar = new JMenuBar();
		JMenu config = new JMenu("Game Setup");

		// Stage Selection Menu
		JMenu chooseStage = new JMenu("Choose a Stage");
		JRadioButtonMenuItem battlefield = new JRadioButtonMenuItem("Battlefield");
		battlefield.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stageType = StageType.BATTLEFIELD;
			}
		});
		chooseStage.add(battlefield);
		JRadioButtonMenuItem finalDestination = new JRadioButtonMenuItem("Final Destination");
		finalDestination.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stageType = StageType.FINAL_DESTINATION;
			}
		});
		chooseStage.add(finalDestination);
		JRadioButtonMenuItem fountainOfDreams = new JRadioButtonMenuItem("Fountain of Dreams");
		fountainOfDreams.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stageType = StageType.FOUNTAIN_OF_DREAMS;
			}
		});
		chooseStage.add(fountainOfDreams);
		JRadioButtonMenuItem greenGreens = new JRadioButtonMenuItem("Green Greens");
		greenGreens.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stageType = StageType.GREEN_GREENS;
			}
		});
		chooseStage.add(greenGreens);
		JRadioButtonMenuItem kalosPokemonLeague = new JRadioButtonMenuItem("Kalos Pokemon League");
		kalosPokemonLeague.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stageType = StageType.KALOS_POKEMON_LEAGUE;
			}
		});
		chooseStage.add(kalosPokemonLeague);
		JRadioButtonMenuItem pokemonStadium = new JRadioButtonMenuItem("Pokemon Stadium");
		pokemonStadium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stageType = StageType.POKEMON_STADIUM;
			}
		});
		chooseStage.add(pokemonStadium);

		// Player One Selection Menu
		JMenu choosePlayerOne = new JMenu("Choose Player One's Character");
		JRadioButtonMenuItem ganondorf1 = new JRadioButtonMenuItem("Ganondorf");
		ganondorf1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterOne = PlayerType.GANONDORF;
			}
		});
		choosePlayerOne.add(ganondorf1);
		JRadioButtonMenuItem kirby1 = new JRadioButtonMenuItem("Kirby");
		kirby1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterOne = PlayerType.KIRBY;
			}
		});
		choosePlayerOne.add(kirby1);
		JRadioButtonMenuItem mario1 = new JRadioButtonMenuItem("Mario");
		mario1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterOne = PlayerType.MARIO;
			}
		});
		choosePlayerOne.add(mario1);
		JRadioButtonMenuItem pikachu1 = new JRadioButtonMenuItem("Pikachu");
		pikachu1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterOne = PlayerType.PIKACHU;
			}
		});
		choosePlayerOne.add(pikachu1);

		// Player Two Selection Menu
		JMenu choosePlayerTwo = new JMenu("Choose Player Two's Character");
		JRadioButtonMenuItem ganondorf2 = new JRadioButtonMenuItem("Ganondorf");
		ganondorf2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterTwo = PlayerType.GANONDORF;
			}
		});
		choosePlayerTwo.add(ganondorf2);
		JRadioButtonMenuItem kirby2 = new JRadioButtonMenuItem("Kirby");
		kirby2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterTwo = PlayerType.KIRBY;
			}
		});
		choosePlayerTwo.add(kirby2);
		JRadioButtonMenuItem mario2 = new JRadioButtonMenuItem("Mario");
		mario2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterTwo = PlayerType.MARIO;
			}
		});
		choosePlayerTwo.add(mario2);
		JRadioButtonMenuItem pikachu2 = new JRadioButtonMenuItem("Pikachu");
		pikachu2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				characterTwo = PlayerType.PIKACHU;
			}
		});
		choosePlayerTwo.add(pikachu2);

		JMenuItem begin = new JMenuItem("Start Match");
		begin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (stageType != null && characterOne != null && characterTwo != null) {
					pressedStart = true;
				} else {
					return;
				}
			}
		});

		JMenuItem instructions = new JMenuItem("How to Play");
		instructions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				menu.toggleInstructions();
				frame.repaint();
			}
		});

		bar.add(config);
		config.add(chooseStage);
		config.add(choosePlayerOne);
		config.add(choosePlayerTwo);
		config.add(instructions);
		config.add(begin);

		frame.setJMenuBar(bar);
		frame.setLayout(new FlowLayout());

		menu.setPreferredSize(new Dimension(800, 600));
		frame.setLocationRelativeTo(null);
		frame.add(menu);

		frame.setFocusable(true);
		frame.requestFocus();
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	public StageType getStageType() {
		return stageType;
	}

	public PlayerType getCharacterOne() {
		return characterOne;
	}

	public PlayerType getCharacterTwo() {
		return characterTwo;
	}
	public boolean pressedStart() {
		return pressedStart;
	}

	

}
