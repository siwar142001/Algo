
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.io.*;




class Pseudo {
    String pseudo;
    int victoires;
    int defaites;
    int partiesJouees;


    // Constructeur pour initialiser le pseudo
    Pseudo(String pseudo) {
        this.pseudo = pseudo;
        
    }

    Pseudo(String pseudo, int victoires, int defaites, int partiesJouees) {
        this.pseudo = pseudo;
        this.victoires = victoires;
        this.defaites = defaites;
        this.partiesJouees = partiesJouees;
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

    public void testPartieEtSauvegarde() {
        // Simulation d'une partie
        System.out.println("Le joueur 1 (J1) a gagné !");
        ajouterResultatPartie("Joueur 1", 1, 0, 1); // Ajouter le résultat du joueur 1
        ajouterResultatPartie("Joueur 2", 0, 1, 1); // Ajouter le résultat du joueur 2

        // Sauvegarder les résultats
        sauvegarderResultats("resultats.txt");

        // Charger et afficher les résultats
        chargerResultats("resultats.txt");
    }

    // Ajouter les résultats d'une partie dans la liste des joueurs
    public void ajouterResultatPartie(String pseudo, int victoires, int defaites, int partiesJouees) {
        Pseudo joueur = new Pseudo(pseudo);
        joueur.victoires = victoires;
        joueur.defaites = defaites;
        joueur.partiesJouees = partiesJouees;
        listeJoueurs.add(joueur);  // Ajoute le joueur à la liste
        System.out.println("Résultats de " + pseudo + " ajoutés à la liste.");
    }

    // Sauvegarder les résultats dans le fichier
    public void sauvegarderResultats(String fichier) {
        try (FileWriter writer = new FileWriter(fichier)) {
            if (listeJoueurs.isEmpty()) {
                System.out.println("Aucun joueur à sauvegarder.");
                return;
            }

            for (Pseudo joueur : listeJoueurs) {
                writer.write(joueur.pseudo + ";" + joueur.victoires + ";" +
                             joueur.defaites + ";" + joueur.partiesJouees + "\n");
            }
            System.out.println("Les résultats ont été sauvegardés dans le fichier : " + fichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des résultats : " + e.getMessage());
        }
    }


    // Charger les résultats depuis le fichier
    public void chargerResultats(String fichier) {
        File file = new File(fichier);
        if (!file.exists()) {
            System.out.println("Le fichier " + fichier + " n'existe pas encore. Aucune donnée chargée.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            System.out.println("test1");

            listeJoueurs.clear(); // Effacer les anciens résultats avant de charger

            while (scanner.hasNextLine()) {
                System.out.println("test2");
                String ligne = scanner.nextLine();
                String[] parts = ligne.split(";");
                if (parts.length == 4) {
                    System.out.println("test3");
                    String pseudo = parts[0];
                    int victoires = Integer.parseInt(parts[1]);
                    int defaites = Integer.parseInt(parts[2]);
                    int partiesJouees = Integer.parseInt(parts[3]);
                    Pseudo joueur = new Pseudo(pseudo);
                    joueur.victoires = victoires;
                    joueur.defaites = defaites;
                    joueur.partiesJouees = partiesJouees;
                    listeJoueurs.add(joueur);
                }
            }

            System.out.println("Les résultats ont été chargés depuis le fichier : " + fichier);

            // Affichage des résultats chargés
            for (Pseudo joueur : listeJoueurs) {
                System.out.println("Joueur : " + joueur.pseudo + ", Victoires : " + joueur.victoires +
                                   ", Défaites : " + joueur.defaites + ", Parties jouées : " + joueur.partiesJouees);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erreur lors du chargement des résultats : " + e.getMessage());
        }
    }
    
    
    


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