package UI;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Entity.FreeMoving.RobotInformationPassThrough;
import Entity.FreeMoving.Spawn;
import Main.Handler;

public class SpawnLocationEditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField spawnNameField;
	private JTextField distanceFromLeftField;
	private JRadioButton radioButton;
	
	private RobotInformationPassThrough robotInfo;
	
	private Spawn spawn;

	/**
	 * @wbp.parser.constructor
	 */
	public SpawnLocationEditPanel(Handler handler, RobotInformationPassThrough robotInfo, Spawn spawn) {
		this.robotInfo = robotInfo;
		this.spawn = spawn;
		
		robotInfo.addSpawn(spawn);
		
		setBorder(new CompoundBorder(new EmptyBorder(1, 0, 1, 0), new LineBorder(new Color(0, 0, 0), 1, true)));
		
		radioButton = new JRadioButton("");
		add(radioButton);
		JLabel lblName = new JLabel("Name: ");
		add(lblName);
		
		spawnNameField = new JTextField();
		spawnNameField.setText(spawn.getName());
		
		add(spawnNameField);
		spawnNameField.setColumns(10);
		
		spawnNameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				setSpawnName();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				setSpawnName();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				setSpawnName();
			}
		});
		
		JLabel lblDistanceFromRight = new JLabel("Distance From Right Side: ");
		add(lblDistanceFromRight);
		
		distanceFromLeftField = new JTextField();
		add(distanceFromLeftField);
		distanceFromLeftField.setColumns(10);
		
		distanceFromLeftField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				setSpawnDistance();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				setSpawnDistance();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				setSpawnDistance();
			}
		});
		
		distanceFromLeftField.setText(Float.toString(spawn.getDistanceMeters()));
		
		radioButton.addActionListener(e -> {
			if(radioButton.isSelected()) {
				robotInfo.setSpawn(spawn);
			}
		});
	}
	
	public SpawnLocationEditPanel(Handler handler, RobotInformationPassThrough robotInfo) {
		this(handler, robotInfo, new Spawn());
	}
	
	private void setSpawnDistance() {
		float distance = 0;
		
		try {
			distance = Float.parseFloat(distanceFromLeftField.getText());
		} catch(NumberFormatException e) {
			distance = 0;
		}
		
		spawn.setDistanceFromRightWallMeters(distance);
		
		if(radioButton.isSelected()) {
			robotInfo.setSpawn(spawn);
		}
	}
	
	private void setSpawnName() {
		spawn.setName(spawnNameField.getText());
	}
	
	public JRadioButton getRadioButton() { return radioButton; }
}
