package ca.qc.bdeb.inf202.tp1;

/**
 * Une classe qui trie des tableaux et retourne les String des tableaux
 * @author Anthony Lim
 * @version JDK 17.0.1
 */

public class Resultats {
    private int tableauDesDistances[];
    private String tableDesMots[];
    private int nombreDeResultatsAAfficher = 5;

    /**
     * Constructeur
     *
     * @param tableauDesDistance Un tableau qui contient toutes les distances entre le mot rentré et les mots parmi la liste
     * @param tableDesMots Un tableau qui contient tous les mots de la liste
     */
    public Resultats(int tableauDesDistance[], String tableDesMots[]) {
        this.tableauDesDistances = tableauDesDistance;
        this.tableDesMots = tableDesMots;
    }

    /**
     * Une méthode qui permet de trier le tableauDesDistances en ordre croissant avec le tableDesMots
     * Source : Document de trie du cours d'introduction à la programmation (420-201-RE) Automne 2021
     */
    public void trier(){
        for (int i = 0; i < tableauDesDistances.length; i++) {
            for (int j = i + 1; j < tableauDesDistances.length; j++)
                if (tableauDesDistances[i] > tableauDesDistances[j]) {
                    int valeurDuTri = tableauDesDistances[i];
                    tableauDesDistances[i] = tableauDesDistances[j];
                    tableauDesDistances[j] = valeurDuTri;
                    String valeurDuTriString = tableDesMots[i];
                    tableDesMots[i] = tableDesMots[j];
                    tableDesMots[j] = valeurDuTriString;
                }
        }
    }
    /**
     * Une méthode qui permet de retourner le message de sortit en String
     * @return un String qui contient les mots les plus proches et leurs distances avec toString
     */
    @Override
    public String toString() {
        return "La distance pour " + tableDesMots[0] + " est de " + tableauDesDistances[0] +
                "\nLa distance pour " + tableDesMots[1] + " est de " + tableauDesDistances[1] +
                "\nLa distance pour " + tableDesMots[2] + " est de " + tableauDesDistances[2] +
                "\nLa distance pour " + tableDesMots[3] + " est de " + tableauDesDistances[3] +
                "\nLa distance pour " + tableDesMots[4] + " est de " + tableauDesDistances[4];
    }
}
