package avis;

import java.util.LinkedList;

/**
 * Représente un livre
 * @author Yann Andreu et Yannick Omnès
 * @version 1.0
 *
 */

public class Book extends Item {

	private String titre;
	private String genre;
	private String auteur;
	private int nbPages;
	
	
	/**
	 * Construit un nouveau Book
	 * @param titre
	 * 		Titre du Book
	 * @param genre
	 * 		Genre du Book
	 * @param auteur
	 * 		Auteur du Book
	 * @param nbPages
	 * 		Nombre de pages du Book
	 */
	public Book(String titre, String genre, String auteur, int nbPages) {
		this.titre = titre;
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
		reviews = new LinkedList<Review>();
	}
	
	/**
	 * Renvoie le titre du Book courant
	 * @return le titre du Book courant
	 */
	public String getTitre() {
		return titre;
	}
	
	
	/**
	 * Renvoie un String décrivant le Book
	 */
	public String toString() {
		String s= "\nTitre : "+ this.titre +"\n";
		s+= "Genre : " + this.genre + "\n";
		s+= "Auteur : " + this.auteur + "\n";
		s+= "Nombre de pages : " + this.nbPages + "\n";
		s+= "Note moyenne : " + this.moyenneNotesReview() + "/5 \n";
		s+= "Avis : \n";
		for(Review avis:reviews) // Ajoute tous les avis déposés sur ce Book
			s+= avis + "\n";
		return s;
	}
	
}
