import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe représentant une grille de jeu pour plusieurs joueurs.
 */
class Grille {

    // Codes ANSI pour les couleurs
    private static final String RESET = "\033[0m";      // Réinitialiser la couleur
    private static final String COULEUR_CONTEXTE = "\033[1;34m"; // Bleu pour le contour
    private static final String COULEUR_POINT = "\033[1;32m";  // Vert pour les points internes

    // Dimensions de la grille
    public static final int LIGNES = 12;
    public static final int COLONNES = 13;

    private final ArrayList<Joueur> joueurs; // Liste des joueurs
    private final char[][] grille;          // Représentation de la grille

    /**
     * Constructeur de la classe Grille.
     * Initialise la grille et ses bordures.
     */
    public Grille() {
        joueurs = new ArrayList<>();
        grille = new char[LIGNES][COLONNES];

        // Initialisation de la grille
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                if (i == 0 || i == LIGNES - 1 || j == 0 || j == COLONNES - 1) {
                    grille[i][j] = '#'; // Bord de la grille
                } else {
                    grille[i][j] = '.'; // Point interne
                }
            }
        }
    }

    /**
     * Ajoute un joueur à la liste des joueurs.
     *
     * @param joueur Le joueur à ajouter.
     */
    public void ajouterJoueur(Joueur joueur) {
        joueurs.add(joueur);
    }

    /**
     * Affiche la grille avec les joueurs et les bordures.
     */
    public void afficherGrille() {
        System.out.print("\033[H\033[2J"); // Effacer l'écran
        System.out.flush();

        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                boolean joueurTrouve = false;

                // Vérifier si un joueur est à cette position
                for (Joueur joueur : joueurs) {
                    if (joueur.getX() == i && joueur.getY() == j && joueur.estActif()) {
                        System.out.print(joueur.getCouleur() + joueur.getId() + "  " + RESET);
                        joueurTrouve = true;
                        break;
                    }
                }

                if (!joueurTrouve) {
                    if (grille[i][j] == '#') {
                        System.out.print(COULEUR_CONTEXTE + "#  " + RESET);
                    } else {
                        System.out.print(COULEUR_POINT + ".  " + RESET);
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Vérifie si une case est valide pour un déplacement ou non occupée.
     *
     * @param x Coordonnée X de la case.
     * @param y Coordonnée Y de la case.
     * @return true si la case est valide, false sinon.
     */
    public boolean estCaseValide(int x, int y) {
        if (grille[x][y] != '.') {
            return false;
        }
        for (Joueur joueur : joueurs) {
            if (joueur.estActif() && joueur.getX() == x && joueur.getY() == y) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si la partie est terminée et affiche le gagnant s'il y en a un.
     *
     * @return true si la partie est terminée, false sinon.
     */
    public boolean estPartieTerminee() {
        int joueursActifs = 0;
        Joueur gagnantTemporaire = null;

        for (Joueur joueur : joueurs) {
            if (joueur.estActif()) {
                if (joueur.peutSeDeplacer(this)) {
                    joueursActifs++;
                    gagnantTemporaire = joueur;
                } else {
                    joueur.eliminer();
                }
            }
        }

        // Si un seul joueur est actif, il est gagnant
        if (joueursActifs == 1) {
            afficherGrille();
            System.out.println("\nLe joueur " + gagnantTemporaire.getId() + " (" + gagnantTemporaire.getCouleur() + "J" + gagnantTemporaire.getId() + RESET + ") a gagné !");
            return true;
        }
        return false;
    }

    /**
     * Gère le déroulement de la partie.
     */
    public void jouer() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                for (Joueur joueur : joueurs) {
                    if (!joueur.estActif()) continue;

                    System.out.println("\nTour du joueur " + joueur.getId() + " (" + joueur.getCouleur() + "J" + joueur.getId() + RESET + ")");
                    afficherGrille();

                    System.out.println("Utilisez Z (haut), S (bas), Q (gauche), D (droite) pour vous déplacer :");
                    char input = scanner.next().toLowerCase().charAt(0);

                    switch (input) {
                        case 'z':
                            joueur.haut(this);
                            break;
                        case 's':
                            joueur.bas(this);
                            break;
                        case 'q':
                            joueur.gauche(this);
                            break;
                        case 'd':
                            joueur.droite(this);
                            break;
                        default:
                            System.out.println("Entrée invalide, veuillez réessayer.");
                            continue;
                    }

                    afficherGrille();

                    // Demander au joueur de détruire une case
                    boolean caseValide = false;
                    while (!caseValide) {
                        System.out.println("Choisissez une case à détruire (ligne et colonne) :");
                        System.out.print("Ligne (1 à " + (LIGNES - 2) + "): ");
                        int ligneADetruire = scanner.nextInt();
                        System.out.print("Colonne (1 à " + (COLONNES - 2) + "): ");
                        int colonneADetruire = scanner.nextInt();

                        if (ligneADetruire > 0 && ligneADetruire < LIGNES - 1 && colonneADetruire > 0 && colonneADetruire < COLONNES - 1) {
                            if (grille[ligneADetruire][colonneADetruire] == '.') {
                                grille[ligneADetruire][colonneADetruire] = '#';
                                System.out.println("Case (" + ligneADetruire + ", " + colonneADetruire + ") détruite.");
                                caseValide = true;
                            } else {
                                System.out.println("Cette case est déjà détruite. Veuillez choisir une autre case.");
                            }
                        } else {
                            System.out.println("Coordonnées invalides. Veuillez réessayer.");
                        }
                    }

                    if (estPartieTerminee()) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * Méthode principale pour démarrer le jeu.
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        Grille grille = new Grille();

        // Ajout des joueurs
        grille.ajouterJoueur(new Joueur(1, "\033[1;31m", 5, 5)); // Rouge
        grille.ajouterJoueur(new Joueur(2, "\033[1;34m", 5, 6)); // Bleu
        grille.ajouterJoueur(new Joueur(3, "\033[1;33m", 6, 5)); // Jaune
        grille.ajouterJoueur(new Joueur(4, "\033[1;35m", 6, 6)); // Rose

        // Lancer la partie
        grille.jouer();
    }
}

/**
 * Classe représentant un joueur dans la grille.
 */
class Joueur {
    private final int id;         // Identifiant du joueur
    private final String couleur; // Couleur du joueur
    private int x;                // Position X du joueur dans la grille
    private int y;                // Position Y du joueur dans la grille
    private boolean actif;        // Indique si le joueur est encore en jeu

    /**
     * Constructeur de la classe Joueur.
     *
     * @param id Identifiant du joueur.
     * @param couleur Couleur du joueur (code ANSI).
     * @param x Position initiale X.
     * @param y Position initiale Y.
     */
    public Joueur(int id, String couleur, int x, int y) {
        this.id = id;
        this.couleur = couleur;
        this.x = x;
        this.y = y;
        this.actif = true;
    }

    /**
     * @return L'identifiant du joueur.
     */
    public int getId() {
        return id;
    }

    /**
     * @return La position X du joueur.
     */
    public int getX() {
        return x;
    }

    /**
     * @return La position Y du joueur.
     */
    public int getY() {
        return y;
    }

    /**
     * @return La couleur du joueur.
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * @return true si le joueur est actif, false sinon.
     */
    public boolean estActif() {
        return actif;
    }

    /**
     * Élimine le joueur de la partie.
     */
    public void eliminer() {
        this.actif = false;
    }

    /**
     * Déplace le joueur vers le haut si possible.
     *
     * @param grille La grille de jeu.
     */
    public void haut(Grille grille) {
        if (x > 1 && grille.estCaseValide(x - 1, y)) {
            x--;
        } else {
            System.out.println("Déplacement impossible : case invalide.");
        }
    }

    /**
     * Déplace le joueur vers le bas si possible.
     *
     * @param grille La grille de jeu.
     */
    public void bas(Grille grille) {
        if (x < Grille.LIGNES - 2 && grille.estCaseValide(x + 1, y)) {
            x++;
        } else {
            System.out.println("Déplacement impossible : case invalide.");
        }
    }

    /**
     * Déplace le joueur vers la gauche si possible.
     *
     * @param grille La grille de jeu.
     */
    public void gauche(Grille grille) {
        if (y > 1 && grille.estCaseValide(x, y - 1)) {
            y--;
        } else {
            System.out.println("Déplacement impossible : case invalide.");
        }
    }

    /**
     * Déplace le joueur vers la droite si possible.
     *
     * @param grille La grille de jeu.
     */
    public void droite(Grille grille) {
        if (y < Grille.COLONNES - 2 && grille.estCaseValide(x, y + 1)) {
            y++;
        } else {
            System.out.println("Déplacement impossible : case invalide.");
        }
    }

    /**
     * Vérifie si le joueur peut se déplacer.
     *
     * @param grille La grille de jeu.
     * @return true si le joueur peut se déplacer, false sinon.
     */
    public boolean peutSeDeplacer(Grille grille) {
        return (x > 1 && grille.estCaseValide(x - 1, y)) ||
                (x < Grille.LIGNES - 2 && grille.estCaseValide(x + 1, y)) ||
                (y > 1 && grille.estCaseValide(x, y - 1)) ||
                (y < Grille.COLONNES - 2 && grille.estCaseValide(x, y + 1));
    }
}
