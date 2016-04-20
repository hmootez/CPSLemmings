package Lemmings.contract;

import Lemmings.decorators.JoueurDecorator;
import Lemmings.error.ContractError;
import Lemmings.error.ErrorHandler;
import Lemmings.error.InvariantError;
import Lemmings.error.PostConditionError;
import Lemmings.error.PreConditionError;
import Lemmings.services.Comportement;
import Lemmings.services.IJoueur;
import Lemmings.services.ILemming;

public class JoueurContract extends JoueurDecorator {

	public JoueurContract(IJoueur delegate) {
		super(delegate);
	}
	
	/**
	 * Init --------------------------------------------------------------------
	 */
	@Override
	public void init(String name, int nb) {
		try {
			checkInitPreConditions(name, nb);	
			checkInvariants();
			super.init(name, nb);	
			checkInvariants();
			checkInitPostConditions(name, nb);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}
	
	private void checkInvariants() throws InvariantError {
		if (!(getNbJetons() == getJetons().size())) {
			throw new InvariantError(
					"Invariant Joueur : getNbJetons() == card(getJetons())");
		}
		for (Comportement c : Comportement.values()) {
			if (!(getNbJetonsByComportement(c) == getJetons().get(c))) {
				throw new InvariantError(
					"Invariant Joueur : JetonsByComportement(c) != VALUE(c, getJetons())");
			}
		}
	}

	private void checkInitPreConditions(String name, int nb) throws PreConditionError {
		if (!(name != "")) {
			throw new PreConditionError("Init_Joueur : name == \"\"");
		}
		if (!(nb > 1)) {
			throw new PreConditionError("Init_Joueur : nb <= 1");
		}
	}

	private void checkInitPostConditions(String name, int nb) throws PostConditionError {
		if (!(getName() == name)) {
			throw new PostConditionError("Init_Joueur  : getName() != name)");
		}
		if (!(maxComportement() == 4)) {
			throw new PostConditionError("Init_Joueur : maxComportement() != 4))");
		}
		if (!(getJetons() != null)) {
			throw new PostConditionError("Init_Joueur  : getJetons() == null");
		}
		if(!(getNbJetons() == nb * maxComportement()))
			throw new PostConditionError("Init_Joueur  : getNbJetons() != nb * maxComportement()");
		}	
	
	/**
	 * DepenserJeton -----------------------------------------------------------
	 */
	@Override
	public void depenserJeton(Comportement c) {
		try {
			int getNbJetonsByComportement_atPre = getNbJetonsByComportement(c);
			String getName_atPre = getName();
			checkDepenserJetonPreConditions();	
			checkInvariants();
			super.depenserJeton(c);	
			checkInvariants();
			checkDepenserJetonPostConditions(c, getNbJetonsByComportement_atPre, getName_atPre);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}

	private void checkDepenserJetonPreConditions() throws PreConditionError {
		if (!(getNbJetons() > 0)) {
			throw new PreConditionError("DepenserJeton_Pre : getNbJetons() <= 0");
		}
	}

	private void checkDepenserJetonPostConditions(Comportement c,
			int getNbJetonsByComportement_atPre, String getName_atPre) throws PostConditionError {
		if (!(getJetons().get(c) == getNbJetonsByComportement_atPre - 1)) {
			throw new PreConditionError("DepenserJeton_Post : getJetons().get(c) != getNbJetonsByComportement_atPre - 1)");
		}
		if (!getName().equals(getName_atPre)) {
			throw new PreConditionError("DepenserJeton_Post : !getName().equals(getName_atPre)");
		}
	}
	
	/**
	 * FaireAction -------------------------------------------------------------
	 */
	@Override
	public void faireAction(ILemming l, Comportement instantComportement) {
		try {
			int getNbJetonsByComportement_atPre = getNbJetonsByComportement(instantComportement);
			String getName_atPre = getName();
			checkFaireActionPreConditions(l);	
			checkInvariants();
			super.faireAction(l, instantComportement);	
			checkInvariants();
			checkFaireActionPostConditions(instantComportement, l, getNbJetonsByComportement_atPre, getName_atPre);
		} catch (ContractError e) {			
			ErrorHandler.printError(e);			
			throw e;			
		}		
	}

	private void checkFaireActionPreConditions(ILemming l) throws PreConditionError {
		if (!(getNbJetons() > 0)) {
			throw new PreConditionError("FaireAction_Pre : getNbJetons() <= 0");
		}
		//FIXME if l==null?
		if (!(l.getGameEng().getActivLemmings().contains(l))) {
			throw new PreConditionError("FaireAction_Pre : !(l.getGameEng().getActivLemmings().contains(l))");
		}
	}

	private void checkFaireActionPostConditions(Comportement c, ILemming l,
			int getNbJetonsByComportement_atPre, String getName_atPre) throws PostConditionError {
		if (!(getJetons().get(c) == getNbJetonsByComportement_atPre - 1)) {
			throw new PreConditionError("FaireAction_Pre : getJetons().get(c) != getNbJetonsByComportement_atPre - 1)");
		}
		if (!getName().equals(getName_atPre)) {
			throw new PreConditionError("FaireAction_Pre : !getName().equals(getName_atPre)");
		}
		//FIXME
		if (!(l.getComportement() == c)) {
			throw new PreConditionError("FaireAction_Pre : l.getComportement() != c)");
		}
	}
}
