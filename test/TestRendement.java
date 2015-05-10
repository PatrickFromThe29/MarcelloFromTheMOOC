package test;

import java.lang.System;

import avis.SocialNetwork;

public class TestRendement {

	
	public static void main(String args[])
	{
		
		long tempsDepart = 0;
		long tempsArrivee = 0;
		SocialNetwork sn = new SocialNetwork();
		SocialNetwork sn2 = new SocialNetwork();
		final String nomMembre = "Patrick";
		final String nomFilm = "monFilm";
		final String nomBook = "monBook";
		
		
		System.out.print ("Ajout de 2500 membres : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<2500;i++)
		{
			try{
				
				sn.addMember(nomMembre+i, "Patrick", "Patrick");
			}
			catch (Exception e)
			{
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		System.out.println((tempsArrivee-tempsDepart)+"ms");
		
		
		
		System.out.print ("Ajout de 2500 Films : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<2500;i++)
		{
			try{
				sn.addItemFilm("Patrick0", "Patrick", nomFilm+i, "Genre", "réalisateur", "scénariste", 123);
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		System.out.println((tempsArrivee-tempsDepart)+"ms");
		
		System.out.print ("Ajout de 2500 Books : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<2500;i++)
		{
			try{
				sn.addItemBook("Patrick0", "Patrick",nomBook+i, "Genre", "Auteur", 560);
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		System.out.println((tempsArrivee-tempsDepart)+"ms");
		
		
		
		
		
		System.out.print ("Ajout de 5000 membres : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<5000;i++)
		{
			try{
				sn2.addMember(nomMembre+i, "Patrick", "Patrick");
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		System.out.println((tempsArrivee-tempsDepart)+"ms");
		
		
		
		System.out.print ("Ajout de 5000 Films : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<5000;i++)
		{
			try{
				sn2.addItemFilm("Patrick0", "Patrick", nomFilm+i, "Genre", "réalisateur", "scénariste", 123);
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		System.out.println((tempsArrivee-tempsDepart)+"ms");
		
		System.out.print ("Ajout de 5000 Books : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<5000;i++)
		{
			try{
				sn2.addItemBook("Patrick0", "Patrick", nomBook+i, "Genre", "Auteur", 560);
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		System.out.println((tempsArrivee-tempsDepart)+"ms");
	}

}
