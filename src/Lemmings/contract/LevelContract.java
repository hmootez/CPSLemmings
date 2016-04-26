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
	 * init --------------------------------------------------------------------
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
		if (!(x > 0)) {
			throw new PreConditionError("Init_Level : x <= 0");
		}
		if (!(y > 0)) {
			throw new PreConditionError("Init_Level : y <= 0");
		}
	}

	private void checkInitPostConditions(int x, int y) throws PostConditionError {
		if (!(getHeight() == y)) {
			throw new PostConditionError("Init_Level : getHeight() != y");
		}
		if (!(getWidth() == x)) {
			throw new PostConditionError("Init_Level : getWidth() != x");
		}
		if (!(isEditing() == true)) {
			throw new PostConditionError("Init_Level : isEditing() != true");
		}
		if (!(getXEntrance() == -1)) {
			throw new PostConditionError("Init_Level : getXEntrance() != -1");
		}
		if (!(getYEntrance() == -1)) {
			throw new PostConditionError("Init_Level : getYEntrance() != -1");
		}
		if (!(getXExit() == -1)) {
			throw new PostConditionError("Init_Level : getXExit() != -1");
		}
		if (!(getYExit() == -1)) {
			throw new PostConditionError("Init_Level : getYExit() != -1");
		}
		for (int j = 0; j < getHeight(); j++) {	
			for (int i = 0; i < getWidth(); i++) {
				if (i == 0 || j == getHeight()-1 || i == getWidth()-1 || j == 0) {
					if (!(getNature(i,j) == Nature.METAL)) {
						throw new PostConditionError("Init_Level : getNature(x,y) != Nature.METAL");
					}
				} else {
					if (!(getNature(i,j) == Nature.EMPTY)) {
						throw new PostConditionError("Init_Level : getNature(x,y) != Nature.EMPTY");
					}
				}
			}
		}
	}
	
	/**
	 * setNature ---------------------------------------------------------------
	 */
	@Override
	public void setNature(int x, int y, Nature n) {
		try {
			boolean isEditing_atPre = isEditing();
			int getXEntrance_atPre = getXEntrance();
			int getYEntrance_atPre = getYEntrance();
			int getXExit_atPre = getXExit();
			int getYExit_atPre = getYExit();

			checkSetNaturePreConditions(x, y);						
			super.setNature(x, y, n);			
			checkSetNaturePostConditions(x, y, n, isEditing_atPre, 
					getXEntrance_atPre, getYEntrance_atPre, getXExit_atPre, getYExit_atPre);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkSetNaturePreConditions(int x, int y) throws PreConditionError {
		if (!(x > 0)) {
			throw new PreConditionError("setNature : x <= 0");
		}
		if (!(x <= getWidth())) {
			throw new PreConditionError("setNature : x > getWidth()");
		}
		if (!(y > 0)) {
			throw new PreConditionError("setNature : y <= 0");
		}
		if (!(y < getHeight())) {
			throw new PreConditionError("setNature : y >= getHeight()");
		}
	}

	private void checkSetNaturePostConditions(int x, int y, Nature n,
			boolean isEditing_atPre, int getXEntrance_atPre, int getYEntrance_atPre,
			int getXExit_atPre, int getYExit_atPre) throws PostConditionError {
		
		if (!(getNature(x, y) == n)) {
			throw new PostConditionError("setNature : getNature(x, y) != n");
		}
		if (!(isEditing() == isEditing_atPre)) {
			throw new PostConditionError("setNature : isEditing() != isEditing_atPre");
		}
		if (!(getXEntrance() == getXEntrance_atPre)) {
			throw new PostConditionError("setNature : getXEntrance() != getXEntrance_atPre");
		}
		if (!(getYEntrance() == getYEntrance_atPre)) {
			throw new PostConditionError("setNature : getYEntrance() != getYEntrance_atPre");
		}
		if (!(getXExit() == getXExit_atPre)) {
			throw new PostConditionError("setNature : getXExit() != getXExit_atPre");
		}
		if (!(getYExit() == getYExit_atPre)) {
			throw new PostConditionError("setNature : getYExit() != getYExit_atPre");
		}
	}
	
	/**
	 * goPlay -------------------------------------------------------------------
	 */
	@Override
	public void goPlay(int xEntrance, int yEntrance, int xExit, int yExit) {
		try {
			Nature[][] getNature_atPre = null;
			for (int y = 0; y < getHeight(); y++) {	
				for (int x = 0; x < getWidth(); x++) {
					getNature_atPre[x][y] = getNature(x, y);
				}
			}
			checkGoPlayPreConditions(xEntrance, yEntrance, xExit, yExit);						
			super.goPlay(xEntrance, yEntrance, xExit, yExit);			
			checkGoPlayPostConditions(getNature_atPre, xEntrance, yEntrance, xExit, yExit);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkGoPlayPreConditions(int xEntrance, int yEntrance, int xExit,
			int yExit) throws PreConditionError {
		
		for (int j = 0; j < getHeight(); j++) {	
			for (int i = 0; i < getWidth(); i++) {
				if (i == 0 || j == getHeight()-1 || i == getWidth()-1 || j == 0) {
					if (!(getNature(i,j) == Nature.METAL)) {
						throw new PreConditionError("goPlay : getNature(x,y) != Nature.METAL ");
					}
				}
			}
		}
		if (!(isEditing() == true)) {
			throw new PreConditionError("goPlay : isEditing() != true");
		}
		if (!(xEntrance > 0)) {
			throw new PreConditionError("goPlay : xEntrance <= 0");
		}
		if (!(xEntrance <= getWidth())) {
			throw new PreConditionError("goPlay : xEntrance > getWidth()");
		}
		if (!(yEntrance > 0)) {
			throw new PreConditionError("goPlay : yEntrance <= 0");
		}
		if (!(yEntrance < getHeight())) {
			throw new PreConditionError("goPlay : yEntrance >= getHeight()");
		}
		if (!(xExit > 0)) {
			throw new PreConditionError("goPlay : xExit <= 0");
		}
		if (!(xExit <= getWidth())) {
			throw new PreConditionError("goPlay : xExit > getWidth()");
		}
		if (!(yExit > 0)) {
			throw new PreConditionError("goPlay : yExit <= 0");
		}
		if (!(yExit < getHeight())) {
			throw new PreConditionError("goPlay : yExit >= getHeight()");
		}
		if (!(getNature(xEntrance,yEntrance) == Nature.EMPTY)) {
			throw new PreConditionError("goPlay : getNature(xEntrance,yEntrance) != Nature.EMPTY");
		}
		if (!(getNature(xEntrance,yEntrance-1) == Nature.EMPTY)) {
			throw new PreConditionError("goPlay : getNature(xEntrance,yEntrance-1) != Nature.EMPTY");
		}		
		if (!(getNature(xEntrance,yEntrance+1) == Nature.EMPTY)) {
			throw new PreConditionError("goPlay : getNature(xEntrance,yEntrance+1) != Nature.EMPTY");
		}
		if (!(getNature(xExit,yExit) == Nature.EMPTY)) {
			throw new PreConditionError("goPlay : getNature(xExit,yExit) != Nature.EMPTY");
		}
		if (!(getNature(xExit,yExit-1) == Nature.METAL)) {
			throw new PreConditionError("goPlay : getNature(xExit,yExit-1) != Nature.METAL");
		}		
		if (!(getNature(xExit,yExit+1) == Nature.EMPTY)) {
			throw new PreConditionError("goPlay : getNature(xExit,yExit+1) != Nature.EMPTY");
		}
	}

	private void checkGoPlayPostConditions(Nature[][] getNature_atPre, int xEntrance, 
			int yEntrance, int xExit, int yExit) throws PostConditionError {
		
		if (!(isEditing() == false)) {
			throw new PostConditionError("goPlay : isEditing() != false");
		}
		for (int y = 0; y < getHeight(); y++) {	
			for (int x = 0; x < getWidth(); x++) {
				if (!(getNature(x, y) == getNature_atPre[x][y])) {
					throw new PostConditionError("goPlay : getNature(x, y) != getNature_atPre[x][y]");
				}
			}
		}
		if (!(getXEntrance() == xEntrance)) {
			throw new PostConditionError("goPlay : getXEntrance() != xEntrance");
		}
		if (!(getYEntrance() == yEntrance)) {
			throw new PostConditionError("goPlay : getYEntrance() != yEntrance");
		}
		if (!(getXExit() == xExit)) {
			throw new PostConditionError("goPlay : getXExit() != xExit");
		}
		if (!(getYExit() == yExit)) {
			throw new PostConditionError("goPlay : getYExit() != yExit");
		}
	}
	
	
	/**
	 * goEditing ---------------------------------------------------------------
	 */
	@Override
	public void goEditing() {
		try {
			int getXEntrance_atPre = getXEntrance();
			int getYEntrance_atPre = getYEntrance();
			int getXExit_atPre = getXExit();
			int getYExit_atPre = getYExit();
			Nature[][] getNature_atPre = null;
			for (int y = 0; y < getHeight(); y++) {	
				for (int x = 0; x < getWidth(); x++) {
					getNature_atPre[x][y] = getNature(x, y);
				}
			}
			checkGoEditingPreConditions();						
			super.goEditing();			
			checkGoEditingPostConditions(getNature_atPre, getXEntrance_atPre, 
					getYEntrance_atPre, getXExit_atPre, getYExit_atPre);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkGoEditingPreConditions() {
		if (!(isEditing() == false)) {
			throw new PreConditionError("goEditing : isEditing() != false");
		}
	}
	
	private void checkGoEditingPostConditions(Nature[][] getNature_atPre,
			int getXEntrance_atPre, int getYEntrance_atPre, int getXExit_atPre,
			int getYExit_atPre) {
		
		for (int y = 0; y < getHeight(); y++) {	
			for (int x = 0; x < getWidth(); x++) {
				if (!(getNature(x, y) == getNature_atPre[x][y])) {
					throw new PostConditionError("goEditing : getNature(x, y) != getNature_atPre[x][y]");
				}
			}
		}
		if (!(getXEntrance() == getXEntrance_atPre)) {
			throw new PostConditionError("goEditing : getXEntrance() != getXEntrance_atPre");
		}
		if (!(getYEntrance() == getYEntrance_atPre)) {
			throw new PostConditionError("goEditing : getYEntrance() != getYEntrance_atPre");
		}
		if (!(getXExit() == getXExit_atPre)) {
			throw new PostConditionError("goEditing : getXExit() != getXExit_atPre");
		}
		if (!(getYExit() == getYExit_atPre)) {
			throw new PostConditionError("goEditing : getYExit() != getYExit_atPre");
		}
	}

	/**
	 * remove ------------------------------------------------------------------
	 */
	@Override
	public void remove(int x, int y) {
		try {
			boolean isEditing_atPre = isEditing();
			int getXEntrance_atPre = getXEntrance();
			int getYEntrance_atPre = getYEntrance();
			int getXExit_atPre = getXExit();
			int getYExit_atPre = getYExit();
			
			checkRemovePreConditions(x, y);						
			super.remove(x,y);			
			checkRemovePostConditions(x, y, isEditing_atPre, getXEntrance_atPre, 
					getYEntrance_atPre, getXExit_atPre, getYExit_atPre);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}

	private void checkRemovePreConditions(int x, int y) throws PreConditionError {
		if (!(getNature(x,y) == Nature.DIRT)) {
			throw new PreConditionError("remove : nature(x,y) != Nature.DIRT");
		}
		if (!(isEditing() == false)) {
			throw new PostConditionError("remove : isEditing() != false");
		}
		if (!(x != getXEntrance())) {
			throw new PostConditionError("remove : x == getXEntrance()");
		}
		if (!(y != getYEntrance())) {
			throw new PostConditionError("remove : y == getYEntrance()");
		}
		if (!(x != getXExit())) {
			throw new PostConditionError("remove : x == getXExit()");
		}
		if (!(y != getYExit())) {
			throw new PostConditionError("remove : y == getYExit()");
		}
	}

	private void checkRemovePostConditions(int x, int y, boolean isEditing_atPre, 
			int getXEntrance_atPre, int getYEntrance_atPre, int getXExit_atPre, 
			int getYExit_atPre) throws PostConditionError {
		
		if (!(getNature(x,y) == Nature.EMPTY)) {
			throw new PreConditionError("remove : getNature(x,y) != Nature.EMPTY");
		}
		if (!(isEditing() == isEditing_atPre)) {
			throw new PostConditionError("remove : isEditing() != isEditing_atPre");
		}
		if (!(getXEntrance() == getXEntrance_atPre)) {
			throw new PostConditionError("remove : getXEntrance() != getXEntrance_atPre");
		}
		if (!(getYEntrance() == getYEntrance_atPre)) {
			throw new PostConditionError("remove : getYEntrance() != getYEntrance_atPre");
		}
		if (!(getXExit() == getXExit_atPre)) {
			throw new PostConditionError("remove : getXExit() != getXExit_atPre");
		}
		if (!(getYExit() == getYExit_atPre)) {
			throw new PostConditionError("remove : getYExit() != getYExit_atPre");
		}
	}
	
	/**
	 * build -------------------------------------------------------------------
	 */
	@Override
	public void build(int x, int y) {
		try {
			boolean isEditing_atPre = isEditing();
			int getXEntrance_atPre = getXEntrance();
			int getYEntrance_atPre = getYEntrance();
			int getXExit_atPre = getXExit();
			int getYExit_atPre = getYExit();
			
			checkBuildPreConditions(x, y);						
			super.build(x,y);			
			checkBuildPostConditions(x, y, isEditing_atPre, getXEntrance_atPre, 
					getYEntrance_atPre, getXExit_atPre, getYExit_atPre);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkBuildPreConditions(int x, int y) throws PreConditionError {
		if (!(getNature(x,y) == Nature.EMPTY)) {
			throw new PreConditionError("build : getNature(x,y) != Nature.EMPTY");
		}
		if (!(isEditing() == false)) {
			throw new PostConditionError("build : isEditing() != false");
		}
		if (!(x != getXEntrance())) {
			throw new PostConditionError("build : x == getXEntrance()");
		}
		if (!(y != getYEntrance())) {
			throw new PostConditionError("build : y == getYEntrance()");
		}
		if (!(x != getXExit())) {
			throw new PostConditionError("build : x == getXExit()");
		}
		if (!(y != getYExit())) {
			throw new PostConditionError("build : y == getYExit()");
		}
	}

	private void checkBuildPostConditions(int x, int y, boolean isEditing_atPre, 
			int getXEntrance_atPre, int getYEntrance_atPre, int getXExit_atPre, 
			int getYExit_atPre) throws PostConditionError {
		
		if (!(getNature(x,y) == Nature.DIRT)) {
			throw new PreConditionError("build : getNature(x,y) != Nature.DIRT");
		}
		if (!(isEditing() == isEditing_atPre)) {
			throw new PostConditionError("build : isEditing() != isEditing_atPre");
		}
		if (!(getXEntrance() == getXEntrance_atPre)) {
			throw new PostConditionError("build : getXEntrance() != getXEntrance_atPre");
		}
		if (!(getYEntrance() == getYEntrance_atPre)) {
			throw new PostConditionError("build : getYEntrance() != getYEntrance_atPre");
		}
		if (!(getXExit() == getXExit_atPre)) {
			throw new PostConditionError("build : getXExit() != getXExit_atPre");
		}
		if (!(getYExit() == getYExit_atPre)) {
			throw new PostConditionError("build : getYExit() != getYExit_atPre");
		}
	}
}
