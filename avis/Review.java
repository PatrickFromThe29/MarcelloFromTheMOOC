package avis;

/**
 * Représente une review
 * @author Yann Andreu et Yannick Omnès
 * @version 1.0
 *
 */


public class Review {
	
	private Member membre;
	private float note;
	private String commentaire;
	
	
	/**
	 * Construit une nouvelle review
	 * 
	 * @param membre
	 * 		Member qui dépose l'avis
	 * @param note
	 * 		note attribuée par le membre à l'item évalué
	 * @param commentaire
	 * 		commentaire rédigé par le membre sur l'item évalué
	 */
	public Review(Member membre, float note, String commentaire) {
		this.membre = membre;
		this.note = note;
		this.commentaire = commentaire;
	}
	
	/**
	 * Modifie la note de la review courante
	 * @param note note à modifier dans la review courante
	 */
	public void setNote(float note) {
		this.note = note;
	}

	/**
	 * Modifie le commentaire de la review courante
	 * @param commentaire commentaire à modifier de la review courante
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	/**
	 * Renvoie le Member qui a déposé la review courante
	 * @return le Member dépositaire de la review courante.
	 */
	public Member getMembre() {
		return membre;
	}

	/**
	 * Renvoie la note associée à la review courante
	 * @return la note associée à la review courante
	 */
	public float getNote() {
		return note;
	}

	/**
	 * Renvoie le contenu de l'avis sous forme d'une chaine de caractères
	 * @return la description de la review sous forme d'un String
	 */
	public String toString() {
		String s = "Avis de "+ this.membre.getPseudo() + " : \n";
		s+= "Note : "+ this.note +"/5 \n";
		s+= "Commentaire : "+ this.commentaire;
		return s;
	}

}
