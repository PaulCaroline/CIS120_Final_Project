package paul.smash.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import paul.smash.display.Helper;
import paul.smash.display.Hud;
import paul.smash.framework.Animation;
import paul.smash.framework.GameObject;
import paul.smash.framework.ObjectAction;
import paul.smash.framework.ObjectType;
import paul.smash.framework.PlayerType;
import paul.smash.framework.Spritesheet;

public class Player extends GameObject {
	private int height;
	private int width;
	private double gravity = 0.75;
	public final double MAX_SPEED = 10;
	private Helper helper;
	private BufferedImage idleSprite;
	private PlayerType character;
	private boolean rightFacing;
	private boolean leftFacing;
	private Animation walkLeft;
	private Animation walkRight;
	private Animation attackARight;
	private Animation attackBRight;
	private Animation attackALeft;
	private Animation attackBLeft;
	private BufferedImage spritesheet; // Originally conceived spritesheet now used for walking
	private BufferedImage fightSheet;
	private int walkAnimationSpeed = 3;
	private int fightAnimationSpeed = 2;
	private int walkFrameSize;
	private int fightFrameSizeA;
	private int fightFrameSizeB;
	private int numStocks = 3;
	private int damage;
	private Hitbox hitA;
	private int hitAWidth;
	private int hitAHeight;
	private Hitbox hitB;
	private int hitBWidth;
	private int hitBHeight;
	private boolean stockLost;
	private int lastTickDamage;
	private BufferedImage damagedSprites;
	private BufferedImage damagedSpriteRight;
	private BufferedImage damagedSpriteLeft;
	private boolean hitRightward;
	private int damagedSpriteSize;
	private int init_x;
	private int init_y;
	

	// private PlayerAction action = PlayerAction.IDLE;

