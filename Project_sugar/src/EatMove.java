import java.awt.*;
import javax.swing.*;

public class EatMove implements Runnable {

	public Thread t;
	boolean suspended = false;

	private JButton creature = new JButton();
	private FreeMove createCreature = new FreeMove(creature);
	private JButton food = new JButton();
	private FreeMove createFood = new FreeMove(food);
	private int exp;

	public EatMove(JButton creature, FreeMove createCreature, JButton food, FreeMove createFood, int exp) {
		this.createCreature = createCreature;
		this.creature = creature;
		this.createFood = createFood;
		this.food = food;
		this.exp = exp;
	}

	public void run() {
		Point pc = creature.getLocation();
		Point pf = food.getLocation();
		int xdir = (pf.x - pc.x) / 10;
		int ydir = (pf.y - pc.y) / 10;
		setIcon("src/Slime"+exp+"eat.png",creature);
		for (int i = 0; i < 10; i++) {
			pc.x += xdir;
			pc.y += ydir;
			creature.setLocation(pc.x, pc.y);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
		}
		setIcon("src/Slime"+exp+".png",creature);
		createCreature.resume();
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public static void setIcon(String file, JButton iconButton) {
		ImageIcon icon = new ImageIcon(file);
		Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(), iconButton.getHeight(),
				icon.getImage().SCALE_AREA_AVERAGING);
		icon = new ImageIcon(temp);
		iconButton.setIcon(icon);
	}
}
