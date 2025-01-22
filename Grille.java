import java.util.Scanner;

public class Grille {

    // Codes ANSI pour les couleurs
    private static final String RESET = "\033[0m";      // Réinitialiser la couleur
    private static final String COULEUR_CONTEXTE = "\033[1;34m"; // Bleu pour le contour
    private static final String COULEUR_POINT = "\033[1;32m";  // Vert pour les points internes
    private static final String COULEUR_MUR = "\033[1;31m";    // Rouge pour les murs

    public static void main(String[] args) {
        // Dimensions de la grille
        byte lignes = 12; // Nombre de lignes
        byte colonnes = 13; // Nombre de colonnes

        // Initialisation de la grille
        char[][] grille = new char[lignes][colonnes];

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (i == 0 || i == lignes - 1 || j == 0 || j == colonnes - 1) {
                    grille[i][j] = '#'; // Bord de la grille
                } else {
                    grille[i][j] = '.'; // Point interne
                }
            }
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Afficher la grille
            afficherGrille(grille);

            // Demander à l'utilisateur de sélectionner une case
            System.out.println("Entrez les coordonnées de la case à détruire (ligne et colonne) ou -1 pour quitter :");
            System.out.print("Ligne (1 à " + (lignes - 2) + "): ");
            int x = scanner.nextInt();
            if (x == -1) break;

            System.out.print("Colonne (1 à " + (colonnes - 2) + "): ");
            int y = scanner.nextInt();
            if (y == -1) break;

            // Vérifier les coordonnées et détruire la case
            if (x > 0 && x < lignes - 1 && y > 0 && y < colonnes - 1) {
                grille[x][y] = '#'; // Transformer la case en mur
            } else {
                System.out.println("Coordonnées invalides. Réessayez.");
            }
        }

        scanner.close();
        System.out.println("Programme terminé.");
    }

    // Fonction pour afficher la grille
    private static void afficherGrille(char[][] grille) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                if (grille[i][j] == '#') {
                    System.out.print(COULEUR_CONTEXTE + "#  " + RESET);
                } else if (grille[i][j] == '.') {
                    System.out.print(COULEUR_POINT + ".  " + RESET);
                } else {
                    System.out.print(COULEUR_MUR + grille[i][j] + RESET);
                }
            }
            System.out.println();
        }
    }
}
