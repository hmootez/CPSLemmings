package Lemmings.services;

import java.util.ArrayList;

public interface IGameEng{
		
		//Types: bool, int, enum Nature {EMPTY, DIRT, METAL}, Set<Integer>
		//use : ILevel, ILemming
		
		/*Observateurs*/
		
		public boolean obstacle(int x,int y);
		
		public int getTour();
		
		// pre : gameOver()=true 
		public int getscore();
		
		public boolean gameOver();
		
		public int getSizeColony();
		
		public int getSpawnSpeed();
		
		public int getnblemmingcreated();
		
		public int getnblemmingsaved();
		
		public ILevel getLevel() ;
		
		public ArrayList<ILemming> getActivLemmings();
		

		/*Constructeurs*/
		/**pre : sc>0 & sp>0
		 * post : getSizeColony(init(sc,sp,l))=sc
		 * post : getSpawnSpeed(init(sc,sp,l))=sp
		 * post : getActivLemmings(init(sc,sp,l))=[]
		 * post : getTour(init(sc,sp,l))=0
		 * post : gameOver(init(sc,sp,l))=false
		 * post : getLevel(init(sc,sp,l))= l
		 **/
		public void init(int x, int y, ILevel level);
		
		
		/*Operateurs*/
		/**pre : gameOver()=false
		 * post :  FORALL ILemming in getActivLemming() ILemming::step()
		 */
		public void step();
		
		
		/**Observations*/
		//INVARIANTS :
		//getnblemmingcreated() <= getSizeColony() ;
		//inv: obstacle(x,y) == ILevel::nature(x,y)==(Nature.DIRT ||Nature.METAL)
		//inv : Card(getActivLemmings()) <= getSizeColony()
		//inv : gameOver() = true <=> getnblemmingcreated() = getSizeColony() && Card(getActivLemmings() = 0)
		//inv : getscore =  (getnblemmingsaved() / getnblemmingcreated())*100 + getTour()  
		
		
		
		
	}
