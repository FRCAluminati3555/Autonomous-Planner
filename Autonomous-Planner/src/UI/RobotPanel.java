package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Entity.FreeMoving.Robot;
import Main.Handler;
import Utils.AssetLoader;
import Utils.InformationUtil;
import World.World;

public class RobotPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private int teamNumber;
	
	private JTextArea robotNameField;
	private JTextArea teamNameField;
	private JLabel teamNumberField;
	private JTextArea descriptionField;
	
	private Handler handler;
	private World world;
	
	public RobotPanel(Handler handler, String teamName, String robotName, int teamNumber, String description) {
		super();

		this.teamNumber = teamNumber;
		this.handler = handler;
		this.world = handler.getWorld();
		
		setName(Integer.toString(teamNumber));
		setLayout(new BorderLayout(0, 0));
		
		JPanel UpperInformationPanel = new JPanel();
		add(UpperInformationPanel, BorderLayout.NORTH);
		UpperInformationPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel imageLabel = new JLabel();
		
		imageLabel.setIcon(scaleImage(new Dimension(400, 400)));
		
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UpperInformationPanel.add(imageLabel, BorderLayout.NORTH);
		
		JPanel InformationPanel = new JPanel();
		UpperInformationPanel.add(InformationPanel, BorderLayout.CENTER);
		GridBagLayout gbl_InformationPanel = new GridBagLayout();
		gbl_InformationPanel.columnWidths = new int[]{1, 0, 0, 0, 0};
		gbl_InformationPanel.rowHeights = new int[]{1, 0, 0, 0, 0, 0};
		gbl_InformationPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_InformationPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		InformationPanel.setLayout(gbl_InformationPanel);
		
		JLabel teamNumberIdentifier = new JLabel("Team Number: ");
		teamNumberIdentifier.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_teamNumberIdentifier = new GridBagConstraints();
		gbc_teamNumberIdentifier.anchor = GridBagConstraints.EAST;
		gbc_teamNumberIdentifier.insets = new Insets(0, 0, 5, 5);
		gbc_teamNumberIdentifier.gridx = 0;
		gbc_teamNumberIdentifier.gridy = 0;
		InformationPanel.add(teamNumberIdentifier, gbc_teamNumberIdentifier);
		
		teamNumberField = new JLabel(getName());
		teamNumberField.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_teamNumber = new GridBagConstraints();
		gbc_teamNumber.anchor = GridBagConstraints.WEST;
		gbc_teamNumber.insets = new Insets(0, 0, 5, 5);
		gbc_teamNumber.gridx = 1;
		gbc_teamNumber.gridy = 0;
		InformationPanel.add(teamNumberField, gbc_teamNumber);
		
		JLabel teamNameIdentifier = new JLabel("Team Name: ");
		teamNameIdentifier.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_teamNameIdentifier = new GridBagConstraints();
		gbc_teamNameIdentifier.anchor = GridBagConstraints.EAST;
		gbc_teamNameIdentifier.insets = new Insets(0, 0, 5, 5);
		gbc_teamNameIdentifier.gridx = 0;
		gbc_teamNameIdentifier.gridy = 1;
		InformationPanel.add(teamNameIdentifier, gbc_teamNameIdentifier);
		
		teamNameField = new JTextArea(teamName != null ? teamName : " ");
		teamNameField.setFont(new Font("Consolas", Font.PLAIN, 20));
		teamNameField.setEditable(true);
		GridBagConstraints gbc_teamNameLabel = new GridBagConstraints();
		gbc_teamNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_teamNameLabel.anchor = GridBagConstraints.WEST;
		gbc_teamNameLabel.gridx = 1;
		gbc_teamNameLabel.gridy = 1;
		InformationPanel.add(teamNameField, gbc_teamNameLabel);
		
		JLabel robotNameIdentifier = new JLabel("Robot Name: ");
		robotNameIdentifier.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_robotNameIdentifier = new GridBagConstraints();
		gbc_robotNameIdentifier.anchor = GridBagConstraints.EAST;
		gbc_robotNameIdentifier.insets = new Insets(0, 0, 5, 5);
		gbc_robotNameIdentifier.gridx = 0;
		gbc_robotNameIdentifier.gridy = 2;
		InformationPanel.add(robotNameIdentifier, gbc_robotNameIdentifier);
		
		robotNameField = new JTextArea(robotName != null ? robotName : " ");
		robotNameField.setFont(new Font("Consolas", Font.PLAIN, 20));
		robotNameField.setEditable(true);
		GridBagConstraints gbc_robotNameLabel = new GridBagConstraints();
		gbc_robotNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_robotNameLabel.anchor = GridBagConstraints.WEST;
		gbc_robotNameLabel.gridx = 1;
		gbc_robotNameLabel.gridy = 2;
		InformationPanel.add(robotNameField, gbc_robotNameLabel);
		
		JButton btnSaveInformation = new JButton("Save Information");
		btnSaveInformation.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_btnSaveInformation = new GridBagConstraints();
		gbc_btnSaveInformation.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveInformation.gridx = 0;
		gbc_btnSaveInformation.gridy = 3;
		InformationPanel.add(btnSaveInformation, gbc_btnSaveInformation);
		
		btnSaveInformation.addActionListener(e -> {
			saveInformation();
		});
		
		JButton btnSavePathData = new JButton("Save Path Data");
		btnSavePathData.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_btnSavePathData = new GridBagConstraints();
		gbc_btnSavePathData.insets = new Insets(0, 0, 5, 5);
		gbc_btnSavePathData.gridx = 1;
		gbc_btnSavePathData.gridy = 3;

		btnSavePathData.addActionListener(e -> {
			savePath();
		});
		
		InformationPanel.add(btnSavePathData, gbc_btnSavePathData);
		
		JButton btnLoadIntoWorld = new JButton("Load Into World");
		btnLoadIntoWorld.setFont(new Font("Calibri", Font.PLAIN, 14));
		GridBagConstraints gbc_btnLoadIntoWorld = new GridBagConstraints();
		gbc_btnLoadIntoWorld.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoadIntoWorld.gridx = 2;
		gbc_btnLoadIntoWorld.gridy = 3;
		InformationPanel.add(btnLoadIntoWorld, gbc_btnLoadIntoWorld);
		
		btnLoadIntoWorld.addActionListener(e -> {
			loadIntoWorld();
		});
		
		JLabel descriptionIdentifier = new JLabel("Description: ");
		descriptionIdentifier.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_descriptionIdentifier = new GridBagConstraints();
		gbc_descriptionIdentifier.insets = new Insets(0, 0, 0, 5);
		gbc_descriptionIdentifier.gridx = 0;
		gbc_descriptionIdentifier.gridy = 4;
		InformationPanel.add(descriptionIdentifier, gbc_descriptionIdentifier);
		
		descriptionField = new JTextArea();
		descriptionField.setWrapStyleWord(true);
		descriptionField.setFont(new Font("Calibri", Font.PLAIN, 18));
		descriptionField.setLineWrap(true);
		descriptionField.setText(description);
//		descriptionArea.setEditable(false);
		add(descriptionField, BorderLayout.CENTER);
	}
	
	private void saveInformation() {
		InformationUtil.exportInformation(this);
	}
	
	private void savePath() {
		for(Robot robot : world.getRobots()) {
			if(robot != null) {
				if(robot.getTeamNumber() == teamNumber) {
					InformationUtil.exportPath(robot);
					break; 
				}
			}
		}
	}
	 
	private void loadIntoWorld() {
		new LoadIntoWorldPanel(handler, teamNumber);
	}
	
	private Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }

	    return new Dimension(new_width, new_height);
	}
	
	private ImageIcon scaleImage(Dimension boundry) {
		File file = new File(AssetLoader.ROBOT_IMAGES_PATH + teamNumber + ".png");
		if(!file.exists())
			return null;
		
		BufferedImage src = AssetLoader.loadImage(AssetLoader.ROBOT_IMAGES_PATH + teamNumber + ".png");
		
		Dimension dimension = getScaledDimension(new Dimension(src.getWidth(), src.getHeight()), boundry);
		
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(dimension.width, dimension.height, type);
        Graphics2D g2 = dst.createGraphics();
        g2.drawImage(src, 0, 0, dimension.width, dimension.height, this);
        g2.dispose();
        return new ImageIcon(dst);
	}

	public JTextArea getRobotNameField() { return robotNameField; }
	public JTextArea getTeamNameField() { return teamNameField; }
	public JTextArea getDescriptionField() { return descriptionField; }
	 
	public int getTeamNumber() { return teamNumber; }
}