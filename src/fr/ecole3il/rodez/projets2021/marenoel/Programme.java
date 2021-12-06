package fr.ecole3il.rodez.projets2021.marenoel;

public class Programme {

	private static final int NB_TOURS = 100;
	private static final long PAINS_INITIAUX = 11000;

	public static void main(String[] args) {
		Jeu jeu = new Jeu("cartes/etang1.carte", NB_TOURS, PAINS_INITIAUX);
		Strategie maStrategie = null/* = à initialiser */;
		long score = jeu.lancer(maStrategie);
		System.out.println("Score final : " + score);

	}

}
