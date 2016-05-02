package Lemmings.implementations;

import java.util.ArrayList;

import Lemmings.ihm.Ihm;
import Lemmings.services.IGameEng;
import Lemmings.services.ILemming;
import Lemmings.services.ILevel;
import Lemmings.tools.Comportement;
import Lemmings.tools.Nature;

public class GameEngImpl implements IGameEng {

	private int tour;
	private int sizeColony;
	private int spawnSpeed;
	private int nbLemmingCreated;
	private int nbLemmingSaved;
	private ILevel level;
	private ArrayList<ILemming> activLemmings;
	
	public GameEngImpl(int sc, int sp, ILevel level) {
		this.init(sc,sp,level); 
		this.step();	
	}
	
	@Override
	public void init(int sc, int sp, ILevel level) {
		this.tour = 0;
		this.sizeColony = sc;
		this.spawnSpeed = sp;
		this.nbLemmingCreated = 0;
		this.nbLemmingSaved = 0;
		this.level = level;
		this.activLemmings = new ArrayList<ILemming>();
	}
	
	@Override
	public void step() {
		Ihm ihm = new Ihm();
		
		//int test = 20;
		//while (test > 0) {
		while (!isGameOver()) {
		
			if (tour % spawnSpeed == 0 && nbLemmingCreated < getSizeColony()) {
				ArrayList<Comportement> comportements = new ArrayList<Comportement>();
				comportements.add(Comportement.FALLER);
				activLemmings.add(new LemmingImpl(comportements, this));
				nbLemmingCreated++;
			}
			applyLemmingStep();
			tour++;
			//test--;
			ihm.updatedraw(getLevel(), getActivLemmings());
		}
		
		System.out.println("\n-----------------End of game-----------------");
		System.out.println("Created: " + getNbLemmingCreated());
		System.out.println("Saved: " + getNbLemmingSaved());
		System.out.println("Score: " + getScore());
		System.out.println("---------------------------------------------");
	}

	private void applyLemmingStep() {
		for (ILemming lem : (ArrayList<ILemming>)activLemmings.clone()) {
			lem.step();
			if (lem.isDead()) {
				System.out.println("Removing lemm...");
				this.activLemmings.remove(lem);
				System.out.println("activLemmings.size(): " + activLemmings.size());
			}
			if (lem.isSaved()) { 
				nbLemmingSaved++ ; 
				this.activLemmings.remove(lem);
			}
		}
	}
	
	@Override
	public boolean isObstacle(int x, int y) {
		return (getLevel().getNature(x, y) == Nature.DIRT || getLevel().getNature(x, y) == Nature.METAL);
	}

	@Override
	public int getTour() {
		return this.tour;
	}
	
	@Override
	public int getScore() {
		return (getNbLemmingSaved() / getNbLemmingCreated()) * 100 + getTour();
	}

	@Override
	public boolean isGameOver() {
		return (getNbLemmingCreated() == getSizeColony() && activLemmings.size() == 0); 
	}

	@Override
	public int getSizeColony() { 
		return this.sizeColony;
	}

	@Override
	public int getSpawnSpeed() {
		return this.spawnSpeed;
	}

	@Override
	public int getNbLemmingCreated() {
		return nbLemmingCreated;
	}

	@Override
	public int getNbLemmingSaved() {
		return nbLemmingSaved;
	}

	@Override
	public ILevel getLevel() {
		return this.level;
	}

	@Override
	public ArrayList<ILemming> getActivLemmings() {
		return this.activLemmings;
	}
}
