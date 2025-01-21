public class game {

    public class Joueur {
        private int id;
        private String couleur;
        private int x, y;
        private boolean actif;

        public Joueur(int id, String couleur, int x, int y) {
            this.id = id;
            this.couleur = couleur;
            this.x = x;
            this.y = y;
            this.actif = true;
        }

        // renvoie true si le joueur est encore en vie

        public boolean estActif() {
            return actif;
        }

        // le joueur est éliminé et donc n'est plus actif

        public void eliminer() {
            this.actif = false;
        }

        //return true ou false si le joueur peut ou non se deplacer sur une case adjacente

        public boolean estBloque(Grille grille) {
            return !(grille.estCaseLibre(x - 1, y) || grille.estCaseLibre(x + 1, y)
                    || grille.estCaseLibre(x, y - 1) || grille.estCaseLibre(x, y + 1));
        }

        // deplacement du joueur

        public void deplacer(int pos_x_joueur,int pos_y_joueur,int prochaine_pos_x,int prochaine_pos_y){

            if
        }



    }



}
