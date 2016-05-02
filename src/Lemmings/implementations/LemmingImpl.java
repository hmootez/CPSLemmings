package Lemmings.implementations;

import java.util.ArrayList;

import Lemmings.services.IGameEng;
import Lemmings.services.ILemming;
import Lemmings.tools.Comportement;
import Lemmings.tools.Nature;

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
	private int builderDelay;
	private int bomberDelay;
	private int basherDelay;

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
	public int getBuilderDelay() {
		return this.builderDelay;
	}
	
	@Override
	public int getBomberDelay() {
		return this.bomberDelay;
	}
	
	@Override
	public int getBasherDelay() {
		return this.basherDelay;
	}
	
	@Override
	public void init(ArrayList<Comportement> c,IGameEng core) {
		this.isDroitier = true;
		this.comportement = c;
		this.xLemming = core.getLevel().getXEntrance();
		this.yLemming = core.getLevel().getYEntrance();
		this.falling = 0;
		this.core = core;
		this.isDead = false;
		this.isSaved = false;
		this.dalles = 0;
		this.builderDelay = 0;
		this.bomberDelay = 5;
		this.basherDelay = 20;
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
				&& (core.isObstacle(getX(), getY() + 1) && falling_atPre >= 8)) {
			System.out.println("cas 8.a");
			isDead = true;
			System.out.println(isDead);
		// Case 8.b:
		} else if (getX() == xExit && getY() == yExit) {
			System.out.println("cas 8.b");
			isSaved = true;
		// Case 1:
	    } else if (getComportement().contains(Comportement.WALKER)
				&& !(core.isObstacle(getX(), getY() + 1))) {
	    	System.out.println("cas 1");
	    	comportement.remove(Comportement.WALKER);
	    	comportement.add(Comportement.FALLER);
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
			xLemming = getX_atPre - 1;
			yLemming = getY_atPre - 1;
			isDroitier = true;
			falling = 0;
			// Case 5.a:
		} else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == true
				&& core.isObstacle(getX(), getY() +1)
				&& !core.isObstacle(getX() + 1, getY()) 
				&& !core.isObstacle(getX() + 1, getY() - 1)) {
			
			System.out.println("cas 5.a");
			xLemming = getX_atPre + 1;
			yLemming = getY_atPre;
			isDroitier = true;
			falling = 0;
			// Case 5.b:
		} else if (getComportement().contains(Comportement.WALKER)
				&& isDroitier == false
				&& core.isObstacle(getX(), getY() +1)
				&& !core.isObstacle(getX() - 1, getY()) 
				&& !core.isObstacle(getX() - 1, getY() - 1)) {
			
			System.out.println("cas 5.b");
			xLemming = getX_atPre - 1;
			yLemming = getY_atPre;
			isDroitier = false;
			falling = 0;
			// Case 6.a:
		} else if (getComportement().contains(Comportement.FALLER) 
				&& !getComportement().contains(Comportement.CLIMBER)
				&& isDroitier == true
				&& !core.isObstacle(getX(), getY() + 1)) {
			
			System.out.println("cas 6.a ");
			//System.out.println("Type (" + getX() + ", " + getY() + ") -> " + core.getLevel().getNature(getX(), getY()));
			System.out.println("\\PRE");
			System.out.println("X -> " + getX() + " Y -> " + getY());
			System.out.println("falling -> " + falling);
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1;
			isDroitier = true;
			falling = falling_atPre + 1;
			System.out.println("\\POST");
			System.out.println("X -> " + xLemming + " Y -> " + yLemming);
			System.out.println("falling -> " + falling);
			// Case 6.b:
		} else if (getComportement().contains(Comportement.FALLER) 
				&& !getComportement().contains(Comportement.CLIMBER)
				&& isDroitier == false
				&& !core.isObstacle(getX(), getY() + 1)) {
			
			System.out.println("cas 6b");
			xLemming = getX_atPre;
			yLemming = getY_atPre +1;
			isDroitier = false;
			falling = falling_atPre + 1;
			// Case 7.a:
		} else if (getComportement().contains(Comportement.FALLER) && !getComportement().contains(Comportement.CLIMBER)
				&& isDroitier == true
				&& (core.isObstacle(getX(), getY() + 1) && falling_atPre < 8)) {
			System.out.println("cas 7.a");
			comportement.remove(Comportement.FALLER);
			comportement.add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			isDroitier = true;
			falling = 0;
			// Case 7.b:
		} else if (getComportement().contains(Comportement.FALLER) && !getComportement().contains(Comportement.CLIMBER)
				&& isDroitier == false
				&& (core.isObstacle(getX(), getY() + 1) && falling_atPre < 8)) {
			System.out.println("cas 7.b");
			comportement.remove(Comportement.FALLER);
			comportement.add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			isDroitier = false;
			falling = 0;
			// Case 9:
		} else if (getComportement().contains(Comportement.DIGGER) 
				&& getGameEng().getLevel().getNature(getX(), getY() + 1) == Nature.EMPTY) {
			System.out.println("cas 9");
			comportement.remove(Comportement.DIGGER);
			comportement.add(Comportement.FALLER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			falling = 0;
			// Case 10:
		} else if (getComportement().contains(Comportement.DIGGER)
				&& getGameEng().getLevel().getNature(getX(), getY() + 1) == Nature.METAL) {
			System.out.println("cas 10");
			comportement.remove(Comportement.DIGGER);
			comportement .add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre;
			falling = 0;
			// Case 11:
		} else if (getComportement().contains(Comportement.DIGGER)
				&& getGameEng().getLevel().getNature(getX(), getY() + 1) == Nature.DIRT) {
			System.out.println("cas 11");
			comportement.remove(Comportement.DIGGER);
			comportement.add(Comportement.WALKER);
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1;
			falling = 0;
			getGameEng().getLevel().setNature(getX(), getY() + 1, Nature.EMPTY);
			if (getGameEng().getLevel().getNature(getX() - 1, getY() + 1) == Nature.DIRT
			 || getGameEng().getLevel().getNature(getX() + 1, getY() + 1) == Nature.DIRT) {
				getGameEng().getLevel().setNature(getX(), getY() + 1, Nature.EMPTY);
			}
			// Case 12.a:
		} else if (isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
			    && core.isObstacle(getX()+1, getY()) && core.isObstacle(getX() + 1, getY() + 1)  
			    && getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY()+2) == Nature.EMPTY) {
			System.out.println("cas 12.a");
			isDroitier = true;
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1;
			// Case 12.b: 
		} else if (!isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
			    && core.isObstacle(getX()-1, getY()) && core.isObstacle(getX() - 1, getY() + 1)  
			    && getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY()+2) == Nature.EMPTY) {
			System.out.println("cas 12.b");
			isDroitier = false;
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1;
			// Case 13.a:
		} else if (isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
			    && core.isObstacle(getX() + 1, getY()) 
			    && getGameEng().getLevel().getNature(getX() + 1, getY() + 1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY() + 1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY() + 2) == Nature.EMPTY) {
			System.out.println("cas 13.a");
			isDroitier = true;
			xLemming = getX_atPre + 1;
			yLemming = getY_atPre + 1;
			// Case 13.b: 
		} else if (!isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
			    && core.isObstacle(getX() - 1, getY()) 
			    && getGameEng().getLevel().getNature(getX() - 1, getY() + 1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY() + 1) == Nature.EMPTY
			    && getGameEng().getLevel().getNature(getX(), getY() + 2) == Nature.EMPTY) {
			System.out.println("cas 13.b");
			isDroitier = false;
			xLemming = getX_atPre - 1;
			yLemming = getY_atPre + 1;
			// cas 1 builder
		} else if (getComportement().contains(Comportement.BUILDER)
				&& getGameEng().getLevel().getNature(getX() + 1, getY()) == Nature.EMPTY
				&& getGameEng().getLevel().getNature(getX() + 2, getY()) == Nature.EMPTY
				&& getGameEng().getLevel().getNature(getX() + 3, getY()) == Nature.EMPTY && getDalles() < 12
				&& getBuilderDelay() == 0) {
			System.out.println("cas 1 builder");
			this.builderDelay = 1;
			// cas 2 builder
		} else if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getBuilderDelay() == 1) {
			System.out.println("cas 2 builder");
			this.builderDelay = 2;
			// cas 3 builder
		} else if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getBuilderDelay() == 2) {
			System.out.println("cas 3 builder");
			this.builderDelay = 3;
			// cas 4 builder
		} else if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getBuilderDelay() == 3) {
			System.out.println("cas 4 builder");
			this.builderDelay = 0;
			this.dalles = this.dalles + 1;
			xLemming = getX_atPre + 2;
			yLemming = getY_atPre - 1;
			getGameEng().getLevel().setNature(getX_atPre+1, getY_atPre, Nature.DIRT);
			getGameEng().getLevel().setNature(getX_atPre+2, getY_atPre, Nature.DIRT);
			getGameEng().getLevel().setNature(getX_atPre+3, getY_atPre, Nature.DIRT);
			// cas 1 floater
		} else if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 0 && isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)) {
			System.out.println("cas 1 floater");
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1;
			this.isDroitier = true ;
			this.falling= falling_atPre;
			// cas 2 floater
		} else if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 1 && isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)) {
			System.out.println("cas 2 floater");
			xLemming = getX_atPre;
			yLemming = getY_atPre ;
			this.isDroitier = true ;
			this.falling= falling_atPre;
			// cas 3 floater
		} else if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 0 && !isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)) {
			System.out.println("cas 3 floater");
			xLemming = getX_atPre;
			yLemming = getY_atPre + 1 ;
			this.isDroitier = false ;
			this.falling= falling_atPre;
			// cas 4 floater
		} else if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 1 && !isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)) {
			System.out.println("cas 4 floater");
			xLemming = getX_atPre;
			yLemming = getY_atPre ;
			this.isDroitier = false  ;
			this.falling= falling_atPre;
			// cas stopper
		} else if (getComportement().contains(Comportement.STOPPER)) {
			System.out.println("cas 1 stopper");
			getGameEng().getLevel().setNature(getX_atPre, getY_atPre, Nature.DIRT);
			getGameEng().getLevel().setNature(getX_atPre, getY_atPre-1, Nature.DIRT);
			// cas 1 bomber
		} else if (getComportement().contains(Comportement.BOMBER) && getBomberDelay() > 0) {
			System.out.println("cas 1 bomber");
			bomberDelay--;
			// cas 2 bomber
		} else if (getComportement().contains(Comportement.BOMBER) && getBomberDelay() == 0) {
			System.out.println("cas 1 bomber");
			if (getGameEng().getLevel().getNature(getX(), getY() + 1) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX(), getY() + 1, Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX(), getY() - 1) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX(), getY() - 1, Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() + 1, getY() + 1) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() + 1, getY() + 1, Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() - 1, getY() + 1) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() - 1, getY() + 1, Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() + 1, getY()) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() + 1, getY(), Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() - 1, getY()) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() - 1, getY(), Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() + 2, getY()) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() + 2, getY(), Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() - 2, getY()) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() - 2, getY(), Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() + 1, getY() - 1) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() + 1, getY() - 1, Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() - 1, getY() - 1) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() - 1, getY() - 1, Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() + 1, getY() - 2) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() + 1, getY() - 2, Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() - 1, getY() - 2) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() - 1, getY() - 2, Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() + 1, getY() - 1) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() + 1, getY() - 1, Nature.DIRT);
			}
			if (getGameEng().getLevel().getNature(getX() - 1, getY() - 1) != Nature.DIRT) {
				getGameEng().getLevel().setNature(getX() - 1, getY() - 1, Nature.DIRT);
				// cas 1 basher
			} else if (getComportement().contains(Comportement.BASHER) && (getBasherDelay() == 0
					|| getGameEng().getLevel().getNature(getX() + 1, getY()) == Nature.METAL
					|| getGameEng().getLevel().getNature(getX(), getY() - 1) == Nature.EMPTY)) {
				System.out.println("cas 1 basher");
				getComportement().remove(Comportement.BASHER);
				getComportement().add(Comportement.WALKER);
				// cas 2 basher
			} else if (getComportement().contains(Comportement.BASHER) && isDroitier() 
					&& getBasherDelay() > 0 && getGameEng().isObstacle(getX(), getY() - 1)
					&& getGameEng().getLevel().getNature(getX() + 1, getY()) != Nature.METAL
					&& getGameEng().getLevel().getNature(getX() + 2, getY()) != Nature.METAL
					&& getGameEng().getLevel().getNature(getX() + 3, getY()) != Nature.METAL) {
				System.out.println("cas 2 basher");
				getGameEng().getLevel().setNature(getX() + 1, getY(), Nature.EMPTY);
				getGameEng().getLevel().setNature(getX() + 2, getY(), Nature.EMPTY);
				getGameEng().getLevel().setNature(getX() + 3, getY(), Nature.EMPTY);
				basherDelay--;
				xLemming = getX_atPre + 1;
				yLemming = getY_atPre;
			} // cas 3 baher
		} else if (getComportement().contains(Comportement.BASHER) && isDroitier() 
				&& getBasherDelay() > 0 && getGameEng().isObstacle(getX(), getY() - 1)
				&& getGameEng().getLevel().getNature(getX() - 1, getY()) != Nature.METAL
				&& getGameEng().getLevel().getNature(getX() - 2, getY()) != Nature.METAL
				&& getGameEng().getLevel().getNature(getX() - 3, getY()) != Nature.METAL) {
			System.out.println("cas 3 basher");
			getGameEng().getLevel().setNature(getX() - 1, getY(), Nature.EMPTY);
			getGameEng().getLevel().setNature(getX() - 2, getY(), Nature.EMPTY);
			getGameEng().getLevel().setNature(getX() - 3, getY(), Nature.EMPTY);
			basherDelay--;
			xLemming = getX_atPre - 1;
			yLemming = getY_atPre;
		}
	}	

	@Override
	public void changeComportement(Comportement c) {
		comportement.add(c); //FIXME .remove()?
	}
}