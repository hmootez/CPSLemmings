package Lemmings.implementations;

import Lemmings.services.ILevel;
import Lemmings.tools.Nature;

public class LevelImpl implements ILevel {

	private int height;
	private int width;
	private boolean editing;
	private Nature[][] grille;
	private int xEntrance;
	private int yEntrance;
	private int xExit;
	private int yExit;
	
	public LevelImpl(int width, int height) {
		init(width, height);
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public boolean isEditing() {
		return editing;
	}

	@Override
	public Nature getNature(int x, int y) {
		return grille[x][y];
	}

	@Override
	public void init(int x, int y) {
		
		this.width = x;
		this.height = y;
		
		this.grille = new Nature[width][height];
		this.editing = true;
		
		this.xEntrance = -1;
		this.yEntrance = -1;
		this.xExit = -1;
		this.yExit = -1;
		
		for (int j = 0; j < height; j++) {	
			for (int i = 0; i < width; i++) {
				if (i == 0 || j == height -1 || i == width -1 || j == 0) {
					this.grille[i][j] = Nature.METAL;
				} else {  
					this.grille[i][j] = Nature.EMPTY;
				}
			}
		}
	}

	@Override
	public void setNature(int x, int y, Nature n) {
		this.grille[x][y] = n;
	}

	@Override
	public void goPlay(int xEntrance, int yEntrance, int xExit, int yExit) {
		this.editing = false;
		this.xEntrance = xEntrance;
		this.yEntrance = yEntrance;
		this.xExit = xExit;
		this.yExit = yExit;
	}
	
	@Override
	public void goEditing() {	
		this.editing = true;
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
