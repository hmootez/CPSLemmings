package Lemmings.contract;

import java.util.ArrayList;

import Lemmings.decorators.LemmingDecorator;
import Lemmings.error.ContractError;
import Lemmings.error.ErrorHandler;
import Lemmings.error.InvariantError;
import Lemmings.error.PostConditionError;
import Lemmings.error.PreConditionError;
import Lemmings.services.IGameEng;
import Lemmings.services.ILemming;
import Lemmings.tools.Comportement;
import Lemmings.tools.Nature;

public class LemmingContract extends LemmingDecorator {

	public LemmingContract(ILemming delegate) {
		super(delegate);
		
	}

	/**
	 * Init --------------------------------------------------------------------
	 */
	@Override
	public void init(ArrayList<Comportement> c , IGameEng core) {
		try {
			checkInitPreConditions(c);
			checkInvariants();
			super.init(c,core);	
			checkInvariants();
			checkInitPostConditions(c,core);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}

	private void checkInitPreConditions(ArrayList<Comportement> c) {
		if (!(c.size() > 0))
			 throw new PreConditionError("Init_Lemming : c.size() <= 0");
	}
	
	private void checkInitPostConditions(ArrayList<Comportement> c,IGameEng core) {
		if (!(isDroitier()))
				 throw new PostConditionError("Init_Lemming : isDroitier() == false");
				
		if (!(getComportement() == c))
			throw new PostConditionError("Init_Lemming : getComportement() != c");
		
		if (!(getX() == core.getLevel().getXEntrance()))
			throw new PostConditionError("Init_Lemming : getX() != core.getLevel().getXEntrance()");
			
		if (!(getY() == core.getLevel().getYEntrance()))
			throw new PostConditionError("Init_Lemming : getY() != core.getLevel().getYEntrance()");
			
		if (!(getFalling() == 0))
			throw new PostConditionError("Init_Lemming : getFalling() != 0");
		
		if (!(isDead() == false))
			throw new PostConditionError("Init_Lemming : isDead() == true");
		
		if (!(isSaved() == false))
			throw new PostConditionError("Init_Lemming : isSaved() == true");
		
		if ( ! (getGameEng() == core))
			throw new PostConditionError("Init_Lemming : (getGameEng()!= core)");
		
		if (!(getDalles() == 0))
			 throw new PostConditionError("Init_Lemming : getDalles() != 0");
		
		if (!(getBuilderDelay() == 0))
			 throw new PostConditionError("Init_Lemming : getBuilderDelay() != 0");
		
		if (!(getBomberDelay() == 5))
			 throw new PostConditionError("Init_Lemming : getBomberDelay() != 5");
		
		if (!(getBasherDelay() == 20))
			 throw new PostConditionError("Init_Lemming : getBasherDelay() != 20");
	}

	private void checkInvariants() {
		if (!(getFalling() >= 0 && getFalling() <= getGameEng().getLevel().getHeight() - 2))
			throw new InvariantError( "Ivariant_Lemming : !getFalling() >= 0 && getFalling() <= getGameEng().getLevel().getHeight()-2)");
		
		if (!(getX() > 0 && getX() <= getGameEng().getLevel().getWidth() - 2))
			throw new InvariantError(
					"Ivariant_Lemming : !(getX()> 0 && getX() <=  getGameEng().getLevel().getWidth()-2");

		if (!(getY() > 0 && getY() <= getGameEng().getLevel().getHeight() - 2))
			throw new InvariantError(
					"Ivariant_Lemming : !(getY()> 0 && getY() <= getGameEng().getLevel().getHeight()-2");

		if (isDead())
			if (!(getFalling() == 8 && !getComportement().contains(Comportement.CLIMBER)))
				throw new InvariantError("Ivariant_Lemming : !(getFalling() = 8 && getComportement() != Comportement.CLIMBER)");

		if (!isDead())
			if (!(getX() == getGameEng().getLevel().getXExit() && getY() == getGameEng().getLevel().getYExit()))
				throw new InvariantError( "Ivariant_Lemming :! isDead() = false <=> getFalling() < 8  ");
		
		if (isSaved())
			if (!(getFalling() == 8 && !getComportement().contains(Comportement.CLIMBER)))
				throw new InvariantError("Ivariant_Lemming : !(true <==> getX() == getXExit() && getY() == getYExit())");
		
		if (!(getBuilderDelay() <= 3))
			throw new InvariantError("Ivariant_Lemming : getBuilderDelay() > 3");
		
		if (!(getBomberDelay() <= 5))
			throw new InvariantError("Ivariant_Lemming : getBomberDelay() > 5");
		
		if (!(getBasherDelay() <= 20))
			throw new InvariantError("Ivariant_Lemming : getBasherDelay() > 20");
	}
	
	
	/**
	 * step --------------------------------------------------------------------
	 */
	@Override
	public void step() {
		try {
			boolean isDroitier_atPre = isDroitier();
			int getX_atPre = getX();
			int getY_atPre = getY();
			int getFalling_atPre = getFalling();
			int getDalles_atPre = getDalles();
			int getBomberDelay_atPre = getBomberDelay();
			int getBasherDelay_atPre = getBasherDelay();
			
			checkInvariants();
			super.step();	
			checkInvariants();
			checkStepPostConditions(getX_atPre, getY_atPre, isDroitier_atPre,
					getFalling_atPre, getDalles_atPre, getBomberDelay_atPre, getBasherDelay_atPre);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}

	private void checkStepPostConditions(int getX_atPre, int getY_atPre, 
			boolean isDroitier_atPre, int getFalling_atPre, int getDalles_atPre, 
			int getBomberDelay_atPre, int getBasherDelay_atPre) {
		if (getComportement().contains(Comportement.WALKER)) {
			// cas n°1
			if (!(getGameEng().isObstacle(getX_atPre,getY_atPre+1))) {
				if (!(getComportement().contains(Comportement.FALLER) && getX() == getX_atPre && getY() == getY_atPre && isDroitier() == isDroitier_atPre && getFalling() == 0 )) {
					throw new PostConditionError("step : cas n°1");
				}
			}
			// cas n°2.a
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && getGameEng().isObstacle(getX_atPre+1,getY_atPre-1) && isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_atPre && getY() == getY_atPre && isDroitier() == false && getFalling() == 0 )) {
					throw new PostConditionError("step : cas n°2.a");
				}
			}
			// cas n°2.b
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && getGameEng().isObstacle(getX_atPre-1,getY_atPre-1) && !isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_atPre && getY() == getY_atPre && isDroitier() == true && getFalling() == 0 )) {
					throw new PostConditionError("step : cas n°2.b");
				}
			}
			// cas n°3.a
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && getGameEng().isObstacle(getX_atPre+1,getY_atPre) && getGameEng().isObstacle(getX_atPre+1,getY_atPre-2) && isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_atPre && getY() == getY_atPre && isDroitier() == false && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°3.a");
				}
			}
			// cas n°3.b
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && getGameEng().isObstacle(getX_atPre-1,getY_atPre) && getGameEng().isObstacle(getX_atPre-1,getY_atPre-2) && !isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_atPre && getY() == getY_atPre && isDroitier() == true && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°3.b");
				}
			}
			// cas n°4.a
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && getGameEng().isObstacle(getX_atPre+1,getY_atPre) && !getGameEng().isObstacle(getX_atPre+1,getY_atPre-1) && !getGameEng().isObstacle(getX_atPre+1,getY_atPre-2) && isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_atPre+1 && getY() == getY_atPre-1 && isDroitier() == true && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°4.a");
				}
			}
			// cas n°4.b
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && getGameEng().isObstacle(getX_atPre-1,getY_atPre) && !getGameEng().isObstacle(getX_atPre-1,getY_atPre-1) && !getGameEng().isObstacle(getX_atPre-1,getY_atPre-2) && !isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_atPre-1 && getY() == getY_atPre-1 && isDroitier() == false && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°4.b");
				}
			}
			// cas n°5.a
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && !getGameEng().isObstacle(getX_atPre+1,getY_atPre) && !getGameEng().isObstacle(getX_atPre+1,getY_atPre-1) && isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_atPre+1 && getY() == getY_atPre && isDroitier() == true && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°5.a");
				}
			}
			// cas n°5.b
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && !getGameEng().isObstacle(getX_atPre-1,getY_atPre) && !getGameEng().isObstacle(getX_atPre-1,getY_atPre-1) && !isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_atPre-1 && getY() == getY_atPre && isDroitier() == false && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°5.b");
				}
			}
		}
		if (getComportement().contains(Comportement.FALLER) && !getComportement().contains(Comportement.CLIMBER)) {
			// cas n°6.a
			if (!getGameEng().isObstacle(getX_atPre,getY_atPre+1) && isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.FALLER) && getX() == getX_atPre && getY() == getY_atPre+1 && isDroitier() == true && getFalling() == getFalling_atPre+1)) {
					throw new PostConditionError("step : cas n°6.a");
				}
			}
			// cas n°6.b
			if (!getGameEng().isObstacle(getX_atPre,getY_atPre+1) && !isDroitier_atPre) {
				if (!(getComportement().contains(Comportement.FALLER) && getX() == getX_atPre && getY() == getY_atPre+1 && isDroitier() == false && getFalling() == getFalling_atPre+1)) {
					throw new PostConditionError("step : cas n°6.b");
				}
			}
			// cas n°7.a
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && getFalling() < 8 && isDroitier_atPre) {
				if (!(!getComportement().contains(Comportement.FALLER) && getComportement().contains(Comportement.WALKER) && getX() == getX_atPre && getY() == getY_atPre && isDroitier() == true && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°7.a");
				}
			}
			// cas n°7.b
			if (getGameEng().isObstacle(getX_atPre,getY_atPre+1) && getFalling() < 8 && !isDroitier_atPre) {
				if (!(!getComportement().contains(Comportement.FALLER) && getComportement().contains(Comportement.WALKER) && getX() == getX_atPre && getY() == getY_atPre && isDroitier() == false && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°7.b");
				}
			}
			// cas n°8.a
			if (!getComportement().contains(Comportement.CLIMBER) && getGameEng().isObstacle(getX_atPre,getY_atPre+1) && getFalling() >= 8) {
				if (!isDead()) {
					throw new PostConditionError("step : cas n°8.a");
				}
			}
			// cas n°8.b
			if (getX() == getGameEng().getLevel().getXExit() && getY() == getGameEng().getLevel().getYExit()) {
				if (!isSaved()) {
					throw new PostConditionError("step : cas n°8.b");
				}
			}
			if (getComportement().contains(Comportement.DIGGER)) {
				// cas n°9
				if (getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.EMPTY) {
					if (!(getComportement().contains(Comportement.FALLER) && getX() == getX_atPre && getY() == getY_atPre && getFalling() == 0)) {
						throw new PostConditionError("step : cas n°9");
					}
				}
				// cas n°10
				if (getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.METAL) {
					if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_atPre && getY() == getY_atPre && getFalling() == 0)) {
						throw new PostConditionError("step : cas n°10");
					}
				}
				// cas n°11
				if (getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.DIRT) {
					if (!(getComportement().contains(Comportement.DIGGER) && getX() == getX_atPre && getY() == getY_atPre+1 && getFalling() == 0 
							&& getGameEng().getLevel().getNature(getX_atPre, getY_atPre+1) == Nature.EMPTY
							&& getGameEng().getLevel().getNature(getX_atPre-1, getY_atPre+1) != Nature.DIRT 
						    && getGameEng().getLevel().getNature(getX_atPre+1, getY_atPre+1) != Nature.DIRT)) {
						
						throw new PostConditionError("step : cas n°11");
					}
				}
			}
			if (getComportement().contains(Comportement.CLIMBER)) {
				// cas n°12.a
				if (isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
				 && getGameEng().isObstacle(getX_atPre+1,getY_atPre) 
				 && getGameEng().isObstacle(getX_atPre+1,getY_atPre+1) 
				 && getGameEng().getLevel().getNature(getX_atPre, getY_atPre+1) == Nature.EMPTY
				 && getGameEng().getLevel().getNature(getX_atPre, getY_atPre+2) == Nature.EMPTY) {
					if (!(isDroitier() == true && getComportement().contains(Comportement.CLIMBER) && getX() == getX_atPre && getY() == getY_atPre+1)) {
						throw new PostConditionError("step : cas n°12.a");
					}
				}
				// cas n°12.b
				if (!isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
				 && getGameEng().isObstacle(getX_atPre-1,getY_atPre) 
				 && getGameEng().isObstacle(getX_atPre-1,getY_atPre+1) 
				 && getGameEng().getLevel().getNature(getX_atPre, getY_atPre+1) == Nature.EMPTY
				 && getGameEng().getLevel().getNature(getX_atPre, getY_atPre+2) == Nature.EMPTY) {
					if (!(isDroitier() == false && getComportement().contains(Comportement.CLIMBER) && getX() == getX_atPre && getY() == getY_atPre+1)) {
						throw new PostConditionError("step : cas n°12.b");
					}
				}
				// cas n°13.a
				if (isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
				 && getGameEng().isObstacle(getX_atPre+1,getY_atPre) 
				 && getGameEng().getLevel().getNature(getX_atPre+1, getY_atPre+1) == Nature.EMPTY
				 && getGameEng().getLevel().getNature(getX_atPre, getY_atPre+1) == Nature.EMPTY
				 && getGameEng().getLevel().getNature(getX_atPre, getY_atPre+2) == Nature.EMPTY) {
					if (!(isDroitier() == true && getComportement().contains(Comportement.CLIMBER) && getX() == getX_atPre+1 && getY() == getY_atPre+1)) {
						throw new PostConditionError("step : cas n°13.a");
					}
				}
				// cas n°13.b
				if (!isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) 
				 && getGameEng().isObstacle(getX_atPre-1,getY_atPre) 
				 && getGameEng().getLevel().getNature(getX_atPre-1, getY_atPre+1) == Nature.EMPTY
				 && getGameEng().getLevel().getNature(getX_atPre, getY_atPre+1) == Nature.EMPTY
				 && getGameEng().getLevel().getNature(getX_atPre, getY_atPre+2) == Nature.EMPTY) {
					if (!(isDroitier() == isDroitier_atPre && getComportement().contains(Comportement.CLIMBER) && getX() == getX_atPre-1 && getY() == getY_atPre+1)) {
						throw new PostConditionError("step : cas n°13.b");
					}
				}				 
			}
			// cas 1 builder
			if (getComportement().contains(Comportement.BUILDER)
					&& getGameEng().getLevel().getNature(getX() + 1, getY()) == Nature.EMPTY
					&& getGameEng().getLevel().getNature(getX() + 2, getY()) == Nature.EMPTY
					&& getGameEng().getLevel().getNature(getX() + 3, getY()) == Nature.EMPTY && getDalles() < 12
					&& getBuilderDelay() == 0) {

				if (!(getBuilderDelay() == 1))
					throw new PostConditionError("step : cas 1 builder");
			}
			// cas 2 builder
			if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getBuilderDelay() == 1) {

				if (!((getBuilderDelay() == 2) && getComportement().contains(Comportement.BUILDER)))
					throw new PostConditionError("step : cas 2 builder");
			}
			// cas 3 builder
			if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getBuilderDelay() == 2) {

				if (!((getBuilderDelay() == 3) && getComportement().contains(Comportement.BUILDER)))
					throw new PostConditionError("step : cas 3 builder");
			}
			// cas 4 builder
			if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getBuilderDelay() == 3) {
				if (!(getComportement().contains(Comportement.BUILDER) && getBuilderDelay() == 0
						&& getDalles() == getDalles_atPre + 1
						&& getGameEng().getLevel().getNature(getX_atPre + 1, getY_atPre) == Nature.DIRT
						&& getGameEng().getLevel().getNature(getX_atPre + 2, getY_atPre) == Nature.DIRT
						&& getGameEng().getLevel().getNature(getX_atPre + 3, getY_atPre) == Nature.DIRT
						&& getX() == getX_atPre + 2 && getY() == getY_atPre - 1))
					throw new PostConditionError("step : cas 3 builder");
			// cas 1 floater
			} if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
					&& getGameEng().getTour() % 2 == 0 && isDroitier_atPre
					&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)) {
				if (!(getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
						&& getX() == getX_atPre && getY() == getY_atPre + 1 && isDroitier()
						&& getFalling() == getFalling_atPre))
					throw new PostConditionError("step : cas 1 floater");
			// cas 2 floater
			} if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
					&& getGameEng().getTour() % 2 == 1 && isDroitier_atPre
					&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1))
				if (!(getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
						&& getX() == getX_atPre && getY() == getY_atPre && isDroitier()
						&& getFalling() == getFalling_atPre))
					throw new PostConditionError("step : cas 2 floater");
			// cas 3 floater
			} if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 0 && !isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)) {
				if (!(getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
					&& getX() == getX_atPre && getY() == getY_atPre + 1 && !isDroitier()
					&& getFalling() == getFalling_atPre))
				throw new PostConditionError("step : cas 3 floater");
			// cas 4 floater
			} if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 1 && !isDroitier_atPre
				&& !getGameEng().isObstacle(getX_atPre, getY_atPre + 1)) {
				if (!(getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
					&& getX() == getX_atPre && getY() == getY_atPre && !isDroitier()
					&& getFalling() == getFalling_atPre))
				throw new PostConditionError("step : cas 1 floater");
			// cas 1 STOPPER
			} if (getComportement().contains(Comportement.STOPPER)) {
				if (!(getGameEng().isObstacle(getX_atPre, getY_atPre) && (getGameEng().isObstacle(getX_atPre, getY_atPre -1)))) {
						throw new PostConditionError("step : cas 1 floater");	
				}
			}
			// cas 1 BOMBER
			if (getComportement().contains(Comportement.BOMBER) && getBomberDelay() > 0) {
				if (!(getBomberDelay() == getBomberDelay_atPre-1)) {
					throw new PostConditionError("step : cas 1 BOMBER");
				}
			}
			// cas 2 BOMBER
			if (getComportement().contains(Comportement.BOMBER) && getBomberDelay() == 0) {
				if (!(isDead() == true
				   && getGameEng().getLevel().getNature(getX(), getY()+1) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX(), getY()-1) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()+1, getY()+1) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()-1, getY()+1) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()+1, getY()) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()-1, getY()) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()+2, getY()) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()-2, getY()) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()+1, getY()-1) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()-1, getY()-1) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()+1, getY()-2) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()-1, getY()-2) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()+1, getY()-1) == Nature.DIRT
				   && getGameEng().getLevel().getNature(getX()-1, getY()-1) == Nature.DIRT)) {
					throw new PostConditionError("step : cas 2 BOMBER");
				}
			}
			// cas 1 BASHER
			if (getComportement().contains(Comportement.BASHER) && (getBasherDelay() == 0
					|| getGameEng().getLevel().getNature(getX()+1, getY()) == Nature.METAL
					|| getGameEng().getLevel().getNature(getX(), getY()-1) == Nature.EMPTY)) {
				if (!(!getComportement().contains(Comportement.BASHER) && getComportement().contains(Comportement.WALKER))) {
					throw new PostConditionError("step : cas 1 BASHER");
				}
			}
			// cas 2 BASHER
			if (getComportement().contains(Comportement.BASHER) && isDroitier() && getBasherDelay() > 0
					&& getGameEng().getLevel().getNature(getX()+1, getY()) != Nature.METAL
					&& getGameEng().getLevel().getNature(getX()+2, getY()) != Nature.METAL
					&& getGameEng().getLevel().getNature(getX()+3, getY()) != Nature.METAL) {
				if (!(getGameEng().getLevel().getNature(getX()+1, getY()) == Nature.EMPTY
						&& getGameEng().getLevel().getNature(getX()+2, getY()) == Nature.EMPTY
						&& getGameEng().getLevel().getNature(getX()+3, getY()) == Nature.EMPTY)
						&& getBasherDelay() == getBasherDelay_atPre-1 && getX() == getX_atPre+1 && getY() == getY_atPre) {
					throw new PostConditionError("step : cas 2 BASHER");
				}
			}
			// cas 3 BASHER
			if (getComportement().contains(Comportement.BASHER) && !isDroitier() && getBasherDelay() > 0
					&& getGameEng().getLevel().getNature(getX()-1, getY()) != Nature.METAL
					&& getGameEng().getLevel().getNature(getX()-2, getY()) != Nature.METAL
					&& getGameEng().getLevel().getNature(getX()-3, getY()) != Nature.METAL) {
				if (!(getGameEng().getLevel().getNature(getX()-1, getY()) == Nature.EMPTY
						&& getGameEng().getLevel().getNature(getX()-2, getY()) == Nature.EMPTY
						&& getGameEng().getLevel().getNature(getX()-3, getY()) == Nature.EMPTY)
						&& getBasherDelay() == getBasherDelay_atPre-1 && getX() == getX_atPre-1 && getY() == getY_atPre) {
					throw new PostConditionError("step : cas 3 BASHER");
				}
			}
		}
	
	/**
	 * changeComportement ------------------------------------------------------
	 */
	@Override
	public void changeComportement(Comportement c) {
		try {
			boolean isDroitier_atPre = isDroitier();
			int getX_atPre = getX();
			int getY_atPre = getY();
			int getFalling_atPre = getFalling();
			IGameEng getGameEng_atPre = getGameEng();
			boolean isDead_atPre = isDead();
			boolean isSaved_atPre = isSaved();
			int getDalles_atPre = getDalles();
			int getBuilderDelay_atPre = getBuilderDelay();
			int getBomberDelay_atPre = getBomberDelay();
			int getBasherDelay_atPre = getBasherDelay();
			
			checkInvariants();
			super.changeComportement(c);	
			checkInvariants();
			checkChangeComportementPostConditions(isDroitier_atPre, getX_atPre,
					getY_atPre, getFalling_atPre, getGameEng_atPre, c, 
					isDead_atPre, isSaved_atPre, getDalles_atPre, 
					getBuilderDelay_atPre, getBomberDelay_atPre, getBasherDelay_atPre);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}

	private void checkChangeComportementPostConditions(
			boolean isDroitier_atPre, int getX_atPre, int getY_atPre,
			int getFalling_atPre, IGameEng getGameEng_atPre, Comportement c,
			boolean isDead_atPre, boolean isSaved_atPre, int getDalles_atPre,
			int getBuilderDelay_atPre, int getBomberDelay_atPre, int getBasherDelay_atPre) {
		
		if (!(isDroitier() == isDroitier_atPre))
			 throw new PostConditionError("Init_Lemming : isDroitier() != isDroitier_atPre");
		
		if (!(getX() == getX_atPre))
			 throw new PostConditionError("Init_Lemming : getX() != getX_atPre");
		
		if (!(getY() == getY_atPre))
			 throw new PostConditionError("Init_Lemming : getY() != getY_atPre");
		
		if (!(getFalling() == getFalling_atPre))
			 throw new PostConditionError("Init_Lemming : getFalling() != getFalling_atPre");
		
		if (!(getGameEng() == getGameEng_atPre))
			 throw new PostConditionError("Init_Lemming : getGameEng() != getGameEng_atPre");
		
		if (!(getComportement().contains(c)))
			 throw new PostConditionError("Init_Lemming : getComportement() != c");
		
		if (!(isDead() == isDead_atPre))
			 throw new PostConditionError("Init_Lemming : isDead() != isDead_atPre");
		
		if (!(isSaved() == isSaved_atPre))
			 throw new PostConditionError("Init_Lemming : isSaved() != isSaved_atPre");
		
		if (!(getDalles() == getDalles_atPre))
			 throw new PostConditionError("Init_Lemming : getDalles() != getDalles_atPre");
		
		if (!(getBuilderDelay() == getBuilderDelay_atPre))
			 throw new PostConditionError("Init_Lemming : getBuilderDelay() != getBuilderDelay_atPre");
		
		if (!(getBomberDelay() == getBomberDelay_atPre))
			 throw new PostConditionError("Init_Lemming : getBomberDelay() != getBomberDelay_atPre");
		
		if (!(getBasherDelay() == getBasherDelay_atPre))
			 throw new PostConditionError("Init_Lemming : getBasherDelay() != getBasherDelay_atPre");
	}
}
