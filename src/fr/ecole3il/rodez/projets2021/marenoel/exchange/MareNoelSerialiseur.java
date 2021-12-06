package fr.ecole3il.rodez.projets2021.marenoel.exchange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import fr.ecole3il.rodez.projets2021.marenoel.Case;
import fr.ecole3il.rodez.projets2021.marenoel.TypeCase;
import fr.ecole3il.rodez.projets2021.marenoel.TypeConstruction;

public class MareNoelSerialiseur {
	/**
	 * Constante indiquant où est stocké le fichier de bilan d'une partie.
	 */
	private static final String PATH = "masolution.sortie";

	/**
	 * 
	 * Lit une carte.
	 * Renvoie une exception non typée (RuntimeException) pour éviter une gestion d'exception
	 * qui ne doit pas arriver, et revient alors à planter le programme.
	 * Pratique de programmation défensive : "En cas d'erreur, faire planter le programme."
	 * 
	 * @param nom Nom du fichier à charger.
	 * @return Un tableau de Case[lignes][colonnes] qui contient les cases du fichier.
	 */
	public static Case[][] lireCarte(String nom) {
		Case[][] carte = null;
		String absolutePath = FileSystems.getDefault().getPath(nom).normalize().toAbsolutePath().toString();
		System.out.println(absolutePath);
		try (BufferedReader br = new BufferedReader(new FileReader(nom))) {
			String ligne = null;
			String dimensions[] = br.readLine().split(" ");
			int lignes = Integer.parseInt(dimensions[0]);
			int colonnes = Integer.parseInt(dimensions[1]);
			carte = new Case[lignes][colonnes];
			for (int i = 0; i < lignes && (ligne = br.readLine()) != null; i++) {
				for (int j = 0; j < colonnes; j++) {
					switch (ligne.charAt(j)) {
					case '~':
						carte[i][j] = new Case(TypeCase.EAU);
						break;
					case '#':
						carte[i][j] = new Case(TypeCase.BERGE);
						break;
					case '%':
						carte[i][j] = new Case(TypeCase.JONCS);
						break;
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return carte;
	}
	
	/**
	 * Fonction volontairement non documentée.
	 * Sera vue en détail au semestre suivant.
	 */
	private static void ecrireChaine(String chaine) {
		try {
			Files.writeString(Paths.get(PATH), chaine, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.err.println("Impossible d'écrire dans le fichier '" + PATH + "' !");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * Inscrit dans le fichier les propriétés de la carte, le nombre de tours et le nombre de morceaux
	 * de pain initiaux.
	 * 
	 * @param nom
	 * @param nbTours
	 * @param painsInitiaux
	 */
	public static void initialiserJeu(String nom, int nbTours, long painsInitiaux) {
		Case[][] carte = lireCarte(nom);
		int lignes = carte.length;
		int colonnes = carte[0].length;
		String entete = String.format("%d %d %d %d\n", nbTours, painsInitiaux, lignes, colonnes);
		try {
			Files.writeString(Paths.get(PATH), entete, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			for (int i = 0; i < lignes; i++) {
				for (int j = 0; j < colonnes; j++) {
					Files.writeString(Paths.get(PATH), carte[i][j].getType().name()+"|", StandardOpenOption.CREATE,
							StandardOpenOption.APPEND);
				}
				Files.writeString(Paths.get(PATH), "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			System.err.println("Impossible d'écrire dans le fichier '" + PATH + "' !");
		}
	}

	/**
	 * 
	 * Doit être appelé par Jeu.construire().
	 * 
	 * @param i
	 * @param j
	 * @param type
	 */
	public static void construireJeu(int i, int j, TypeConstruction type) {
		ecrireChaine(String.format("C %d %d %s|", i, j, type.name()));
	}

	/**
	 * 
	 * Doit être appelé par Jeu.detruire().
	 * 
	 * @param i
	 * @param j
	 */
	public static void detruireJeu(int i, int j) {
		ecrireChaine(String.format("D %d %d|", i, j));
	}

	/**
	 * 
	 * Doit être appelé par Jeu.ameliorer().
	 * 
	 * @param i
	 * @param j
	 */
	public static void ameliorerJeu(int i, int j) {
		ecrireChaine(String.format("A %d %d|", i, j));
	}

	/**
	 * 
	 * Doit être appelé par Jeu.tourSuivant().
	 * 
	 */
	public static void tourSuivantJeu() {
		ecrireChaine("\n");
	}

	
}
