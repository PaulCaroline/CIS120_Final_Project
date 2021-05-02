package paul.smash.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import paul.smash.framework.GameObject;
import paul.smash.framework.ObjectType;
import paul.smash.framework.PlayerType;

public class Hitbox extends GameObject {

	private Player player;
	private int damageGiven;
	private PlayerType character;
	private boolean triggered;

	public boolean isTriggered() {
		return triggered;
	}

	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}

	public Hitbox(int x, int y, int width, int height, Player player, int damageGiven) {
		super(x, y, width, height);
		this.player = player;
		this.character = player.getCharacter();
		this.damageGiven = damageGiven;
		this.type = ObjectType.HITBOX;
	}

	@Override
	public void tick() {
		// The subtypes will override this method
		if (player.rightFacing()) {
			x = player.getX() + player.getWidth() / 2;
			y = player.getY();
		}
		if (!player.rightFacing()) {
			x = player.getX() - player.getWidth() / 2;
			y = player.getY();
		}
	}

	public int getDamageGiven() {
		if (triggered) {
			return 0;
		} else {
			return damageGiven;
		}
	}

	public PlayerType getCharacter() {
		return character;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.drawRect(x, y, getWidth(), getHeight());
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, getWidth(), getHeight());
	}

	public Player getPlayer() {
		return player;
	}

}
