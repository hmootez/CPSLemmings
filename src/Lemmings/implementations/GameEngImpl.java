package Lemmings.implementations;

import java.sql.Savepoint;
import java.util.ArrayList;

import Lemmings.ihm.Ihm;
import Lemmings.services.Comportement;
import Lemmings.services.IGameEng;
import Lemmings.services.ILemming;
import Lemmings.services.ILevel;
import Lemmings.services.Nature;

public class GameEngImpl implements IGameEng {

	private int tour;
	//XXX
	private final int maxTour = 50;
	private int score;
	private boolean gameOver;
	private int sizeColony;
	private int spawnSpeed;
	private int nblemmingcreated;
	private int nblemmingsaved;	
	
	private ILevel level;
	private ArrayList<ILemming> activLemmings;
	
	public GameEngImpl(int sc, int sp, ILevel level) {
		init(sc,sp,level); 
		 startGame() ;
		
	}
	
	private void startGame() {
		
		Ihm ihm = new Ihm();
		//FIXME < maxTour ou <= maxTour+1 ??? 
		for(int i = 0; i <= maxTour+1; i++) {
			tour = i ;
			this.step();
			
			ihm.updatedraw(getLevel(),getActivLemmings());
			
			// vérification si la partie est fini 
			if (gameOver()) {
				System.err.println(getscore());
				break;
			}
			
			if (i % spawnSpeed == 0 && nblemmingcreated < getSizeColony()) {
				ArrayList<Comportement> comportements = new ArrayList<Comportement>();
				comportements.add(Comportement.FALLER);
				activLemmings.add(new LemmingImpl(comportements, this));
				nblemmingcreated++;
			}
		}
		
		System.err.println("End of game     xxxxxxxxxxxxx");
		System.out.println("Saved: " + getnblemmingsaved());
	}

	@Override
	public boolean obstacle(int x, int y) {
		return (this.getLevel().nature(x, y) == Nature.DIRT || this.getLevel().nature(x, y)== Nature.METAL);
	}

	@Override
	public int getTour() {
		return this.tour;
	}

	@Override
	public int getscore() {
		return (getnblemmingsaved() / getnblemmingcreated())*100 + getTour();
	}

	@Override
	public boolean gameOver() {
		return (getnblemmingsaved() == getSizeColony() && activLemmings.size() == 0) ; 
	}

	@Override
	public int getSizeColony(){ 
		return this.sizeColony;
	}

	@Override
	public int getSpawnSpeed() {
		return this.spawnSpeed;
	}

	@Override
	public int getnblemmingcreated() {
		return nblemmingcreated;
	}

	@Override
	public int getnblemmingsaved() {
		return nblemmingsaved;
	}

	@Override
	public ILevel getLevel() {
		return this.level;
	}

	@Override
	public ArrayList<ILemming> getActivLemmings() {
		return this.activLemmings;
	}

	@Override
	public void init(int sc, int sp, ILevel level) {
		this.sizeColony = sc;
		this.spawnSpeed = sp;
		this.activLemmings = new ArrayList<ILemming>();
		this.tour=0;
		this.gameOver = false;
		this.level = level;
	}

	@Override
	public void step() {		
		for (ILemming lem : (ArrayList<ILemming>)activLemmings.clone()) {
			lem.step();
			if (lem.isDead()) this.activLemmings.remove(lem);
			if(lem.isSaved()) { 
				nblemmingsaved++ ; 
				this.activLemmings.remove(lem);
			}
		}
	}
}
