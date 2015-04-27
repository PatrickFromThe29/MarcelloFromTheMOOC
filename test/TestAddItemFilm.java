package test;


import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.NotMember;

/** 
 * @author Yannick Omnès
 * @date mars 2015
 * @version V1.0
 */

public class TestAddItemFilm {


	/**
	 * vérifie que l'ajout d'un film est refusé en cas de mauvaise saisie des paramètres (levée de l'exception BadEntry)
	 * Si c'est bien le cas, ne fait rien
	 * Sinon, affiche le message d'erreur passé en paramètre
	 * @param sn SocialNetwork avec lequel on travaille
	 * @param pseudo pseudo du membre 
	 * @param pwd password du membre
	 * @param titre titre du film
	 * @param genre genre du film
	 * @param realisateur réalisateur du film
	 * @param scenariste scénariste du film
	 * @param duree durée du film
	 * @param idTest id du test
	 * @param messErreur message d'erreur à renvoyer si l'ajout est autorisé
	 * @return 0 si le test s'est bien passé, 1 sinon
	 */
	public static int addItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){

		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de films a été modifié");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addFilmOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest){
		int nbFilms = sn.nbFilms();
		try{
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			if (sn.nbFilms() != nbFilms+1) {
				System.out.println("Test " + idTest + " :  le nombre de films n'a pas été correctement incrémenté");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addFilmAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree,  String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		
		catch (ItemFilmAlreadyExists e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien été levée mais le nombre de films a été modifié");
				return 1;
			}
			else
				return 0;
		}
		
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int addFilmNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree,  String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		
		catch (NotMember e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de films a été modifié");
				return 1;
			}
			else
				return 0;
		}
		
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}




	public static void main(String[] args) {

		//================================= Déclarations =======================================
		
		int nbTests = 0;
		int nbErreurs = 0;

		SocialNetwork sn = new SocialNetwork();
		

		int nbFilms = sn.nbFilms() ;

		
		System.out.println("Tests d'ajout de films au réseau social.");


		try {
			
			sn.addMember("Membre 1", "password", "");
		}
		catch(Exception e){
			System.out.println("Impossible d'ajouter un membre.");
			System.exit(1);

		} 

		//===================================== Tests ==========================================

		// <=> fiche numéro 3

		// Exception BadEntry : tentative d'ajout de membres avec entrées "incorrectes"
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, null, "password", "a","a","a","a",0, "3.1", "L'ajout d'un film avec pseudo non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "     ", "password", "a","a","a","a",0, "3.2", "L'ajout d'un film avec un pseudo contenant uniquement des espaces est accepté.");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Membre 1", null, "a", "a","a","a",0,"3.3", "L'ajout d'un film avec password non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Membre 1", "   qwd ", "a", "a","a","a",0,"3.4", "L'ajout d'un film avec un password ne contenant pas plus de 4 caractères autres que leading/trailing blanks est accepté.");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Membre 1", "password", null,"a","a","a",0, "3.5", "L'ajout d'un film avec un titre non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Membre 1", "password", "   ","a","a","a",0, "3.6", "L'ajout d'un film avec titre ne contenant que des espaces est accepté.");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Membre 1", "password", "b",null,"a","a",0, "3.7", "L'ajout d'un film avec un genre non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Membre 1", "password", "b","a",null,"a",0, "3.8", "L'ajout d'un film avec un realisateur non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Membre 1", "password", "b","a","a",null,0, "3.9", "L'ajout d'un film avec un scenariste non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "Membre 1", "password", "b","a","a","a",-5, "3.10", "L'ajout d'un film avec une durée négative est accepté.");

		
		nbTests++;
		if(nbFilms != sn.nbFilms())
			
			{
			System.out.println("Le nombre de films a changé après tentative d'ajout avec Bad Entry");
			nbErreurs++;
			}

		// <=> fiche numéro 4

		// Ajout de 3 films avec entrées "correctes"
		nbTests++;
		nbFilms++;
		nbErreurs += addFilmOKTest (sn, "Membre 1", "password", "La grande vadrouille", "Comédie", "Gérard Oury", "Gérard Oury", 132, "4.1a");
		nbTests++;
		nbFilms++;
		nbErreurs += addFilmOKTest (sn, "Membre 1", "password", "Mords-moi sans hésitation","Parodie", "Jason Friedberg", "Aaron Seltzer", 84, "4.1b");
		nbTests++;
		nbFilms++;
		nbErreurs += addFilmOKTest (sn, "Membre 1", "password", "Gran Torino", "Drame", "Clint Eastwood", "Nick Schenk", 120, "4.1c");
		
		
		nbTests++;
		if(nbFilms != sn.nbFilms())
			{
			System.out.println("Le nombre de films ne s'est pas correctement incrémenté après ajout de films");
			nbErreurs++;
			}

		// Exception NotMember : Tentative d'ajout de films avec erreur d'authentification
		nbTests++;
		nbErreurs += addFilmNotMemberTest(sn, "M", "password", "La grande vadrouille","Comédie","Gérard Oury","Gérard Oury",130, "4.2", "Membre inexistant accepté.");
		nbTests++;
		nbErreurs += addFilmNotMemberTest(sn, "Membre 1", " sdmmf ", "La grande vadrouille","Comédie","Gérard Oury","Gérard Oury",130, "4.3", "Mauvais password accepté.");
		
		nbTests++;
		if(nbFilms != sn.nbFilms())
		{
			System.out.println("Le nombre de films a changé après ajout incorrect de film");
			nbErreurs++;
		}

		
		// Exception ItemFilmAlreadyExists : tentative d'ajout de film déjà existant
		nbTests++;
		nbErreurs += addFilmAlreadyExistsTest(sn, "Membre 1", "password", "La grande vadrouille","Comédie","Gerard Oury","Gerard Oury",130, "4.4a", "Ajout d'un film identique au premier ajouté accepté.");
		nbTests++;
		nbErreurs += addFilmAlreadyExistsTest(sn, "Membre 1", "password", "Gran Torino","", "", "", 0,  "4.4b", "L'ajout d'un film identique au dernier film ajouté est accepté.");
		nbTests++;
		nbErreurs += addFilmAlreadyExistsTest(sn, "Membre 1", "password", "Mords-MOI sans hésitation", "Parodie", "Jason Friedberg", "Aaron Seltzer", 84,  "4.5", "L'ajout d'un film déjà existant (avec casse différente) est accepté.");
		nbTests++;
		nbErreurs += addFilmAlreadyExistsTest(sn, "Membre 1", "password", "           Mords-moi sans hésitation            ", "Parodie", "Jason Friedberg", "Aaron Seltzer", 84,  "4.6", "L'ajout d'un film déjà existant (avec leading et trailing blanks) est accepté.");		
		
		nbTests++;
		if(nbFilms != sn.nbFilms())
		{
			nbErreurs++;
			System.out.println("Le nombre de films a changé après la tentative d'ajout de film déjà existant");
		}

		// Ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// Bilan du test de addItemFilm
		System.out.println("TestsAddItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");
		
		if ((args != null) && (args.length == 2)) {        
            nbTests = nbTests + new Integer(args[0]);
            nbErreurs = nbErreurs + new Integer(args[1]);       
            args[0] = "" + nbTests;
            args[1] = "" + nbErreurs;
         }

	}
}
