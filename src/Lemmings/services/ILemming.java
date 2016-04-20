package Lemmings.services;

import java.util.ArrayList;

public interface ILemming {
	//Types: bool, int, enum Direction {droitier,gaucher},
	//enum Comportement {WALKER,FALLER,DIGGER,CLIMBER,BUILDER,FLOATER,BOMBER,STOPPER,BASHER}
	//use : IGameEng 
	
	/*Observateurs*/
	
	public boolean isDroitier();
	
	public ArrayList<Comportement> getComportement();
	
	public int getX();
	
	public int getY();
	
	public int getFalling();
	
	public IGameEng getGameEng();
	
	public boolean isDead();
	
	public boolean isSaved();
	
	public int getDalles();
	
	public int getTourIncr();
	
	
	/**
	 * pre :  card(c) > 0
	 * post : isDroitier(init(c,core)) = True
	 * post : getComportement(init(c,core)) = c
	 * a modifier
	 * post : getX(init(c,core)) = IGameEng::ILevel::getXEntrance()
	 * post : getY(init(c,core)) = IGameEng::ILevel::getYEntrance()
	 * post : isDead(init(c,core)) = false 
	 * post : getFalling(c,core)) = 0
	 * post : getGameEng(init(c,core)) = core
	 * post : getDalles(init(c,core)) = 0
	 * post : getTourIncr(init(c,core)) = 0
	 * 
	 **/
	public void init(ArrayList<Comportement> c, IGameEng core);
	
	
	/*Operateurs*/
	/**
	 * --------------------------------- WALKER --------------------------------
	 * post : Comportement.WALKER IN getComportement() &&  !(IGameEng::obstacle(getX(),getY()+1)) => getComportement = Comportement.FALLER
	 *  && getX()= getX()@pre && getY()= getY()@pre && isDroitier() = isDroitier()@pre && getFalling() = 0 ;
	 *  
	 * post : Comportement.WALKER IN getComportement() && isDroitier = True &&  (IGameEng::obstacle(getX(),getY()+1)
	 *  && (IGameEng::obstacle(getX()+1,getY()-1) => getComportement = Comportement.WALKER 
	 *  && getX()= getX()@pre && getY()= getY()@pre && isDroitier() = false  && getFalling() = 0 ;
	 *  
	 *  post : Comportement.WALKER IN getComportement() && isDroitier = false &&  (IGameEng::obstacle(getX(),getY()+1)
	 *  && (IGameEng::obstacle(getX()-1,getY()-1) => getComportement = Comportement.WALKER 
	 *  && getX()= getX()@pre && getY()= getY()@pre && isDroitier() = True  && getFalling() = 0 ;
	 *  
	 *  post : Comportement.WALKER IN getComportement() && isDroitier = True &&  (IGameEng::obstacle(getX(),getY()+1) 
	 *  &&  (IGameEng::obstacle(getX()+1,getY()) &&  (IGameEng::obstacle(getX()+1,getY()-2) =>  getComportement = Comportement.WALKER 
	 *  && getX()= getX()@pre && getY()= getY()@pre && isDroitier() = false  && getFalling() = 0 ;
	 *  
	 * post : Comportement.WALKER IN getComportement() && isDroitier = false &&  (IGameEng::obstacle(getX(),getY()+1) 
	 *  &&  (IGameEng::obstacle(getX()-1,getY()) &&  (IGameEng::obstacle(getX()-1,getY()-2) =>  getComportement = Comportement.WALKER 
	 *  && getX()= getX()@pre && getY()= getY()@pre && isDroitier() = true  && getFalling() = 0 ;  
	 *  
	 *  post : Comportement.WALKER IN getComportement() && isDroitier = True &&  (IGameEng::obstacle(getX(),getY()+1) 
	 *  &&  (IGameEng::obstacle(getX()+1,getY()) && !(IGameEng::obstacle(getX()+1,getY()-1)&& !(IGameEng::obstacle(getX()+1,getY()-2) =>  getComportement = Comportement.WALKER 
	 *  && getX()= getX()@pre +1 && getY()= getY()@pre -1 && isDroitier() = true  && getFalling() = 0 ;
	 *  
	 *  post : Comportement.WALKER IN getComportement() && isDroitier = false &&  (IGameEng::obstacle(getX(),getY()+1) 
	 *  &&  (IGameEng::obstacle(getX()-1,getY()) && !(IGameEng::obstacle(getX()-1,getY()-1)&& !(IGameEng::obstacle(getX()-1,getY()-2) =>  getComportement = Comportement.WALKER 
	 *  && getX()= getX()@pre -1 && getY()= getY()@pre -1 && isDroitier() = false  && getFalling() = 0 ;
	 *  
	 *  post : Comportement.WALKER IN getComportement() && isDroitier = True &&  (IGameEng::obstacle(getX(),getY()+1) 
	 *  &&  !(IGameEng::obstacle(getX()+1,getY()) && !(IGameEng::obstacle(getX()+1,getY()-1)=>  getComportement = Comportement.WALKER 
	 *  && getX()= getX()@pre +1 && getY()= getY()@pre && isDroitier() = true  && getFalling() = 0 ;
	 *  
	 *  post : Comportement.WALKER IN getComportement() && isDroitier = false &&  (IGameEng::obstacle(getX(),getY()+1) 
	 *  &&  !(IGameEng::obstacle(getX()-1,getY()) && !(IGameEng::obstacle(getX()-1,getY()-1)=>  getComportement = Comportement.WALKER 
	 *  && getX()= getX()@pre -1 && getY()= getY()@pre && isDroitier() = false  && getFalling() = 0 ;
	 *  
	 *  -------------------------------- FALLER --------------------------------
	 * post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER IN getComportement() && isDroitier = True && !(IGameEng::obstacle(getX(),getY()+1)&& getFalling() < 8 =>  getComportement = Comportement.FALLER
	 *  && getX()= getX()@pre  && getY()= getY()@pre +1 && isDroitier() = True  && getFalling() = getFalling()@pre +1 ;
	 *  
	 *  post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER IN getComportement() && isDroitier = false && !(IGameEng::obstacle(getX(),getY()+1) && getFalling() < 8 =>  getComportement = Comportement.FALLER
	 *  && getX()= getX()@pre  && getY()= getY()@pre +1 && isDroitier() = false  && getFalling() = getFalling()@pre +1 ;
	 *  
	 *  post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER IN getComportement() && isDroitier = True && (IGameEng::obstacle(getX(),getY()+1) && getFalling() < 8 =>  getComportement = Comportement.WALKER
	 *  && getX()= getX()@pre  && getY()= getY()@pre && isDroitier() = True  && getFalling() = 0;
	 *  
	 *   post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER IN getComportement() && isDroitier = false && (IGameEng::obstacle(getX(),getY()+1) && getFalling() < 8 =>  getComportement = Comportement.WALKER
	 *  && getX()= getX()@pre  && getY()= getY()@pre && isDroitier() = false  && getFalling() = 0 ;
	 *  
	 *  post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER IN getComportement() && (IGameEng::obstacle(getX(),getY()+1) && (getFalling() == 8) =>  isDead() = true
	 *  
	 *  --------------------------------- Safe ---------------------------------
	 *  post : getX() = IGameEng::ILevel::getXExit() && getY() = IGameEng::ILevel::getYExit() => isSafe = true
	 *  
	 *  -------------------------------- DIGGER --------------------------------
	 *  post : Comportement.DIGGER IN getComportement() &&  IGameEng::ILevel::nature(getX(), getY()+1) == Nature.EMPTY => getComportement = Comportement.FALLER
	 *  && getX() = getX()@pre && getY() = getY()@pre && getFalling() = 0
	 *  
	 *  post : Comportement.DIGGER IN getComportement() &&  IGameEng::ILevel::nature(getX(), getY()+1) == Nature.METAL => getComportement = Comportement.WALKER
	 *  && getX() = getX()@pre && getY()= getY()@pre && getFalling() = 0
	 *  
	 *  post : Comportement.DIGGER IN getComportement() && IGameEng::ILevel::nature(getX(), getY()+1) == Nature.DIRT => getComportement = Comportement.DIGGER
	 *  && getX() = getX()@pre && getY() = getY()@pre + 1 && getFalling() = 0 && IGameEng::ILevel::remove(getX()@pre, getY()@pre+1)
	 *  && if (IGameEng::ILevel::nature(getX()@pre-1, getY()@pre+1) == Nature.DIRT) { IGameEng::ILevel::remove(getX()@pre-1, getY()@pre+1) }
	 *  && if (IGameEng::ILevel::nature(getX()@pre+1, getY()@pre+1) == Nature.DIRT) { IGameEng::ILevel::remove(getX()@pre+1, getY()@pre+1) }
	 *  
	 *  -------------------------------- CLIMBER -------------------------------
	 *  post : (isDroitier_pre && Comportement.CLIMBER IN getComportement() 
				 && IGameEng::obstacle(getX_pre+1,getY_pre) 
				 && IGameEng::obstacle(getX_pre+1,getY_pre+1) 
				 && IGameEng::Level::nature(getX_pre, getY_pre+1) == Nature.EMPTY
				 && IGameEng::Level::nature(getX_pre, getY_pre+2) == Nature.EMPTY)
				 => isDroitier() = true && getComportement() = Comportement.CLIMBER
				 && getX() = getX()@pre && getY() = getY()@pre+1
				 
		post : (!isDroitier_pre && Comportement.CLIMBER IN getComportement() 
				 && IGameEng::obstacle(getX_pre-1,getY_pre) 
				 && IGameEng::obstacle(getX_pre-1,getY_pre+1) 
				 && IGameEng::Level::nature(getX_pre, getY_pre+1) == Nature.EMPTY
				 && IGameEng::Level::nature(getX_pre, getY_pre+2) == Nature.EMPTY)
				 => isDroitier() = false && getComportement() = Comportement.CLIMBER
				 && getX() = getX()@pre && getY() = getY()@pre+1
		
		post : (isDroitier_pre && Comportement.CLIMBER IN getComportement() 
				 && IGameEng::obstacle(getX_pre+1,getY_pre) 
				 && IGameEng::Level::nature(getX_pre+1, getY_pre+1) == Nature.EMPTY
				 && IGameEng::Level::nature(getX_pre, getY_pre+1) == Nature.EMPTY
				 && IGameEng::Level::nature(getX_pre, getY_pre+2) == Nature.EMPTY)
				 => isDroitier() = true && getComportement() = Comportement.CLIMBER
				 && getX() = getX()@pre+1 && getY() = getY()@pre+1
				 
		post : (!isDroitier_pre && Comportement.CLIMBER IN getComportement() 
				 && IGameEng::obstacle(getX_pre-1,getY_pre) 
				 && IGameEng::Level::nature(getX_pre-1, getY_pre+1) == Nature.EMPTY
				 && IGameEng::Level::nature(getX_pre, getY_pre+1) == Nature.EMPTY
				 && IGameEng::Level::nature(getX_pre, getY_pre+2) == Nature.EMPTY)
				 => isDroitier() = true && getComportement() = Comportement.CLIMBER
				 && getX() = getX()@pre+1 && getY() = getY()@pre+1
				 
		-------------------------------- BUILDER -------------------------------
	 *  post : Comportement.BUILDER IN getComportement() && getDalles() < 12 && getTourIncr() == 0 && IGameEng::ILevel::nature(getX()+1, getY()) == Nature.EMPTY 
	 *  && IGameEng::ILevel::nature(getX()+2, getY()) == Nature.EMPTY && IGameEng::ILevel::nature(getX()+3, getY()) == Nature.EMPTY => getComportement = Comportement.BUILDER
	 *  && getTourIncr() == 1 
	 *  
	 *  post : Comportement.BUILDER IN getComportement() && getDalles() < 12 && getTourIncr() == 1 => getComportement = Comportement.BUILDER
	 *  && getTourIncr() == 2 
	 *  
	 *   post : Comportement.BUILDER IN getComportement() && getTourIncr() == 2 && getDalles() < 12 => getComportement = Comportement.BUILDER
	 *  && getTourIncr() == 3 
	 *  
	 *   post : Comportement.BUILDER IN getComportement() && getTourIncr() == 3 && getDalles() < 12 => getComportement = Comportement.BUILDER
	 *   && getTourIncr() == 0 
	 *   && getDalles() == getDalles@pre+1
	 *   && IGameEng::ILevel::nature(getX()@pre+1, getY()@pre) == Nature.DIRT
	 *   && IGameEng::ILevel::nature(getX()@pre+2, getY()@pre) == Nature.DIRT  
	 *   && IGameEng::ILevel::nature(getX()@pre+3, getY()@pre) == Nature.DIRT
	 *   && getX() = getX()@pre  + 2 && getY() = getY()@pre -1
		
		-------------------------------- FLOATER -------------------------------
	 *   post : getComportement() contains Comportement.FLOATER && isDroitier = True && getComportement() contains Comportement.FALLER
	 *   && IGameEng::getTour() mod 2 == 0  &&  !(IGameEng::obstacle(getX(),getY()+1) =>  getComportement contains Comportement.FLOATER 
	 *   && getComportement() contains Comportement.FALLER
	 *   && getX()= getX()@pre  && getY() = getY()@pre +1 && isDroitier() = True  && getFalling() = getFalling()@pre;
	 *   
	 *   post : getComportement() contains Comportement.FLOATER && isDroitier = True && getComportement() contains Comportement.FALLER
	 *   && IGameEng::getTour() mod 2 == 1 && !(IGameEng::obstacle(getX(),getY()+1) =>  getComportement contains Comportement.FLOATER 
	 *   && getComportement() contains Comportement.FALLER
	 *   && getX()= getX()@pre  && getY()= getY()@pre && isDroitier() = True  && getFalling() = getFalling()@pre;  
	 *   
	 *    post : getComportement() contains Comportement.FLOATER && isDroitier = FALSE && getComportement() contains Comportement.FALLER
	 *   && IGameEng::getTour() mod 2 == 0 && !(IGameEng::obstacle(getX(),getY()+1) =>  getComportement contains Comportement.FLOATER 
	 *   && getComportement() contains Comportement.FALLER
	 *   && getX()= getX()@pre  && getY()= getY()@pre+1 && isDroitier() = false  && getFalling() = getFalling()@pre;  
	 *   
	 *   post : getComportement() contains Comportement.FLOATER && isDroitier = false && getComportement() contains Comportement.FALLER
	 *   && IGameEng::getTour() mod 2 == 1 && !(IGameEng::obstacle(getX(),getY()+1) =>  getComportement contains Comportement.FLOATER 
	 *   && getComportement() contains Comportement.FALLER
	 *   && getX()= getX()@pre  && getY()= getY()@pre && isDroitier() = false  && getFalling() = getFalling()@pre;
	 *   
	 *   -------------------------------- BOMBER -------------------------------
	 *   post : Comportement.BOMBER IN getComportement()  
	 *   => isDead() = true && if (IGameEng::ILevel::nature(getX(), getY()+1) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX(), getY()+1,Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX(), getY()-1) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX(), getY()-1,Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()+1, getY()+1) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()+1, getY()+1,Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()-1, getY()+1) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()-1, getY()+1,Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()+1, getY()) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()+1, getY(),Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()-1, getY()) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()-1, getY(),Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()+2, getY()) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()+2, getY(),Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()-2, getY()) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()-2, getY(),Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()+1, getY()-1) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()+1, getY()-1,Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()-1, getY()-1) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()-1, getY()-1,Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()+1, getY()-2) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()+1, getY()-2,Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()-1, getY()-2) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()-1, getY()-2,Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()+1, getY()-1) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()+1, getY()-1,Nature.DIRTY) }
	 *      isDead() = true && if (IGameEng::ILevel::nature(getX()-1, getY()-1) != Nature.DIRTY) { IGameEng::ILevel::setNature(getX()-1, getY()-1,Nature.DIRTY) }
	 *  -------------------------------- STOPPER -------------------------------
	 *    post : getComportement() contains Comportement.STOPPER =>  IGameEng::obstacle(getX()@pre,getY()@pre) 
	 *    &&  IGameEng::obstacle(getX()@pre,getY()@pre -1)
	 * 
	 *  TODO le reste des observateurs..
	 */
	public void step();
	
	
	/** post : isDroitier(changeComportement(c)) = isDroitier()@pre
	*   post : getX(changeComportement(c)) = getX()@pre
	*   post : getY(changeComportement(c)) = getY()@pre
	*   post : getFalling(changeComportement(c)) = getFalling()@pre
	*   post : getGameEng(changeComportement(c)) = getGameEng()@pre 
	*   post : getComportement(changeComportement(c)) = c
	*   post : isDead(changeComportement(c)) = isDead()@pre
	*   post : isSaved(changeComportement(c)) = isSaved()@pre
	*   post : getDalles(changeComportement(c)) = getDalles()@pre
	*   post : getTourIncr(changeComportement(c)) = getTourIncr()@pre
	*/
	public void changeComportement(Comportement c);
	
	
	/**Observations
	*INVARIANTS :
	*inv: 0 <= getFalling() <= IGameEng :: getLevel :: height() - 2  ;
	*inv: 0 < getX() < = IGameEng :: getLevel :: widht() - 2 ;
	*inv : 0 < getY() < = IGameEng :: getLevel :: height() - 2 ;
	*inv : isDead() = true <=> getFalling() = 8 
	*inv : isDead() = false <=> getFalling() < 8
	*inv : XXX isSaved()???
	*/ 
}