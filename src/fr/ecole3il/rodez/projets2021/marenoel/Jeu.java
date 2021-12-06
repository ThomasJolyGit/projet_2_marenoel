package fr.ecole3il.rodez.projets2021.marenoel;

import fr.ecole3il.rodez.projets2021.marenoel.exchange.MareNoelSerialiseur;

public class Jeu {
	
	LongModifiable morceauxPain;
	long score;
	int tourActuel;
	int nombreTours;
	Etang carte;
	
	public Jeu(String nomCarte, int nombreTours, long morceauxDePainInitiaux) {
		// TODO Constructeur à terminer.
		// ...
		
		
		MareNoelSerialiseur.initialiserJeu(nomCarte, nombreTours, morceauxDePainInitiaux);
	}
	
	public long lancer(Strategie robot) {
		robot.commencer(this);
		while(nombreTours < tourActuel) {
			robot.tourSuivant(this);
			tourSuivant();
		}
		robot.terminer(this);
		return score;
	}
	
	public void tourSuivant() {
		morceauxPain.changer(morceauxPain.obtenir() + carte.generation(carte.nombreDeCanards()));
		score += carte.generation(carte.nombreDeCanards());
		nombreTours ++;
		MareNoelSerialiseur.tourSuivantJeu();
	}
	
	public boolean construire(int i, int j, TypeConstruction type) {
		//TODO
		// Quelque chose à tester...
		if(false) {
			MareNoelSerialiseur.construireJeu(i, j, type);
		}
		return false;
	}
	
	public boolean ameliorer(int i, int j) {
		//TODO
		// Quelque chose à tester...
		if(false) {
			MareNoelSerialiseur.ameliorerJeu(i, j);
		}
		return false;
	}

	public boolean detruire(int i, int j) {
		//TODO
		// Quelque chose à tester...
		if(false) {
			MareNoelSerialiseur.detruireJeu(i, j);
		}
		return false;
	}
	
	// TODO
}
