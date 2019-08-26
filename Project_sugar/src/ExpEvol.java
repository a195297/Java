import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ExpEvol {
	static int exp = 0;
	JButton slime = new JButton();

	public void expAdd() {
		exp += 2;
	}

	public int getExp() {
		return exp;
	}

	// public boolean evolve = false;

	public void evol(JButton slime) {
		this.slime = slime;
		if (exp % 10 == 0 && exp < 50) {
			setIcon("src/Slime" + exp / 10 + ".png", slime);
			slime.setSize(80 + exp / 10 * 20, 80 + exp / 10 * 20);
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
