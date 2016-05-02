package Lemmings.services;

import java.util.HashMap;

import Lemmings.tools.Comportement;

public interface IJoueur {
	
	// OBSERVATORS -------------------------------------------------------------
	HashMap<Comportement,Integer> getJetons();
	int getNbJetons();
	int getNbJetonsByComportement(Comportement c);
	String getName();
	int maxComportement();
	
	
	
	// INVARIANTS --------------------------------------------------------------
	/**
	 *  \inv : getNbJetons() =(min) card(getJetons())
	 *  \inv : FORALL c IN Comportement getNbJetonsByComportement(c) == VALUE(c, getJetons()), VALUE() est une fonction de HashMap
	 */
	
	
	
	// CONSTRUCTORS ------------------------------------------------------------
	/**
	 *  \pre : name != "" && nb > 1
	 *  \post : getName(init(nom,nb)) = nom 
	 *  \post : maxComportement(init(nom,nb)) = 4 
	 *  \post : getJetons(init(nom,nb)) != null
	 *  \post : getNbJetons(init(nom,nb)) = nb * maxComportement()
	*/
	public void init(String name, int nb);
	
	
	
	// OPERATORS ---------------------------------------------------------------
	/**
	 *  \pre : getNbJetons() > 0 && l IN IGameEng::getActivLemmings()
	 *  \post : getJetons(depenserJeton(c)) = getNbJetonsByComportement(c)@pre - 1  
	 *  \post : getName(depenserJeton(c)) = getName()@pre
	*/
	public void depenserJeton(Comportement c);
	
	
	/**
	 *  \pre : getNbJetons() > 0 && l IN IGameEng::getActivLemmings()
	 *  \post : getJetons(faireAction(l,ic)) = getNbJetonsByComportement(c)@pre - 1  
	 *  \post : getName(faireAction(l,ic)) = getName()@pre
	 *  \post : ILemming::getComportement(faireAction(l ,ic)) = ic
	*/
	public void faireAction(ILemming l, Comportement instantComportement);
}
