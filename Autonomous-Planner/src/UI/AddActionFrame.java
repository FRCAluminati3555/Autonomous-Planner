package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import Entity.FreeMoving.Action.Robot.MoveDistance;
import Entity.FreeMoving.Action.Robot.Turn;
import Entity.FreeMoving.Action.Robot.WaitAction;
import Main.Handler;

public class AddActionFrame {
	public AddActionFrame(Handler handler, int teamNumber) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(200, 200);
		
		Box verticalBox = Box.createVerticalBox();
		frame.getContentPane().add(verticalBox, BorderLayout.CENTER);
		
		JRadioButton moveDistanceButton = new JRadioButton("Move Distance");
		moveDistanceButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		moveDistanceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		moveDistanceButton.setHorizontalAlignment(SwingConstants.CENTER);
		verticalBox.add(moveDistanceButton);
		
		JRadioButton turnButton = new JRadioButton("Turn");
		turnButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		turnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(turnButton);
		
		JRadioButton waitButton = new JRadioButton("Wait");
		waitButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		waitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(waitButton);
		
		JButton addButton = new JButton("Add");
		addButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(addButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(moveDistanceButton);
		group.add(turnButton);
		group.add(waitButton);
		
		addButton.addActionListener(e -> {
			if(moveDistanceButton.isSelected()) {
				MoveDistance action = new MoveDistance(0, 0);
				handler.getFrame().getRobotPanel(teamNumber).addAction(action);
				handler.getRobotInfo(teamNumber).getPath().add(action);
			} else if(turnButton.isSelected()) {
				Turn turn = new Turn(0, 0);
				handler.getFrame().getRobotPanel(teamNumber).addAction(turn);
				handler.getRobotInfo(teamNumber).getPath().add(turn);
			} else if(waitButton.isSelected()) {
				WaitAction wait = new WaitAction(0);
				handler.getFrame().getRobotPanel(teamNumber).addAction(wait);
				handler.getRobotInfo(teamNumber).getPath().add(wait);
			} else 
				return;
			
			handler.getFrame().getRobotPanel(teamNumber).revalidate();
			frame.dispose();
		});
		
		frame.setVisible(true);
	}
}
