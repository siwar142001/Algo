import java.util.ArrayList;
import java.util.Scanner;

class Grille {

    // Codes ANSI pour les couleurs
    public static final String RESET = "\033[0m";      // Réinitialiser la couleur
    public static final String COULEUR_CONTEXTE = "\033[1;34m"; // Bleu pour le contour
    public static final String COULEUR_POINT = "\033[1;32m";  // Vert pour les points internes
    public static final int lignes = 12; // Nombre de lignes
    public static final int colonnes = 13; // Nombre de lignes

    private ArrayList<game> joueurs;       //creation d'une liste des joueurs//

    // Constructeur pour initialiser la liste des joueurs

    public Grille() {
        joueurs = new ArrayList<>();
    }

    // Ajouter un joueur à la grille

    public void ajouterJoueur(game joueur) {
        joueurs.add(joueur);
    }

    // Rafraichis puis Affiche la grille avec les joueurs

    public void afficherGrille() {
        System.out.print("\033[H\033[2J"); // Effacer l'écran
        System.out.flush();

        for (int i = 0; i < lignes; i++) {  //on parcourt toute les case //
            for (int j = 0; j < lignes; j++) {
                boolean joueurTrouve = false;   //on ne trouve pas le joueur //

                // Vérifier si un joueur est à cette position

                for (game joueur : joueurs) {   //on parcourt tout les joueur de la liste joueur
                    if (joueur.getX() == i && joueur.getY() == j && joueur.estActif()) {
                        System.out.print(joueur.getCouleur() + "J" + joueur.getId() + "  " + RESET); //on affiche le nom du joueur avec son Id//
                        joueurTrouve = true;    //on a trouvé le joueur //
                        break;
                    }
                }

                //on construit le terrain si aucun joueur n'est trouvé//

                if (!joueurTrouve) {
                    if (i == 0 || i == lignes - 1 || j == 0 || j == 11) {
                        System.out.print(COULEUR_CONTEXTE + "#   " + RESET); // Bordure
                    } else {
                        System.out.print(COULEUR_POINT + ".   " + RESET); // Case vide
                    }
                }
            }
            System.out.println();   //on passe a la ligne suivante//
        }
    }

    // Lancer la partie
    public void jouer() {
        Scanner scanner = new Scanner(System.in);   //lire les entrée clavier//

        while (true) {
            for (game joueur : joueurs) {       //on parcourt tous les joueurs de la liste//
                if (!joueur.estActif()) continue;

                System.out.println("\nTour du joueur " + joueur.getId() + " (" + joueur.getCouleur() + "J" + joueur.getId() + RESET + ")");
                afficherGrille();       //on affiche le joueurs sur la grille//

                System.out.println("Utilisez Z (haut), S (bas), Q (gauche), D (droite) pour vous déplacer :");
                char input = scanner.next().charAt(0);  //on explique//

                switch (input) {
                    case 'z':
                        joueur.haut();
                        break;
                    case 's':
                        joueur.bas(lignes);
                        break;
                    case 'q':
                        joueur.gauche();
                        break;
                    case 'd':
                        joueur.droite(colonnes);
                        break;
                    default:
                        System.out.println("Entrée invalide, veuillez réessayer."); //si aucun des cas si-dessu , erreur//
                        continue;
                }

                afficherGrille();


            }



        }
    }

    public static void main(String[] args) {
        Grille grille = new Grille();

        // Ajout des joueurs
        grille.ajouterJoueur(new game(1, "\033[1;31m", 5, 5)); // Rouge
        grille.ajouterJoueur(new game(2, "\033[1;34m", 6, 6)); // Bleu

        // Lancer la partie
        grille.jouer();
    }
}

class game {
    private int id;         // Identifiant du joueur
    private String couleur; // Couleur du joueur
    private int x;          // Position X du joueur dans la grille
    private int y;          // Position Y du joueur dans la grille
    private boolean actif;  // Indique si le joueur est encore en jeu

    public game(int id, String couleur, int x, int y) {
        this.id = id;
        this.couleur = couleur;
        this.x = x;
        this.y = y;
        this.actif = true;
    }

    // Méthodes getter
    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCouleur() {
        return couleur;
    }

    public boolean estActif() {
        return actif;
    }

    // Le joueur est éliminé
    public void eliminer() {
        this.actif = false;
    }

    // Déplace le joueur vers le haut
    public void haut() {
        if (x > 1) { // Bord supérieur
            x--;
        } else {
            System.out.println("Déplacement impossible : bord supérieur atteint.");
        }
    }

    // Déplace le joueur vers le bas
    public void bas(int maxLignes) {
        if (x < maxLignes - 2) { // Bord inférieur
            x++;
        } else {
            System.out.println("Déplacement impossible : bord inférieur atteint.");
        }
    }

    // Déplace le joueur vers la gauche
    public void gauche() {

        if (y > 1) { // Bord gauche
            y--;
        } else {
            System.out.println("Déplacement impossible : bord gauche atteint.");
        }
    }

    // Déplace le joueur vers la droite
    public void droite(int maxColonnes) {
        if (y < maxColonnes - 2) { // Bord droit
            y++;
        } else {
            System.out.println("Déplacement impossible : bord droit atteint.");
        }
    }
}


