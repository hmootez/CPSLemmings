package Lemmings.main;

import Lemmings.implementations.GameEngImpl;
import Lemmings.implementations.LevelImpl;
import Lemmings.services.IGameEng;
import Lemmings.services.ILevel;
import Lemmings.tools.Nature;

public class LetsPlay {

	public static void main(String[] args) {
	
		ILevel l = new LevelImpl(13, 11);
		l.setNature(1, 4, Nature.METAL);
		l.setNature(2, 4, Nature.METAL);
		l.setNature(3, 4, Nature.METAL);
		l.setNature(4, 4, Nature.METAL);
		l.setNature(5, 4, Nature.METAL);
		l.setNature(6, 4, Nature.METAL);
		l.setNature(7, 4, Nature.METAL);
		l.setNature(8, 4, Nature.METAL);
		l.setNature(9, 4, Nature.METAL);
		l.setNature(10, 4, Nature.METAL);
		
		l.setNature(2, 7, Nature.METAL);
		l.setNature(3, 7, Nature.METAL);
		l.setNature(4, 7, Nature.METAL);
		l.setNature(5, 7, Nature.METAL);
		l.setNature(6, 7, Nature.METAL);
		l.setNature(7, 7, Nature.METAL);
		l.setNature(8, 7, Nature.METAL);
		l.setNature(9, 7, Nature.METAL);
		l.setNature(10, 7, Nature.METAL);
		l.setNature(11, 7, Nature.METAL);
		
		IGameEng g = new GameEngImpl(5, 2, l);
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

