package avis;

import java.util.LinkedList;

/**
 * Permet de regrouper les items sous un même type
 * On ne factorise aucun attribut et méthode. En effet, ce classe doit rester la plus générique possible pour pouvoir désigner n'importe quel item qui serait ajouté plus tard sur ToutAvis
 * @author Yann Andreu et Yannick Omnès
 *
 */
public abstract class Item {
	
	protected LinkedList<Review> reviews;
	
	/**
	 * Ajoute une review dans la LinkedList reviews attachée à l'item.
	 * @see Review
	 * @param membre
	 * 		Member qui évalue l'item
	 * @param note
	 * 		note donnée par le membre à l'item
	 * @param commentaire
	 * 		commentaire rédigé par le membre sur l'item
	 */
	public void addOrModifyReview(Member membre, float note, String commentaire){
		// Si le Member membre a déjà déposé un avis, on le modifie
		for (int i=0; i<reviews.size();i++)
			if(reviews.get(i).getMembre().equals(membre)){
				reviews.get(i).setNote(note);
				reviews.get(i).setCommentaire(commentaire);
				return;
			}
		
		// Si on n'a pas trouvé d'avis pour membre, on en crée un
		this.reviews.add(new Review(membre, note, commentaire));
	}
	
	/**
	 * Renvoie la moyenne des notes des reviews associées à l'item courant.
	 * @return la moyenne des notes associées à l'item courant.
	 */
	public float moyenneNotesReview(){
		float sommeNotes = 0.0f;
		for(Review r : reviews)
			sommeNotes += r.getNote();
		return (sommeNotes / reviews.size());	
	}
}
