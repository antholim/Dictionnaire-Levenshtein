package ca.qc.bdeb.inf202.tp1;

import java.util.Locale;
import java.util.Scanner;

/**
 *  Un programme qui calcule la distance entre un mot rentré et sort les 5 mots les plus proches parmi une liste de mots
 * @author Anthony Lim
 * @version JDK 17.0.1
 */
public class Main {

    public static void main(String[] args) {
        Dictionnaire dictionnaire = new Dictionnaire("dictionnaire.txt");
        dictionnaire.remplirLeTableau();
        boolean quitterLeProgramme = true;
        System.out.println("Bienvenue dans le programme ! Pour quitter, appuyer sur enter");
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez un mot :");
            String mot = sc.nextLine();
            if (mot.equals("")) {
                quitterLeProgramme = false;
                System.out.println("Merci d'avoir utiliser ce programme !");
            } else {
                dictionnaire.valider(mot);
            }
        } while (quitterLeProgramme);
    }
}
