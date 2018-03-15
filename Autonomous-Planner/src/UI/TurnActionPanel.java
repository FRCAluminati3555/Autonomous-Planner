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
import Entity.FreeMoving.Action.Robot.Turn;
import Utils.Util;

public class TurnActionPanel extends ActionPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField angleField;
	private JTextField speedField;

	private Turn action;
	
	public TurnActionPanel(RobotPanel robotPanel, ArrayList<Action> actions, Turn action) {
		super(robotPanel, actions, action);
		
		this.action = action;
		
		setBorder(new CompoundBorder(new EmptyBorder(1, 0, 1, 0), new LineBorder(new Color(0, 0, 0), 1, true)));
		JLabel lblTurnAction = new JLabel("Turn Action");
		lblTurnAction.setFont(new Font("Consolas", Font.BOLD, 14));
		add(lblTurnAction, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblTurn = new JLabel("Angle: ");
		panel.add(lblTurn);
		lblTurn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		angleField = new JTextField(Float.toString(action.getAngle()));
		panel.add(angleField);
		angleField.setColumns(10);
		
		angleField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				setAngle();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				setAngle();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				setAngle();
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
		return angleField.getText();
	}
	
	private void setAngle() { 
		action.setAngle(Util.parseFloat(angleField.getText()));
	}
	
	private void setSpeed() {
		action.setSpeed(Util.parseFloat(speedField.getText()));
	}
}
