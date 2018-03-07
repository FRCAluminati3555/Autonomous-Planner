package UI;

import javax.swing.JPanel;

import Entity.FreeMoving.Robot;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SpwanLocationEditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField spawnNameField;
	private JTextField distanceFromLeftField;

	public SpwanLocationEditPanel() {
		JLabel lblName = new JLabel("Name: ");
		add(lblName);
		
		spawnNameField = new JTextField();
		add(spawnNameField);
		spawnNameField.setColumns(10);
		
		JLabel lblDistanceFromLeft = new JLabel("Distance From Left Side: ");
		add(lblDistanceFromLeft);
		
		distanceFromLeftField = new JTextField();
		add(distanceFromLeftField);
		distanceFromLeftField.setColumns(10);

		
	}
}
