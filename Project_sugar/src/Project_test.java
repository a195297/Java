import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project_test extends JFrame implements ActionListener {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Scanner keyin = new Scanner(System.in);

		Project_test zoo = new Project_test();
		zoo.setVisible(true);
		zoo.setResizable(false);

	}

	BGM bgm = new BGM("src/BackGroundMusic.wav");

	static JFrame zoo = new JFrame();
	static JButton startButton = new JButton("Start");
	static JPanel background = new JPanel() {
		public void paintComponent(Graphics g) {
			ImageIcon icon = new ImageIcon("src/background.jpg");
			g.drawImage(icon.getImage(), 0, 0, this.getSize().width, this.getSize().height, this);
		}
	};

	static JLabel label = new JLabel("Exp¡G0");

	static JButton slime = new JButton();
	static FreeMove createSlime = new FreeMove(slime);
	static JButton food;
	static FreeMove createFood;

	static Setting gaming = new Setting();
	
	public Project_test() {

		setSize(360, 560);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		background.setLayout(null);
		background.setSize(360, 560);
		background.setLocation(0, 0);
		//background.setOpaque(true);
		//background.setVisible(true);

		startButton.setSize(180, 50);
		startButton.setLocation(90, 240);
		startButton.addActionListener(this);
		startButton.setVisible(true);

		label.setSize(250, 50);
		label.setLocation(10, 10);

		background.add(startButton);
		background.add(label);

		add(background);
	}

	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		if (command.equals("Start")) {
			gaming.start();
			bgm.start();
			startButton.setVisible(false);
			createSlime();
			createFood();
		}
	}

	public static void setIcon(String file, JButton iconButton) {
		ImageIcon icon = new ImageIcon(file);
		Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(), iconButton.getHeight(),
				icon.getImage().SCALE_AREA_AVERAGING);
		icon = new ImageIcon(temp);
		iconButton.setIcon(icon);
	}

	public static void createSlime() {
		slime.setSize(80, 80);
		slime.setContentAreaFilled(false);
		slime.setBorderPainted(false);
		setIcon("src/Slime0.png", slime);
		slime.setText("Slime");
		CreatureButtonListener slimelistener = new CreatureButtonListener(slime, createSlime);
		slime.addActionListener(slimelistener);
		background.add(slime);
		createSlime.start();
	}

	public static void createFood() {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 8; i++) {
					try {
						food = new JButton("Food");
						createFood = new FreeMove(food);
						food.setSize(90, 90);
						food.setContentAreaFilled(false);
						food.setBorderPainted(false);
						setIcon("src/food.png", food);
						CreatureButtonListener foodlistener = new CreatureButtonListener(slime, createSlime, food,
								createFood, background, label);
						food.addActionListener(foodlistener);
						background.add(food);
						createFood.start();
						Thread.sleep((int) (Math.random() + 2) * 1000);
					} catch (Exception e1) {
						e1.getStackTrace();
					}
				}
			}
		}).start();
	}

}
