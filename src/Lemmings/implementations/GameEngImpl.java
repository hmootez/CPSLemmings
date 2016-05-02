package Lemmings.implementations;

import java.util.ArrayList;

import Lemmings.ihm.Ihm;
import Lemmings.services.IGameEng;
import Lemmings.services.ILemming;
import Lemmings.services.ILevel;
import Lemmings.tools.Comportement;
import Lemmings.tools.Nature;

public class GameEngImpl implements IGameEng {

	private int maxTour;
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
		this.maxTour = 50;
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
		for (int i = 0; i <= maxTour + 1; i++) { // FIXME < maxTour ou <= maxTour+1 ???
			
			if (i % spawnSpeed == 0 && nbLemmingCreated < getSizeColony()) {
				ArrayList<Comportement> comportements = new ArrayList<Comportement>();
				comportements.add(Comportement.FALLER);
				activLemmings.add(new LemmingImpl(comportements, this));
				nbLemmingCreated++;
			}
			if (isGameOver()) {
				System.out.println(getScore());
				break;
			}
			applyLemmingStep();
			tour++;
			ihm.updatedraw(getLevel(), getActivLemmings());
		}
		
		System.out.println("-----------------End of game-----------------");
		System.out.println("Saved: " + getNbLemmingCreated());
	}

	private void applyLemmingStep() {
		for (ILemming lem : (ArrayList<ILemming>)activLemmings.clone()) {
			lem.step();
			if (lem.isDead()) {
				this.activLemmings.remove(lem);
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
	public int getMaxTour() {
		return this.maxTour;
	}
	
	@Override
	public int getScore() {
		return (getNbLemmingSaved() / getNbLemmingCreated()) * 100 + getTour();
	}

	@Override
	public boolean isGameOver() {
		return (getNbLemmingSaved() == getSizeColony() && activLemmings.size() == 0); 
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
