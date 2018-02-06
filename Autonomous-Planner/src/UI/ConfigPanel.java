package UI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Entity.FreeMoving.Robot;
import World.World;

public class ConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private World world;
	private Robot[] robots;
	
	private JRadioButton robot0, robot1, robot2;
	
	public ConfigPanel(World world) {
		this.world = world;
		this.robots = world.getRobots();
		
		setName("Configuration");
		
//		JScrollPane configPane = new JScrollPane();
//		tabbedPane.addTab("Configuration", null, configPane, null);
		
//		JPanel configPanel = new JPanel();
//		configPane.setViewportView(configPanel);
		GridBagLayout gbl_configPanel = new GridBagLayout();
		gbl_configPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_configPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_configPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_configPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_configPanel);
		
		JLabel gameDataIdentifier = new JLabel("Game Data");
		gameDataIdentifier.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_gameDataIdentifier = new GridBagConstraints();
		gbc_gameDataIdentifier.insets = new Insets(0, 0, 5, 5);
		gbc_gameDataIdentifier.gridx = 0;
		gbc_gameDataIdentifier.gridy = 0;
		add(gameDataIdentifier, gbc_gameDataIdentifier);
		
		JRadioButton rl = new JRadioButton("RL");
		rl.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 1;
		add(rl, gbc_rdbtnNewRadioButton);
		
		JRadioButton rr = new JRadioButton("RR");
		rr.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 1;
		gbc_rdbtnNewRadioButton_2.gridy = 1;
		add(rr, gbc_rdbtnNewRadioButton_2);
		
		JRadioButton lr = new JRadioButton("LR");
		lr.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 0;
		gbc_rdbtnNewRadioButton_1.gridy = 2;
		add(lr, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton ll = new JRadioButton("LL");
		ll.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_3.gridx = 1;
		gbc_rdbtnNewRadioButton_3.gridy = 2;
		add(ll, gbc_rdbtnNewRadioButton_3);
		
		JLabel lblNewLabel = new JLabel("Actions");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel robotLabel = new JLabel("Robots");
		robotLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_robotLabel = new GridBagConstraints();
		gbc_robotLabel.insets = new Insets(0, 0, 5, 0);
		gbc_robotLabel.gridx = 2;
		gbc_robotLabel.gridy = 4;
		add(robotLabel, gbc_robotLabel);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 5;
		add(btnStart, gbc_btnStart);
		
		robot0 = new JRadioButton(Integer.toString(robots[0].getTeamNumber()));
		robot0.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbcc_rdbtnNewRadioButton = new GridBagConstraints();
		gbcc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
		gbcc_rdbtnNewRadioButton.gridx = 2;
		gbcc_rdbtnNewRadioButton.gridy = 5;
		add(robot0, gbcc_rdbtnNewRadioButton);
		
		robot1 = new JRadioButton(Integer.toString(robots[1].getTeamNumber()));
		robot1.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbcc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbcc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbcc_rdbtnNewRadioButton_1.gridx = 2;
		gbcc_rdbtnNewRadioButton_1.gridy = 6;
		add(robot1, gbcc_rdbtnNewRadioButton_1);
		
		robot2 = new JRadioButton(Integer.toString(robots[2].getTeamNumber()));
		robot2.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbcc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbcc_rdbtnNewRadioButton_2.gridx = 2;
		gbcc_rdbtnNewRadioButton_2.gridy = 7;
		add(robot2, gbcc_rdbtnNewRadioButton_2);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 6;
		add(btnReset, gbc_btnNewButton);
		
		ButtonGroup dataButtons = new ButtonGroup();
		dataButtons.add(rr);
		dataButtons.add(ll);
		dataButtons.add(rl);
		dataButtons.add(lr);
		
		rr.addActionListener(e -> {
			setGameData(0);
		});
		
		ll.addActionListener(e -> {
			setGameData(1);
		});
		
		lr.addActionListener(e -> {
			setGameData(2);
		});
		
		rl.addActionListener(e -> {
			setGameData(3);
		});
		
		rl.doClick();
		
		btnStart.addActionListener(e -> {
			for(Robot robot : robots)
				robot.start();
		});
		
		btnReset.addActionListener(e -> {
			for(Robot robot : robots) {
				robot.stop();
				robot.spawn();
			}
		});
		
		ButtonGroup robotButtons = new ButtonGroup();
		robotButtons.add(robot0);
		robotButtons.add(robot1);
		robotButtons.add(robot2);

		robot0.addActionListener(e -> {
			robots[0].edit();
			robots[1].disableEdit();
			robots[2].disableEdit();
		});
		
		robot1.addActionListener(e -> {
			robots[1].edit();
			robots[0].disableEdit();
			robots[2].disableEdit();
		});
		
		robot2.addActionListener(e -> {
			robots[2].edit();
			robots[0].disableEdit();
			robots[1].disableEdit();
		});
		
		robot0.doClick();
	}

	private void setGameData(int index) {
		for(Robot robot : world.getRobots()) {
			robot.stop();
			robot.spawn();
			robot.setGameData(index);

			if(robot0.isSelected())
				robots[0].edit();
			else if(robot1.isSelected())
				robots[1].edit();
			else if(robot2.isSelected())
				robots[2].edit();
		}
	}
}
