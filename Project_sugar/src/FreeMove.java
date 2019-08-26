import java.util.*;
import javax.swing.*;

public class FreeMove implements Runnable {

	public Thread t;
	private JButton creature;
	boolean suspended = false;
	boolean ate=false;

	public FreeMove(JButton creature) {
		this.creature = creature;
	}

	public void run() {
		Random freeMove = new Random();
		Random wait = new Random();

		int x = freeMove.nextInt(50) + 75;
		int y = freeMove.nextInt(100) + 170;
		while (true) {
			int xdir = freeMove.nextInt(5) - 2;
			int ydir = freeMove.nextInt(5) - 2;
			if (xdir != 0 || ydir != 0) {
				for (int i = 0; i < 100; i++) {
					if ((x <= 20 && xdir < 0) || (x >= 200 && xdir > 0))
						break;
					if ((y <= 70 && ydir < 0) || (y > 370 && ydir > 0))
						break;
					x += xdir;
					y += ydir;
					creature.setLocation(x, y);
					try {
						Thread.sleep(60);
						synchronized (this) {
							while (suspended) {
								wait();
								x = creature.getLocation().x;
								y = creature.getLocation().y;
							}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(wait.nextInt(10) * 100);
				x = creature.getLocation().x;
				y = creature.getLocation().y;
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	void suspend() {
		suspended = true;
	}

	synchronized void resume() {
		suspended = false;
		notify();
	}
}
