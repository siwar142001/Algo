import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;




class Pseudo {
    String pseudo;
    int victoires;
    int defaites;
    int partiesJouees;


    // Constructeur pour initialiser le pseudo
    Pseudo(String pseudo) {
        this.pseudo = pseudo;
        this.victoires = 0;
        this.defaites = 0;
        this.partiesJouees = 0;
    }


    // Méthodes pour mettre à jour les statistiques
    public void ajouterVictoire() {
        this.victoires++;
        this.partiesJouees++;
    }


    public void ajouterDefaite() {
        this.defaites++;
        this.partiesJouees++;
    }


    public void ajouterPartieJouee() {
        this.partiesJouees++;
    }


    @Override
    public String toString() {
        return pseudo + " (Victoires: " + victoires + ", Défaites: " + defaites + ", Parties Jouées: " + partiesJouees + ")";
    }
}




class Game {
    private List<Pseudo> listeJoueurs = new LinkedList<>();


    // Méthode pour enregistrer un joueur (avec saisie utilisateur)
   /*public void enregistrementPseudo() {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Entrez votre pseudo : ");
       String pseudo = scanner.nextLine();
       enregistrerPseudo(pseudo);
   }*/


    // Méthode pour enregistrer un joueur (avec pseudo passé en paramètre)
    public void enregistrerPseudo(String pseudo) {
        Pseudo joueur = new Pseudo(pseudo);
        listeJoueurs.add(joueur);
        System.out.println("Bienvenue, " + joueur + "! Votre pseudo a été enregistré.");

    }


    // Méthode pour trier en ordre croissant selon les victoires
    public void trieCroissant(Scanner scanner) {
        int choix = 0;


        System.out.println("\nClassement des joueurs enregistré de manière croissante :");


        // Tri à bulles avec plusieurs critères
        for (int i = 0; i < listeJoueurs.size() - 1; i++) {
            for (int j = i + 1; j < listeJoueurs.size(); j++) {
                if (shouldSwap(listeJoueurs.get(i), listeJoueurs.get(j), true)) {
                    Pseudo temp = listeJoueurs.get(i);
                    listeJoueurs.set(i, listeJoueurs.get(j));
                    listeJoueurs.set(j, temp);
                }
            }
        }
        // Afficher les joueurs après tri (maximum 10 joueurs)
        afficherClassement();
        System.out.println("1. Score");
        System.out.println("2. Décroissant");
        System.out.println("3. Revenir au menu principal");
        try {
            choix = Integer.parseInt(scanner.nextLine());


            switch (choix) {
                case 1:
                    afficherJoueurs(scanner);
                    return;
                case 2:
                    trieDecroissant(scanner);
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Ce choix n'existe pas ! Veuillez choisir une option valide !");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
        }


    }


    // Méthode pour trier en ordre décroissant selon les victoires
    public void trieDecroissant(Scanner scanner) {
        int choix = 0;


        System.out.println("\nClassement des joueurs enregistré de manière décroissante :");


        // Tri à bulles avec plusieurs critères
        for (int i = 0; i < listeJoueurs.size() - 1; i++) {
            for (int j = i + 1; j < listeJoueurs.size(); j++) {
                if (shouldSwap(listeJoueurs.get(i), listeJoueurs.get(j), false)) {
                    Pseudo temp = listeJoueurs.get(i);
                    listeJoueurs.set(i, listeJoueurs.get(j));
                    listeJoueurs.set(j, temp);
                }
            }
        }


        // Afficher les joueurs après tri (maximum 10 joueurs)
        afficherClassement();
        System.out.println("1. Score");
        System.out.println("2. Décroissant");
        System.out.println("3. Revenir au menu principal");
        try {
            choix = Integer.parseInt(scanner.nextLine());


            switch (choix) {
                case 1:
                    afficherJoueurs(scanner);
                    return;
                case 2:
                    trieCroissant(scanner);
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Ce choix n'existe pas ! Veuillez choisir une option valide !");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
        }
    }


    // Méthode pour déterminer si deux joueurs doivent être échangés
    private boolean shouldSwap(Pseudo joueur1, Pseudo joueur2, boolean croissant) {
        if (croissant) {
            // Tri croissant
            if (joueur1.victoires != joueur2.victoires) {
                return joueur1.victoires > joueur2.victoires;
            } else if (joueur1.partiesJouees != joueur2.partiesJouees) {
                return joueur1.partiesJouees > joueur2.partiesJouees;
            } else {
                return joueur1.defaites > joueur2.defaites; // Plus de défaites en dernier
            }
        } else {
            // Tri décroissant
            if (joueur1.victoires != joueur2.victoires) {
                return joueur1.victoires < joueur2.victoires;
            } else if (joueur1.partiesJouees != joueur2.partiesJouees) {
                return joueur1.partiesJouees < joueur2.partiesJouees;
            } else {
                return joueur1.defaites < joueur2.defaites; // Moins de défaites en premier
            }
        }
    }


    // Méthode pour afficher le classement (maximum 10 joueurs)
    private void afficherClassement() {
        System.out.println("Classement des joueurs (maximum 10) :");
        for (int i = 0; i < listeJoueurs.size() && i < 10; i++) {
            System.out.println("- " + listeJoueurs.get(i));
        }
    }


    // Méthode pour afficher la liste des joueurs
    public void afficherJoueurs(Scanner scanner) {
        int choix = 0;
        int count = 0;


        System.out.println("\nListe des joueurs enregistrés :");
        System.out.println("Classement des joueurs (maximum 10) :");


        while (choix != 4) {
            System.out.println("1. Croissant");
            System.out.println("2. Décroissant");


            for (Pseudo joueur : listeJoueurs) {
                System.out.println("- " + joueur);
                count++;


                if (count >= 10) {
                    break;
                }
            }


            System.out.println("3. Revenir au menu principal");
            System.out.print("Entrez votre choix : ");


            try {
                choix = Integer.parseInt(scanner.nextLine());


                switch (choix) {
                    case 1:
                        trieCroissant(scanner);
                        return;
                    case 2:
                        trieDecroissant(scanner);
                        return;
                    case 3:
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

