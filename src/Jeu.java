public class Jeu {

    public static char determinerPionOrdinateur(char choixUtilisateur) throws InstantiationException {
        char choixOrdi = ' ';
        if(choixUtilisateur == 'X'){
            choixOrdi = 'O';
        }
        else if(choixUtilisateur == 'O'){
            choixOrdi = 'X';
        }
        if(choixUtilisateur != 'X' && choixUtilisateur != 'O'){
            throw new InstantiationException("Le choix de l'utilisateur n'est pas valide");
        }
        return choixOrdi;
    }

    public static void afficher(char[][] entrees){
        String grille =
                "-------------\n" +
                        "| " + entrees[0][0] + " | " + entrees[0][1] + " | " + entrees[0][2] + " |\n" +
                        "-------------\n" +
                        "| " + entrees[1][0] + " | " + entrees[1][1] + " | " + entrees[1][2] + " |\n" +
                        "-------------\n" +
                        "| " + entrees[2][0] + " | " + entrees[2][1] + " | " + entrees[2][2] + " |\n" +
                        "-------------\n";
        System.out.println(grille);
    }

    public static char[][] mettreAJourGrille(int rangee, int colonne, char choix, char[][] entrees, String grille){
        if((rangee >= 0 && rangee <=2) && (colonne >= 0 && colonne <=2)){
            entrees[rangee][colonne] = choix;
        }else {
            throw new ArrayIndexOutOfBoundsException("La cellule selectionnee n'existe pas");
        }
        return entrees;
    }

    public static boolean estOccupeeCellule(char[][] entrees, int rangee, int colonne,
                                            char choixJoueur, char choixAutreJoueur){
        return entrees[rangee][colonne] == choixJoueur || entrees[rangee][colonne] == choixAutreJoueur;
    }

    public static char[][] trouverCelluleVide(char[][] entrees, char choixOrdinateur){
        boolean celluleAssignee = false;
        int rangee = 0;
        do{
            for(int i = 0; i < 3; i++){
                if(entrees[rangee][i] == '*'){
                    entrees[rangee][i] = choixOrdinateur;
                    celluleAssignee = true;
                    break;
                }
            }
            rangee++;
        }while(!celluleAssignee);
        return entrees;
    }

    public static boolean matchNul(char[][] entree){
        boolean grilleRemplie = true;
        int rangee = 0;
        do{
            for(int i = 0; i < 3; i++){
                if(entree[rangee][i] == '*'){
                    grilleRemplie = false;
                    break;
                }
            }
            rangee++;
        }while(grilleRemplie && rangee < 3);
        return grilleRemplie;
    }

    public static boolean partieGagnante(char[][] entree, char choix){
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
