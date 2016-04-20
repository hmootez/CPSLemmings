package Lemmings.contract;

import java.util.ArrayList;

import Lemmings.decorators.LemmingDecorator;
import Lemmings.error.ContractError;
import Lemmings.error.ErrorHandler;
import Lemmings.error.InvariantError;
import Lemmings.error.PostConditionError;
import Lemmings.error.PreConditionError;
import Lemmings.services.Comportement;
import Lemmings.services.IGameEng;
import Lemmings.services.ILemming;
import Lemmings.services.Nature;

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
			
		if (!(isDead() == false))
			throw new PostConditionError("Init_Lemming : isDead == true");
			
		if (!(getFalling() == 0))
			throw new PostConditionError("Init_Lemming : getFalling() != 0");
		
		if ( ! (getGameEng() == core))
			throw new PostConditionError("Init_Lemming : (getGameEng()!= core)");
		
		if (!(getDalles() == 0))
			 throw new PostConditionError("Init_Lemming : getDalles() != 0");
		
		if (!(getTourIncr() == 0))
			 throw new PostConditionError("Init_Lemming : getTourIncr() != 0");
	}

	private void checkInvariants() {
		if (!(getFalling() >= 0 && getFalling() <= getGameEng().getLevel()
				.height() - 2))
			throw new InvariantError(
					"Ivariant_Lemming : !getFalling() >= 0 && getFalling() <= getGameEng().getLevel().height()-2)");
		if (!(getX() > 0 && getX() <= getGameEng().getLevel().width() - 2))
			throw new InvariantError(
					"Ivariant_Lemming : !(getX()> 0 && getX() <=  getGameEng().getLevel().width()-2");

		if (!(getY() > 0 && getY() <= getGameEng().getLevel().height() - 2))
			throw new InvariantError(
					"Ivariant_Lemming : !(getY()> 0 && getY() <=  getGameEng().getLevel().height()-2");

		if (isDead())
			if (!(getFalling() == 8))
				throw new InvariantError(
						"Ivariant_Lemming :! isDead() = true <=> getFalling() = 8  ");

		if (!isDead())
			if (!(getFalling() < 8))
				throw new InvariantError(
						"Ivariant_Lemming :! isDead() = false <=> getFalling() < 8  ");
	}
	
	
	/**
	 * step --------------------------------------------------------------------
	 */
	@Override
	public void step(){
		try {
			int getX_pre = getX();
			int getY_pre = getY();
			boolean isDroitier_pre = isDroitier();
			int getFalling_atPre = getFalling();
			 int getDalles_Pre = getDalles();
			checkInvariants();
			super.step();	
			checkInvariants();
			checkStepPostConditions(getX_pre, getY_pre, isDroitier_pre, getFalling_atPre, getDalles_Pre);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}

	private void checkStepPostConditions(int getX_pre, int getY_pre, boolean isDroitier_pre, int getFalling_atPre, int getDalles_Pre) {
		if (getComportement().contains(Comportement.WALKER)) {
			// cas n°1
			if (!(getGameEng().obstacle(getX_pre,getY_pre+1))) {
				if (!(getComportement().contains(Comportement.FALLER) && getX() == getX_pre && getY() == getY_pre && isDroitier() == isDroitier_pre && getFalling() == 0 )) {
					throw new PostConditionError("step : cas n°1");
				}
			}
			// cas n°2.a
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && getGameEng().obstacle(getX_pre+1,getY_pre-1) && isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre && getY() == getY_pre && isDroitier() == false && getFalling() == 0 )) {
					throw new PostConditionError("step : cas n°2.a");
				}
			}
			// cas n°2.b
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && getGameEng().obstacle(getX_pre-1,getY_pre-1) && !isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre && getY() == getY_pre && isDroitier() == true && getFalling() == 0 )) {
					throw new PostConditionError("step : cas n°2.b");
				}
			}
			// cas n°3.a
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && getGameEng().obstacle(getX_pre+1,getY_pre) && getGameEng().obstacle(getX_pre+1,getY_pre-2) && isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre && getY() == getY_pre && isDroitier() == false && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°3.a");
				}
			}
			// cas n°3.b
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && getGameEng().obstacle(getX_pre-1,getY_pre) && getGameEng().obstacle(getX_pre-1,getY_pre-2) && !isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre && getY() == getY_pre && isDroitier() == true && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°3.b");
				}
			}
			// cas n°4.a
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && getGameEng().obstacle(getX_pre+1,getY_pre) && !getGameEng().obstacle(getX_pre+1,getY_pre-1) && !getGameEng().obstacle(getX_pre+1,getY_pre-2) && isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre+1 && getY() == getY_pre-1 && isDroitier() == true && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°4.a");
				}
			}
			// cas n°4.b
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && getGameEng().obstacle(getX_pre-1,getY_pre) && !getGameEng().obstacle(getX_pre-1,getY_pre-1) && !getGameEng().obstacle(getX_pre-1,getY_pre-2) && !isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre-1 && getY() == getY_pre-1 && isDroitier() == false && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°4.b");
				}
			}
			// cas n°5.a
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && !getGameEng().obstacle(getX_pre+1,getY_pre) && !getGameEng().obstacle(getX_pre+1,getY_pre-1) && isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre+1 && getY() == getY_pre && isDroitier() == true && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°5.a");
				}
			}
			// cas n°5.b
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && !getGameEng().obstacle(getX_pre-1,getY_pre) && !getGameEng().obstacle(getX_pre-1,getY_pre-1) && !isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre-1 && getY() == getY_pre && isDroitier() == false && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°5.b");
				}
			}
		}
		if (getComportement().contains(Comportement.FALLER) && !getComportement().contains(Comportement.CLIMBER)) {
			// cas n°6.a
			if (!getGameEng().obstacle(getX_pre,getY_pre+1) && getFalling() < 8 && isDroitier_pre) {
				if (!(getComportement().contains(Comportement.FALLER) && getX() == getX_pre && getY() == getY_pre+1 && isDroitier() == true && getFalling() == getFalling_atPre+1)) {
					throw new PostConditionError("step : cas n°6.a");
				}
			}
			// cas n°6.b
			if (!getGameEng().obstacle(getX_pre,getY_pre+1) && getFalling() < 8 && !isDroitier_pre) {
				if (!(getComportement().contains(Comportement.FALLER) && getX() == getX_pre && getY() == getY_pre+1 && isDroitier() == false && getFalling() == getFalling_atPre+1)) {
					throw new PostConditionError("step : cas n°6.b");
				}
			}
			// cas n°7.a
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && getFalling() < 8 && isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre && getY() == getY_pre && isDroitier() == true && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°7.a");
				}
			}
			// cas n°7.b
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && getFalling() < 8 && !isDroitier_pre) {
				if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre && getY() == getY_pre && isDroitier() == false && getFalling() == 0)) {
					throw new PostConditionError("step : cas n°7.b");
				}
			}
			// cas n°8.a
			if (getGameEng().obstacle(getX_pre,getY_pre+1) && getFalling() == 8) {
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
				if (getGameEng().getLevel().nature(getX(), getY()+1) == Nature.EMPTY) {
					if (!(getComportement().contains(Comportement.FALLER) && getX() == getX_pre && getY() == getY_pre && getFalling() == 0)) {
						throw new PostConditionError("step : cas n°9");
					}
				}
				// cas n°10
				if (getGameEng().getLevel().nature(getX(), getY()+1) == Nature.METAL) {
					if (!(getComportement().contains(Comportement.WALKER) && getX() == getX_pre && getY() == getY_pre && getFalling() == 0)) {
						throw new PostConditionError("step : cas n°10");
					}
				}
				// cas n°11
				if (getGameEng().getLevel().nature(getX(), getY()+1) == Nature.DIRT) {
					if (!(getComportement().contains(Comportement.DIGGER) && getX() == getX_pre && getY() == getY_pre+1 && getFalling() == 0 
							&& getGameEng().getLevel().nature(getX_pre, getY_pre+1) == Nature.EMPTY
							&& getGameEng().getLevel().nature(getX_pre-1, getY_pre+1) != Nature.DIRT 
						    && getGameEng().getLevel().nature(getX_pre+1, getY_pre+1) != Nature.DIRT)) {
						
						throw new PostConditionError("step : cas n°11");
					}
				}
			}
			if (getComportement().contains(Comportement.CLIMBER)) {
				// cas n°12.a
				if (isDroitier_pre && getComportement().contains(Comportement.CLIMBER) 
				 && getGameEng().obstacle(getX_pre+1,getY_pre) 
				 && getGameEng().obstacle(getX_pre+1,getY_pre+1) 
				 && getGameEng().getLevel().nature(getX_pre, getY_pre+1) == Nature.EMPTY
				 && getGameEng().getLevel().nature(getX_pre, getY_pre+2) == Nature.EMPTY) {
					if (!(isDroitier() == true && getComportement().contains(Comportement.CLIMBER) && getX() == getX_pre && getY() == getY_pre+1)) {
						throw new PostConditionError("step : cas n°12.a");
					}
				}
				// cas n°12.b
				if (!isDroitier_pre && getComportement().contains(Comportement.CLIMBER) 
				 && getGameEng().obstacle(getX_pre-1,getY_pre) 
				 && getGameEng().obstacle(getX_pre-1,getY_pre+1) 
				 && getGameEng().getLevel().nature(getX_pre, getY_pre+1) == Nature.EMPTY
				 && getGameEng().getLevel().nature(getX_pre, getY_pre+2) == Nature.EMPTY) {
					if (!(isDroitier() == false && getComportement().contains(Comportement.CLIMBER) && getX() == getX_pre && getY() == getY_pre+1)) {
						throw new PostConditionError("step : cas n°12.b");
					}
				}
				// cas n°13.a
				if (isDroitier_pre && getComportement().contains(Comportement.CLIMBER) 
				 && getGameEng().obstacle(getX_pre+1,getY_pre) 
				 && getGameEng().getLevel().nature(getX_pre+1, getY_pre+1) == Nature.EMPTY
				 && getGameEng().getLevel().nature(getX_pre, getY_pre+1) == Nature.EMPTY
				 && getGameEng().getLevel().nature(getX_pre, getY_pre+2) == Nature.EMPTY) {
					if (!(isDroitier() == true && getComportement().contains(Comportement.CLIMBER) && getX() == getX_pre+1 && getY() == getY_pre+1)) {
						throw new PostConditionError("step : cas n°13.a");
					}
				}
				// cas n°13.b
				if (!isDroitier_pre && getComportement().contains(Comportement.CLIMBER) 
				 && getGameEng().obstacle(getX_pre-1,getY_pre) 
				 && getGameEng().getLevel().nature(getX_pre-1, getY_pre+1) == Nature.EMPTY
				 && getGameEng().getLevel().nature(getX_pre, getY_pre+1) == Nature.EMPTY
				 && getGameEng().getLevel().nature(getX_pre, getY_pre+2) == Nature.EMPTY) {
					if (!(isDroitier() == isDroitier_pre && getComportement().contains(Comportement.CLIMBER) && getX() == getX_pre-1 && getY() == getY_pre+1)) {
						throw new PostConditionError("step : cas n°13.b");
					}
				}				 
			}
			// cas 1 builder
			if (getComportement().contains(Comportement.BUILDER)
					&& getGameEng().getLevel().nature(getX() + 1, getY()) == Nature.EMPTY
					&& getGameEng().getLevel().nature(getX() + 2, getY()) == Nature.EMPTY
					&& getGameEng().getLevel().nature(getX() + 3, getY()) == Nature.EMPTY && getDalles() < 12
					&& getTourIncr() == 0) {

				if (!(getTourIncr() == 1))
					throw new PostConditionError("step : cas 1 builder");
			}
			// cas 2 builder
			if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getTourIncr() == 1) {

				if (!((getTourIncr() == 2) && getComportement().contains(Comportement.BUILDER)))
					throw new PostConditionError("step : cas 2 builder");
			}
			// cas 3 builder
			if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getTourIncr() == 2) {

				if (!((getTourIncr() == 3) && getComportement().contains(Comportement.BUILDER)))
					throw new PostConditionError("step : cas 3 builder");
			}
			// cas 4 builder
			if (getComportement().contains(Comportement.BUILDER) && getDalles() < 12 && getTourIncr() == 3) {
				if (!(getComportement().contains(Comportement.BUILDER) && getTourIncr() == 0
						&& getDalles() == getDalles_Pre + 1
						&& getGameEng().getLevel().nature(getX_pre + 1, getY_pre) == Nature.DIRT
						&& getGameEng().getLevel().nature(getX_pre + 2, getY_pre) == Nature.DIRT
						&& getGameEng().getLevel().nature(getX_pre + 3, getY_pre) == Nature.DIRT
						&& getX() == getX_pre + 2 && getY() == getY_pre - 1))
					throw new PostConditionError("step : cas 3 builder");
			// cas 1 floater
			} if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
					&& getGameEng().getTour() % 2 == 0 && isDroitier_pre
					&& !getGameEng().obstacle(getX_pre, getY_pre + 1)) {
				if (!(getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
						&& getX() == getX_pre && getY() == getY_pre + 1 && isDroitier()
						&& getFalling() == getFalling_atPre))
					throw new PostConditionError("step : cas 1 floater");
			// cas 2 floater
			} if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
					&& getGameEng().getTour() % 2 == 1 && isDroitier_pre
					&& !getGameEng().obstacle(getX_pre, getY_pre + 1))
				if (!(getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
						&& getX() == getX_pre && getY() == getY_pre && isDroitier()
						&& getFalling() == getFalling_atPre))
					throw new PostConditionError("step : cas 2 floater");
			// cas 3 floater
			} if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 0 && !isDroitier_pre
				&& !getGameEng().obstacle(getX_pre, getY_pre + 1)) {
			if (!(getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
					&& getX() == getX_pre && getY() == getY_pre + 1 && !isDroitier()
					&& getFalling() == getFalling_atPre))
				throw new PostConditionError("step : cas 3 floater");
			// cas 4 floater
			} if (getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
				&& getGameEng().getTour() % 2 == 1 && !isDroitier_pre
				&& !getGameEng().obstacle(getX_pre, getY_pre + 1)) {
			if (!(getComportement().contains(Comportement.FLOATER) && getComportement().contains(Comportement.FALLER)
					&& getX() == getX_pre && getY() == getY_pre && !isDroitier()
					&& getFalling() == getFalling_atPre))
				throw new PostConditionError("step : cas 1 floater");
			// cas 1 STOPPER
			} if (getComportement().contains(Comportement.STOPPER)) {
				if (!(getGameEng().obstacle(getX_pre, getY_pre) && (getGameEng().obstacle(getX_pre, getY_pre -1)))) {
						throw new PostConditionError("step : cas 1 floater");	
				}
			} 
		}
	
	/**
	 * changeComportement --------------------------------------------------------------------
	 * TODO.....
	 */
}
