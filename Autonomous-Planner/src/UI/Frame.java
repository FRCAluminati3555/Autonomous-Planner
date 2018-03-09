package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import Main.Handler;
import Utils.InformationUtil;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTabbedPane tabbedPane;
	private Handler handler;
	
	private ConfigPanel config;
	
	private HashMap<Integer, RobotPanel> robotPanels;
	
	public Frame(Handler handler) {
		super();
		this.handler = handler;
		
		robotPanels = new HashMap<>();
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 600);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		config = new ConfigPanel(this, handler);
		JScrollPane scroll = new JScrollPane(config);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		RobotPanel panel = new RobotPanel(handler, teamName, robotName, teamNumber, description);
		JScrollPane scroll = new JScrollPane(panel);
		
		scroll.getVerticalScrollBar().setUnitIncrement(8);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setName(panel.getName());
		
		scroll.getViewport().putClientProperty("EnableWindowBlit", Boolean.TRUE);
		scroll.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
		
		robotPanels.put(teamNumber, panel);
		
		addRobotPanel(scroll);
		InformationUtil.readPathUI(panel, teamNumber);
	}
	
	public RobotPanel getRobotPanel(int teamNumber) {
		return robotPanels.get(teamNumber);
	}
	
	public void updateRobotNumbers(int teamNumber) {
		config.updateTeamNumbers(teamNumber);
	}
	
	public void addRobotPanel(Component comp) {
		tabbedPane.addTab(comp.getName(), null, comp, null);
	}
}
