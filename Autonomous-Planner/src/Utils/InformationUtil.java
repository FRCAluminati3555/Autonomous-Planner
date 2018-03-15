package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.FreeMoving.Robot;
import Entity.FreeMoving.RobotInformationPassThrough;
import Entity.FreeMoving.Spawn;
import Entity.FreeMoving.Action.Action;
import Main.Handler;
import UI.Frame;
import UI.RobotPanel;
import UI.SpawnLocationEditPanel;

public class InformationUtil {
	public static void readInformation(Frame frame) throws FileNotFoundException {
		File dir = new File("res/Information");
		File[] directoryListing = dir.listFiles();
		
		if (directoryListing != null) {
			for(File child : directoryListing) {
				File[] content = child.listFiles();
				
				for(File infoFile : content) {
					if(infoFile.getName().substring(infoFile.getName().lastIndexOf(".")).equals(".info")) {
						String[] info = readInfoFile(infoFile);
						frame.addRobotPanel(info[1], info[2], Integer.parseInt(info[0]), info[3]);
					}
				}
			}
		}
	}
	
	private static String[] readInfoFile(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		
		String[] information = new String[4];
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < 3; i++) 
			information[i] = sc.nextLine();
		
		while(sc.hasNextLine()) 
			builder.append(sc.nextLine());
		
		sc.close();
		
		information[3] = builder.toString();
		return information;
	}
	
	public static void exportInformation(RobotPanel panel) {
		int teamNumber = panel.getTeamNumber();
		String teamName = panel.getTeamNameField().getText();
		String robotName = panel.getRobotNameField().getText();
		String description = panel.getDescriptionField().getText();
		
		File directory = new File("res/Information/" + teamNumber);
		
		if(!directory.exists()) {
			directory.mkdir();
		}
		
		File file = new File("res/Information/" + teamNumber + "/r" + teamNumber + ".info");
		
//		if(!file.exists()) {
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				return;
//			}
//		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		writer.println(teamNumber);
		writer.println(teamName);
		writer.println(robotName);
		writer.println(description);
		
		writer.close();
	}
	
//	public static void readPathRobot(Handler handler, Robot robot) {//int teamNumber, ArrayList<Vector2f> rr, ArrayList<Vector2f> ll, ArrayList<Vector2f> lr, ArrayList<Vector2f> rl) {
//		File file = new File("res/Information/" + robot.getTeamNumber() + "/r" + robot.getTeamNumber() + ".path");
//		RobotInformationPassThrough robotInfo = robot.getInfo();
//		TwoKeyMap paths = robotInfo.getPaths();
//		
//		Scanner sc;
//		try {
//			sc = new Scanner(file);
//		} catch (FileNotFoundException e) {
//			return;
//		}
//		
//		ArrayList<Action> currentList = null;
//		while(sc.hasNextLine()) {
//			String line = sc.nextLine();
//			
//			if(line.contains(",")) {//<Location, Ownership> -> Center, RL or Left, R
//				String[] parts = line.split(",");
//				for(int i = 0; i < parts.length; i++) {
//					parts[i] = parts[i].replaceAll(" ", "");
//				}
//				
//				currentList = paths.put(parts[0], parts[1]);
//			} else {
//				currentList.add(Action.parseAction(line));
//			}
//		}
//		
//		sc.close();
//	}
	
	public static void readPath(Handler handler, RobotPanel robotPanel, int teamNumber) {//int teamNumber, ArrayList<Vector2f> rr, ArrayList<Vector2f> ll, ArrayList<Vector2f> lr, ArrayList<Vector2f> rl) {
		File file = new File("res/Information/" + teamNumber + "/r" + teamNumber + ".path");
		
		RobotInformationPassThrough robotInfo = handler.getRobotInfo(teamNumber);
		TwoKeyMap paths = robotInfo.getPaths();
		
		Scanner sc;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			return;
		}

		boolean addedSpawn = false;
		boolean first = true;
		ArrayList<Action> currentList = null;
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
//			if(robotPanel != null && values[0].equals("Spawn")) {
			if(line.contains("Spawn")) {
				String[] values = line.split(" ");
				
				StringBuilder s = new StringBuilder();
				for(int i = 1; i < values.length - 1; i++) 
					s.append(values[i]);
				
				SpawnLocationEditPanel panel = robotPanel.addSpawnLocationPanel(new Spawn(s.toString(), Float.parseFloat(values[values.length - 1])));
				if(first) {
					panel.getRadioButton().doClick();
					
					first = false;
				}

				addedSpawn = true;
			} else if(line.contains(",")) {//<Location, Ownership> -> Center, RL or Left, R
				String[] parts = line.split(",");
				for(int i = 0; i < parts.length; i++) {
					parts[i] = parts[i].replaceAll(" ", "");
				}
				
				currentList = paths.put(parts[0], parts[1]);
			} else if(!line.isEmpty()){
				currentList.add(Action.parseAction(line));
			}
		}
		
		if(!addedSpawn) { //Make sure that there is at least one Spawn edit panel
			SpawnLocationEditPanel panel = robotPanel.addSpawnLocationPanel(new Spawn("Default", 0.0f));
			panel.getRadioButton().doClick();
		}
		
		sc.close();
	}
	
	public static void exportPath(Handler handler, Robot robot) {
		RobotInformationPassThrough info = robot.getInfo();
		
		File directory = new File("res/Information/" + robot.getTeamNumber());
		
		if(!directory.exists()) {
			try {
				directory.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		File file = new File("res/Information/" + robot.getTeamNumber() + "/r" + robot.getTeamNumber() + ".path");
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return;
			}
		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Spawn
		for(Spawn spawn : info.getSpawns()) {
			writer.println("Spawn " + spawn.getName() + " " + Util.tilesToMeters(spawn.getLocation().x)); //robot.getSpawn().getLocation().x + " " + robot.getSpawn().getLocation().y);
		}
		
		//Actual Path
		
		TwoKeyMap map = info.getPaths();
		for(String location : map.keySet()) {
			for(String ownership : map.get(location).keySet()) {
				writer.println(location + ", " + ownership);
				
				for(Action a : map.get(location, ownership)) {
					writer.println(a);
				}
			}
		}
		
//		writer.println("rr");
//		for(Vector2f vector : robot.getRr())
//			writer.println(vector.x + " " + vector.y);
//
//		writer.println("ll");
//		for(Vector2f vector : robot.getLl())
//			writer.println(vector.x + " " + vector.y);
//		
//		writer.println("rl");
//		for(Vector2f vector : robot.getRl())
//			writer.println(vector.x + " " + vector.y);
//		
//		writer.println("lr");
//		for(Vector2f vector : robot.getLr())
//			writer.println(vector.x + " " + vector.y);
		
		writer.close();
	}
}
