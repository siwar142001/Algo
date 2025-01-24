import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

/**
 * Représente un joueur avec un pseudo et des statistiques de jeu (victoires, défaites, parties jouées).
 */
class Pseudo {
    String pseudo; // Nom du joueur
    int victoires; // Nombre de victoires
    int defaites;  // Nombre de défaites
    int partiesJouees; // Nombre total de parties jouées

    /**
     * Constructeur pour initialiser un joueur avec seulement son pseudo.
     * @param pseudo le pseudo du joueur
     */
    Pseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Constructeur pour initialiser un joueur avec des statistiques complètes.
     * @param pseudo le pseudo du joueur
     * @param victoires le nombre de victoires
     * @param defaites le nombre de défaites
     * @param partiesJouees le nombre total de parties jouées
     */
    Pseudo(String pseudo, int victoires, int defaites, int partiesJouees) {
        this.pseudo = pseudo;
        this.victoires = victoires;
        this.defaites = defaites;
        this.partiesJouees = partiesJouees;
    }

    /**
     * Ajoute une victoire au joueur et incrémente le nombre de parties jouées.
     */
    public void ajouterVictoire() {
        this.victoires++;
        this.partiesJouees++;
    }

    /**
     * Ajoute une défaite au joueur et incrémente le nombre de parties jouées.
     */
    public void ajouterDefaite() {
        this.defaites++;
        this.partiesJouees++;
    }

    /**
     * Incrémente uniquement le nombre de parties jouées.
     */
    public void ajouterPartieJouee() {
        this.partiesJouees++;
    }

    /**
     * Retourne une représentation textuelle du joueur avec ses statistiques.
     * @return une chaîne de caractères représentant le joueur
     */
    @Override
    public String toString() {
        return pseudo + " (Victoires: " + victoires + ", Défaites: " + defaites + ", Parties Jouées: " + partiesJouees + ")";
    }
}

/**
 * Gère les joueurs, leurs statistiques, ainsi que la sauvegarde et le chargement des résultats.
 */
class Game {
    private List<Pseudo> listeJoueurs = new LinkedList<>(); // Liste des joueurs
    private static final String FICHIER_RESULTATS = "resultats.txt"; // Nom du fichier pour sauvegarder les résultats

    /**
     * Teste une partie fictive et sauvegarde les résultats.
     */
    public void testPartieEtSauvegarde() {
        System.out.println("Le joueur 1 (J1) a gagné !");
        ajouterResultatPartie("Joueur 1", 1, 0, 1);
        ajouterResultatPartie("Joueur 2", 0, 1, 1);

        sauvegarderResultats(FICHIER_RESULTATS);
        chargerResultats(FICHIER_RESULTATS);
    }

    /**
     * Ajoute les résultats d'une partie pour un joueur donné.
     * @param pseudo le pseudo du joueur
     * @param victoires le nombre de victoires ajoutées
     * @param defaites le nombre de défaites ajoutées
     * @param partiesJouees le nombre de parties jouées ajoutées
     */
    public void ajouterResultatPartie(String pseudo, int victoires, int defaites, int partiesJouees) {
        Pseudo joueur = new Pseudo(pseudo, victoires, defaites, partiesJouees);
        listeJoueurs.add(joueur);
        System.out.println("Résultats de " + pseudo + " ajoutés à la liste.");
    }

    /**
     * Sauvegarde les résultats des joueurs dans un fichier texte.
     * @param fichier le nom du fichier de sauvegarde
     */
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

    /**
     * Charge les résultats des joueurs depuis un fichier texte.
     * @param fichier le nom du fichier de chargement
     */
    public void chargerResultats(String fichier) {
        File file = new File(fichier);
        if (!file.exists()) {
            System.out.println("Le fichier " + fichier + " n'existe pas encore. Aucune donnée chargée.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            listeJoueurs.clear(); // Réinitialise la liste avant de charger

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] parts = ligne.split(";");
                if (parts.length == 4) {
                    String pseudo = parts[0];
                    int victoires = Integer.parseInt(parts[1]);
                    int defaites = Integer.parseInt(parts[2]);
                    int partiesJouees = Integer.parseInt(parts[3]);
                    listeJoueurs.add(new Pseudo(pseudo, victoires, defaites, partiesJouees));
                }
            }

            System.out.println("Les résultats ont été chargés depuis le fichier : " + fichier);
            for (Pseudo joueur : listeJoueurs) {
                System.out.println(joueur);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erreur lors du chargement des résultats : " + e.getMessage());
        }
    }

    /**
     * Enregistre un joueur en utilisant son pseudo.
     * @param pseudo le pseudo du joueur
     */
    public void enregistrerPseudo(String pseudo) {
        Pseudo joueur = new Pseudo(pseudo);
        listeJoueurs.add(joueur);
        System.out.println("Bienvenue, " + joueur + "! Votre pseudo a été enregistré.");
    }

    /**
     * Trie les joueurs par ordre croissant de victoires et affiche le classement.
     * @param scanner un scanner pour l'interaction utilisateur
     */
    public void trieCroissant(Scanner scanner) {
        listeJoueurs.sort((j1, j2) -> {
            if (j1.victoires != j2.victoires) {
                return Integer.compare(j1.victoires, j2.victoires);
            } else if (j1.partiesJouees != j2.partiesJouees) {
                return Integer.compare(j1.partiesJouees, j2.partiesJouees);
            } else {
                return Integer.compare(j1.defaites, j2.defaites);
            }
        });
        afficherClassement();
        sauvegarderResultats(FICHIER_RESULTATS);
    }

    /**
     * Trie les joueurs par ordre décroissant de victoires et affiche le classement.
     * @param scanner un scanner pour l'interaction utilisateur
     */
    public void trieDecroissant(Scanner scanner) {
        listeJoueurs.sort((j1, j2) -> {
            if (j1.victoires != j2.victoires) {
                return Integer.compare(j2.victoires, j1.victoires);
            } else if (j1.partiesJouees != j2.partiesJouees) {
                return Integer.compare(j2.partiesJouees, j1.partiesJouees);
            } else {
                return Integer.compare(j2.defaites, j1.defaites);
            }
        });
        afficherClassement();
    }

    /**
     * Affiche le classement des joueurs (maximum 10).
     */
    private void afficherClassement() {
        System.out.println("Classement des joueurs (maximum 10) :");
        for (int i = 0; i < listeJoueurs.size() && i < 10; i++) {
            System.out.println("- " + listeJoueurs.get(i));
        }
    }
}
