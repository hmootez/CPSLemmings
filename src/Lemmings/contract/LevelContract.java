package Lemmings.contract;

import Lemmings.decorators.LevelDecorator;
import Lemmings.error.ContractError;
import Lemmings.error.ErrorHandler;
import Lemmings.error.PostConditionError;
import Lemmings.error.PreConditionError;
import Lemmings.services.ILevel;
import Lemmings.services.Nature;

public class LevelContract extends LevelDecorator {

	public LevelContract(ILevel delegate) {
		super(delegate);
	}

	/**
	 * Init --------------------------------------------------------------------
	 */
	@Override
	public void init(int x, int y) {
		try {
			checkInitPreConditions(x, y);						
			super.init(x, y);			
			checkInitPostConditions(x, y);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkInitPreConditions(int x, int y) throws PreConditionError {
		if (!(x>0 && y>0)) {
			throw new PreConditionError("Init_Level : x<=0 && y<=0");
		}
		
	}

	private void checkInitPostConditions(int x, int y) throws PostConditionError {
		if (!(height() == y)) {
			throw new PostConditionError("Init_Level : height() != y");
		}
		if (!(width() == x)) {
			throw new PostConditionError("Init_Level : width() != x");
		}
		if (!(editing() == true)) {
			throw new PostConditionError("Init_Level : editing() != true");
		}
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				if (i == 1 || i == width() || j == height() || j == 1) {
					if (!(nature(i,j) == Nature.METAL)) {
						throw new PostConditionError("Init_Level : nature(x,y) != Nature.METAL");
					}
				}
			}
		}
	}
	
	/**
	 * setNature -----------------------------------------------------------------
	 */
	@Override
	public void setNature(int x, int y, Nature n) {
		try {
			checkSetNaturePreConditions(x, y);						
			super.setNature(x, y, n);			
			checkSetNaturePostConditions(x, y, n);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkSetNaturePreConditions(int x, int y) throws PreConditionError {
		if (!(x>0 && x<=width() && y>0 && y<height())) {
			throw new PreConditionError("setNature : x<=0 || x>width() || y<=0 || y>=height()");
		}
		
	}

	private void checkSetNaturePostConditions(int x, int y, Nature n) throws PostConditionError {
		if (!(nature(x, y) == n)) {
			throw new PostConditionError("setNature : nature(x, y) != n");
		}
	}
	
	/**
	 * goPlay -------------------------------------------------------------------
	 */
	@Override
	public void goPlay() {
		try {
			checkGoPlayPreConditions();						
			super.goPlay();			
			checkGoPlayPostConditions();
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkGoPlayPreConditions() throws PreConditionError {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				if (i == 1 || i == width() || j == height() || j == 1) {
					if (!(nature(i,j) == Nature.METAL)) {
						throw new PreConditionError("goPlay :  nature(x,y) != Nature.METAL ");
					}
				}
			}
		}
			}

	private void checkGoPlayPostConditions() throws PostConditionError {
		if (!(editing() == false)) {
			throw new PostConditionError("goPlay : editing() != false");
		}
	}
	
	
	/**
	 * remove -------------------------------------------------------------------
	 */
	@Override
	public void remove(int x, int y) {
		try {
			checkremovePreConditions(x,y);						
			super.remove(x,y);			
			checkremovePostConditions(x,y);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkremovePreConditions(int x, int y) throws PreConditionError {
		if (!(nature(x,y) == Nature.DIRT)) {
			throw new PreConditionError("remove : nature(x,y) != Nature.DIRT");
		}
		if (!(editing() == false)) {
			throw new PostConditionError("remove : editing() != false");
		}
		}
			

	private void checkremovePostConditions(int x, int y) throws PostConditionError {
		if (!(nature(x,y) == Nature.EMPTY)) {
			throw new PreConditionError("remove : nature(x,y) != Nature.EMPTY");
		}
	}
	
	
	
	/**
	 * build -------------------------------------------------------------------
	 */
	@Override
	public void build(int x, int y) {
		try {
			checkbuildPreConditions(x,y);						
			super.build(x,y);			
			checkbuildPostConditions(x,y);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkbuildPreConditions(int x, int y) throws PreConditionError {
		if (!(nature(x,y) == Nature.EMPTY)) {
			throw new PreConditionError("build : nature(x,y) != Nature.EMPTY");
		}
		if (!(editing() == false)) {
			throw new PostConditionError("build : editing() != false");
		}
		}
			

	private void checkbuildPostConditions(int x, int y) throws PostConditionError {
		if (!(nature(x,y) == Nature.DIRT)) {
			throw new PreConditionError("build : nature(x,y) != Nature.DIRT");
		}
	}
	
	
}
