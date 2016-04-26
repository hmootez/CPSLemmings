package Lemmings.decorators;

import Lemmings.services.ILevel;
import Lemmings.services.Nature;

public abstract class LevelDecorator implements ILevel {

	private final ILevel delegate;
	
	public LevelDecorator(ILevel delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public boolean isEditing() {
		return delegate.isEditing();
	}

	@Override
	public Nature getNature(int x, int y) {
		return delegate.getNature(x, y);
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
	public void goPlay(int xEntrance, int yEntrance, int xExit, int yExit) {
		delegate.goPlay(xEntrance, yEntrance, xExit, yExit);
	}

	@Override
	public void goEditing() {
		delegate.goEditing();
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
