package Lemmings.preset;

import org.json.JSONObject;

import Lemmings.services.ILevel;

public class LevelPreset extends AbstractEntityPreset {
	
	private static int height = 0;
	private static int width = 0;
	private static boolean isInitialized = false;
	
	public static ILevel getLevel() {
		if (!isInitialized) {
			parseLevel();
		}
		return factory.makeLevel(width, height);
	}
	
	public static void parseLevel() {
		configure();
		JSONObject rootObject = new JSONObject(configuration);
		JSONObject MineObject = rootObject.getJSONObject("Level");
		height = MineObject.getInt("height");
		width = MineObject.getInt("width");
		isInitialized = true;
	}
}
