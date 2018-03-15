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
import Entity.FreeMoving.Action.Robot.WaitAction;
import Utils.Util;

public class WaitActionPanel extends ActionPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField timeField;
	private WaitAction action;
	
	public WaitActionPanel(RobotPanel robotPanel, ArrayList<Action> actions, WaitAction action) {
		super(robotPanel, actions, action);
		
		this.action = action;
		
		setBorder(new CompoundBorder(new EmptyBorder(1, 0, 1, 0), new LineBorder(new Color(0, 0, 0), 1, true)));
		JLabel lblForwardAction = new JLabel("Wait Action");
		lblForwardAction.setFont(new Font("Consolas", Font.BOLD, 14));
		add(lblForwardAction, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblTime = new JLabel("Time: ");
		panel.add(lblTime);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		timeField = new JTextField(Float.toString(action.getTime()));
		panel.add(timeField);
		timeField.setColumns(10);
		
		timeField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				setTime();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				setTime();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				setTime();
			}
		});
		
//		JLabel lblSpeed = new JLabel("Speed: ");
//		panel.add(lblSpeed);
//		lblSpeed.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		
//		speedField = new JTextField(Float.toString(action.getSpeed()));
//		panel.add(speedField);
//		speedField.setColumns(10);
//		
//		speedField.getDocument().addDocumentListener(new DocumentListener() {
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				setSpeed();
//			}
//			
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				setSpeed();
//			}
//			
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//				setSpeed();
//			}
//		});
	}
	
	private void setTime() { 
		action.setTime(Util.parseFloat(timeField.getText()));
	}
}
