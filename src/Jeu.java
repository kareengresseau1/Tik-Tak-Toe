public class Jeu {

    public static void initierTableau(char[][] entrees){
        for(int i = 0; i < 2; i++){
            for(int a = 0; a < 2; a++){
                entrees[i][a] = ' ';
            }
        }
    }

    public static char determinerPionOrdinateur(char choixUtilisateur) throws InstantiationException {
        char choixOrdi = ' ';
        if(choixUtilisateur == 'X'){
            choixOrdi = 'O';
        }
        else if(choixUtilisateur == 'O'){
            choixOrdi = 'X';
        }
        else{
            throw new InstantiationException("Le choix de l'utilisateur n'est pas valide");
        }
        return choixOrdi;
    }

    public void afficher(String grille){
        System.out.println(grille);
    }

    public String mettreAJourGrille(int rangee, int colonne, char choix, char[][] entrees, String grille){
        if((rangee >= 0 && rangee <=2) && (colonne >= 0 && colonne <=2)){
            entrees[rangee][colonne] = choix;
        }else {
            throw new ArrayIndexOutOfBoundsException("La cellule selectionnee n'existe pas");
        }
        return grille;
    }

    public boolean partieGagnante(char[][] entree, char choix){
        boolean aGagne = false;
        if(entree[0][0] == choix && entree[0][1] == choix && entree[0][2] == choix){
            aGagne = true;
        }
        else if(entree[1][0] == choix && entree[1][1] == choix && entree[1][2] == choix){
            aGagne = true;
        }
        else if(entree[2][0] == choix && entree[2][1] == choix && entree[2][2] == choix){
            aGagne = true;
        }
        else if(entree[0][0] == choix && entree[1][0] == choix && entree[2][0] == choix){
            aGagne = true;
        }
        else if(entree[0][1] == choix && entree[1][1] == choix && entree[2][1] == choix){
            aGagne = true;
        }
        else if(entree[0][2] == choix && entree[1][2] == choix && entree[2][2] == choix){
            aGagne = true;
        }
        else if(entree[0][0] == choix && entree[1][1] == choix && entree[2][2] == choix){
            aGagne = true;
        }
        else if(entree[0][2] == choix && entree[1][1] == choix && entree[2][0] == choix){
            aGagne = true;
        }
        return aGagne;
    }


}
