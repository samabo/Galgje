package galgje;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Galgje {

	
	private Scanner scanner = new Scanner(System.in);
	private ArrayList<String> woordOpties = new ArrayList<String>();
	private String rwoord;
	private ArrayList<String> genoemdeLetters = new ArrayList<String>();
	private ArrayList<String> voortgang = new ArrayList<String>();

	private int fouten = 0;
	private int beurt = 0;

	public static void main(String[] args) {
		Galgje galgje = new Galgje();

		// methode prepGame wordt aangeroepen, er wordt random een woord geselecteerd
		galgje.prepGame();

		// start van het spel
		System.out.println("Welkom bij galgje!");
		
		// woord wordt vervangen door puntjes
		System.out.println("Het te raden woord is " + galgje.voortgang.toString().replace(",", ""));

		// start gameloop, raad een letter etc.
		galgje.startGame();
	}

		// woorden in de arrayList woordopties
	private void prepGame() {
		this.woordOpties.add("tomaat");
		this.woordOpties.add("banaan");
		this.woordOpties.add("appel");
		this.woordOpties.add("komkommer");
		this.woordOpties.add("quiz");
		this.woordOpties.add("fiasco");
		this.woordOpties.add("bijziend");
		this.woordOpties.add("aardappel");
		this.woordOpties.add("oogappel");
		this.woordOpties.add("granaatappel");
		this.woordOpties.add("haard");
		this.woordOpties.add("emoe");
		this.woordOpties.add("piano");
		this.woordOpties.add("gitarist");
		this.woordOpties.add("viool");
		this.woordOpties.add("gastronomie");
		this.woordOpties.add("restaurant");
		this.woordOpties.add("strandtent");
		this.woordOpties.add("halogeen");
		this.woordOpties.add("herfst");
		
		// kies random een woord
		int random = (int) (Math.random() * this.woordOpties.size());
		this.rwoord = this.woordOpties.get(random);

		// woord wordt omgezet in puntjes via arrayList voortgang
		for (int i = 0; i < this.rwoord.length(); i++) {
			this.voortgang.add(".");
		}
	}

	private void startGame() {

		// gameloop
		while (true) {
			System.out.println("Doe een gok!");

			String gok = scanner.next();

			// beurten bijhouden
			beurt++;
			System.out.println("\nBeurt: " + beurt);

			// is de letter goed of fout, en voeg de letter toe aan arrayList genoemde letters, indien hij daar niet al in zit.
			if (isGuessValid(gok)) {
				genoemdeLetters.add(gok);
				System.out.println("Correct! Het woord bevat deze letter!");
			} else {
				fouten++;
				genoemdeLetters.add(gok);
				System.out.println("Helaas, dit is fout. Je mag nog " + (13 - fouten) + " fouten maken.");
			}

			System.out.println("De geprobeerde letters zijn: " + genoemdeLetters + ".");
			System.out.println(this.printProgress(gok));

			
			if (this.gameWon()) {
				System.out.println("\nJe hebt gewonnen!!!");
				break;
			}

			if (fouten > 12) {
				System.out.println("\nHet spel is ten einde, je hangt aan de galg! Bedankt voor het spelen.");
				break;
			}

		}
	}
	// check of letter in genoemde letters zit
	private boolean isGuessValid(String guess) {
		for (String letter : genoemdeLetters) {
			if (guess.equals(letter)) {
				System.out.println("Deze letter heb je al genoemd.");
				return false;
			}
		}
		// check of de gegokte letter in het woord zit
		if (rwoord.contains(guess)) {
			return true;
		}
		// als hij dus niet in het woord zit. 
		return false;
	}

	// vervang de puntjes van de goed gegokte letters door de letter.
	// een for loop voor alle letters in genoemde letters. per index wordt vergeleken.
		// een extra for loop: als de letter in genoemde letters zit, zit hij dan ook in woord? gaat alle indexen van woord af.
		// per match in genoemde letters. 
	private String printProgress(String gok) {

		for (int i = 0; i < this.genoemdeLetters.size(); i++) {
			for (int y = 0; y < this.rwoord.length(); y++) {
				if (String.valueOf(this.rwoord.charAt(y)).equals(this.genoemdeLetters.get(i))) {
					this.voortgang.set(y, String.valueOf(this.rwoord.charAt(y)));
				}
			}
		}

		// haalt de komma's weg in de voortgang. anders staat er ., ., ., om de indexen te scheiden. maar hoeft niet in dit geval.
		String resultString = this.voortgang.toString();
		resultString = resultString.replace(",", "");
		return resultString;
	}

		// het spel is niet gewonnen als er nog een puntje staat in voortgang.
	private boolean gameWon() {
		for (String letter : this.voortgang) {
			if (letter.equals(".")) {
				return false;
			}
		}

		return true;
	}

}

// To Do: een gok is alleen geldig als er 1 letter tegelijk wordt ingetypt!! 
// 		Als er meerdere letters in 1 gok zitten, dan moet er opnieuw worden gevraagd om een gok, zonder dat iets goed of fout was.
//		cijfers zijn ook geen geldige invoer.
