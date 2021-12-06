package fr.ecole3il.rodez.projets2021.marenoel;

public abstract class Strategie {

	public abstract void commencer(Jeu jeu);
	public abstract void tourSuivant(Jeu jeu);
	public abstract void terminer(Jeu jeu);
}
