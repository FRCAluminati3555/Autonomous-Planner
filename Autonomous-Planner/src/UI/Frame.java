package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import World.World;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private World world;
	private JTabbedPane tabbedPane;
	
	public Frame(World world) {
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		ConfigPanel config = new ConfigPanel(world);
		JScrollPane scroll = new JScrollPane(config);
		scroll.setName(config.getName());
		addRobotPanel(config);
		
		addRobotPanel("Aluminati", "Danky Kang", 3555, 
				"This is the 3555 Aluminati robot. It does a lotta lotta lotta lotta lotta lotta lotta lotta lotta lotta lotta lotta lotta stuff");

		setResizable(false);
		setVisible(true);
	}
	
	public void addRobotPanel(String teamName, String robotName, int teamNumber, String description) {
		RobotPanel panel = new RobotPanel(teamName, robotName, teamNumber, description);
		JScrollPane pane = new JScrollPane(panel);
		pane.setName(panel.getName());
		
		addRobotPanel(pane);
	}
	
	public void addRobotPanel(Component comp) {
		tabbedPane.addTab(comp.getName(), null, comp, null);
	}
}
