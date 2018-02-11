package UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Entity.FreeMoving.Robot;
import Main.Handler;
import World.World;

public class LoadIntoWorldPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	
	private Handler handler;
	private int teamNumber;
	
	public LoadIntoWorldPanel(Handler handler, int teamNumber) {
		this.handler = handler;
		this.teamNumber = teamNumber;
		
		Robot[] robots = handler.getWorld().getRobots();
		
		setLayout(new BorderLayout(0, 0));
		
		World world = handler.getWorld();
		
		JLabel lblChooseARobot = new JLabel("Choose a Robot to Override");
		lblChooseARobot.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseARobot.setFont(new Font("Calibri", Font.PLAIN, 26));
		add(lblChooseARobot, BorderLayout.NORTH);
		
		JButton btnRobot1 = new JButton(robots[1] == null ? "Un-Filled" : Integer.toString(robots[1].getTeamNumber()));
		btnRobot1.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(btnRobot1, BorderLayout.CENTER);
		
		btnRobot1.addActionListener(e -> {
			replaceRobot(1);
//			world.replaceRobot(1, teamNumber, world.getRobots()[1] == null ? 0 : world.getRobots()[1].getPosition2D().x);
//			world.getRobots()[1] = new Robot(handler, teamNumber, world.getRobots()[1] == null ? 0 : world.getRobots()[1].getPosition2D().x);
			frame.dispose();
		});
		
		JButton btnRobot0 = new JButton(robots[0] == null ? "Un-Filled" : Integer.toString(robots[0].getTeamNumber()));
		btnRobot0.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(btnRobot0, BorderLayout.WEST);
		
		btnRobot0.addActionListener(e -> {
			replaceRobot(0);
//			world.replaceRobot(2, teamNumber, world.getRobots()[0] == null ? 0 : world.getRobots()[0].getPosition2D().x);
//			world.getRobots()[0] = new Robot(handler, teamNumber, world.getRobots()[0] == null ? 0 : world.getRobots()[0].getPosition2D().x);
			frame.dispose();
		});
		
		JButton btnRobot2 = new JButton(robots[2] == null ? "Un-Filled" : Integer.toString(robots[2].getTeamNumber()));
		btnRobot2.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(btnRobot2, BorderLayout.EAST);
		
		btnRobot2.addActionListener(e -> {
			replaceRobot(2);
//			world.getRobots()[2] = new Robot(handler, teamNumber, world.getRobots()[2] == null ? 0 : world.getRobots()[2].getPosition2D().x);
			frame.dispose();
		});
		
		frame = new JFrame("Choose Robot!");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(handler.getFrame());
		frame.add(this);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}
	
	private void replaceRobot(int index) {
		handler.getWorld().replaceRobot(index, teamNumber, handler.getWorld().getRobots()[index] == null ? 0 : handler.getWorld().getRobots()[index].getPosition2D().x - (handler.getWorld().getRobots()[index].getWidth() / 2.0f));
	}
}
