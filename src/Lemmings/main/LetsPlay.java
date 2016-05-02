package Lemmings.main;

import Lemmings.implementations.GameEngImpl;
import Lemmings.implementations.LevelImpl;
import Lemmings.services.ILevel;
import Lemmings.tools.Nature;

public class LetsPlay {

	static final int WIDTH = 13;
	static final int HEIGHT = 16;//11
	
	static final int X_ENTRANCE = 2;
	static final int Y_ENTRANCE = 1;
	static final int X_EXIT = 10;
	static final int Y_EXIT = 9;
	
	static final int SIZE_COLONY = 1;//5
	static final int SPAWN_SPEED = 2;
	
	public static void main(String[] args) {
		
		ILevel level = new LevelImpl(WIDTH, HEIGHT);
		
		if (level.isEditing()) {
			/*level.setNature(1, 4, Nature.METAL);
			level.setNature(2, 4, Nature.METAL);
			level.setNature(3, 4, Nature.METAL);
			level.setNature(4, 4, Nature.METAL);
			level.setNature(5, 4, Nature.METAL);
			level.setNature(6, 4, Nature.METAL);
			level.setNature(7, 4, Nature.METAL);
			level.setNature(8, 4, Nature.METAL);
			level.setNature(9, 4, Nature.METAL);
			level.setNature(10, 4, Nature.METAL);
			
			level.setNature(2, 7, Nature.METAL);
			level.setNature(3, 7, Nature.METAL);
			level.setNature(4, 7, Nature.METAL);
			level.setNature(5, 7, Nature.METAL);
			level.setNature(6, 7, Nature.METAL);
			level.setNature(7, 7, Nature.METAL);
			level.setNature(8, 7, Nature.METAL);
			level.setNature(9, 7, Nature.METAL);
			level.setNature(10, 7, Nature.METAL);
			level.setNature(11, 7, Nature.METAL);*/
		}
		
		level.goPlay(X_ENTRANCE, Y_ENTRANCE, X_EXIT, Y_EXIT);
		new GameEngImpl(SIZE_COLONY, SPAWN_SPEED, level);

//		
//		JoueurImpl j  = new JoueurImpl("adel", 8);
//
//		for(int i = 0; i< 10; i++) {
//		
//			
//			Scannthread t = new Scannthread() ;
//			t.start();
//			long start = System.currentTimeMillis();
//			long end = start + 5*1000; // 3 sec
//			System.out.println("p pour pause");
//			
//			while (t.keyPressed() || System.currentTimeMillis() < end) {
//				
//				
//				if (t.keyPressed() ){
//					
//					// TODO
//				j.affichagePause();
//					//System.out.println("Resume");
//					t.keyPressed = false ;
//				}
//			}
//			t.stop();
//			System.out.println("step suivante");
//		}	
//		System.out.println("END!");
	}
	
}

