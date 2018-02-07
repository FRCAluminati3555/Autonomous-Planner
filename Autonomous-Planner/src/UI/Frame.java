package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import Utils.InformationUtil;
import World.World;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private World world;
	private JTabbedPane tabbedPane;
	
	public Frame(World world) {
		super();
		
		this.world = world;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		ConfigPanel config = new ConfigPanel(world);
		JScrollPane scroll = new JScrollPane(config);
		scroll.setName(config.getName());
		addRobotPanel(config);
		
		try {
			InformationUtil.readInformation(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		setResizable(false);
		setVisible(true);
	}
	
	public void addRobotPanel(String teamName, String robotName, int teamNumber, String description) {
		RobotPanel panel = new RobotPanel(world, teamName, robotName, teamNumber, description);
		JScrollPane pane = new JScrollPane(panel);
		pane.setName(panel.getName());
		
		addRobotPanel(pane);
	}
	
	public void addRobotPanel(Component comp) {
		tabbedPane.addTab(comp.getName(), null, comp, null);
	}
}
