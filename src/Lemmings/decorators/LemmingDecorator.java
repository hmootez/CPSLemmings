package Lemmings.decorators;

import java.util.ArrayList;

import Lemmings.services.Comportement;
import Lemmings.services.IGameEng;
import Lemmings.services.ILemming;

public abstract class LemmingDecorator implements ILemming {

	private final ILemming delegate;
	
	public LemmingDecorator(ILemming delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public boolean isDroitier() {
		return delegate.isDroitier();
	}

	@Override
	public ArrayList<Comportement> getComportement() {
		return delegate.getComportement();
	}

	@Override
	public int getX() {
		return delegate.getX();
	}

	@Override
	public int getY() {
		return delegate.getY();
	}
	
	@Override
	public int getFalling() {
		return delegate.getFalling();
	}

	@Override
	public IGameEng getGameEng() {
		return delegate.getGameEng();
	}

	@Override
	public boolean isDead() {
		return delegate.isDead();
	}

	@Override
	public boolean isSaved() {
		return delegate.isSaved();
	}
	
	@Override
	public void init(ArrayList<Comportement> c , IGameEng core) {
		delegate.init(c, core);
	}

	@Override
	public void step() {
		delegate.step();
	}

	@Override
	public void changeComportement(Comportement c) {
		delegate.changeComportement(c);
	}

	@Override
	public int getDalles() {
		return delegate.getDalles();
	}

	@Override
	public int getTourIncr() {
		return delegate.getTourIncr();
	}
}
