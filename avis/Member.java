package avis;


/**
 * 
 * @author Yann Andreu et Yannick Omnès
 * @version 1.0
 */

public class Member {
	
	private String pseudo;
	private String password;
	private String profil;
	
	/**
	 * Construit un nouveau Member
	 * @param pseudo
	 * 		pseudo du nouveau Member
	 * @param password
	 * 		password à associer au nouveau Member
	 * @param profil
	 * 		profil entré par le nouveau Member
	 */
	public Member(String pseudo, String password, String profil) {
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
	}
	
	/**
	 * Obtenir le pseudo du membre courant
	 * @return
	 * 		Renvoie le pseudo du membre courant sous forme d'un String.
	 */
	public String getPseudo() {
		return this.pseudo;
	}
	
	/**
	 * Obtenir le profil du membre courant
	 * @return
	 * 		Renvoie le profil du membre courant
	 */
	public String getProfil(){
		return this.profil;
	}
	
	
	/**
	 * Renvoie un String décrivant le Member courant
	 */
	public String toString() {
		String s = this.pseudo + " : " + this.profil+"\n";
		return s;
	}
	
	/**
	 * 
	 * @param EnteredPassword
	 * 		Mot de passe entré par un visiteur pour s'authentifier
	 * @return
	 * 		true si le mot de passe entré correspond à celui du Member, faux sinon.
	 */
	public boolean passwordMatches (String EnteredPassword) {
		return this.password.equals(EnteredPassword);
	}
	

	@Override
	public boolean equals(Object o){
		
		if (o==null || !(o instanceof Member))
			return false;
		else if (((Member)o).pseudo == this.pseudo && ((Member)o).profil == this.profil && ((Member)o).password == this.password)
			return true;
		else
			return false;
	}
}
