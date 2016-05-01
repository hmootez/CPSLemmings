package Lemmings.services;

import java.util.ArrayList;

public interface ILemming {
	
	// TYPES: bool, int, enum Comportement {WALKER, FALLER, DIGGER, CLIMBER, BUILDER, FLOATER, BOMBER, STOPPER, BASHER}
	// USE : IGameEng 
	
	
	
	// OBSERVATORS -------------------------------------------------------------
	public boolean isDroitier();
	public ArrayList<Comportement> getComportement();
	public int getX();
	public int getY();
	public int getFalling();
	public IGameEng getGameEng();
	public boolean isDead();
	public boolean isSaved();
	public int getDalles();
	public int getBuilderDelay();
	public int getBomberDelay();
	public int getBasherDelay();
	
	
	// INVARIANTS --------------------------------------------------------------
	/**
	 *  \inv : 0 <= getFalling() <= IGameEng::ILevel::getHeight()-2;
	 *  \inv : 0 < getX() <= IGameEng::ILevel::getWidht()-2 ;
	 *  \inv : 0 < getY() < = IGameEng::ILevel::getHeight()-2;
	 *  \inv : isDead() == {
	 *  				   true <==> getFalling() = 8 && getComportement() != Comportement.CLIMBER
     *                     false <==> getFalling() < 8
     *                     }
	 *  \inv : isSaved() == {
	 *  					true <==> getX() == IGameEng::ILevel::getXExit() && getY() == IGameEng::ILevel::getYExit()
	 *  					false else
	 *  					}
	 *  \inv : getBuilderDelay() <= 3
	 *  \inv : getBomberDelay() <= 5
	 *  \inv : getBasherDelay() <= 20 
	 */ 
	
	
	
	// CONSTRUCTORS ------------------------------------------------------------
	/**
	 *  \pre :  card(c) > 0
	 *  \post : isDroitier(init(c,core)) == True
	 *  \post : getComportement(init(c,core)) == c
	 *  \post : getX(init(c,core)) == IGameEng::ILevel::getXEntrance()
	 *  \post : getY(init(c,core)) == IGameEng::ILevel::getYEntrance() 
	 *  \post : getFalling(c,core)) == 0
	 *  \post : getGameEng(init(c,core)) == core
	 *  \post : isDead(init(c,core)) == false
	 *  \post : isSaved(init(c,core)) == false
	 *  \post : getDalles(init(c,core)) == 0
	 *  \post : getBuilderDelay(init(c,core)) == 0
	 *  \post : getBomberDelay(init(c,core)) == 5
	 *  \post : getBasherDelay(init(c,core)) == 20
	 **/
	public void init(ArrayList<Comportement> c, IGameEng core);
	
	
	
