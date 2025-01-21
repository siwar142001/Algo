public class Grille {

    // Codes ANSI pour les couleurs
    private static final String RESET = "\033[0m";      // Réinitialiser la couleur
    private static final String COULEUR_CONTEXTE = "\033[1;34m"; // Bleu pour le contour
    private static final String COULEUR_POINT = "\033[1;32m";  // Vert pour les points internes

    public static void main(String[] args) {
        // Dimensions de la grille
        int lignes = 12; // Nombre de lignes
        int colonnes = 13; // Nombre de colonnes

        // Création et affichage de la grille
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (i == 0 || i == lignes - 1 || j == 0 || j == colonnes - 1) {
                    // Affiche les bords de la grille avec la couleur du contour
                    System.out.print(COULEUR_CONTEXTE + "#   " + RESET);
                } else {
                    // Affiche les points internes avec de l'espacement
                    System.out.print(COULEUR_POINT + ".   " + RESET);
                }
            }
            System.out.println(); // Nouvelle ligne après chaque ligne de la grille
        }
    }
}
