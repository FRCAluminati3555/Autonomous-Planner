package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Utils.AssetLoader;

public class RobotPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private int teamNumber;
	
	public RobotPanel(String teamName, String robotName, int teamNumber, String description) {
		super();

		this.teamNumber = teamNumber;
		
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
		gbl_InformationPanel.rowHeights = new int[]{1, 0, 0, 0, 0};
		gbl_InformationPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_InformationPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		InformationPanel.setLayout(gbl_InformationPanel);
		
		JLabel teamNumberIdentifier = new JLabel("Team Number: ");
		teamNumberIdentifier.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_teamNumberIdentifier = new GridBagConstraints();
		gbc_teamNumberIdentifier.anchor = GridBagConstraints.EAST;
		gbc_teamNumberIdentifier.insets = new Insets(0, 0, 5, 5);
		gbc_teamNumberIdentifier.gridx = 0;
		gbc_teamNumberIdentifier.gridy = 0;
		InformationPanel.add(teamNumberIdentifier, gbc_teamNumberIdentifier);
		
		JLabel teamNumberLabel = new JLabel(getName());
		teamNumberLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_teamNumber = new GridBagConstraints();
		gbc_teamNumber.anchor = GridBagConstraints.WEST;
		gbc_teamNumber.insets = new Insets(0, 0, 5, 5);
		gbc_teamNumber.gridx = 1;
		gbc_teamNumber.gridy = 0;
		InformationPanel.add(teamNumberLabel, gbc_teamNumber);
		
		JLabel teamNameIdentifier = new JLabel("Team Name: ");
		teamNameIdentifier.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_teamNameIdentifier = new GridBagConstraints();
		gbc_teamNameIdentifier.anchor = GridBagConstraints.EAST;
		gbc_teamNameIdentifier.insets = new Insets(0, 0, 5, 5);
		gbc_teamNameIdentifier.gridx = 0;
		gbc_teamNameIdentifier.gridy = 1;
		InformationPanel.add(teamNameIdentifier, gbc_teamNameIdentifier);
		
		JLabel teamNameLabel = new JLabel(teamName);
		teamNameLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_teamNameLabel = new GridBagConstraints();
		gbc_teamNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_teamNameLabel.anchor = GridBagConstraints.WEST;
		gbc_teamNameLabel.gridx = 1;
		gbc_teamNameLabel.gridy = 1;
		InformationPanel.add(teamNameLabel, gbc_teamNameLabel);
		
		JLabel robotNameIdentifier = new JLabel("Robot Name: ");
		robotNameIdentifier.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_robotNameIdentifier = new GridBagConstraints();
		gbc_robotNameIdentifier.anchor = GridBagConstraints.EAST;
		gbc_robotNameIdentifier.insets = new Insets(0, 0, 5, 5);
		gbc_robotNameIdentifier.gridx = 0;
		gbc_robotNameIdentifier.gridy = 2;
		InformationPanel.add(robotNameIdentifier, gbc_robotNameIdentifier);
		
		JLabel robotNameLabel = new JLabel(robotName);
		robotNameLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_robotNameLabel = new GridBagConstraints();
		gbc_robotNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_robotNameLabel.anchor = GridBagConstraints.WEST;
		gbc_robotNameLabel.gridx = 1;
		gbc_robotNameLabel.gridy = 2;
		InformationPanel.add(robotNameLabel, gbc_robotNameLabel);
		
		JLabel descriptionIdentifier = new JLabel("Description: ");
		descriptionIdentifier.setFont(new Font("Consolas", Font.PLAIN, 20));
		GridBagConstraints gbc_descriptionIdentifier = new GridBagConstraints();
		gbc_descriptionIdentifier.insets = new Insets(0, 0, 0, 5);
		gbc_descriptionIdentifier.gridx = 0;
		gbc_descriptionIdentifier.gridy = 3;
		InformationPanel.add(descriptionIdentifier, gbc_descriptionIdentifier);
		
		JTextArea descriptionArea = new JTextArea();
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setFont(new Font("Calibri", Font.PLAIN, 18));
		descriptionArea.setLineWrap(true);
		descriptionArea.setText(description);
		descriptionArea.setEditable(false);
		add(descriptionArea, BorderLayout.CENTER);
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
		BufferedImage src = AssetLoader.loadImage(AssetLoader.ROBOT_IMAGES_PATH + teamNumber + ".png");
		
		Dimension dimension = getScaledDimension(new Dimension(src.getWidth(), src.getHeight()), boundry);
		
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(dimension.width, dimension.height, type);
        Graphics2D g2 = dst.createGraphics();
        g2.drawImage(src, 0, 0, dimension.width, dimension.height, this);
        g2.dispose();
        return new ImageIcon(dst);
	}
}
