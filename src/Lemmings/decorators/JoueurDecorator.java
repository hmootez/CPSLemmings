package Lemmings.decorators;

import java.util.HashMap;

import Lemmings.services.Comportement;
import Lemmings.services.IJoueur;
import Lemmings.services.ILemming;

public abstract class JoueurDecorator implements IJoueur {
	
	private final IJoueur delegate;

	public JoueurDecorator(IJoueur delegate) {
		this.delegate = delegate;
	}
		
	@Override
	public HashMap<Comportement, Integer> getJetons() {
		return delegate.getJetons();
	}

	@Override
	public int getNbJetons() {
		return delegate.getNbJetons();
	}

	@Override
	public int getNbJetonsByComportement(Comportement c) {
		return delegate.getNbJetonsByComportement(c);
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

	@Override
	public int maxComportement() {
		return delegate.maxComportement();
	}

	@Override
	public void init(String name, int nb) {
		delegate.init(name, nb);		
	}

	@Override
	public void depenserJeton(Comportement c) {
		delegate.depenserJeton(c);		
	}

	@Override
	public void faireAction(ILemming l, Comportement instantComportement) {
		delegate.faireAction(l, instantComportement);
	}
}
