package Lemmings.services;

import java.util.ArrayList;

public interface IGameEng {
		
	// TYPES: bool, int, enum Nature {EMPTY, DIRT, METAL}, Set<Integer>
	// USE : ILevel, ILemming
		
	
	
	// OBSERVATORS -------------------------------------------------------------
	public boolean isObstacle(int x,int y);
	public int getTour();
	public int getMaxTour(); /* const */
	/**  \pre : isGameOver() == true */ 
	public int getScore();
	public boolean isGameOver();
	public int getSizeColony(); /* const */
	public int getSpawnSpeed(); /* const */
	public int getNbLemmingCreated();
	public int getNbLemmingSaved();
	public ILevel getLevel();
	public ArrayList<ILemming> getActivLemmings();
		

	
	// INVARIANTS --------------------------------------------------------------
	/**
	 *  \inv : getNbNemmingCreated() <= getSizeColony()
	 *  \inv : getTour() <= getMaxTour()
	 *  \inv : isObstacle(x,y) == {
	 *  						  true <==> (ILevel::getNature(x,y) == (Nature.DIRT || Nature.METAL))
	 *                            false <==> (ILevel::getNature(x,y) == Nature.EMPTY)
	 *                            }
	 *  \inv : Card(getActivLemmings()) <= getSizeColony()
	 *  \inv : isGameOver() == {
	 *  					   true <==> (getNbLemmingCreated() == getSizeColony() && Card(getActivLemmings() == 0))
	 *  					   else false
	 *  					   } 
	 *  \inv : getScore =(min) (getNbLemmingsaved() / getNbLemmingCreated()) * 100 + getTour()
	 */
	
	
	
	// CONSTRUCTORS ------------------------------------------------------------
	/**
	 *  \pre : sc > 0 && sp > 0
	 *  \post : getTour(init(sc,sp,l)) == 0
	 *  \post : getMaxTour(init(sc,sp,l)) == 50
	 *  \post : getScore(init(sc,sp,l)) == 0
	 *  \post : isGameOver(init(sc,sp,l)) == false
	 *  \post : getSizeColony(init(sc,sp,l)) == sc
	 *  \post : getSpawnSpeed(init(sc,sp,l)) == sp
	 *  \post : getNbLemmingCreated(init(sc,sp,l)) == 0
	 *  \post : getNbLemmingSaved(init(sc,sp,l)) == 0
	 *  \post : getLevel(init(sc,sp,l)) == l
	 *  \post : getActivLemmings(init(sc,sp,l)) = []
	 **/
	public void init(int x, int y, ILevel level);
		
	
		
	// OPERATORS ---------------------------------------------------------------
	/**
	 *  \pre : isGameOver() == false
	 *  \post : getTour(step()) == getTour()@Pre + 1
	 *  \post : getNbLemmingCreated(step()) ==  FOR i (0..getMaxTour()) 
	 *  										(i % getSpawnSpeed() == 0 && getNbLemmingCreated() < getSizeColony()) <==> getNbLemmingCreated()@Pre + 1 
	 *  \post : getNbLemmingSaved(step()) == FORALL ILemming IN getActivLemming()
	 *  									 ILemming::isSaved() == true ==> getNbLemmingSaved()@Pre + 1
	 *  \post : getLevel(step()) == getLevel()@Pre
	 *  \post : getActivLemming(step()) <==> FORALL ILemming in getActivLemming(step()) ILemming::step()
	 */
	public void step();
}
