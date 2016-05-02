package Lemmings.ihm;

import java.io.IOException;
import java.util.ArrayList;

import Lemmings.services.ILemming;
import Lemmings.services.ILevel;
import Lemmings.tools.Nature;

public class Ihm {

	public void updatedraw(ILevel l, ArrayList<ILemming> lems) {
		
		String[][] res = new String[l.getWidth()][l.getHeight()];
		
		// 1- Fill
		for (int j = 0; j < l.getHeight(); j++) {
			for (int i = 0; i < l.getWidth(); i++) {
				if ((l.getNature(i, j) == Nature.METAL)) {
					if (i == l.getWidth()-1) {
						res[i][j]= "M\n";
					} else {
						res[i][j] = "M";
					}
				}
				if (l.getNature(i, j) == Nature.DIRT) {
					res[i][j] = "D";
				}
				if (l.getNature(i, j) == Nature.EMPTY) {
					if (!((l.getXExit() == i && l.getYExit() == j) || (l
								.getXEntrance() == i && l.getYEntrance() == j))) { 
						res[i][j]=" ";
					} else if (i == l.getXEntrance() && j == l.getYEntrance()) {
						res[i][j]="E";
					} else if (i == l.getXExit() && j == l.getYExit()) {
						res[i][j] ="S";
					}
				}
				for (ILemming lem : lems) {
					if (lem.getX() == i&& lem.getY() == j) {
						if (lem.isDroitier()) {
							res[i][j] ="R";
						} else {
							res[i][j] ="L";
						}
					}
				}
			}
		}
		
	    try {
	    	// 2- Clear
//	    	for(int i = 0; i < 25; i++) {
//	    	    System.out.println("");
//	    	}
	    	
	    	// 3- Draw
			for (int j = 0; j < l.getHeight(); j++) { 
				for (int i = 0; i < l.getWidth(); i++) {		
					System.out.print(res[i][j]);
				}
			}
			
			// 4- Wait
	    	Thread.sleep(000); // one second.
	        
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	}
}