	public Player(int x, int y, int width, int height, Helper helper, ObjectType type, PlayerType character) {
		super(x, y, width, height);
		this.x = x;
		this.y = y;
		this.init_x = x;
		this.init_y = y;
		this.height = height;
		this.width = width;
		this.helper = helper;
		this.type = type;
		this.character = character;
		
		try {
			if (damagedSprites == null) {
				damagedSprites = ImageIO.read(new File("res/damage_sheet.png"));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
		
		Spritesheet damageSheet = new Spritesheet(damagedSprites);
		
		// Defines images for animations
		switch (character) {
		case KIRBY:
			try {
				if (idleSprite == null) {
					idleSprite = ImageIO.read(new File("res/kirby_sprite_idle.png"));
					damagedSpriteRight = damageSheet.getImage(2, 1, 290, 290);
					damagedSpriteLeft = damageSheet.getImage(1, 1, 290, 290);
					damagedSpriteSize = 40;
				}
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}	

			// Code for Kirby's walking animation (rK1 --> walk right animation "Kirby"
			// frame One)
			try {
				spritesheet = ImageIO.read(new File("res/kirby_spritesheet.png"));
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}

			Spritesheet kirbySheet = new Spritesheet(spritesheet);

			BufferedImage rK1 = kirbySheet.getImage(1, 1, 300, 315);
			BufferedImage rK2 = kirbySheet.getImage(2, 1, 300, 315);
			BufferedImage rK3 = kirbySheet.getImage(3, 1, 300, 315);
			BufferedImage rK4 = kirbySheet.getImage(4, 1, 300, 315);
			BufferedImage rK5 = kirbySheet.getImage(5, 1, 300, 315);
			BufferedImage rK6 = kirbySheet.getImage(6, 1, 300, 315);

			walkRight = new Animation(walkAnimationSpeed, rK1, rK2, rK3, rK4, rK5, rK6);

			BufferedImage lK1 = kirbySheet.getImage(1, 2, 300, 315);
			BufferedImage lK2 = kirbySheet.getImage(2, 2, 300, 315);
			BufferedImage lK3 = kirbySheet.getImage(3, 2, 300, 315);
			BufferedImage lK4 = kirbySheet.getImage(4, 2, 300, 315);
			BufferedImage lK5 = kirbySheet.getImage(5, 2, 300, 315);
			BufferedImage lK6 = kirbySheet.getImage(6, 2, 300, 315);

			walkLeft = new Animation(walkAnimationSpeed, lK1, lK2, lK3, lK4, lK5, lK6);
			walkFrameSize = 38;

			// Code for Kirby's fighting animation
			hitAWidth = 32;
			hitAHeight = 32;
			hitBWidth = getWidth();
			hitBHeight = getHeight();
			try {
				fightSheet = ImageIO.read(new File("res/kirby_fightSheet.png"));
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}

			Spritesheet kirbyFight = new Spritesheet(fightSheet);

			BufferedImage aRK1 = kirbyFight.getImage(1, 1, 300, 315);
			BufferedImage aRK2 = kirbyFight.getImage(2, 1, 300, 315);
			BufferedImage aRK3 = kirbyFight.getImage(3, 1, 300, 315);
			BufferedImage aRK4 = kirbyFight.getImage(4, 1, 300, 315);
			BufferedImage aRK5 = kirbyFight.getImage(5, 1, 300, 315);
			BufferedImage aRK6 = kirbyFight.getImage(6, 1, 300, 315);

			attackARight = new Animation(fightAnimationSpeed, aRK1, aRK2, aRK3, aRK4, aRK5, aRK6);

			BufferedImage aLK1 = kirbyFight.getImage(6, 2, 300, 315);
			BufferedImage aLK2 = kirbyFight.getImage(5, 2, 300, 315);
			BufferedImage aLK3 = kirbyFight.getImage(4, 2, 300, 315);
			BufferedImage aLK4 = kirbyFight.getImage(3, 2, 300, 315);
			BufferedImage aLK5 = kirbyFight.getImage(2, 2, 300, 315);
			BufferedImage aLK6 = kirbyFight.getImage(1, 2, 300, 315);

			attackALeft = new Animation(fightAnimationSpeed, aLK1, aLK2, aLK3, aLK4, aLK5, aLK6);
			fightFrameSizeA = 75;

			BufferedImage bK1 = kirbyFight.getImage(1, 3, 300, 290);
			BufferedImage bK2 = kirbyFight.getImage(2, 3, 300, 290);
			attackBLeft = new Animation(fightAnimationSpeed, bK1, bK1, bK2, bK2, bK2);
			attackBRight = attackBLeft;
			fightFrameSizeB = 64;

			break;

		case PIKACHU:
			try {
				if (idleSprite == null) {
					idleSprite = ImageIO.read(new File("res/pikachu_sprite_idle.png"));
					damagedSpriteRight = damageSheet.getImage(2, 2, 290, 290);
					damagedSpriteLeft = damageSheet.getImage(1, 2, 290, 290);
					damagedSpriteSize = 50;

				}
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}
		
			// Code for Pikachu's walking animation
			try {
				spritesheet = ImageIO.read(new File("res/pikachu_spritesheet.png"));
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}

			Spritesheet pikachuSheet = new Spritesheet(spritesheet);

			BufferedImage rP1 = pikachuSheet.getImage(1, 1, 300, 315);
			BufferedImage rP2 = pikachuSheet.getImage(2, 1, 300, 315);
			BufferedImage rP3 = pikachuSheet.getImage(3, 1, 300, 315);
			BufferedImage rP4 = pikachuSheet.getImage(4, 1, 300, 315);

			walkRight = new Animation(walkAnimationSpeed, rP1, rP2, rP3, rP4);

			BufferedImage lP1 = pikachuSheet.getImage(1, 2, 300, 315);
			BufferedImage lP2 = pikachuSheet.getImage(2, 2, 300, 315);
			BufferedImage lP3 = pikachuSheet.getImage(3, 2, 300, 315);
			BufferedImage lP4 = pikachuSheet.getImage(4, 2, 300, 315);

			walkLeft = new Animation(walkAnimationSpeed, lP1, lP2, lP3, lP4);
			walkFrameSize = 50;

			// Code for Pikachu's fighting animation
			hitAWidth = 32;
			hitAHeight = 32;
			hitBWidth = getWidth();
			hitBHeight = getHeight();
			try {
				fightSheet = ImageIO.read(new File("res/pikachu_fightSheet.png"));
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}

			Spritesheet pikachuFight = new Spritesheet(fightSheet);

			BufferedImage aRP1 = pikachuFight.getImage(1, 1, 300, 315);
			BufferedImage aRP2 = pikachuFight.getImage(2, 1, 300, 315);
			BufferedImage aRP3 = pikachuFight.getImage(3, 1, 300, 315);
			BufferedImage aRP4 = pikachuFight.getImage(4, 1, 300, 315);

			attackARight = new Animation(fightAnimationSpeed, aRP1, aRP2, aRP3, aRP4);

			BufferedImage aLP1 = pikachuFight.getImage(4, 2, 300, 315);
			BufferedImage aLP2 = pikachuFight.getImage(3, 2, 300, 315);
			BufferedImage aLP3 = pikachuFight.getImage(2, 2, 300, 315);
			BufferedImage aLP4 = pikachuFight.getImage(1, 2, 300, 315);

			attackALeft = new Animation(fightAnimationSpeed, aLP1, aLP2, aLP3, aLP4);
			fightFrameSizeA = 55;

			BufferedImage bP1 = pikachuFight.getImage(1, 3, 300, 290);
			BufferedImage bP2 = pikachuFight.getImage(3, 3, 300, 290);
			BufferedImage bP3 = pikachuFight.getImage(3, 3, 300, 290);

			attackBRight = new Animation(fightAnimationSpeed, bP1, bP2, bP3);
			attackBLeft = attackBRight;

			fightFrameSizeB = 45;

			break;

		case GANONDORF:
			try {
				if (idleSprite == null) {
					idleSprite = ImageIO.read(new File("res/ganondorf_sprite_idle.png"));
					damagedSpriteRight = damageSheet.getImage(6, 1, 290, 290);
					damagedSpriteLeft = damageSheet.getImage(5, 1, 290, 290);
					damagedSpriteSize = 50;

				}
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}
		
			// Code for Ganondorf's walking animation
			try {
				spritesheet = ImageIO.read(new File("res/ganondorf_spritesheet.png"));
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}

			Spritesheet ganondorfSheet = new Spritesheet(spritesheet);

			BufferedImage rG1 = ganondorfSheet.getImage(1, 1, 300, 315);
			BufferedImage rG2 = ganondorfSheet.getImage(2, 1, 300, 315);
			BufferedImage rG3 = ganondorfSheet.getImage(3, 1, 300, 315);
			BufferedImage rG4 = ganondorfSheet.getImage(4, 1, 300, 315);
			BufferedImage rG5 = ganondorfSheet.getImage(5, 1, 300, 315);
			BufferedImage rG6 = ganondorfSheet.getImage(6, 1, 300, 315);

			walkRight = new Animation(walkAnimationSpeed, rG1, rG2, rG3, rG4, rG5, rG6);

			BufferedImage lG1 = ganondorfSheet.getImage(1, 2, 300, 315);
			BufferedImage lG2 = ganondorfSheet.getImage(2, 2, 300, 315);
			BufferedImage lG3 = ganondorfSheet.getImage(3, 2, 300, 315);
			BufferedImage lG4 = ganondorfSheet.getImage(4, 2, 300, 315);
			BufferedImage lG5 = ganondorfSheet.getImage(5, 2, 300, 315);
			BufferedImage lG6 = ganondorfSheet.getImage(6, 2, 300, 315);

			walkLeft = new Animation(walkAnimationSpeed, lG1, lG2, lG3, lG4, lG5, lG6);
			walkFrameSize = 64;

			// Code for Ganondorf's fighting animation
			hitAWidth = getWidth();
			hitAHeight = getHeight();
			hitBWidth = getWidth();
			hitBHeight = getHeight();
			try {
				fightSheet = ImageIO.read(new File("res/ganondorf_fightSheet.png"));
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}

			Spritesheet ganondorfFight = new Spritesheet(fightSheet);

			BufferedImage aRG1 = ganondorfFight.getImage(4, 1, 300, 315);
			BufferedImage aRG2 = ganondorfFight.getImage(3, 1, 300, 315);
			BufferedImage aRG3 = ganondorfFight.getImage(2, 1, 300, 315);
			BufferedImage aRG4 = ganondorfFight.getImage(1, 1, 300, 315);

			attackARight = new Animation(fightAnimationSpeed, aRG1, aRG2, aRG3, aRG4);

			BufferedImage aLG1 = ganondorfFight.getImage(1, 2, 300, 315);
			BufferedImage aLG2 = ganondorfFight.getImage(2, 2, 300, 315);
			BufferedImage aLG3 = ganondorfFight.getImage(3, 2, 300, 315);
			BufferedImage aLG4 = ganondorfFight.getImage(4, 2, 300, 315);

			attackALeft = new Animation(fightAnimationSpeed, aLG1, aLG2, aLG3, aLG4);
			fightFrameSizeA = 115;

			BufferedImage bRG1 = ganondorfFight.getImage(5, 2, 300, 290);
			BufferedImage bRG2 = ganondorfFight.getImage(6, 2, 300, 290);
			BufferedImage bRG3 = ganondorfFight.getImage(1, 3, 300, 290);
			BufferedImage bRG4 = ganondorfFight.getImage(3, 3, 300, 290);

			attackBRight = new Animation(fightAnimationSpeed, bRG1, bRG2, bRG3, bRG3, bRG4);

			BufferedImage bLG1 = ganondorfFight.getImage(6, 1, 300, 290);
			BufferedImage bLG2 = ganondorfFight.getImage(5, 1, 300, 290);
			BufferedImage bLG3 = ganondorfFight.getImage(4, 3, 300, 290);
			BufferedImage bLG4 = ganondorfFight.getImage(6, 3, 300, 290);

			attackBLeft = new Animation(fightAnimationSpeed, bLG1, bLG2, bLG3, bLG3, bLG4);
			fightFrameSizeB = 95;

			break;

		case MARIO:
			try {
				if (idleSprite == null) {
					idleSprite = ImageIO.read(new File("res/mario_sprite_idle.png"));
					damagedSpriteRight = damageSheet.getImage(4, 1, 290, 290);
					damagedSpriteLeft = damageSheet.getImage(3, 1, 290, 290);
					damagedSpriteSize = 50;

				}
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}
			
			// Code for Mario's walking animation
			try {
				spritesheet = ImageIO.read(new File("res/mario_spritesheet.png"));
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}

			Spritesheet marioSheet = new Spritesheet(spritesheet);

			BufferedImage rM1 = marioSheet.getImage(1, 1, 300, 315);
			BufferedImage rM2 = marioSheet.getImage(2, 1, 300, 315);
			BufferedImage rM3 = marioSheet.getImage(3, 1, 300, 315);
			BufferedImage rM4 = marioSheet.getImage(4, 1, 300, 315);
			BufferedImage rM5 = marioSheet.getImage(5, 1, 300, 315);
			BufferedImage rM6 = marioSheet.getImage(6, 1, 300, 315);

			walkRight = new Animation(walkAnimationSpeed, rM1, rM2, rM3, rM4, rM5, rM6);

			BufferedImage lM1 = marioSheet.getImage(1, 2, 300, 315);
			BufferedImage lM2 = marioSheet.getImage(2, 2, 300, 315);
			BufferedImage lM3 = marioSheet.getImage(3, 2, 300, 315);
			BufferedImage lM4 = marioSheet.getImage(4, 2, 300, 315);
			BufferedImage lM5 = marioSheet.getImage(5, 2, 300, 315);
			BufferedImage lM6 = marioSheet.getImage(6, 2, 300, 315);

			walkLeft = new Animation(walkAnimationSpeed, lM1, lM2, lM3, lM4, lM5, lM6);
			walkFrameSize = 60;

			// Code for Mario's fighting animation
			hitAWidth = 32;
			hitAHeight = 32;
			hitBWidth = getWidth();
			hitBHeight = getHeight();
			try {
				fightSheet = ImageIO.read(new File("res/mario_fightSheet.png"));
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}

			Spritesheet marioFight = new Spritesheet(fightSheet);

			BufferedImage aRM1 = marioFight.getImage(1, 3, 300, 290);
			BufferedImage aRM2 = marioFight.getImage(2, 3, 300, 290);
			BufferedImage aRM3 = marioFight.getImage(3, 3, 300, 290);
			BufferedImage aRM4 = marioFight.getImage(4, 3, 300, 290);

			attackARight = new Animation(fightAnimationSpeed, aRM1, aRM2, aRM3, aRM4);

			BufferedImage aLM1 = marioFight.getImage(6, 1, 300, 290);
			BufferedImage aLM2 = marioFight.getImage(6, 2, 300, 290);
			BufferedImage aLM3 = marioFight.getImage(6, 3, 300, 290);
			BufferedImage aLM4 = marioFight.getImage(5, 3, 300, 290);

			attackALeft = new Animation(fightAnimationSpeed, aLM1, aLM2, aLM3, aLM4);

			fightFrameSizeA = 85;

			BufferedImage bRM1 = marioFight.getImage(1, 1, 300, 290);
			BufferedImage bRM2 = marioFight.getImage(2, 1, 300, 290);
			BufferedImage bRM3 = marioFight.getImage(3, 1, 300, 290);
			BufferedImage bRM4 = marioFight.getImage(4, 1, 300, 290);
			BufferedImage bRM5 = marioFight.getImage(5, 1, 300, 290);

			attackBRight = new Animation(fightAnimationSpeed, bRM1, bRM2, bRM3, bRM4);

			BufferedImage bLM1 = marioFight.getImage(1, 2, 300, 290);
			BufferedImage bLM2 = marioFight.getImage(2, 2, 300, 290);
			BufferedImage bLM3 = marioFight.getImage(3, 2, 300, 290);
			BufferedImage bLM4 = marioFight.getImage(4, 2, 300, 290);
			BufferedImage bLM5 = marioFight.getImage(5, 2, 300, 290);

			attackBLeft = new Animation(fightAnimationSpeed, bLM1, bLM2, bLM3, bLM4);

			fightFrameSizeB = 80;

			break;

		}
		hitA = new Hitbox(x, y, hitAWidth, hitAHeight, this, 1);
		hitA.setTriggered(true);
		hitB = new Hitbox(x, y, hitBWidth, hitBHeight, this, 2);
		hitB.setTriggered(true);
		helper.addObject(hitA);
		helper.addObject(hitB);

	}

	@Override
	public void tick() {
		x += getVelX();
		y += getVelY();
		
		//Record the last value for damage to detect an increase and draw a dazed Sprite
				lastTickDamage = damage;

		if (getVelX() == 0 && getVelY() == 0 && getAction() != ObjectAction.ATTACK_A
				&& getAction() != ObjectAction.ATTACK_B) {
			this.setAction(ObjectAction.IDLE);
			hitA.setTriggered(true);
			hitB.setTriggered(true);
		}
		if (getVelX() > 0 && getVelY() == 0 && getAction() != ObjectAction.ATTACK_A) {
			rightFacing = true;
			this.setAction(ObjectAction.WALK);
		}
		if (getVelX() < 0 && getVelY() == 0 && getAction() != ObjectAction.ATTACK_A) {
			rightFacing = false;
			this.setAction(ObjectAction.WALK);
		}

		if (falling() || jumping()) {
			setVelY(getVelY() + gravity);
		}
		if (getVelY() > MAX_SPEED) {
			setVelY(MAX_SPEED);
		}
		impact();
		hit();		
		
	}

	public void impact() {
		LinkedList<GameObject> objects = helper.objects;
		for (int i = 0; i < objects.size(); i++) {

			GameObject tempObject = objects.get(i);
			if (tempObject.getType() == ObjectType.PLATFORM) {
				if (getBottomBound().intersects(tempObject.getBounds())) {
					this.setY(tempObject.getY() - this.height);
					setVelY(0);
					setFalling(false);
					setJumping(false);
				} else {
					setFalling(true);
				}

				if (getTopBound().intersects(tempObject.getBounds())) {
					this.setY(tempObject.getY() + tempObject.getHeight());
					setVelY(0);
				}
			}
			if (tempObject.getBounds().intersects(getBounds())) {
				if (tempObject.getType() == ObjectType.HITBOX) {
					Hitbox hitbox = (Hitbox) tempObject;
					if (type != hitbox.getPlayer().getType()) {
						damage += hitbox.getDamageGiven();
						hitbox.setTriggered(true);
						if (hitbox.getPlayer().rightFacing()) {
							hitRightward = true;
						}
						if (!hitbox.getPlayer().rightFacing()) {
							hitRightward = false;
						}
						if (damage > 100 && numStocks > 0) {
							numStocks--;
							stockLost = true;
							damage = 0;
							x = init_x;
							y = init_y - 250;
						}
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		switch (action) {

		case ATTACK_A:
			switch (character) {
			case KIRBY:
				if (rightFacing) {
					attackARight.runAnimation();
					attackARight.drawAnimation(g, x - 15, y - 30, fightFrameSizeA, fightFrameSizeA);
				} else {
					attackALeft.runAnimation();
					attackALeft.drawAnimation(g, x - 15, y - 30, fightFrameSizeA, fightFrameSizeA);
				}
				break;

			case GANONDORF:
				if (rightFacing) {
					attackARight.runAnimation();
					attackARight.drawAnimation(g, x - 15, y - 32, fightFrameSizeA, fightFrameSizeA);
				} else {
					attackALeft.runAnimation();
					attackALeft.drawAnimation(g, x - 50, y - 32, fightFrameSizeA, fightFrameSizeA);
				}
				break;

			case MARIO:
				if (rightFacing) {
					attackARight.runAnimation();
					attackARight.drawAnimation(g, x - 10, y - 25, fightFrameSizeA, fightFrameSizeA);
				} else {
					attackALeft.runAnimation();
					attackALeft.drawAnimation(g, x - 20, y - 27, fightFrameSizeA, fightFrameSizeA);
				}
				break;

			case PIKACHU:
				if (rightFacing) {
					attackARight.runAnimation();
					attackARight.drawAnimation(g, x, y - 5, fightFrameSizeA, fightFrameSizeA);
				} else {
					attackALeft.runAnimation();
					attackALeft.drawAnimation(g, x, y - 2, fightFrameSizeA, fightFrameSizeA);
				}
				break;

			default:
				if (rightFacing) {
					attackARight.runAnimation();
					attackARight.drawAnimation(g, x, y, fightFrameSizeA, fightFrameSizeA);
				} else {
					attackALeft.runAnimation();
					attackALeft.drawAnimation(g, x, y, fightFrameSizeA, fightFrameSizeA);
				}
				break;

			}
			break;

		case ATTACK_B:
			switch (character) {
			case KIRBY:
				if (falling()) {
					attackBLeft.runAnimation();
					attackBLeft.drawAnimation(g, x - 15, y - 30, fightFrameSizeB, fightFrameSizeB);
				}
				break;

			case GANONDORF:
				if (rightFacing) {
					attackBRight.runAnimation();
					attackBRight.drawAnimation(g, x - 5, y - 30, fightFrameSizeB, fightFrameSizeB);
				} else {
					attackBLeft.runAnimation();
					attackBLeft.drawAnimation(g, x - 35, y - 30, fightFrameSizeB, fightFrameSizeB);
				}
				break;

			case MARIO:
				if (rightFacing) {
					attackBRight.runAnimation();
					attackBRight.drawAnimation(g, x - 5, y - 30, fightFrameSizeB, fightFrameSizeB);
				} else {
					attackBLeft.runAnimation();
					attackBLeft.drawAnimation(g, x - 35, y - 30, fightFrameSizeB, fightFrameSizeB);
				}
				break;

			case PIKACHU:
				if (rightFacing) {
					attackBRight.runAnimation();
					attackBRight.drawAnimation(g, x, y - 12, fightFrameSizeB, fightFrameSizeB);
				} else {
					attackBLeft.runAnimation();
					attackBLeft.drawAnimation(g, x, y - 12, fightFrameSizeB, fightFrameSizeB);
				}
				break;

			}
			break;

		case WALK:
			if (rightFacing) {
				walkRight.runAnimation();
				walkRight.drawAnimation(g, x, y, walkFrameSize, walkFrameSize);
			} else {
				walkLeft.runAnimation();
				walkLeft.drawAnimation(g, x, y, walkFrameSize, walkFrameSize);
			}
			break;

		case IDLE:
			if (lastTickDamage != damage && hitRightward) {
				if (character == PlayerType.PIKACHU) {
					g.drawImage(damagedSpriteRight, x, y - 10, damagedSpriteSize, damagedSpriteSize, null);
				} else {
					g.drawImage(damagedSpriteRight, x, y, damagedSpriteSize, damagedSpriteSize, null);
				}
			} else if (lastTickDamage != damage && !hitRightward) {
				if (character == PlayerType.PIKACHU) {
					g.drawImage(damagedSpriteLeft, x, y - 10, damagedSpriteSize, damagedSpriteSize, null);
				} else {
					g.drawImage(damagedSpriteLeft, x - (Math.abs(height - width)), y, damagedSpriteSize,
							damagedSpriteSize, null);
				}
			} else {
				g.drawImage(idleSprite, x, y, width, height, null);
			}
			break;
		}

	}

	public void hit() {
		if (action == ObjectAction.ATTACK_A) {
			hitB.setTriggered(true);
			hitA.setTriggered(false);
		}
		if (action == ObjectAction.ATTACK_B) {
			hitA.setTriggered(true);
			hitB.setTriggered(false);
		}

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Rectangle getBottomBound() {
		return new Rectangle(x, y + (height / 2), width, height / 2);
	}

	public Rectangle getTopBound() {
		return new Rectangle(x, y, width, height / 2);
	}

	public Rectangle getLeftBound() {
		return new Rectangle(x, y, width / 2, height);
	}

	public Rectangle getRightBound() {
		return new Rectangle(x + (width / 2), y, width / 2, height);
	}

	public PlayerType getCharacter() {
		return character;
	}

	public int getNumStocks() {
		return numStocks;
	}

	public int getDamage() {
		return damage;
	}
	public boolean stockLost() {
		return stockLost;
	}
	public void findRemainingStocks() {
		stockLost = false;
	}

}
