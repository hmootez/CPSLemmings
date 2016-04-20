package Lemmings.contract;


import Lemmings.decorators.GameEngDecorator;
import Lemmings.error.ContractError;
import Lemmings.error.ErrorHandler;
import Lemmings.error.InvariantError;
import Lemmings.error.PostConditionError;
import Lemmings.error.PreConditionError;
import Lemmings.services.IGameEng;
import Lemmings.services.ILevel;
import Lemmings.services.Nature;

public class GameEngContract extends GameEngDecorator {

	public GameEngContract(IGameEng delegate) {
		super(delegate);
		
	}
	
	/**
	 * Init --------------------------------------------------------------------
	 */
	@Override
	public void init(int sc, int sp, ILevel l) {
		try {
			checkInitPreConditions(sc, sp);	
			checkInvariants();
			super.init(sc, sp,l);	
			checkInvariants();
			checkInitPostConditions(sc, sp,l);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkInvariants() throws InvariantError {

		if (!(getnblemmingcreated() <= getSizeColony())) {
			throw new InvariantError(
					"Invariant GameEng : getnblemmingcreated() > getSizeColony()");
		}

		for (int x = 0; x < getLevel().width(); x++)
			for (int y = 0; x < getLevel().height(); y++) {
				if ((obstacle(x, y) == true))
					if (!(getLevel().nature(x, y) == Nature.DIRT || getLevel()
							.nature(x, y) == Nature.METAL))
						throw new InvariantError(
								"Invariant GameEng : ! obstacle(x,y) == ILevel::nature(x,y)==(Nature.DIRT ||Nature.METAL)");
			}
		if (!(getActivLemmings().size() <= getSizeColony()))
			throw new InvariantError(
					"Invariant GameEng : !(getActivLemmings().size()<= getSizeColony()");

		if (gameOver())
			if (!(getnblemmingcreated() == getSizeColony() && getActivLemmings()
					.size() == 0))
				throw new InvariantError(
						"Invariant GameEng : !(getnblemmingcreated()== getSizeColony() && getActivLemmings().size()==0)");

		if(!( getscore() ==  (getnblemmingsaved() / getnblemmingcreated())*100 + getTour()))
			throw new InvariantError(
					"Invariant GameEng : !  getscore() ==  (getnblemmingsaved() / getnblemmingcreated())*100 + getTour()");
			
		
	}


	private void checkInitPreConditions(int sc, int sp) throws PreConditionError {
		if (!(sc > 0)) {
			throw new PreConditionError("Init_GameEng : sc <= 0");
		}
		if (!(sp > 0)) {
			throw new PreConditionError("Init_GameEng : sp <= 0");
		}
	}

	private void checkInitPostConditions(int sc, int sp, ILevel l) throws PostConditionError {
		if (!(getSizeColony() == sc)) {
			throw new PostConditionError("Init_GameEng : getSizeColony() != sc)");
		}
		if (!( getSpawnSpeed() == sp)) {
			throw new PostConditionError("Init_GameEng : getSpawnSpeed() != sp)");
		}
		if (!(getActivLemmings().isEmpty())) {
			throw new PostConditionError("Init_GameEng : !getActivLemmings().isEmpty()");
		}
		if (!(getTour() == 0))
			throw new PostConditionError("Init_GameEng : getTour != 0");
		if (!(gameOver() == false))
			throw new PostConditionError("Init_GameEng : gameOver == true");
		if (!(getLevel() == l))
			throw new PostConditionError("Init_GameEng : getLevel() != l");
}
	
	
	/**
	 * step --------------------------------------------------------------------
	 */
	
	
	@Override
	public void step() {
		try {
			checkStepPreConditions();	
			checkInvariants();
			super.step();	
			checkInvariants();
			checkStepPostConditions();
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}



	private void checkStepPostConditions() {
		// TODO a faiiiiiiiiiiiiiiiiiiiiire !!!
	}



	private void checkStepPreConditions() {
		if(!(gameOver()== false))
			throw new PostConditionError("Step_GameEng  :  gameOver == true");
}
		
	}

