package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Entity.FreeMoving.Action.Action;

public class TwoKeyMap {
	private HashMap<String, HashMap<String, ArrayList<Action>>> map;
	
	public TwoKeyMap() {
		map = new HashMap<>();
	}
	
	public ArrayList<Action> get(String location, String ownership) {
		HashMap<String, ArrayList<Action>> innerMap = map.get(location);
		
		if(innerMap == null)
			return null;
		
		return innerMap.get(ownership);
	}
	
	public void put(String location, String ownership, ArrayList<Action> actions) {
		HashMap<String, ArrayList<Action>> innerMap = map.get(location);
		
		if(innerMap == null)
			map.put(location, new HashMap<String, ArrayList<Action>>());
		
		map.get(location).put(ownership, actions);
	}

	public ArrayList<Action> put(String location, String ownership) { 
		put(location, ownership, new ArrayList<>()); 
		return get(location, ownership); 
	}
	
	public HashMap<String, ArrayList<Action>> get(String location) {
		return map.get(location);
	}
	
	public Set<String> keySet() {
		return map.keySet();
	}
}
