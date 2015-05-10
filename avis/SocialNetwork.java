package avis;

import java.util.LinkedList;


import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author A. Beugnard, 
 * @author G. Ouvradou
 * @author B. Prou
 * @author Yann Andreu et Yannick Omnès
 * @date février - mars 2011
 * @version V0.6
 */


/** 
 * <p>
 * <b>Système de mutualisation d'opinions portant sur des domaines
 * variés (littérature, cinéma, art, gastronomie, etc.) et non limités.</b>
 * </p>
 * <p>
 * L'accès aux items et aux opinions qui leurs sont associées
 * est public. La création d'item et le dépôt d'opinion nécessite en revanche 
 * que l'utilisateur crée son profil au préalable.
 * </p>
 * <p>
 * Lorsqu'une méthode peut lever deux types d'exception, et que les conditions sont réunies 
 * pour lever l'une et l'autre, rien ne permet de dire laquelle des deux sera effectivement levée.
 * </p>
 * <p>
 * Dans une version avancée (version 2), une opinion peut également
 * être évaluée. Chaque membre se voit dans cette version décerner un "karma" qui mesure
 * la moyenne des notes portant sur les opinions qu'il a émises.
 * L'impact des opinions entrant dans le calcul de la note moyenne attribuée à un item
 * est pondéré par le karma des membres qui les émettent.
 * </p>
 */

public class SocialNetwork {

	private LinkedList<Member> members;
	private LinkedList<Item> items;

	/**
	 * constructeur de <i>SocialNetwok</i> 
	 * 
	 */

	public SocialNetwork() {
		members = new LinkedList<Member>();
		items = new LinkedList<Item>();
	}

	/**
	 * Obtenir le nombre de membres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de membres
	 */
	public int nbMembers() {
		return members.size();
	}

	/**
	 * Obtenir le nombre de films du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de films
	 */
	public int nbFilms() {
		int nbFilms=0;
		for(Item i : items)
			if (i instanceof Film)
				nbFilms++;
		return nbFilms;
	}

	/**
	 * Obtenir le nombre de livres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de livres
	 */
	public int nbBooks() {
		int nbBooks=0;
		for (Item i : items)
			if (i instanceof Book)
				nbBooks++;
		return nbBooks;
	}


	/**
	 * Ajouter un nouveau membre au <i>SocialNetwork</i>
	 * 
	 * @param pseudo son pseudo
	 * @param password son mot de passe 
	 * @param profil un slogan choisi par le membre pour se définir
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leading or trailing blanks. </li>
	 *  <li>  si le profil n'est pas instancié.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists membre de même pseudo déjà présent dans le <i>SocialNetwork</i> (même pseudo : indifférent à  la casse  et aux leading et trailing blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists  {
		
		// Pour des raisons de debug
		/*System.out.println("\nLes informations récupérées par addMember() sont :");
		System.out.println("pseudo :"+pseudo+".");
		System.out.println("password :"+password+".");
		System.out.println("profil :"+profil+".");*/
		
		//===================================== ANALYSE DES CAS D'ERREURS =======================================
		
		// Si l'une des informations n'est pas instanciée, on lève l'exception BadEntry
		if (pseudo==null || password==null || profil==null)
			throw new BadEntry("le pseudo, le password et le profil doivent être instanciés");
		
		// Si le pseudo a moins de 1 caractère autres que des espaces on lève BadEntry
		if (pseudo.replaceAll(" ", "").length()<1)
			throw new BadEntry("Le pseudo doit contenir au moins un caractère qui n'est pas un espace.");
		
		//Si le password a moins de 4 caractères autres que des leading ou trailing blanks, on lève BadEntry
		if(password.trim().length()<4)
			throw new BadEntry("Le mot de passe doit contenir au moins 4 caractères autres que des espaces en début et en fin.");
			
