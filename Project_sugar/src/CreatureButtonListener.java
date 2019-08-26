import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class CreatureButtonListener implements ActionListener {

	private JButton creature = new JButton();
	private FreeMove createCreature = new FreeMove(creature);
	private JButton food = new JButton();
	private FreeMove createFood = new FreeMove(food);
	private JPanel panel = new JPanel();
	private EatMove eatFood;
	private JLabel label = new JLabel();
	private int expClick;
	private ExpEvol expCal = new ExpEvol();

	public CreatureButtonListener(JButton creature, FreeMove createCreature) {
		this.createCreature = createCreature;
		this.creature = creature;
	}

	public CreatureButtonListener(JButton creature, FreeMove createCreature, JButton food, FreeMove createFood,
			JPanel panel, JLabel label) {
		this.createCreature = createCreature;
		this.creature = creature;
		this.createFood = createFood;
		this.food = food;
		this.panel = panel;
		this.label = label;
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("Slime")) {
			try {
				synchronized (this) {
					if (createCreature.suspended == false) {
						createCreature.suspend();
					} else {
						createCreature.resume();

					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (command.equals("Food")) {
			expCal.expAdd();
			label.setText("Exp¡G" + expCal.getExp());
			eatFood = new EatMove(creature, createCreature, food, createFood,(int)(expCal.getExp()/10));
			createFood.suspend();
			createCreature.suspend();
			eatFood.start();
			expCal.evol(creature);
			recreate();
		}
	}

	public void recreate() {
		new Thread(new Runnable() {
			public void run() {
				try {
					food.setVisible(false);
					Thread.sleep((int) (Math.random() + 1) * 5000);
					food.setVisible(true);
					createFood.resume();
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		}).start();
	}
}
