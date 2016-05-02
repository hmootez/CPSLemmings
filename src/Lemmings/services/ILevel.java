package Lemmings.services;

import Lemmings.tools.Nature;

public interface ILevel {
	
	// TYPES: bool, int, enum Nature {EMPTY, DIRT, METAL}
	
	
	
	// OBSERVATORS -------------------------------------------------------------
	public int getHeight(); /* const */ 
	public int getWidth(); /* const */
	public boolean isEditing();
	/** \pre : x => 0 && x <= width && y => 0 && y <= height*/
	public Nature getNature(int x,int y);
	public int getXEntrance(); 
	public int getYEntrance();
	public int getXExit();
	public int getYExit();
	
	
	
	// No INVARIANTS -----------------------------------------------------------
	
	
	
	// CONSTRUCTORS ------------------------------------------------------------
	/**
	 *  \pre : x > 0 && y > 0
	 *  \post : getHeight(init(x,y)) == y
	 *  \post : getWidth(init(x,y)) == x
	 *  \post : isEditing(init(x,y)) == true
	 *  \post : getXEntrance(init(x,y)) == -1
	 *  \post : getYEntrance(init(x,y)) == -1
	 *  \post : getXExit(init(x,y)) == -1
	 *  \post : getYExit(init(x,y)) == -1
	 *  \post : getNature(init(h,w),x,y) => (
	 * 	    							 Nature.METAL FORALL (x,y) / 
	 *  								 (x = 1 && 0 < y < getHeight()) || (y = 1 && 0 < x < getWidth()) ||
	 *                                   (x = getWidth() && 0 < y < getHeight()) || (y = getHeight() && 0 < x < getWidth())
	 *                               	 )
	 *                                && (
	 *                               	 Nature.EMPTY FORALL (x,y) / 
	 * 									 (x != 1 && 0 < y < getHeight()) || (y != 1 && 0 < x < getWidth()) || 
	 *                                   (x != getWidth() && 0 < y < getHeight()) || (y != getHeight() && 0 < x < getWidth())
	 *                               	 )
	 */
	public void init(int x, int y);
	
	
	
	// OPERATORS ---------------------------------------------------------------
	/**
	 *  \pre : x > 0 && x <= getWidth() && y > 0 && y <= getHeight()
	 *  \post : getNature(setNature(x,y,n),x,y) == n 
	 *  \post : isEditing(setNature(x,y,n)) == isEditing()@Pre
	 *  \post : getXEntrance(setNature(x,y,n)) == getXEntrance()@Pre
	 *  \post : getYEntrance(setNature(x,y,n)) == getYEntrance()@Pre
	 *  \post : getXExit(setNature(x,y,n)) == getXExit()@Pre
	 *  \post : getYExit(setNature(x,y,n)) == getYExit()@Pre
	 */
	public void setNature(int x, int y, Nature n);
	
	
	/**
	 *  \pre : FORALL (x,y) / (x = 1 && 0 < y < getHeight()) || (y = 1 && 0 < x < getWidth()) 
	 *                     || (x = getWidth() && 0 < y < getHeight()) || (y = getHeight() && 0 < x < getWidth()) 
	 *                     && getNature(x,y) == Nature.METAL
	 *  \pre : isEditing() == true
	 *  \Pre : xEntrance > 0 && xEntrance < getWidth() && yEntrance > 0 && yEntrance < getHeight()
	 *  \Pre : xExit > 0 && xExit < getWidth() && yExit > 0 && yExit < getHeight()
	 *  \Pre : getNature(xEntrance,yEntrance) == Nature.EMPTY && getNature(xEntrance,yEntrance-1) == Nature.EMPTY && getNature(xEntrance,yEntrance+1) == Nature.EMPTY
	 *  \Pre : getNature(xExit,yExit) == Nature.EMPTY && getNature(xExit,yExit-1) == Nature.METAL && getNature(xExit,yExit+1) == Nature.EMPTY
	 *  \post : isEditing(goPlay()) == false
	 *  \post : FORALL (x,y) / getNature(goPlay(),x,y) == getNature(x,y)@Pre
	 *  \post : getXEntrance(goPlay()) == xEntrance
	 *  \post : getYEntrance(goPlay()) == yEntrance
	 *  \post : getXExit(goPlay()) == xExit
	 *  \post : getYExit(goPlay()) == yExit
	 */
	public void goPlay(int xEntrance, int yEntrance, int xExit, int yExit);

	
	/**
	 *  \Pre : isEditing() == false
	 *  \post : isEditing(goEditing()) == true
	 *  \post : FORALL (x,y) / getNature(goEditing()) == getNature(x,y)@Pre
	 *  \post : getXEntrance(goEditing()) == getXEntrance()@Pre
	 *  \post : getYEntrance(goEditing()) == getYEntrance()@Pre
	 *  \post : getXExit(goEditing()) == getXExit()@Pre
	 *  \post : getYExit(goEditing()) == getYExit()@Pre
	 */
	public void goEditing();
	
	
	/**
	 *  \Pre : getNature(x,y) == Nature.DIRT
	 *  \Pre : isEditing() == false
	 *  \Pre : x != getXEntrance() && y != getYEntrance() && x != getXExit() && y != getYExit()
	 *  \post : getNature(remove(x,y),x,y) == Nature.EMPTY
	 *  \post : isEditing(remove(x,y)) == isEditing()@Pre
	 *  \post : getXEntrance(remove(x,y)) == getXEntrance()@Pre
	 *  \post : getYEntrance(remove(x,y)) == getYEntrance()@Pre
	 *  \post : getXExit(remove(x,y)) == getXExit()@Pre
	 *  \post : getYExit(remove(x,y)) == getYExit()@Pre
	 */
	public void remove(int x, int y);
	
	
	/**
	 *  \Pre : getNature(x,y) == Nature.EMPTY
	 *  \Pre : isEditing() == false
	 *  \Pre : x != getXEntrance() && y != getYEntrance() && x != getXExit() && y != getYExit()
	 *  \post : getNature(build(x,y),x,y) == Nature.DIRT
	 *  \post : isEditing(build(x,y)) == isEditing()@Pre
	 *  \post : getXEntrance(build(x,y)) == getXEntrance()@Pre
	 *  \post : getYEntrance(build(x,y)) == getYEntrance()@Pre
	 *  \post : getXExit(build(x,y)) == getXExit()@Pre
	 *  \post : getYExit(build(x,y)) == getYExit()@Pre
	 */
	public void build(int x, int y);
}
