import java.util.Scanner;
import java.util.Random;

public class TikTakToe {

    private static char[] initialisation(char[][] entrees)
            throws InstantiationException {
        char[] lesChoix = new char[2];
        do{
            System.out.println("Choisis un symbole (X ou O) ");
            Scanner clavier = new Scanner(System.in);
            lesChoix[0] = clavier.next().charAt(0);
            if(lesChoix[0] != 'X' && lesChoix[0] != 'O'){
                System.out.println("Erreur: le symbole doit etre un X (x majuscule) ou un O (o majuscule)");
            }
        }while(lesChoix[0] != 'X' && lesChoix[0] != 'O');
        lesChoix[1] = Jeu.determinerPionOrdinateur(lesChoix[0]);
        return lesChoix;
    }

    public static int choisirnombre(){
        int nombre;
        do{
            System.out.println("Choisis le nombre 0 ou le nombre 1");
            Scanner clavier = new Scanner(System.in);
            nombre = clavier.nextInt();
            if(nombre != 0 && nombre != 1){
                System.out.println("Le nombre doit etre 0 ou 1");
            }
        }while(nombre != 0 && nombre != 1);
        return nombre;
    }

    public static boolean commencerJeu(int choixUtilisateur){
        boolean utilisateurCommence = false;
        Random hasard = new Random();
        int maximum = 2;
        int chiffre = hasard.nextInt(maximum);
        if(chiffre == choixUtilisateur){
            utilisateurCommence = true;
        }
        return utilisateurCommence;
    }

    public static char[][] utilisateurJouer(char choixUtilisateur,char choixOrdinateur, char[][] entrees, String grille){
        int colonne = 0;
        int rangee = 0;
        boolean celluleOccupee = false;
        Jeu.afficher(entrees);
        do{
            System.out.println("Choisir la rangee (1, 2 ou 3)");
            Scanner choixRangee = new Scanner(System.in);
            rangee = choixRangee.nextInt();
            if(rangee != 1 && rangee != 2 && rangee != 3){
                System.out.println("Le choix de rangee est invalide");
            }
        }while(rangee != 1 && rangee != 2 && rangee != 3);
        do{
            System.out.println("Choisir la colonne(1, 2 ou 3)");
            Scanner choixColonne = new Scanner(System.in);
            colonne = choixColonne.nextInt();
            if(colonne != 1 && colonne != 2 && colonne != 3){
                System.out.println("Le choix de colonne est invalide");
            }
        }while(colonne != 1 && colonne != 2 && colonne !=3);
        celluleOccupee = Jeu.estOccupeeCellule(entrees, rangee -1, colonne -1, choixUtilisateur, choixOrdinateur);
        if(celluleOccupee){
            System.out.println("La cellule est deja occupee");
            utilisateurJouer(choixUtilisateur,choixOrdinateur, entrees, grille);
        } else {
            entrees = Jeu.mettreAJourGrille(rangee -1, colonne -1, choixUtilisateur, entrees, grille);
        }
        return entrees;
    }

    public static char[][] ordinateurJouer(char choixOrdinateur,char choixUtilisateur, char[][] entrees, String grille){
        System.out.println("Au tour de l'ordinateur !");
        int colonne = 0;
        int rangee= 0;
        boolean celluleOccupee = false;
        Random choixColonne = new Random();
        Random choixRangee = new Random();
        int maximum = 2;
        rangee = choixRangee.nextInt(maximum);
        colonne = choixColonne.nextInt(maximum);
        celluleOccupee = Jeu.estOccupeeCellule(entrees, rangee, colonne, choixOrdinateur, choixUtilisateur);
        if(celluleOccupee){
            entrees = Jeu.trouverCelluleVide(entrees, choixOrdinateur);
        }else{
            entrees = Jeu.mettreAJourGrille(rangee, colonne, choixOrdinateur, entrees, grille);
        }
        return entrees;
    };

    public static void finPartie(boolean gagneOrdinateur, boolean gagneUtilisateur, boolean matchNul, char[][] entrees){
        Jeu.afficher(entrees);
        if(gagneOrdinateur){
            System.out.println("L'ordinateur a gagne !");
        }else if(gagneUtilisateur){
            System.out.println("Tu as gagne !");
        }else if(matchNul){
            System.out.println("Match nul !");
        }
    }

