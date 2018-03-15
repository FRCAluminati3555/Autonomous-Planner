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
import javax.swing.JTextField;

import Entity.FreeMoving.Robot;
import Main.Handler;
import World.World;

public class ConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private World world;
	private Handler handler;
	private Robot[] robots;
	private JTextField robotNumberField;
	private JRadioButton rl;
	private JRadioButton rr;
	private JRadioButton ll;
	private JRadioButton l;
	private JRadioButton r;
	private JRadioButton lr;
	
	public ConfigPanel(Frame frame, Handler handler) {
		this.world = handler.getWorld();
		this.handler = handler;
		this.robots = world.getRobots();
		
		setName("Configuration");
		
//		JScrollPane configPane = new JScrollPane();
//		tabbedPane.addTab("Configuration", null, configPane, null);
		
//		JPanel configPanel = new JPanel();
//		configPane.setViewportView(configPanel);
		GridBagLayout gbl_configPanel = new GridBagLayout();
		gbl_configPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_configPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_configPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_configPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_configPanel);
		
		JLabel gameDataIdentifier = new JLabel("Game Data");
		gameDataIdentifier.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_gameDataIdentifier = new GridBagConstraints();
		gbc_gameDataIdentifier.insets = new Insets(0, 0, 5, 5);
		gbc_gameDataIdentifier.gridx = 0;
		gbc_gameDataIdentifier.gridy = 0;
		add(gameDataIdentifier, gbc_gameDataIdentifier);
		
		rl = new JRadioButton("RL");
		rl.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_rl = new GridBagConstraints();
		gbc_rl.insets = new Insets(0, 0, 5, 5);
		gbc_rl.gridx = 0;
		gbc_rl.gridy = 1;
		add(rl, gbc_rl);
		
		rr = new JRadioButton("RR");
		rr.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_rr = new GridBagConstraints();
		gbc_rr.insets = new Insets(0, 0, 5, 5);
		gbc_rr.gridx = 1;
		gbc_rr.gridy = 1;
		add(rr, gbc_rr);
		
		JButton btnAddRobot = new JButton("Add Robot");
		btnAddRobot.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_btnAddRobot = new GridBagConstraints();
		gbc_btnAddRobot.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddRobot.gridx = 3;
		gbc_btnAddRobot.gridy = 1;
		add(btnAddRobot, gbc_btnAddRobot);
		
		lr = new JRadioButton("LR");
		lr.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_lr = new GridBagConstraints();
		gbc_lr.insets = new Insets(0, 0, 5, 5);
		gbc_lr.gridx = 0;
		gbc_lr.gridy = 2;
		add(lr, gbc_lr);
		
		ll = new JRadioButton("LL");
		ll.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_ll = new GridBagConstraints();
		gbc_ll.insets = new Insets(0, 0, 5, 5);
		gbc_ll.gridx = 1;
		gbc_ll.gridy = 2;
		add(ll, gbc_ll);
		
		robotNumberField = new JTextField();
		robotNumberField.setToolTipText("Team Number");
		GridBagConstraints gbc_robotNumberField = new GridBagConstraints();
		gbc_robotNumberField.insets = new Insets(0, 0, 5, 0);
		gbc_robotNumberField.fill = GridBagConstraints.HORIZONTAL;
		gbc_robotNumberField.gridx = 3;
		gbc_robotNumberField.gridy = 2;
		add(robotNumberField, gbc_robotNumberField);
		robotNumberField.setColumns(10);
		
		r = new JRadioButton("R");
		r.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_r = new GridBagConstraints();
		gbc_r.insets = new Insets(0, 0, 5, 5);
		gbc_r.gridx = 0;
		gbc_r.gridy = 3;
		add(r, gbc_r);
		
		l = new JRadioButton("L");
		l.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_l = new GridBagConstraints();
		gbc_l.insets = new Insets(0, 0, 5, 5);
		gbc_l.gridx = 1;
		gbc_l.gridy = 3;
		add(l, gbc_l);
		
		JLabel lblNewLabel = new JLabel("Actions");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 5;
		add(btnStart, gbc_btnStart);
		
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
		dataButtons.add(r);
		dataButtons.add(l);
		
		rr.addActionListener(e -> {
			setGameData("rr");
		});
		
		ll.addActionListener(e -> {
			setGameData("ll");
		});
		
		lr.addActionListener(e -> {
			setGameData("lr");
		});
		
		rl.addActionListener(e -> {
			setGameData("rl");
		});
		
		r.addActionListener(e -> {
			setGameData("r");
		});
		
		l.addActionListener(e -> {
			setGameData("l");
		});
		
		btnStart.addActionListener(e -> {
			for(Robot robot : robots) {
				if(robot != null) {
					if(!robot.isRunning())
						robot.start();
					else
						robot.stop();
				}
			}
		});
		
		btnReset.addActionListener(e -> {
			for(Robot robot : robots) {
				if(robot != null) {
					robot.stop();
					robot.spawn();
				}
			}
		});
		
		btnAddRobot.addActionListener(e -> {
			int teamNumber = Integer.parseInt(robotNumberField.getText());
			frame.addRobotPanel("", "", teamNumber, "");
		});
		
		ButtonGroup robotButtons = new ButtonGroup();

//		robot0.addActionListener(e -> {
//			if(robots[0] != null)
//				robots[0].edit();
//			if(robots[1] != null)
//				robots[1].disableEdit();
//			if(robots[2] != null)
//				robots[2].disableEdit();
//		});
//		
//		robot1.addActionListener(e -> {
//			if(robots[1] != null)
//				robots[1].edit();
//			if(robots[0] != null)
//				robots[0].disableEdit();
//			if(robots[2] != null)
//				robots[2].disableEdit();
//		});
//		
//		robot2.addActionListener(e -> {
//			if(robots[2] != null)
//				robots[2].edit();
//			if(robots[0] != null)
//				robots[0].disableEdit();
//			if(robots[1] != null)
//				robots[1].disableEdit();
//		});
		
//		robot0.doClick();
	}
	
	public JRadioButton getRl() { return rl; }
	public JRadioButton getRr() { return rr; }
	public JRadioButton getLl() { return ll; }
	public JRadioButton getL() { return l; }
	public JRadioButton getR() { return r; }
	public JRadioButton getLr() { return lr; }

	private void setGameData(String string) {
		for(RobotPanel robotPanel : handler.getFrame().getRobotPanels().values()) {
			robotPanel.getOwnershipButton(string).doClick();
		}
		
//		for(Robot robot : world.getRobots()) {
//			if(robot != null) {
//				robot.stop();
//				robot.spawn();
//				
//				robot.getInfo().setOwnership(string);
//				
//				handler.getFrame().getRobotPanel(robot.getTeamNumber()).getOwnershipButton(string).doClick();
//			}
//		}
	}
	
}
