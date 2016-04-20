package Lemmings.factory;

import java.util.ArrayList;

import Lemmings.services.Comportement;
import Lemmings.services.IGameEng;
import Lemmings.services.IJoueur;
import Lemmings.services.ILemming;
import Lemmings.services.ILevel;


public interface IFacory {
	public ILevel makeLevel(int x, int y);
	public IGameEng makeGameEng(int sc, int sp, ILevel level);
	public ILemming makeLemming(ArrayList<Comportement> c, IGameEng core);
	public IJoueur makeJoueur(String name, int nb);
}
