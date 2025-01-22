import java.util.Scanner; // Import la classe scaner pour lire les entrés de l'utilisateur

public class Menu {
    private static Game game = new Game(); // Instance de Game

    // Affiche le menu principal et ses options
    public static void afficherMenu() {
        System.out.println("\nDetructible Chess");
        System.out.println("1. Nouvelle partie");
        System.out.println("2. Règle");
        System.out.println("3. Score");
        System.out.println("4. Quitter");
        System.out.print("Entrez votre choix : ");
    }

    // Point d'entré du programme
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Permet de lire l'entrée utilisateur
        int choix = 0; // Variable pour stocker le choix de l'utilisateur

        // Boucle principale du menu
        while (choix != 4) {
            afficherMenu();

            try {
                choix = Integer.parseInt(scanner.nextLine()); // Lecture de l'entrée utilisateur
                switch (choix) {
                    case 1: // Nouvelle partie
                        nouvellePartie(scanner);
                        break;
                    case 2: // Règles du jeu
                        afficherRegles(scanner);
                        break;
                    case 3: // Afficher les scores
                        game.afficherJoueurs(scanner);
                        break;
                    case 4: // Quitter
                        System.out.println("Merci d'avoir joué ! À bientôt !");
                        break;
                    default:
                        System.out.println("Ce choix n'existe pas ! Veuillez choisir une option valide !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
        scanner.close();
    }

    // Méthode pour gérer une nouvelle partie
    public static void nouvellePartie(Scanner scanner) {
        int choix = 0;

        while (choix != 4) {
            System.out.println("\nChoix du nombre de joueurs");
            System.out.println("1. 2 Joueurs");
            System.out.println("2. 3 Joueurs");
            System.out.println("3. 4 Joueurs");
            System.out.println("4. Revenir au menu principal");
            System.out.print("Entrez votre choix : ");

            try {
                choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 1:
                        enregistrerJoueurs(2, scanner); // Enregistre 2 joueurs
                        return;
                    case 2:
                        enregistrerJoueurs(3, scanner); // Enregistre 3 joueurs
                        return;
                    case 3:
                        enregistrerJoueurs(4, scanner); // Enregistre 4 joueurs
                        return;
                    case 4:
                        return; // Retourner au menu principal
                    default:
                        System.out.println("Ce choix n'existe pas ! Veuillez choisir une option valide !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
    }

    public static void enregistrerJoueurs(int nombreDeJoueurs, Scanner scanner) {
        // Affiche un message pour indiquer le début de l'enregistrement des joueurs.
        System.out.println("\nEnregistrement des joueurs (" + nombreDeJoueurs + " joueurs) :");

        for (int i = 1; i <= nombreDeJoueurs; i++) {
            String pseudo;
            while (true) { // Boucle pour redemander le pseudo si nécessaire
                System.out.print("Entrez le pseudo du joueur " + i + " (entre 2 et 10 caractères) : ");
                pseudo = scanner.nextLine();

                if (pseudo.length() <= 10 && pseudo.length() >= 2) {
                    game.enregistrerPseudo(pseudo); // Ajoute le pseudo dans la liste des joueurs via l'objet `game`.
                    break; // Sortie de la boucle si le pseudo est conforme
                } else {
                    System.out.println("Veuillez respectez les conditions sinon jte bute");
                }
            }
        }
        System.out.println("Tous les joueurs ont été enregistrés !"); // Confirme que tout les joueur son enregistré
    }

    // Méthode pour afficher les règles du jeu
    public static void afficherRegles(Scanner scanner) {
        System.out.println("\n\nRègle du Jeu");
        System.out.println("\nLes règles du jeu sont simples, " +
                "\nvous avez un carré qui peut se déplacer sur une surface 10x11, " +
                "\nvous pouvez poser des blocs. " +
                "\nVotre but est de bloquer votre adversaire en posant ces blocs. " +
                "\nVous ne pouvez avancer sur un bloc qui à été posé par un joueur. " +
                "\nVous ne pouvez vous déplacer que horizontalement ou verticalement. " +
                "\nVous ne pouvez pas foncer dans un mur. Une fois que tous vos adversaires ont été bloqués, " +
                "\nvous avez gagné la partie.");
        System.out.println("\nAppuyez sur Entrée pour revenir au menu principal...");
        scanner.nextLine(); // Attente d'une entrée pour revenir au menu
    }
}


/*
    Je dois avoir une page menu.
    Dans cette page, il doit y avoir 4 bouton et un titre. (Titre au choix)
    Le première bouton doit nous renvoyer vers l'endroit ou on choisit le nombre de joueur avec lequel on veut jouer.
    Le deuxième bouton nous renvoie vers les règle du jeu.
    Le troisième bouton nous renvoie vers nos score durant les partie.
    Le quatrième nous fais quitter le jeu.
    Sur chaque page, il doit y avoir un bouton retour pour nous renvoyer vers le page précedante.
 */