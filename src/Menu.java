import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principale du menu pour le jeu "Detructible Chess".
 * Permet de naviguer entre les différentes options du jeu :
 * - Nouvelle partie
 * - Affichage des règles
 * - Affichage des scores
 * - Quitter
 */
public class Menu {

    /** Instance de la classe Game pour gérer les parties. */
    private static Game game = new Game(); // Instance de Game

    /** Nom du fichier pour sauvegarder et charger les résultats. */
    private static final String FICHIER_RESULTATS = "resultats.txt"; // Nom du fichier pour sauvegarder les résultats

    /**
     * Affiche le menu principal et ses options.
     */
    public static void afficherMenu() {
        System.out.println("\nDetructible Chess");
        System.out.println("1. Nouvelle partie");
        System.out.println("2. Règle");
        System.out.println("3. Score");
        System.out.println("4. Quitter");
        System.out.print("Entrez votre choix : ");
    }

    /**
     * Point d'entrée principal du programme.
     * @param args Arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Permet de lire les entrées utilisateur

        // Affichage de la tentative de chargement des résultats
        System.out.println("Chargement des résultats depuis le fichier : " + FICHIER_RESULTATS);

        // Test des parties et sauvegarde initiale
        game.testPartieEtSauvegarde();

        // Charger les résultats depuis le fichier
        game.chargerResultats(FICHIER_RESULTATS);

        int choix = 0; // Variable pour stocker le choix de l'utilisateur

        // Boucle principale pour gérer le menu
        while (choix != 4) {
            afficherMenu();

            if (scanner.hasNextLine()) { // Vérifie s'il y a une entrée utilisateur
                try {
                    choix = Integer.parseInt(scanner.nextLine()); // Lecture du choix utilisateur
                    switch (choix) {
                        case 1: // Nouvelle partie
                            nouvellePartie(scanner);
                            game.sauvegarderResultats(FICHIER_RESULTATS);
                            break;
                        case 2: // Afficher les règles
                            afficherRegles(scanner);
                            break;
                        case 3: // Afficher les scores
                            game.chargerResultats(FICHIER_RESULTATS);
                            break;
                        case 4: // Quitter
                            System.out.println("Sauvegarde des résultats en cours...");
                            game.sauvegarderResultats(FICHIER_RESULTATS);
                            System.out.println("Merci d'avoir joué !");
                            scanner.close();
                            break;
                        default:
                            System.out.println("Choix invalide ! Veuillez réessayer.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                }
            } else {
                System.out.println("Aucune entrée détectée. Arrêt du programme.");
                break;
            }
        }
    }

    /**
     * Permet de démarrer une nouvelle partie avec un choix du nombre de joueurs.
     * @param scanner Scanner pour lire les entrées utilisateur.
     */
    public static void nouvellePartie(Scanner scanner) {
        int choix = 0; // Stocke le choix du nombre de joueurs

        // Boucle pour afficher les options du nombre de joueurs
        while (choix != 4) {
            System.out.println("\nChoix du nombre de joueurs");
            System.out.println("1. 2 Joueurs");
            System.out.println("2. 3 Joueurs");
            System.out.println("3. 4 Joueurs");
            System.out.println("4. Revenir au menu principal");
            System.out.print("Entrez votre choix : ");

            try {
                choix = Integer.parseInt(scanner.nextLine()); // Lecture du choix utilisateur

                switch (choix) {
                    case 1:
                        lancerJeu(2, scanner); // Lance une partie avec 2 joueurs
                        return;
                    case 2:
                        lancerJeu(3, scanner); // Lance une partie avec 3 joueurs
                        return;
                    case 3:
                        lancerJeu(4, scanner); // Lance une partie avec 4 joueurs
                        return;
                    case 4:
                        return; // Retourne au menu principal
                    default:
                        System.out.println("Ce choix n'existe pas ! Veuillez choisir une option valide !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
    }

    /**
     * Lance une partie avec un nombre donné de joueurs.
     * @param nombreDeJoueurs Le nombre de joueurs pour la partie.
     * @param scanner Scanner pour lire les entrées utilisateur.
     */
    public static void lancerJeu(int nombreDeJoueurs, Scanner scanner) {
        System.out.println("\nEnregistrement des joueurs (" + nombreDeJoueurs + " joueurs) :");

        ArrayList<Joueur> joueurs = new ArrayList<>(); // Liste des joueurs

        // Enregistrement des joueurs
        for (int i = 1; i <= nombreDeJoueurs; i++) {
            String pseudo;
            while (true) { // Boucle pour obtenir un pseudo valide
                System.out.print("Entrez le pseudo du joueur " + i + " (entre 2 et 10 caractères) : ");
                pseudo = scanner.nextLine();

                if (pseudo.length() <= 10 && pseudo.length() >= 2) {
                    // Assigner une couleur unique à chaque joueur
                    String couleur = switch (i) {
                        case 1 -> "\033[1;31m"; // Rouge
                        case 2 -> "\033[1;34m"; // Bleu
                        case 3 -> "\033[1;33m"; // Jaune
                        case 4 -> "\033[1;32m"; // Vert
                        default -> "\033[1;37m"; // Blanc
                    };

                    // Initialisation des positions de départ
                    int x = 2 + i;
                    int y = 2 + i;

                    joueurs.add(new Joueur(i, couleur, x, y)); // Création et ajout d'un joueur
                    break;
                } else {
                    System.out.println("Pseudo invalide. Veuillez entrer un pseudo entre 2 et 10 caractères.");
                }
            }
        }

        // Initialisation de la grille et ajout des joueurs
        Grille grille = new Grille();
        for (Joueur joueur : joueurs) {
            grille.ajouterJoueur(joueur);
        }

        // Démarrage de la partie
        System.out.println("Tous les joueurs ont été enregistrés ! La partie commence !");
        grille.jouer();
    }

    /**
     * Affiche les règles du jeu.
     * @param scanner Scanner pour attendre une entrée utilisateur avant de retourner au menu principal.
     */
    public static void afficherRegles(Scanner scanner) {
        System.out.println("\n\nRègle du Jeu");
        System.out.println("\nLes règles du jeu sont simples, " +
                "\nvous avez un carré qui peut se déplacer sur une surface 10x11, " +
                "\nvous pouvez poser des blocs. " +
                "\nVotre but est de bloquer votre adversaire en posant ces blocs. " +
                "\nVous ne pouvez avancer sur un bloc qui a été posé par un joueur. " +
                "\nVous ne pouvez vous déplacer que horizontalement ou verticalement. " +
                "\nVous ne pouvez pas foncer dans un mur. Une fois que tous vos adversaires ont été bloqués, " +
                "\nvous avez gagné la partie.");
        System.out.println("\nAppuyez sur Entrée pour revenir au menu principal...");
        scanner.nextLine(); // Attente d'une entrée pour retourner au menu
    }
}
