package ca.qc.bdeb.inf202.tp1;
import java.io.*;

/**
 * Une classe qui transfère les données d'un fichier à un tableau, qui détermine quoi faire avec un String et qui calcule la distance de 2 String
 * @author Anthony Lim
 * @version JDK 17.0.1
 */

public class Dictionnaire {
    private String nomDuFichier;
    private int nombreDeLignes;
    private String []tableDesMots;
    private int[][] tabLevenshtein;
    private String mot;

    /**
     * Constructeur
     *
     * @param nomDuFichier Le nom du fichier à lire par la méthode
     */
    public Dictionnaire(String nomDuFichier) {
        this.nomDuFichier = nomDuFichier;

    }

    /**
     * Une méthode qui permet de transférer les mots du fichier en question dans un tableau de String
     *
     */
    public void remplirLeTableau() {
        try {
            BufferedReader fichierDuDictionnaire = new BufferedReader(new FileReader(nomDuFichier));
            String ligne;
            while ((ligne = fichierDuDictionnaire.readLine()) != null) {
                nombreDeLignes++;
            }
            tableDesMots = new String[nombreDeLignes];
            fichierDuDictionnaire.close();
            fichierDuDictionnaire = new BufferedReader(new FileReader(nomDuFichier));
            int caseDuTableau = 0;
            while ((ligne = fichierDuDictionnaire.readLine()) != null) {
                tableDesMots[caseDuTableau] = ligne;
                caseDuTableau++;
            }
            fichierDuDictionnaire.close();
        } catch (FileNotFoundException E) {
            System.out.println("Erreur 1");

        } catch (IOException E) {
            System.out.println("Erreur 2");
        }
    }

    /**
     * Une méthode qui permet de valider un mot pour déterminer quel sera l'action suivante
     * @param mot Un String qui est rentré depuis le main qui sera le mot comparé à la liste des mots
     */
    public void valider(String mot) {
        boolean verification = false;

        for (int i = 0; i < tableDesMots.length; i++) {
            if (mot.equals(" ")) {

            } else if (mot.equals(tableDesMots[i])) {
                System.out.println("null");
                verification = true;
            }
        }
        if (!verification) {
            calculerLeMinimum(mot);
        }
    }

    /**
     * Une méthode qui calcule la distance la plus courte entre 2 String
     * Sources : https://en.wikipedia.org/wiki/Levenshtein_distance , https://www.geeksforgeeks.org/edit-distance-dp-5/
     * @param mot Un String qui est rentré depuis le main qui sera le mot comparé à la liste des mots
     */
    public void calculerLeMinimum(String mot) {
        int valeurLevenshtein = 0;
        int tableauDesDistances[] = new int[tableDesMots.length];
        for (int k = 0; k < tableDesMots.length; k++) {
            tabLevenshtein = new int[mot.length() + 1][(tableDesMots[k]).length() + 1];
            for (int o = 0; o < mot.length() + 1; o++) {
                tabLevenshtein[o][0] = o;
            }
            for (int p = 0; p < tableDesMots[k].length() + 1; p++) {
                    tabLevenshtein[0][p] = p;
                }
            for (int i = 1; i < mot.length() + 1; i++) {
                for (int s = 1; s < tableDesMots[k].length() + 1; s++) {
                    if (mot.charAt(i-1) == tableDesMots[k].charAt(s-1)) {
                        valeurLevenshtein = 0;
                    } else {
                        valeurLevenshtein = 1;
                    }
                    int case1 = tabLevenshtein[i - 1][s] + 1;
                    int case2 = tabLevenshtein[i][s - 1] + 1;
                    int case3 = tabLevenshtein[i - 1][s - 1] + valeurLevenshtein;
                    int min = case1;
                    if (case2 < min) {
                        min = case2;
                    }
                    if (case3 < min) {
                        min = case3;
                    }
                    tabLevenshtein[i][s] = min;
                }
            }
            tableauDesDistances[k] = tabLevenshtein[mot.length()][(tableDesMots[k]).length()];
        }
        Resultats resultats = new Resultats(tableauDesDistances, tableDesMots);
        resultats.trier();
        System.out.println(resultats.toString());
    }
}
