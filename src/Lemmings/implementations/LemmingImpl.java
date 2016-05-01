package Lemmings.implementations;

import java.util.ArrayList;

import Lemmings.services.Comportement;
import Lemmings.services.IGameEng;
import Lemmings.services.ILemming;
import Lemmings.services.Nature;

public class LemmingImpl implements ILemming {
	
	private boolean isDroitier;
	private ArrayList<Comportement> comportement;
	private int falling;
	private boolean isDead;
	private boolean isSaved;
	private IGameEng core;
	private int xLemming;
	private int yLemming;
	private int dalles;
	private int tourIncr;

	public LemmingImpl(ArrayList<Comportement> c,IGameEng core) {
		this.init(c,core);
	}
	
	@Override
	public boolean isDroitier() {
		return this.isDroitier ;
	}

	@Override
	public ArrayList<Comportement> getComportement() {
	
		return this.comportement;
	}

	@Override
	public int getX() {
		return this.xLemming;
	}

	@Override
	public int getY() {
		return this.yLemming;
	}
	
	@Override
	public int getFalling() {
		return this.falling;
	}

	@Override
	public IGameEng getGameEng(){
		return this.core;
	}

	@Override
	public boolean isDead() {
		return isDead;
	}
	
	@Override
	public boolean isSaved() {
		return this.isSaved;
	}

	@Override
	public int getDalles() {
		return this.dalles;
	}

	@Override
	public int getTourIncr() {
		return this.tourIncr;
	}
	
	@Override
	public void init(ArrayList<Comportement> c,IGameEng core) {
		this.comportement = c;
		this.isDroitier = true;
		this.core = core;
		this.xLemming = getGameEng().getLevel().getXEntrance();
		this.yLemming = getGameEng().getLevel().getYEntrance();
		this.isDead = false;
		this.falling = 0;
		this.dalles = 0;
		this.tourIncr = 0;
	}
	
