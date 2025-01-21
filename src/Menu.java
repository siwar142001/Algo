import java.util.Scanner;

public class Menu {
    // Affiche le menu principale et c'est option
    public static void menu() {
        System.out.println("Detructible Chess");
        System.out.println("1. Nouvelle partie");
        System.out.println("2. Règle");
        System.out.println("3. Score");
        System.out.println("4. Quitter");
        System.out.println("Entrer votre choix : ");
    }

    // Méthode principale
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Permet de lire l'entrée utilisateur
        int choix = 0; // Variable pour stocker le choix de l'utilisateur

        // Boucle du menu, on y reste tante que l'on a pas choisi une aption
        while (choix != 4) {
            menu();

            try { // Si on entre un nomnbre on entre dans try sinon non
                // Lecture et conversion de l'entrée utilisateur en entier
                choix = Integer.parseInt(scanner.nextLine());
                switch (choix) {
                    case 1: // Appuie sur 1 pour aller dans la page "Nouvelle partie"
                        ChoixNmbJoueur(scanner);
                        break;
                    case 2:
                        Règle(scanner);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default: // Envoie une erreur si l'utilisateur ne choisi pas 1; 2; 3 ou 4
                        System.out.println("Ce choix n'existe pas ! Veuillez choisir une option valide !");
                }
            } catch (NumberFormatException e) { // Envoie une erreur si l'utilisateur ne choisi pas un nombre.
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
        scanner.close();
    }

    // Méthode pour la page "Nouvelle partie"
    public static void ChoixNmbJoueur(Scanner scanner) {
        int choix = 0; // Variable pour stocker le choix du nombre de joueurs

        // Boucle pour gérer les choix de l'utilisateur
        while (choix != 4) {
            System.out.println("Choix du nombre de joueurs");
            System.out.println("1. 2 Joueurs");
            System.out.println("2. 3 Joueurs");
            System.out.println("3. 4 Joueurs");
            System.out.println("4. Revenir en arrière");
            System.out.print("Entrez votre choix : ");

            try {
                choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        menu();
                        break;
                    default:
                        System.out.println("Ce choix n'existe pas ! Veuillez choisir une option valide !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
    }

    public static void Règle(Scanner scanner) {
        int choix = 0;

        while (choix != 1) {
            System.out.println("Règle du Jeu");
            System.out.println("\nLes règles du jeu sont simples, " +
                    "\nvous avez un carré qui peut se déplacer sur une surface 10x11, " +
                    "\nvous pouvez poser des blocs. " +
                    "\nVotre but est de bloquer votre adversaire en posant ces blocs. " +
                    "\nVous ne pouvez avancer sur un bloc qui à été posé par un joueur. " +
                    "\nVous ne pouvez vous déplacer que horizontalement ou verticalement. " +
                    "\nVous ne pouvez pas foncer dans un mur. Une fois que tous vos adversaires ont été bloqués, " +
                    "\nous avez gagné la partie.\n");
            System.out.println("1. Revenir en arrière");
            System.out.print("Entrez votre choix : ");

            try {
                choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 1:
                        return;
                    default:
                        System.out.println("Ce choix n'existe pas ! Veuillez choisir une option valide !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
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