package test;

public class TestSocialNetwork {


	   public static void main(String[] args) {

	      String [] resultats = new String[] {"0", "0"};
	      
	      TestsInitialisation.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");

	      TestsAddMember.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");

	      TestAddItemFilm.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");

	      TestReviewItemFilm.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");
	      
	      // .... d'autres appels à des tests
	      
	      System.out.println("Bilan des Tests :   " + resultats[1] + 
	                         " erreur(s) / " +  resultats[0] + " tests effectués");
	      
	   }

	}