	@Override
	public void step() {
		int getX_atPre = getX();
		int getY_atPre = getY();
		boolean isDroitier_atPre = isDroitier();
		int falling_atPre = getFalling();
		int xExit = getGameEng().getLevel().getXExit();
		int yExit = getGameEng().getLevel().getYExit();

		// Case 8.a:
		if (getComportement().contains(Comportement.FALLER) && !getComportement().contains(Comportement.CLIMBER)
				&& (core.isObstacle(getX(), getY() +1) && falling_atPre == 8)) {
			System.out.println("cas 8.a");
			isDead = true;
		// Case 8.b:
		} else if (getX() == xExit && getY() == yExit) {
			System.out.println("cas 8.b");
			isSaved = true;
		// Case 1:
	    } else if (getComportement().contains(Comportement.WALKER)
				&& !(core.isObstacle(getX(), getY() + 1))) {
	    	System.out.println("cas 1");
			comportement .add(Comportement.FALLER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			isDroitier = isDroitier_atPre;
			falling = 0;
			// Case 2.a:
		} else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == true
				&& (core.isObstacle(getX(), getY() + 1) && (core.isObstacle(
						getX() + 1, getY() - 1)))) {
			System.out.println("cas 2.a");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			isDroitier = false;
			falling = 0;
		}// Case 2.b:
		else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == false
				&& (core.isObstacle(getX(), getY() + 1) && (core.isObstacle(
						getX() - 1, getY() - 1)))) {
			System.out.println("cas 2.b");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			isDroitier = true;
			falling = 0;
			// Case 3.a:
		} else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == true
				&& (core.isObstacle(getX(), getY() +1)
						&& (core.isObstacle(getX() + 1, getY())) && (core
							.isObstacle(getX() + 1, getY() - 2)))) {
			System.out.println("cas 3.a");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			isDroitier = false;
			falling = 0;
			// Case 3.b:
		} else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == false
				&& (core.isObstacle(getX(), getY() + 1)
						&& (core.isObstacle(getX() - 1, getY())) && (core
							.isObstacle(getX() - 1, getY() - 2)))) {
			System.out.println("cas 3.b");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			isDroitier = true;
			falling = 0;
			// Case 4.a:
		} else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == true
				&& (core.isObstacle(getX(), getY() + 1)
						&& (core.isObstacle(getX() + 1, getY()))
						&& !(core.isObstacle(getX() + 1, getY() -1)) && !(core
							.isObstacle(getX() + 1, getY() - 2)))) {
			System.out.println("cas 4.a");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre + 1;
			yLemming = getY_atPre - 1;
			isDroitier = true;
			falling = 0;
			// Case 4.b:
		} else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == false
				&& (core.isObstacle(getX(), getY() + 1)
						&& (core.isObstacle(getX() - 1, getY()))
						&& !(core.isObstacle(getX() - 1, getY() -1)) && !(core
							.isObstacle(getX() - 1, getY() - 2)))) {
			System.out.println("cas 4.b");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre - 1;
			yLemming = getY_atPre - 1;
			isDroitier = true;
			falling = 0;
			// Case 5.a:
		} else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == true
				&& (core.isObstacle(getX(), getY() +1)
						&& !(core.isObstacle(getX() + 1, getY())) && !(core.isObstacle(getX() + 1, getY() -1)))) {
			System.out.println("cas 5.a");

			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre + 1;
			yLemming = getY_atPre;
			isDroitier = true;
			falling = 0;
			// Case 5.b:
		} else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == false
				&& (core.isObstacle(getX(), getY() +1)
						&& !(core.isObstacle(getX() - 1, getY())) && !(core
							.isObstacle(getX() - 1, getY() - 1)))) {
			System.out.println("cas 5.b");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre - 1;
			yLemming = getY_atPre;
			isDroitier = false;
			falling = 0;
			// Case 6.a:
		} else if (getComportement().contains(Comportement.FALLER) && !getComportement().contains(Comportement.CLIMBER)
				&& isDroitier == true
				&& !(core.isObstacle(getX(), getY() + 1) && falling_atPre < 8)) {
			
			System.out.println("cas 6.a "+getX()+" "+(getY()+1));
			System.out.println("Type (" + getX() + ", " + getY() + ") -> " + core.getLevel().getNature(getX(), getY()));
			comportement .add(Comportement.FALLER);
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1;
			isDroitier = true;
			falling = falling_atPre + 1;
			// Case 6.b:
		} else if (getComportement().contains(Comportement.FALLER) && !getComportement().contains(Comportement.CLIMBER)
				&& isDroitier == false
				&& !(core.isObstacle(getX(), getY() + 1) && falling_atPre < 8)) {
			System.out.println("cas 6b");
			comportement .add(Comportement.FALLER);
			xLemming = getX_atPre;
			yLemming = getY_atPre +1;
			isDroitier = false;
			falling = falling_atPre + 1;
			// Case 7.a:
		} else if (getComportement().contains(Comportement.FALLER) && !getComportement().contains(Comportement.CLIMBER)
				&& isDroitier == true
				&& (core.isObstacle(getX(), getY() + 1) && falling_atPre < 8)) {
			System.out.println("cas 7.a");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			isDroitier = true;
			falling = 0;
			// Case 7.b:
		} else if (getComportement().contains(Comportement.FALLER) && !getComportement().contains(Comportement.CLIMBER)
				&& isDroitier == false
				&& (core.isObstacle(getX(), getY() + 1) && falling_atPre < 8)) {
			System.out.println("cas 7.b");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			isDroitier = false;
			falling = 0;
			// Case 9:
		} else if (getComportement().contains(Comportement.DIGGER) 
				&& getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.EMPTY) {
			System.out.println("cas 9");
			comportement .add(Comportement.FALLER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			falling = 0;
			// Case 10:
		} else if (getComportement().contains(Comportement.DIGGER)
				&& getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.METAL) {
			System.out.println("cas 10");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			falling = 0;
			// Case 11:
		} else if (getComportement().contains(Comportement.DIGGER)
				&& getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.DIRT) {
			System.out.println("cas 11");
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1;
			falling = 0;
			getGameEng().getLevel().setNature(getX(), getY()+1, Nature.EMPTY);
			if (getGameEng().getLevel().getNature(getX()-1, getY()+1) == Nature.DIRT
			 || getGameEng().getLevel().getNature(getX()+1, getY()+1) == Nature.DIRT) {
				getGameEng().getLevel().setNature(getX(), getY()+1, Nature.EMPTY);
			}
			// Case 12.a:
		} else if (isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
			    && core.isObstacle(getX()+1, getY()) && core.isObstacle(getX()+1, getY()+1)  
			    && getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY()+2) == Nature.EMPTY) {
			System.out.println("cas 12.a");
			comportement .add(Comportement.CLIMBER);
			isDroitier = true;
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1;
			// Case 12.b: 
		} else if (!isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
			    && core.isObstacle(getX()-1, getY()) && core.isObstacle(getX()-1, getY()+1)  
			    && getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY()+2) == Nature.EMPTY) {
			System.out.println("cas 13.a");
			comportement .add(Comportement.CLIMBER);
			isDroitier = false;
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1;
			// Case 13.a:
		} else if (isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
			    && core.isObstacle(getX()+1, getY()) 
			    && getGameEng().getLevel().getNature(getX()+1, getY()+1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY()+2) == Nature.EMPTY) {
			System.out.println("cas 13.a");
			comportement .add(Comportement.CLIMBER);
			isDroitier = true;
			xLemming = getX_atPre + 1;
			yLemming = getY_atPre + 1;
			// Case 13.b: 
		} else if (!isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
			    && core.isObstacle(getX()-1, getY()) 
			    && getGameEng().getLevel().getNature(getX()-1, getY()+1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY()+2) == Nature.EMPTY) {
			System.out.println("cas 13.b");
			comportement .add(Comportement.CLIMBER);
			isDroitier = false;
			xLemming = getX_atPre - 1;
			yLemming = getY_atPre + 1;
			// cas 1 builder
		} else if (getComportement().contains(Comportement.BUILDER)
				&& getGameEng().getLevel().getNature(getX() + 1, getY()) == Nature.EMPTY
				&& getGameEng().getLevel().getNature(getX() + 2, getY()) == Nature.EMPTY
				&& getGameEng().getLevel().getNature(getX() + 3, getY()) == Nature.EMPTY && getDalles() < 12
				&& getTourIncr() == 0) {
			this.tourIncr = 1;
			// cas 2 builder
		} else if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getTourIncr() == 1) {
			this.tourIncr = 2;
			this.comportement .add(Comportement.BUILDER);
			// cas 3 builder
		} else if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getTourIncr() == 2) {
			this.tourIncr = 3;
			this.comportement .add(Comportement.BUILDER);
			// cas 4 builder
		} else if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getTourIncr() == 3) {
			this.comportement .add(Comportement.BUILDER);
			this.tourIncr = 0 ;
			this.dalles = this.dalles + 1;
			xLemming = getX_atPre + 2;
			yLemming = getY_atPre - 1;
			getGameEng().getLevel().setNature(getX_atPre+1, getY_atPre, Nature.DIRT);
			getGameEng().getLevel().setNature(getX_atPre+2, getY_atPre, Nature.DIRT);
			getGameEng().getLevel().setNature(getX_atPre+3, getY_atPre, Nature.DIRT);
			// cas 1 floater
		} else if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 0 && isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)){
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1 ;
			this.isDroitier = true ;
			this.falling= falling_atPre;
			// cas 2 floater
		} else if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 1 && isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)){
			xLemming = getX_atPre;
			yLemming = getY_atPre ;
			this.isDroitier = true ;
			this.falling= falling_atPre;
			// cas 3 floater
		} else if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 0 && !isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)){
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1 ;
			this.isDroitier = false ;
			this.falling= falling_atPre;
			// cas 4 floater
		} else if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 1 && !isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)){
			xLemming = getX_atPre;
			yLemming = getY_atPre ;
			this.isDroitier = false  ;
			this.falling= falling_atPre;
			// cas 1 stopper
		} else if (getComportement().contains(Comportement.STOPPER)) {
			getGameEng().getLevel().setNature(getX_atPre, getY_atPre, Nature.DIRT);
			getGameEng().getLevel().setNature(getX_atPre, getY_atPre-1, Nature.DIRT);
		}
	}	

	@Override
	public void changeComportement(Comportement c) {
		
	}
}
