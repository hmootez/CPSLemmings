package Lemmings.main;

import Lemmings.preset.LevelPreset;
import Lemmings.services.ILevel;

public class TestPreset {
	private static ILevel level;
	
	private static void testInit() {
		level = LevelPreset.getLevel();
	}
	
	private static void testLevel(ILevel l) {
		System.out.println("Height : " + l.getHeight());
		System.out.println("Width : " + l.getWidth());
	}
	
	public static void main(String[] args) {
		testInit();
		System.out.println("-------------Test Level 1-------------");
		testLevel(level);
	}
}
