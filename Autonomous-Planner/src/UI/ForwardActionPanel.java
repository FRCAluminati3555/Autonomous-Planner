package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Entity.FreeMoving.Action.Action;
import Entity.FreeMoving.Action.Robot.MoveDistance;
import Utils.Util;

public class ForwardActionPanel extends ActionPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField distanceField;
	private JTextField speedField;

	private MoveDistance action;
	
	public ForwardActionPanel(RobotPanel robotPanel, ArrayList<Action> actions, MoveDistance action) {
		super(robotPanel, actions, action);
		
		this.action = action;
		
		setBorder(new CompoundBorder(new EmptyBorder(1, 0, 1, 0), new LineBorder(new Color(0, 0, 0), 1, true)));
		JLabel lblForwardAction = new JLabel("Forward Action");
		lblForwardAction.setFont(new Font("Consolas", Font.BOLD, 14));
		add(lblForwardAction, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblDistance = new JLabel("Distance: ");
		panel.add(lblDistance);
		lblDistance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		distanceField = new JTextField(Float.toString(action.getDistanceMeters()));
		panel.add(distanceField);
		distanceField.setColumns(10);
		
		distanceField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				setDistance();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				setDistance();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				setDistance();
			}
		});
		
		JLabel lblSpeed = new JLabel("Speed: ");
		panel.add(lblSpeed);
		lblSpeed.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		speedField = new JTextField(Float.toString(action.getSpeed()));
		panel.add(speedField);
		speedField.setColumns(10);
		
		speedField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				setSpeed();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				setSpeed();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				setSpeed();
			}
		});
	}
	
	public String toString() { 
		return distanceField.getText();
	}
	
	private void setDistance() { 
		action.setDistance(Util.parseFloat(distanceField.getText()));
	}
	
	private void setSpeed() {
		action.setSpeed(Util.parseFloat(speedField.getText()));
	}
}
