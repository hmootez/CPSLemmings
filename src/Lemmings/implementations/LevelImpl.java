package Lemmings.implementations;

import Lemmings.services.ILevel;
import Lemmings.services.Nature;

public class LevelImpl implements ILevel {

	private final int height;
	private final int width;
	private boolean editing;
	private Nature[][] grille;
	
	// a rajouter
	private final int xEntrance = 3;
	private final int yEntrance = 1;
	private final int xExit = 10;
	private final int yExit = 9;
	
	public LevelImpl(int width, int height) {
		
		init(width, height);
	}

	@Override
	public int height() {
		return height;
	}

	@Override
	public int width() {
		return width;
	}

	@Override
	public boolean editing() {
		return editing;
	}

	@Override
	public Nature nature(int x, int y) {
		//FIXME return DIRT, EMPTY ou METAL
		
		return grille[x][y];
	}

	@Override
	public void init(int x, int y) {
		
		
		this.width = x;
		this.height = y;
		
		this.grille = new Nature[width][height];
		this.editing = true;
		
			for (int j = 0; j < height; j++) {	
				for (int i = 0; i < width; i++) {
				
				if (i == 0 || j == height -1 || i == width -1 || j == 0) {
				//	System.out.println(j+"  init  "+i);
					this.grille[i][j] = Nature.METAL;
				}
				else  
					this.grille[i][j] = Nature.EMPTY;
			}
		}
	}

	@Override
	public void setNature(int x, int y, Nature n) {
		this.grille[x][y] = n;
	}

	@Override
	public void goPlay() {
		this.editing = false;
	}

	@Override
	public void remove(int x, int y) {
		this.grille[x][y] = Nature.EMPTY;
	}

	@Override
	public void build(int x, int y) {
		this.grille[x][y] = Nature.DIRT;
	}
	
	@Override
	public int getXEntrance() {
		
		return this.xEntrance;
	}

	@Override
	public int getYEntrance() {
		
		return this.yEntrance;
	}
	
	@Override
	public int getXExit() {
		
		return this.xExit;
	}

	@Override
	public int getYExit() {
		
		return this.yExit;
	}

}
