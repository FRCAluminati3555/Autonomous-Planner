package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import Entity.FreeMoving.Action.Action;

public class ActionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	protected Action action;
	protected ArrayList<Action> actions;
	
	private JButton upButton, downButton;
	private RobotPanel robotPanel;
	
	private JButton removeButton;
	
	public ActionPanel(RobotPanel robotPanel, ArrayList<Action> actions, Action action) {
		this.robotPanel = robotPanel;
		this.actions = actions;
		this.action = action;
		
		setLayout(new BorderLayout());
		
		JPanel navPanel = new JPanel();
		navPanel.setLayout(new BorderLayout());
		
		upButton = new JButton("UP");
		downButton = new JButton("Down");

		navPanel.add(upButton, BorderLayout.NORTH);
		navPanel.add(downButton, BorderLayout.SOUTH);

		JPanel removePanel = new JPanel();
		removeButton = new JButton("-");
		removeButton.setFont(new Font("Arial", Font.PLAIN, 32));
		
		removePanel.setLayout(new BorderLayout());
		removePanel.add(removeButton, BorderLayout.WEST);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		removePanel.add(horizontalStrut, BorderLayout.EAST);
		
		add(navPanel, BorderLayout.WEST);
		add(removePanel, BorderLayout.EAST);
	}
	
	public void init(ActionPanel panel) {
		upButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Box box = (Box) getParent();
				
				int index = getIndex();
				if(index == 0) 
					return;
				
				box.remove(panel);
				box.add(panel, index - 1);
				
				actions.remove(action);
				actions.add(index - 1, action);
				
				robotPanel.revalidate();
			}
		});
		
		downButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Box box = (Box) getParent();
				
				int index = getIndex();
				if(index + 1 == box.getComponentCount()) 
					return;
				
				box.remove(panel);
				box.add(panel, index + 1);
				
				actions.remove(action);
				actions.add(index + 1, action);
				
				robotPanel.revalidate();
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Box box = (Box) getParent();
				
				box.remove(panel);
				actions.remove(action);
				
				robotPanel.revalidate();
			}
		});
	}
	
	private int getIndex() {
		Box box = (Box) getParent();
		
		Component[] components = box.getComponents();

		int index = 0;
		
		for(int i = 0; i < components.length; i++) {
			if(components[i] == this) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	private int getLength() {
		Box box = (Box) getParent();
		Component[] components = box.getComponents();
		return components.length;
	}
	
	public ArrayList<Action> getActions() { return actions; }
	public Action getAction() { return action; }
}