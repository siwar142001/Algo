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

    // Méthode pour enregistrer un joueur
    public void enregistrementPseudo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre pseudo : ");
        String pseudo = scanner.nextLine();

        Pseudo joueur = new Pseudo(pseudo);
        listeJoueurs.add(joueur);

        System.out.println("Bienvenue, " + joueur + "! Votre pseudo a été enregistré.");
    }

    // Méthode pour afficher la liste des joueurs
    public void afficherJoueurs() {
        System.out.println("Liste des joueurs enregistrés :");


        int count = 0;
        for (Pseudo joueur : listeJoueurs) {
            System.out.println("- " + joueur);
            count++;

            if (count >= 10) {
                break;
            }
        }
    }


    // Méthode pour ajouter une victoire à un joueur
    public void ajouterVictoire(String pseudo) {
        for (Pseudo joueur : listeJoueurs) {
            if (joueur.pseudo.equals(pseudo)) {
                joueur.ajouterVictoire();
                System.out.println("Victoire ajoutée pour " + pseudo);
                return;
            }
        }
        System.out.println("Joueur non trouvé !");
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.enregistrementPseudo();
        game.enregistrementPseudo();

        game.ajouterVictoire("Alice");
        game.afficherJoueurs();
    }
}
