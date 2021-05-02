package paul.smash.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import paul.smash.display.Helper;

public class KeyboardInput extends KeyAdapter {
	private Helper helper;

	public KeyboardInput(Helper helper) {
		this.helper = helper;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println((char) key +  " was pressed");
		System.out.println(helper.objects.size());
		
		for (int i = 0; i < helper.objects.size(); i++) {
			GameObject tempObject = helper.objects.get(i);
			System.out.println(tempObject.type);

			if (tempObject.getType() == ObjectType.PLAYER_ONE) {
				if (key == KeyEvent.VK_A) {
					System.out.print("A was pressed");
					System.out.println((tempObject.getX()));
					System.out.println(tempObject.getVelX());


					tempObject.setVelX(-5);
					tempObject.setLeftFacing(true);
					tempObject.setRightFacing(false);
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
					tempObject.setLeftFacing(false);
					tempObject.setRightFacing(true);
				}
				
				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-10);
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setAction(ObjectAction.ATTACK_A);
				}
				if (key == KeyEvent.VK_Z || key == KeyEvent.VK_X) {
					tempObject.setAction(ObjectAction.ATTACK_B);
				}

			}

			if (tempObject.getType() == ObjectType.PLAYER_TWO) {
				if (key == KeyEvent.VK_J) {
					tempObject.setVelX(-5);
					tempObject.setLeftFacing(true);
					tempObject.setRightFacing(false);
				}
				if (key == KeyEvent.VK_L) {
					tempObject.setVelX(5);
					tempObject.setLeftFacing(false);
					tempObject.setRightFacing(true);
				}
				
				if (key == KeyEvent.VK_I) {
					tempObject.setVelY(-10);
				}
				if (key == KeyEvent.VK_K) {
					tempObject.setAction(ObjectAction.ATTACK_A);
				}
				if (key == KeyEvent.VK_M || key == KeyEvent.VK_COMMA) {
					tempObject.setAction(ObjectAction.ATTACK_B);
				}

			}
		}
	}
	
	
	
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println((char) key +  " was released");
		for (int i = 0; i < helper.objects.size(); i++) {
			GameObject tempObject = helper.objects.get(i);

			if (tempObject.getType() == ObjectType.PLAYER_ONE) {
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(0);
					System.out.println("A was pressed");
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(0);
				}
				
				if (key == KeyEvent.VK_W) {
					tempObject.setJumping(false);
					tempObject.setFalling(true);
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setAction(ObjectAction.IDLE);
				}
				if (key == KeyEvent.VK_Z || key == KeyEvent.VK_X) {
					tempObject.setAction(ObjectAction.IDLE);
				}

			}

			if (tempObject.getType() == ObjectType.PLAYER_TWO) {
				if (key == KeyEvent.VK_J) {
					tempObject.setVelX(0);
				}
				if (key == KeyEvent.VK_L) {
					tempObject.setVelX(0);
				}
				
				if (key == KeyEvent.VK_I) {
					tempObject.setJumping(false);
					tempObject.setFalling(true);
				}
				if (key == KeyEvent.VK_K) {
					tempObject.setAction(ObjectAction.IDLE);
				}
				if (key == KeyEvent.VK_M || key == KeyEvent.VK_COMMA) {
					tempObject.setAction(ObjectAction.IDLE);
				}

			}
		}
	}

}
