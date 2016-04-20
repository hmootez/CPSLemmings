package Lemmings.factory;

import java.util.ArrayList;

import Lemmings.implementations.GameEngImpl;
import Lemmings.implementations.JoueurImpl;
import Lemmings.implementations.LemmingImpl;
import Lemmings.implementations.LevelImpl;
import Lemmings.services.Comportement;
import Lemmings.services.IGameEng;
import Lemmings.services.IJoueur;
import Lemmings.services.ILemming;
import Lemmings.services.ILevel;

public class FactoryImpl implements IFacory {

	@Override
	public ILevel makeLevel(int x, int y) {
		return new LevelImpl(x, y);
	}

	@Override
	public IGameEng makeGameEng(int sc, int sp, ILevel level) {
		return  new GameEngImpl(sc, sp, level);
	}

	@Override
	public ILemming makeLemming(ArrayList<Comportement> c, IGameEng core) {
		return new LemmingImpl(c, core);
	}

	@Override
	public IJoueur makeJoueur(String name, int nb) {
		return new JoueurImpl(name, nb);
	}
}
