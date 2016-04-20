package Lemmings.decorators;

import Lemmings.services.ILevel;
import Lemmings.services.Nature;

public abstract class LevelDecorator implements ILevel {

	private final ILevel delegate;
	
	public LevelDecorator(ILevel delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public int height() {
		return delegate.height();
	}

	@Override
	public int width() {
		return delegate.width();
	}

	@Override
	public boolean editing() {
		return delegate.editing();
	}

	@Override
	public Nature nature(int x, int y) {
		return delegate.nature(x, y);
	}

	@Override
	public void init(int x, int y) {
		delegate.init(x, y);
	}

	@Override
	public void setNature(int x, int y, Nature n) {
		delegate.setNature(x, y, n);
	}

	@Override
	public void goPlay() {
		delegate.goPlay();
	}

	@Override
	public void remove(int x, int y) {
		delegate.remove(x, y);
	}

	@Override
	public void build(int x, int y) {
		delegate.build(x, y);
	}

	@Override
	public int getXEntrance() {
		
		return delegate.getXEntrance();
	}

	@Override
	public int getYEntrance() {
		
		return delegate.getYEntrance();
	}
	
	@Override
	public int getXExit() {
		
		return delegate.getXExit();
	}

	@Override
	public int getYExit() {
		
		return delegate.getYExit();
	}
}
