package avis;

import java.util.LinkedList;

/**
 * Représente un Film
 * @author Yann Andreu et Yannick Omnès
 * @version 1.0
 *
 */

public class Film extends Item {
	
	
	private String titre;
	private String genre;
	private String realisateur;
	private String scenariste;
	private int duree;
	
	
	/**
	 * Construit un nouveau Film
	 * @param titre
	 * 		Titre du Film
	 * @param genre
	 * 		Genre du Film
	 * @param realisateur
	 * 		Réalisateur du Film
	 * @param scenariste
	 * 		Scénariste du Film
	 * @param duree
	 * 		Durée du Film
	 */
	public Film(String titre, String genre, String realisateur, String scenariste, int duree) {
		this.titre = titre;
		this.genre = genre;
		this.scenariste = scenariste;
		this.realisateur = realisateur;
		this.duree=duree;
		reviews = new LinkedList<Review>();
	}
	
	/**
	 * Renvoie le titre du Film courant
	 * @return le titre du Film courant
	 */
	public String getTitre() {
		return titre;
	}


	/**
	 * Renvoie un String décrivant le Film
	 */
	public String toString() {
		String s= "\nTitre : "+ this.titre +"\n";
		s+= "Genre : " + this.genre + "\n";
		s+= "Réalisateur : " + this.realisateur + "\n";
		s+= "Scénariste : " + this.scenariste + "\n";
		s+= "Durée : "+ this.duree+ "\n";
		s+= "Note moyenne : " + this.moyenneNotesReview() + "/5 \n";
		s+= "Avis : \n";
		if (!reviews.isEmpty())
			for(Review avis:reviews) // Ajoute tous les avis déposés sur ce Film
				s+= avis + "\n";
		return s;
	}
	
}