	// OPERATORS ---------------------------------------------------------------
	/**
	 *  // Remarque : l'opérateur (+) et (-) sont comme les méthodes prédéfinis des listes add() et remove() resp.
	 *  // exemple : maListe = maListe@pre (+) element <==> maListe.add(element)
	 *
	 * --------------------------------- WALKER --------------------------------
	 *  \post : Comportement.WALKER IN getComportement() && !(IGameEng::isObstacle(getX()@pre,getY()@pre+1)) 
	 *  	==> getComportement() = getComportement()@pre (-) Comportement.WALKER (+) Comportement.FALLER
	 *  		&& getX() = getX()@pre && getY() = getY()@pre && isDroitier() = isDroitier()@pre && getFalling() = 0
	 *  
	 *  \post : Comportement.WALKER IN getComportement() && isDroitier()@pre = True && (IGameEng::isObstacle(getX()@pre,getY()@pre+1)
	 *  		&& (IGameEng::isObstacle(getX()@pre+1,getY()@pre-1) ==> Comportement.WALKER IN getComportement() 
	 *  		&& getX() = getX()@pre && getY() = getY()@pre && isDroitier() = false && getFalling() = 0
	 *  
	 *  \post : Comportement.WALKER IN getComportement() && isDroitier()@pre = false && (IGameEng::isObstacle(getX()@pre,getY()@pre+1)
	 *	  		&& (IGameEng::isObstacle(getX()@pre-1,getY()@pre-1) ==> Comportement.WALKER IN getComportement() 
	 *  		&& getX() = getX()@pre && getY() = getY()@pre && isDroitier() = True && getFalling() = 0
	 *  
	 *  \post : Comportement.WALKER IN getComportement() && isDroitier()@pre = True && (IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *  		&& (IGameEng::isObstacle(getX()@pre+1,getY()@pre) && (IGameEng::isObstacle(getX()@pre+1,getY()@pre-2) 
	 *  	==> Comportement.WALKER IN getComportement() && getX() = getX()@pre && getY() = getY()@pre && isDroitier() = false && getFalling() = 0
	 *  
	 *  \post : Comportement.WALKER IN getComportement() && isDroitier()@pre = false && (IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *  		&& (IGameEng::isObstacle(getX()@pre-1,getY()@pre) && (IGameEng::isObstacle(getX()@pre-1,getY()@pre-2) 
	 *  	==> Comportement.WALKER IN getComportement() && getX() = getX()@pre && getY() = getY()@pre && isDroitier() = true && getFalling() = 0
	 *  
	 *  \post : Comportement.WALKER IN getComportement() && isDroitier()@pre = True && (IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *  		&& (IGameEng::isObstacle(getX()@pre+1,getY()@pre) && !(IGameEng::isObstacle(getX()@pre+1,getY()@pre-1)&& !(IGameEng::isObstacle(getX()@pre+1,getY()@pre-2) 
	 *  	==> Comportement.WALKER IN getComportement() && getX() = getX()@pre +1 && getY() = getY()@pre -1 && isDroitier() = true && getFalling() = 0
	 *  
	 *  \post : Comportement.WALKER IN getComportement() && isDroitier()@pre = false && (IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *  		&& (IGameEng::isObstacle(getX()@pre-1,getY()@pre) && !(IGameEng::isObstacle(getX()@pre-1,getY()@pre-1)&& !(IGameEng::isObstacle(getX()@pre-1,getY()@pre-2) 
	 *  	==> Comportement.WALKER IN getComportement() && getX() = getX()@pre -1 && getY() = getY()@pre -1 && isDroitier() = false && getFalling() = 0
	 *  
	 *  \post : Comportement.WALKER IN getComportement() && isDroitier()@pre = True && (IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *  		&& !(IGameEng::isObstacle(getX()@pre+1,getY()@pre) && !(IGameEng::isObstacle(getX()@pre+1,getY()@pre-1) 
	 *  	==> Comportement.WALKER IN getComportement() && getX() = getX()@pre +1 && getY() = getY()@pre && isDroitier() = true && getFalling() = 0
	 *  
	 *  \post : Comportement.WALKER IN getComportement() && isDroitier()@pre = false && (IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *  		&& !(IGameEng::isObstacle(getX()@pre-1,getY()@pre) && !(IGameEng::isObstacle(getX()@pre-1,getY()@pre-1) 
	 *  	==> Comportement.WALKER IN getComportement() && getX() = getX()@pre -1 && getY() = getY()@pre && isDroitier() = false && getFalling() = 0
	 *  
	 *  -------------------------------- FALLER --------------------------------
	 *  \post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER NOT IN getComportement() && isDroitier()@pre = True && !(IGameEng::isObstacle(getX()@pre,getY()@pre+1) && getFalling()@pre < 8 
	 *  	==> Comportement.FALLER IN getComportement() && getX() = getX()@pre  && getY() = getY()@pre +1 && isDroitier() = True && getFalling() = getFalling()@pre +1
	 *  
	 *  \post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER NOT IN getComportement() && isDroitier()@pre = false && !(IGameEng::isObstacle(getX()@pre,getY()@pre+1) && getFalling() < 8 
	 *  	==> Comportement.FALLER IN getComportement() && getX() = getX()@pre  && getY() = getY()@pre +1 && isDroitier() = false && getFalling() = getFalling()@pre +1
	 *  
	 *  \post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER NOT IN getComportement() && isDroitier()@pre = True && (IGameEng::isObstacle(getX()@pre,getY()@pre+1) && getFalling() < 8 
	 *  	==> getComportement() = getComportement()@pre (-) Comportement.FALLER (+) Comportement.WALKER 
	 *  		getX() = getX()@pre  && getY() = getY()@pre && isDroitier() = True && getFalling() = 0
	 *  
	 *  \post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER NOT IN getComportement() && isDroitier()@pre = false && (IGameEng::isObstacle(getX()@pre,getY()@pre+1) && getFalling() < 8 
	 *  	==> getComportement() = getComportement()@pre (-) Comportement.FALLER (+) Comportement.WALKER 
	 *  		&& getX() = getX()@pre  && getY() = getY()@pre && isDroitier() = false && getFalling() = 0
	 *  
	 *  \post : Comportement.FALLER IN getComportement() && Comportement.CLIMBER NOT IN getComportement() && (IGameEng::isObstacle(getX()@pre,getY()@pre+1) && (getFalling() == 8) ==> isDead() = true
	 *  
	 *  --------------------------------- Safe ---------------------------------
	 *  \post : getX()@pre = IGameEng::ILevel::getXExit() && getY()@pre = IGameEng::ILevel::getYExit() ==> isSafe() = true
	 *  
	 *  -------------------------------- DIGGER --------------------------------
	 *  \post : Comportement.DIGGER IN getComportement() && IGameEng::ILevel::nature(getX()@pre, getY()@pre+1) == Nature.EMPTY 
	 *  	==> getComportement() = getComportement()@pre (-) Comportement.DIGGER (+) Comportement.FALLER 
	 *  		&& getX() = getX()@pre && getY() = getY()@pre && getFalling() = 0
	 *  
	 *  \post : Comportement.DIGGER IN getComportement() && IGameEng::ILevel::nature(getX()@pre, getY()@pre+1) == Nature.METAL 
	 *  	==> getComportement() = getComportement()@pre (-) Comportement.DIGGER (+) Comportement.WALKER
	 *  		&& getX() = getX()@pre && getY() = getY()@pre && getFalling() = 0
	 *  
	 *  \post : Comportement.DIGGER IN getComportement() && IGameEng::ILevel::nature(getX()@pre, getY()@pre+1) == Nature.DIRT 
	 *  	==> Comportement.DIGGER IN getComportement()
	 *  		&& getX() = getX()@pre && getY() = getY()@pre + 1 && getFalling() = 0 && IGameEng::ILevel::remove(getX()@pre, getY()@pre+1)
	 *  		&& if (IGameEng::ILevel::nature(getX()@pre-1, getY()@pre+1) == Nature.DIRT) { IGameEng::ILevel::remove(getX()@pre-1, getY()@pre+1) }
	 *  		&& if (IGameEng::ILevel::nature(getX()@pre+1, getY()@pre+1) == Nature.DIRT) { IGameEng::ILevel::remove(getX()@pre+1, getY()@pre+1) }
	 *  
	 *  -------------------------------- CLIMBER -------------------------------
	 *  \post : (isDroitier()@pre && Comportement.CLIMBER IN getComportement() 
	 *			 && IGameEng::isObstacle(getX()@pre+1,getY()@pre) 
	 *			 && IGameEng::isObstacle(getX()@pre+1,getY()@pre+1) 
	 *			 && IGameEng::Level::nature(getX()@pre, getY()@pre+1) == Nature.EMPTY
	 *			 && IGameEng::Level::nature(getX()@pre, getY()@pre+2) == Nature.EMPTY)
	 * 			 ==> isDroitier() = true && Comportement.CLIMBER IN getComportement()
	 * 			 && getX() = getX()@pre && getY() = getY()@pre+1
	 *			 
	 *	\post : (!isDroitier()@pre && Comportement.CLIMBER IN getComportement() 
	 *			 && IGameEng::isObstacle(getX()@pre-1,getY()@pre) 
	 *			 && IGameEng::isObstacle(getX()@pre-1,getY()@pre+1) 
	 *			 && IGameEng::Level::nature(getX()@pre, getY()@pre+1) == Nature.EMPTY
	 *			 && IGameEng::Level::nature(getX()@pre, getY()@pre+2) == Nature.EMPTY)
	 *			 ==> isDroitier() = false && Comportement.CLIMBER IN getComportement()
	 *			 && getX() = getX()@pre && getY() = getY()@pre+1
	 *	
	 *	\post : (isDroitier()@pre && Comportement.CLIMBER IN getComportement() 
	 *			 && IGameEng::isObstacle(getX()@pre+1,getY()@pre) 
	 *			 && IGameEng::Level::nature(getX()@pre+1, getY()@pre+1) == Nature.EMPTY
	 *			 && IGameEng::Level::nature(getX()@pre, getY()@pre+1) == Nature.EMPTY
	 *			 && IGameEng::Level::nature(getX()@pre, getY()@pre+2) == Nature.EMPTY)
	 *			 ==> isDroitier() = true && Comportement.CLIMBER IN getComportement()
	 *			 && getX() = getX()@pre+1 && getY() = getY()@pre+1
	 *			 
	 *	\post : (!isDroitier()@pre && Comportement.CLIMBER IN getComportement() 
	 *			 && IGameEng::isObstacle(getX()@pre-1,getY()@pre) 
	 *			 && IGameEng::Level::nature(getX()@pre-1, getY()@pre+1) == Nature.EMPTY
	 *			 && IGameEng::Level::nature(getX()@pre, getY()@pre+1) == Nature.EMPTY
	 *			 && IGameEng::Level::nature(getX()@pre, getY()@pre+2) == Nature.EMPTY)
	 *			 ==> isDroitier() = true && Comportement.CLIMBER IN getComportement()
	 *			 && getX() = getX()@pre+1 && getY() = getY()@pre+1
	 *			 
	 * 	-------------------------------- BUILDER -------------------------------
	 *  \post : Comportement.BUILDER IN getComportement() && getBuilderDelay() == 0 && getDalles() < 12 && IGameEng::ILevel::nature(getX()@pre+1, getY()@pre) == Nature.EMPTY 
	 *  		&& IGameEng::ILevel::nature(getX()@pre+2, getY()@pre) == Nature.EMPTY && IGameEng::ILevel::nature(getX()@pre+3, getY()@pre) == Nature.EMPTY 
	 *  	==> Comportement.BUILDER IN getComportement() && getBuilderDelay() == 1 
	 *  
	 *  \post : Comportement.BUILDER IN getComportement() && getBuilderDelay() == 1 && getDalles() < 12 ==> Comportement.BUILDER IN getComportement()
	 *  		&& getBuilderDelay() == 2 
	 *  
	 *  \post : Comportement.BUILDER IN getComportement() && getBuilderDelay() == 2 && getDalles() < 12 ==> Comportement.BUILDER IN getComportement()
	 *  		&& getBuilderDelay() == 3 
	 *  
	 *  \post : Comportement.BUILDER IN getComportement() && getBuilderDelay() == 3 && getDalles() < 12 ==> Comportement.BUILDER IN getComportement()
	 *  		&& getBuilderDelay() == 0 
	 *  		&& getDalles() == getDalles@pre+1
	 *  		&& IGameEng::ILevel::nature(getX()@pre+1, getY()@pre) == Nature.DIRT
	 *  		&& IGameEng::ILevel::nature(getX()@pre+2, getY()@pre) == Nature.DIRT  
	 *  		&& IGameEng::ILevel::nature(getX()@pre+3, getY()@pre) == Nature.DIRT
	 *  		&& getX() = getX()@pre+2 && getY() = getY()@pre-1
     *		
	 *	-------------------------------- FLOATER -------------------------------
	 *  \post : Comportement.FLOATER IN getComportement() && isDroitier()@pre = True && getComportement() contains Comportement.FALLER
	 *  		&& IGameEng::getTour() mod 2 == 0 && !(IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *  	==> Comportement.FLOATER IN getComportement() && Comportement.FALLER IN getComportement()
	 *   		&& getX() = getX()@pre && getY() = getY()@pre+1 && isDroitier() = True && getFalling() = getFalling()@pre
	 *   
	 *  \post : Comportement.FLOATER IN getComportement() && isDroitier()@pre = True && getComportement() contains Comportement.FALLER
	 *   		&& IGameEng::getTour() mod 2 == 1 && !(IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *   	==> Comportement.FLOATER IN getComportement() && Comportement.FALLER IN getComportement()
	 *   		&& getX() = getX()@pre && getY() = getY()@pre && isDroitier() = True && getFalling() = getFalling()@pre
	 *   
	 *  \post : Comportement.FLOATER IN getComportement() && isDroitier()@pre = false && getComportement() contains Comportement.FALLER
	 *   		&& IGameEng::getTour() mod 2 == 0 && !(IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *   	==> Comportement.FLOATER IN getComportement() && Comportement.FALLER IN getComportement()
	 *   		&& getX() = getX()@pre && getY() = getY()@pre+1 && isDroitier() = false && getFalling() = getFalling()@pre  
	 *   
	 *  \post : Comportement.FLOATER IN getComportement() && isDroitier()@pre = false && getComportement() contains Comportement.FALLER
	 *   		&& IGameEng::getTour() mod 2 == 1 && !(IGameEng::isObstacle(getX()@pre,getY()@pre+1) 
	 *   	==> Comportement.FLOATER IN getComportement() && Comportement.FALLER IN getComportement()
	 *   		&& getX()= getX()@pre && getY() = getY()@pre && isDroitier() = false && getFalling() = getFalling()@pre
	 *   
	 *   -------------------------------- BOMBER -------------------------------
	 *  \post : Comportement.BOMBER IN getComportement() && getBomberDelay() > 0 ==> getBomberDelay() = getBomberDelay()@Pre-1
	 *   
	 *  \post : Comportement.BOMBER IN getComportement() && getBomberDelay() == 0 ==> isDead() = true 
	 *  		&& if (IGameEng::ILevel::nature(getX(), getY()+1) != Nature.DIRT) { IGameEng::ILevel::setNature(getX(), getY()+1,Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX(), getY()-1) != Nature.DIRT) { IGameEng::ILevel::setNature(getX(), getY()-1,Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()+1, getY()+1) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()+1, getY()+1,Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()-1, getY()+1) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()-1, getY()+1,Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()+1, getY()) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()+1, getY(),Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()-1, getY()) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()-1, getY(),Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()+2, getY()) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()+2, getY(),Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()-2, getY()) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()-2, getY(),Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()+1, getY()-1) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()+1, getY()-1,Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()-1, getY()-1) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()-1, getY()-1,Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()+1, getY()-2) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()+1, getY()-2,Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()-1, getY()-2) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()-1, getY()-2,Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()+1, getY()-1) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()+1, getY()-1,Nature.DIRT) }
	 *      	&& if (IGameEng::ILevel::nature(getX()-1, getY()-1) != Nature.DIRT) { IGameEng::ILevel::setNature(getX()-1, getY()-1,Nature.DIRT) } 
	 *  
	 *  -------------------------------- STOPPER -------------------------------
	 *  \post : getComportement() contains Comportement.STOPPER ==> IGameEng::isObstacle(getX()@pre,getY()@pre) 
	 *  		&& IGameEng::isObstacle(getX()@pre,getY()@pre-1)
	 * 
	 *  -------------------------------- BASHER --------------------------------
	 *  \post : Comportement.BASHER IN getComportement() 
	 *  		&& (getBasherDelay() == 0 || 
	 *  			IGameEng::ILevel::nature(getX()@pre+1, getY()@pre) == Nature.METAL || 
	 *  			IGameEng::ILevel::nature(getX()@pre, getY()@pre-1) == Nature.EMPTY) 
	 *  		==> getComportement() = getComportement()@Pre (-) Comportement.BASHER (+) Comportement.WALKER 
	 *  
	 *  \post : (Comportement.BASHER IN getComportement() && isDroitier() && getBasherDelay() > 0 
	 *  	 	&& IGameEng::isObstacle(getX()@pre,getY()@pre-1)
	 *  		&& IGameEng::ILevel::nature(getX()@pre+1, getY()@pre) != Nature.METAL
	 *  		&& IGameEng::ILevel::nature(getX()@pre+2, getY()@pre) != Nature.METAL
	 *  		&& IGameEng::ILevel::nature(getX()@pre+3, getY()@pre) != Nature.METAL)
	 *  		==> IGameEng::ILevel::setNature(getX()@pre+1, getY()@pre,Nature.EMPTY)
	 * 			&& IGameEng::ILevel::setNature(getX()@pre+2, getY()@pre,Nature.EMPTY)
	 *  		&& IGameEng::ILevel::setNature(getX()@pre+3, getY()@pre,Nature.EMPTY)
	 *  		&& getBasherDelay() = getBasherDelay()@Pre-1
	 *  		&& getX()= getX()@pre+1 && getY() = getY()@pre
	 *  
	 *  \post : (Comportement.BASHER IN getComportement() && !isDroitier() && getBasherDelay() > 0 
	 *  	 	&& IGameEng::isObstacle(getX()@pre,getY()@pre-1)
	 *  		&& IGameEng::ILevel::nature(getX()@pre-1, getY()@pre) != Nature.METAL
	 *  		&& IGameEng::ILevel::nature(getX()@pre-2, getY()@pre) != Nature.METAL
	 *  		&& IGameEng::ILevel::nature(getX()@pre-3, getY()@pre) != Nature.METAL)
	 *  		==> IGameEng::ILevel::setNature(getX()@pre-1, getY()@pre, Nature.EMPTY)
	 * 			&& IGameEng::ILevel::setNature(getX()@pre-2, getY()@pre, Nature.EMPTY)
	 *  		&& IGameEng::ILevel::setNature(getX()@pre-3, getY()@pre, Nature.EMPTY)
	 *  		&& getBasherDelay() = getBasherDelay()@Pre-1
	 *  		&& getX()= getX()@pre-1 && getY() = getY()@pre
	 */
	public void step();
	
	
	/** 
	 *  \post : isDroitier(changeComportement(c)) == isDroitier()@pre
	 *  \post : getX(changeComportement(c)) == getX()@pre
	 *  \post : getY(changeComportement(c)) == getY()@pre
	 *  \post : getFalling(changeComportement(c)) == getFalling()@pre
	 *  \post : getGameEng(changeComportement(c)) == getGameEng()@pre 
	 *  \post : getComportement(changeComportement(c)) == c
	 *  \post : isDead(changeComportement(c)) == isDead()@pre
	 *  \post : isSaved(changeComportement(c)) == isSaved()@pre
	 *  \post : getDalles(changeComportement(c)) == getDalles()@pre
	 *  \post : getBuilderDelay(changeComportement(c)) == getBuilderDelay()@pre
	 *  \post : getBomberDelay(changeComportement(c)) == getBomberDelay()@pre
	 *  \post : getBasherDelay(changeComportement(c)) == getBasherDelay()@pre
	 */
	public void changeComportement(Comportement c);
}