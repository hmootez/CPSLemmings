package Lemmings.nonContractTests;

import org.junit.Before;

import Lemmings.contractTests.AbstractLevelTest;
import Lemmings.implementations.LevelImpl;

public class TestLevel extends AbstractLevelTest {
	
	@Override
	@Before 
	public void beforeTesting() {
		level = new LevelImpl();
	}
}
