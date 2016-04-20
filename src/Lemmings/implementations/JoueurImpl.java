package Lemmings.implementations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import Lemmings.services.Comportement;
import Lemmings.services.IJoueur;
import Lemmings.services.ILemming;

public class JoueurImpl implements IJoueur {
	
	HashMap<Comportement, Integer>  jetons;
	String name;
	final int maxComportement = 4;

	public JoueurImpl(String name , int nb) {
		this.init(name, nb);
	}

	@Override
	public HashMap<Comportement, Integer> getJetons() {
		return jetons;
	}

	@Override
	public int getNbJetons() {
		int res = 0;
		Comportement[] comp = { Comportement.WALKER };
		
		for (int i = 0; i < jetons.size(); i++)
			res = res + jetons.get(comp[i]);
	
		return res;
	}

	@Override
	public int getNbJetonsByComportement(Comportement c) {
		return jetons.get(c);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int maxComportement() {
		return this.maxComportement;
	}

	@Override
	public void init(String name, int nb) {
		this.name = name;
		this.jetons = new HashMap<Comportement, Integer>(maxComportement);
		Comportement[] comp= { Comportement.WALKER,Comportement.FALLER} ;
		
		for (int i = 0; i < comp.length; i++)
			jetons.put(comp[i], nb);
	}

	@Override
	public void depenserJeton(Comportement c) {
		jetons.put(c, jetons.get(c)-1);
	}

	@Override
	public void faireAction(ILemming l, Comportement instantComportement) {
		l.changeComportement(instantComportement);
	}
	
	
	public void affichagePause() {
		
		System.out.println("Game Paused.");
		String chaine = "Inventaire ";

		Set cles = jetons.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()){
		   Comportement cle = (Comportement) it.next();
		   Integer valeur = jetons.get(cle); 
		   
		   chaine = chaine + " Comportement : "+cle+" jetons : "+ valeur ;
		   
		}
		
		
		//ArrayList<Comportement> cles = (ArrayList<Comportement>) jetons.keySet();
		
		//for(int i = 0 ; i< jetons.size();i++)
			
		
		System.out.println(chaine);
		System.out.println("Changement de comportement :  entrer x lemming");
		Scanner keyboard = new Scanner(System.in);
		int x = keyboard.nextInt();
		System.out.println("Changement de comportement :  entrer y lemming");
		int y = keyboard.nextInt();
		System.out.println("Changement de comportement :  entrer Comportement");
		int c = keyboard.nextInt();
		
		
	}

}
