package Lemmings.contractTests;

import org.junit.Before;
import org.junit.Test;

import Lemmings.contract.LevelContract;
import Lemmings.error.PreConditionError;
import Lemmings.implementations.LevelImpl;

public class TestContractLevel extends AbstractLevelTest {

	@Override
	@Before
	public void beforeTesting() {
		level = new LevelContract(new LevelImpl(100, 80));		
	}

	/**
	 * Init --------------------------------------------------------------------
	 * @param x
	 * @param y
	 */	
	@Test(expected=PreConditionError.class)
	public void level_Init_5() {						
		level.init(-5, 80);		
	}
	
	@Test(expected=PreConditionError.class)
	public void level_Init_6() {						
		level.init(20, 0);		
	}
}