		// Si le pseudo correspond déjà à un membre, on refuse l'inscription
		// On prend en compte les leading et trailing blanks, ainsi que la casse (tout en majuscules)
		for(Member membre:members)
			if (membre.getPseudo().toUpperCase().equals(pseudo.trim().toUpperCase()))
				throw new MemberAlreadyExists();
		
		
		// ====================================== AJOUT D'UN MEMBRE ===============================================
		// Si on arrive ici, c'est que les informations saisies ont été considérées comme acceptables
		// On entre le pseudo en majuscules et sans ses trailing et leading blanks.
		members.add(new Member(pseudo.trim(), password, profil));
		//System.out.println(" Un nouveau membre correctement ajouté.");
	}


	/**
	 * Ajouter un nouvel item de film au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du film
	 * @param genre son genre (aventure, policier, etc.)
	 * @param realisateur le réalisateur
	 * @param scenariste le scénariste
	 * @param duree sa durée en minutes
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leading or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si le réalisateur n'est pas instancié. </li>
	 *  <li>  si le scénariste n'est pas instancié. </li>
	 *  <li>  si la durée n'est pas positive.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists : item film de même titre  déjà présent (même titre : indifférent à  la casse  et aux leading et trailing blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		
		boolean allowed = false;
		//===================================== ANALYSE DES CAS D'ERREURS =======================================
		
		// Si le pseudo, le password, le titre, le genre, le réalisateur ou le scénariste n'est (ne sont) pas instancié(s)
		if (pseudo==null || password==null || titre==null || genre==null || realisateur==null || scenariste==null)
			throw new BadEntry("Le pseudo, le password, le réalisateur, le titre, le genre, le réalisateur et le scénariste doivent être instanciés.");
		
		//Si le pseudo a moins d'un caractère autre que des espaces
		if (pseudo.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le pseudo doit comporter au moins un caractère autre que des espaces.");
		
		// Si le password possède moins de 4 caractères autres que des leading or trailing blanks
		if (password.trim().length()<4)
			throw new BadEntry ("Le password doit comporter au moins 4 caractères autres que des espaces de début ou fin.");
		
		//Si le titre a moins de 1 caractère autre que des espaces
		if (titre.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le titre doit contenir au moins 1 caractère autre que des espaces.");
		
		// Si la durée n'est pas positive
		if (duree<0)
			throw new BadEntry ("La durée doit être positive et saisie en minutes.");
		
		// Si le membre n'existe pas ou si le password est incorrect
		for (Member m: members)
			if(m.getPseudo().trim().equals(pseudo.trim()))
			{
				if (m.passwordMatches(password))
					allowed = true;
				else
					break; //Inutile de continuer si on a déjà trouvé le membre mais que son password est faux
			}
		if (!allowed)
			throw new NotMember("Les informations fournies n'ont pas permis de vous authentifier. Vérifiez votre pseudo et votre password.");
		
		// Si le film existe déjà
		for (Item i : items)
			if (i instanceof Film) // On recherche uniquement parmi les films
			{
				if(((Film)i).getTitre().trim().toUpperCase().equals(titre.trim().toUpperCase()))
					throw new ItemFilmAlreadyExists();
			}
		
		// ====================================== AJOUT D'UN FILM ===============================================	
		items.add(new Film(titre, genre, realisateur, scenariste, duree));
		//System.out.println("Un nouveau film ajouté avec succès");
	}

	/**
	 * Ajouter un nouvel item de livre au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du livre
	 * @param genre son genre (roman, essai, etc.)
	 * @param auteur l'auteur
	 * @param nbPages le nombre de pages
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leading or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si l'auteur n'est pas instancié. </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists item livre de même titre  déjà présent (même titre : indifférent à la casse  et aux leading et trailing blanks)
	 * 
	 * 
	 */
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws  BadEntry, NotMember, ItemBookAlreadyExists{

		boolean allowed = false;
		//===================================== ANALYSE DES CAS D'ERREURS =======================================
		
		// Si le pseudo, le password, le titre, le genre ou l'auteur n'est (ne sont) pas instancié(s)
		if (pseudo==null || password==null || titre==null || genre==null || auteur==null)
			throw new BadEntry("Le pseudo, le password, l'auteur, le titre et le genre doivent être instanciés.");
		
		//Si le pseudo a moins d'un caractère autre que des espaces
		if (pseudo.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le pseudo doit comporter au moins un caractère autre que des espaces.");
		
		// Si le password possède moins de 4 caractères autres que des leading or trailing blanks
		if (password.trim().length()<4)
			throw new BadEntry ("Le password doit comporter au moins 4 caractères autres que des espaces de début ou fin.");
		
		//Si le titre a moins de 1 caractère autre que des espaces
		if (titre.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le titre doit contenir au moins 1 caractère autre que des espaces.");
		
		// Si le nombre de pages n'est pas positif
		if (nbPages<0)
			throw new BadEntry ("Lae nombre de pages doit être positif.");
		
		// Si le membre n'existe pas ou si le password est incorrect
		for (Member m: members)
			if(m.getPseudo().trim().equals(pseudo.trim()))
			{
				if (m.passwordMatches(password))
					allowed = true;
				else
					break; //Inutile de continuer si on a déjà trouvé le membre mais que son password est faux
			}
		if (!allowed)
			throw new NotMember("Les informations fournies n'ont pas permis de vous authentifier. Vérifiez votre pseudo et votre password.");
		
		// Si le Book existe déjà
		for (Item i : items)
			if (i instanceof Book) // On recherche uniquement parmi les Book
			{
				if(((Book)i).getTitre().trim().toUpperCase().equals(titre.trim().toUpperCase()))
					throw new ItemBookAlreadyExists();
			}
		
		// ====================================== AJOUT D'UN BOOK ===============================================	
		items.add(new Book(titre, genre, auteur, nbPages));
		//System.out.println("Un nouveau livre ajouté avec succès");
	}

	/**
	 * Consulter les items du <i>SocialNetwork</i> par nom
	 * 
	 * @param nom son nom (eg. titre d'un film, d'un livre, etc.)
	 * 
	 * @throws BadEntry : si le nom n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 * 
	 * @return LinkedList <String> : la liste des représentations de tous les items ayant ce nom 
	 * Cette représentation contiendra la note de l'item s'il a été noté.
	 * (une liste vide si aucun item ne correspond) 
	 */
	public LinkedList <String> consultItems(String nom) throws BadEntry {
		LinkedList<String> result = new LinkedList<String>();
		Film film = null;
		Book book = null;
		
		//===================================== ANALYSE DES CAS D'ERREURS =======================================
		
		if (nom==null || nom.replaceAll(" ", "").length()<1)
			throw new BadEntry("Le nom de l'item à rechercher doit être instancié et comporter au moins un caractère différent d'un espace)");
		
		//===================================== RECHERCHE DANS LES ITEMS ========================================
		
		// On regarde le nom de chaque item. S'il correspond, on le rajoute à la liste de chaines result
		for (Item i : items)
		{
			if ( i instanceof Film)
			{
				film = (Film)i;
				if (film.getTitre().trim().toUpperCase().equals(nom.trim().toUpperCase())) // On passe tout en majuscules pour ignorer la casse lors de la comparaison. On élimine également les espaces en début et fin
					result.add(film.toString());
			}
			else if (i instanceof Book)
			{
				book = (Book)i;
				if (book.getTitre().trim().toUpperCase().equals(nom.trim().toUpperCase()))
					result.add(book.toString());
			}
		}
		
		return result;
	}



	/**
	 * Donner son opinion sur un item film.
	 * Ajoute l'opinion de ce membre sur ce film au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce film  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du film  concerné
	 * @param note la note qu'il donne au film 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leading or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film  
	 */
	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		
		Member membre = null;
		Film film = null;
		//===================================== ANALYSE DES CAS D'ERREURS =======================================
		
		// Si pseudo, password, titre ou commentaire pas instanciés
		if (pseudo==null || password==null || titre==null || commentaire==null)
			throw new BadEntry("Le pseudo, le password, le titre et le commentaire doivent être instanciés.");
		
		// Si le pseudo a moins de 1 caractère autre que des espaces
		if (pseudo.replaceAll(" ", "").length()<1)
			throw new BadEntry("Le pseudo doit présenter au moins un caractère qui n'est pas un espace.");
		
		// Si le password a moins de 4 caractères autres que des leading ou trailing blanks
		if (password.trim().length()<4)
			throw new BadEntry("Le password doit comporter au moins 4 caractères autres que des leading ou trailing blanks.");
		
		// Si le titre a moins de 1 caractère autre que des espaces
		if (titre.replaceAll(" ", "").length()<1)
			throw new BadEntry("Le titre doit comporter au moins un caractère différent d'un espace.");
		
		// Si la note n'est pas comprise entre 0.0 et 5.0
		if (note<0.0f || note>5.0f)
			throw new BadEntry("La note doit être comprise entre 0.0 et 5.0.");
		
		// Si le membre n'existe pas ou si le password est incorrect
		for (Member m: members)
			if(m.getPseudo().equals(pseudo))
			{
				if (m.passwordMatches(password))
					membre = m;
				else
					break; //Inutile de continuer si on a déjà trouvé le membre mais que son password est faux
			}
		if (membre==null)
			throw new NotMember("Les informations fournies n'ont pas permis de vous authentifier. Vérifiez votre pseudo et votre password.");
			
		// Si le titre n'est pas celui d'un film
		for (Item i : items)
			if (i instanceof Film)
				if (((Film) i).getTitre().trim().toUpperCase().equals(titre.trim().toUpperCase()))
					film = (Film)i;
		if (film==null)
			throw new NotItem("Le titre saisi ne correspond pas à un film répertorié.");
			
		
		//===================================== AJOUT DE LA REVIEW AU BON FILM ==================================
		film.addOrModifyReview(membre, note, commentaire); 
		return film.moyenneNotesReview();
	}



	/**
	 * Donner son opinion sur un item livre.
	 * Ajoute l'opinion de ce membre sur ce livre au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce livre  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du livre  concerné
	 * @param note la note qu'il donne au livre 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leading or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un livre.
	 * 
	 * @return la note moyenne des notes sur ce livre
	 */
	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		
		Member membre = null;
		Book book = null;
		//===================================== ANALYSE DES CAS D'ERREURS =======================================
		
		// Si pseudo, password, titre ou commentaire pas instanciés
		if (pseudo==null || password==null || titre==null || commentaire==null)
			throw new BadEntry("Le pseudo, le password, le titre et le commentaire doivent être instanciés.");
		
		// Si le pseudo a moins de 1 caractère autre que des espaces
		if (pseudo.replaceAll(" ", "").length()<1)
			throw new BadEntry("Le pseudo doit présenter au moins un caractère qui n'est pas un espace.");
		
		// Si le password a moins de 4 caractères autres que des leading ou trailing blanks
		if (password.trim().length()<4)
			throw new BadEntry("Le password doit comporter au moins 4 caractères autres que des leading ou trailing blanks.");
		
		// Si le titre a moins de 1 caractère autre que des espaces
		if (titre.replaceAll(" ", "").length()<1)
			throw new BadEntry("Le titre doit comporter au moins un caractère différent d'un espace.");
		
		// Si la note n'est pas comprise entre 0.0 et 5.0
		if (note<0.0f || note>5.0f)
			throw new BadEntry("La note doit être comprise entre 0.0 et 5.0.");
		
		// Si le membre n'existe pas ou si le password est incorrect
		for (Member m: members)
			if(m.getPseudo().equals(pseudo))
			{
				if (m.passwordMatches(password))
					membre = m;
				else
					break; //Inutile de continuer si on a déjà trouvé le membre mais que son password est faux
			}
		if (membre==null)
			throw new NotMember("Les informations fournies n'ont pas permis de vous authentifier. Vérifiez votre pseudo et votre password.");
			
		// Si le titre n'est pas celui d'un livre
		for (Item i : items)
			if (i instanceof Book)
				if (((Book) i).getTitre().trim().toUpperCase().equals(titre.trim().toUpperCase()))
					book = (Book)i;
		if (book==null)
			throw new NotItem("Le titre saisi ne correspond pas à un livre répertorié.");
			
		
		//===================================== AJOUT DE LA REVIEW AU BON FILM ==================================
		book.addOrModifyReview(membre, note, commentaire); 
		return book.moyenneNotesReview();
	}


	/**
	 * Obtenir une représentation textuelle du <i>SocialNetwork</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>SocialNetwork</i> 
	 */
	public String toString() {
		String s = "Le SocialNetwork est composé des éléments suivants:\n";
		
		s+= "\nMEMBRES (" + nbMembers() + ") : \n";
		if (!members.isEmpty())
			for(Member m:members)
				s+=m;
		else
			s+= "Aucun membre.\n";
			
		if(!items.isEmpty()) {
			s+="\nLIVRES (" + nbBooks() + ") : \n";
			for(Item i: items)
				if (i instanceof Book)
					s+= (Book)i;
			
			s+= "\nFILMS (" + nbFilms() + ") : \n";
			for(Item i : items)
				if (i instanceof Film)
					s+= (Film)i;
		}
		else
			s+= "\n AUCUN ITEM.\n";
		
		return s;
	}
}
