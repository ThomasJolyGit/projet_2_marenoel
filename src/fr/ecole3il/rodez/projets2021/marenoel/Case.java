package fr.ecole3il.rodez.projets2021.marenoel;

public class Case {
	
	/**
	 * La construction présente sur la case (si non null).
	 */
	Construction construction;
	
	
	/**
	 * Le type de case. 
	 */
	TypeCase type;


	/**
	 * 
	 * Constructeur de case (au chargement de la carte)
	 * 
	 * @param type Le type de case à initialiser.
	 */
	public Case(TypeCase type) {
		this.type = type;
		this.construction = null;
	}


	/**
	 * 
	 * Accesseur pour le type de case.
	 * 
	 * @return Le type de case.
	 */
	public TypeCase getType() {
		return type;
	}
	
	// TODO
}