    public static void utilisateurCommence(char choixUtilisateur, char choixOrdinateur,
                                           char[][] entrees, String grille){
        boolean gagneUtilisateur;
        boolean gagneOrdinateur = false;
        boolean matchNul;
        System.out.println("Tu vas jouer en premier !");
        do{
            entrees = utilisateurJouer(choixUtilisateur,choixOrdinateur, entrees, grille);
            gagneUtilisateur = Jeu.partieGagnante(entrees, choixUtilisateur);
            matchNul = Jeu.matchNul(entrees);
            if(!gagneUtilisateur && !matchNul){
                entrees = ordinateurJouer(choixOrdinateur,choixUtilisateur, entrees, grille);
                gagneOrdinateur = Jeu.partieGagnante(entrees, choixOrdinateur);
                matchNul = Jeu.matchNul(entrees);
            }
        }while((!gagneUtilisateur && !gagneOrdinateur) && !matchNul);
        finPartie(gagneOrdinateur, gagneUtilisateur, matchNul, entrees);
    }

    public static void ordinateurCommence(char choixUtilisateur, char choixOrdinateur, char[][] entrees, String grille){
        boolean gagneUtilisateur = false;
        boolean gagneOrdinateur;
        boolean matchNul;
        System.out.println("L'ordinateur va jouer en premier !");
        do{
            entrees = ordinateurJouer(choixOrdinateur, choixUtilisateur, entrees, grille);
            gagneOrdinateur = Jeu.partieGagnante(entrees, choixOrdinateur);
            matchNul = Jeu.matchNul(entrees);
            if(!gagneOrdinateur && !matchNul){
                entrees = utilisateurJouer(choixUtilisateur, choixOrdinateur, entrees, grille);
                gagneUtilisateur = Jeu.partieGagnante(entrees, choixUtilisateur);
                matchNul = Jeu.matchNul(entrees);
            }
        }while((!gagneOrdinateur && !gagneUtilisateur) && !matchNul);
        finPartie(gagneOrdinateur, gagneUtilisateur, matchNul, entrees);
    }

    public static void main(String[] args) throws InstantiationException {
        char recommencer;
        do{
            boolean utilisateurCommence;
            int choixNombreUtilisateur;
            char[] lesChoix;
            char choixUtilisateur = ' ';
            char choixOrdinateur = ' ';
            char[][] entrees;
            entrees = new char[][]{{'*', '*', '*'}, {'*', '*', '*'}, {'*', '*', '*'}};
            String grille =
                    "-------------\n" +
                            "| " + entrees[0][0] + " | " + entrees[0][1] + " | " + entrees[0][2] + " |\n" +
                            "-------------\n" +
                            "| " + entrees[1][0] + " | " + entrees[1][1] + " | " + entrees[1][2] + " |\n" +
                            "-------------\n" +
                            "| " + entrees[2][0] + " | " + entrees[2][1] + " | " + entrees[2][2] + " |\n" +
                            "-------------\n";

            lesChoix = initialisation(entrees);
            choixUtilisateur = lesChoix[0];
            choixOrdinateur = lesChoix[1];
            choixNombreUtilisateur = choisirnombre();
            utilisateurCommence = commencerJeu(choixNombreUtilisateur);
            if(utilisateurCommence){
                utilisateurCommence(choixUtilisateur, choixOrdinateur, entrees, grille);
            }else {
                ordinateurCommence(choixUtilisateur, choixOrdinateur, entrees, grille);
            }
            do{
                System.out.println("Veux-tu recommencer ? (O pour oui N pour non)");
                Scanner choixRecommencer = new Scanner(System.in);
                recommencer = choixRecommencer.next().charAt(0);
                if(recommencer != 'O' && recommencer != 'N'){
                    System.out.println("Les choix doivent etre O ou N");
                }
            }while(recommencer != 'O' && recommencer != 'N');
        }while(recommencer == 'O');
        System.out.println("Fin de la partie !");
    }


}
