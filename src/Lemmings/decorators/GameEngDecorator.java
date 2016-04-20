package Lemmings.decorators;

import java.util.ArrayList;

import Lemmings.services.IGameEng;
import Lemmings.services.ILemming;
import Lemmings.services.ILevel;

public abstract class GameEngDecorator implements IGameEng {

	private final IGameEng delegate;

	public GameEngDecorator(IGameEng delegate) {
		this.delegate = delegate;
	}

	@Override
	public boolean obstacle(int x, int y) {

		return delegate.obstacle(x, y);
	}

	@Override
	public int getTour() {

		return delegate.getTour();
	}

	@Override
	public int getscore() {

		return delegate.getscore();
	}

	@Override
	public boolean gameOver() {
		return delegate.gameOver();
	}

	@Override
	public int getSizeColony() {
		return delegate.getSizeColony();
	}

	@Override
	public int getSpawnSpeed() {
		return delegate.getSpawnSpeed();
	}

	@Override
	public int getnblemmingcreated() {
		return delegate.getnblemmingcreated();
	}

	@Override
	public int getnblemmingsaved() {

		return delegate.getnblemmingsaved();
	}

	@Override
	public ILevel getLevel() {

		return delegate.getLevel();
	}

	@Override
	public ArrayList<ILemming> getActivLemmings() {
		return delegate.getActivLemmings();
	}

	@Override
	public void init(int x, int y, ILevel l) {
		delegate.init(x, y,l);
	}

	@Override
	public void step() {
		delegate.step();

	}

}