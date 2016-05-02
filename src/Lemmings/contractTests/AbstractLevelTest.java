package Lemmings.contractTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Lemmings.services.ILevel;
import Lemmings.tools.Nature;

public abstract class AbstractLevelTest {

	protected ILevel level;
	
	@Before
	public abstract void beforeTesting();
	
	@After
	public void afterTesting() {
		level = null;
	}
	
	/**
	 * Init --------------------------------------------------------------------
	 */
	@Test
	public void level_Init_1() {
		testLevelInitWith(5, 10);						 					
	}
	
	@Test
	public void level_Init_2() {
		testLevelInitWith(10, 5);						 					
	}
	
	@Test
	public void level_Init_3() {
		testLevelInitWith(999, 999);						 					
	}
	
	@Test
	public void level_Init_4() {
		testLevelInitWith(100, 80);						 					
	}
	
	private void testLevelInitWith(int x, int y) {			
		
		level.init(x, y);
		
		Assert.assertTrue("Test Level Init : ",
				level.getHeight() ==  y
			 && level.getWidth() ==  x
		);
	}
	
	/**
	 * setNature ---------------------------------------------------------------
	 */
	@Test
	public void level_setNature_1() {
		testLevelSetNatureWith(5, 10, Nature.DIRT);						 					
	}
	
	private void testLevelSetNatureWith(int x, int y, Nature n) {			
		
		boolean isEditing_atPre = level.isEditing();
		int getXEntrance_atPre = level.getXEntrance();
		int getYEntrance_atPre = level.getYEntrance();
		int getXExit_atPre = level.getXExit();
		int getYExit_atPre = level.getYExit();
		
		level.init(100, 80);
		level.setNature(x, y, n);		
				
		Assert.assertTrue("Test HotelVille Dépot : ",
				level.getNature(x, y) == Nature.DIRT
			 &&	level.isEditing() == isEditing_atPre
			 && level.getXEntrance() == getXEntrance_atPre
			 && level.getYEntrance() == getYEntrance_atPre
			 && level.getXExit() == getXExit_atPre
			 && level.getYExit() == getYExit_atPre
		);
	}
}
