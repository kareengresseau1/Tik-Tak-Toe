import java.util.Scanner;
import java.util.Random;

public class main {

    private static void initialisation(char[][] entrees, char choixOrdinateur, char choixUtilisateur)
            throws InstantiationException {
        Jeu.initierTableau(entrees);
        do{
            System.out.println("Choisis un symbole (X ou O) ");
            Scanner clavier = new Scanner(System.in);
            choixOrdinateur = clavier.next().charAt(0);
            if(choixOrdinateur != 'X' && choixOrdinateur != 'O'){
                System.out.println("Erreur: le symbole doit etre un X (x majuscule) ou un O (o majuscule)");
            }
        }while(choixOrdinateur != 'X' && choixOrdinateur != 'O');
        choixOrdinateur = Jeu.determinerPionOrdinateur(choixUtilisateur);
    }

    public static int choisirnombre(){
        int nombre;
        do{
            System.out.println("Choisis un nombre entre 0 et 1 (inclusivement)");
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

    public static void main(String[] args) throws InstantiationException {
        boolean utilisateurCommence = false;
        int choixNombreUtilisateur;
        char choixUtilisateur = ' ';
        char choixOrdinateur = ' ';
        char[][] entrees =  new char[3][3];

        String grille =
                "------------\n" +
                        "| " + entrees[0][0] + " | " + entrees[0][1] + " | " + entrees[0][2] + " |\n" +
                        "-------------" +
                        "| " + entrees[1][0] + " | " + entrees[1][1] + " | " + entrees[1][2] + " |\n" +
                        "-------------\n" +
                        "| " + entrees[2][0] + " | " + entrees[2][1] + " | " + entrees[2][2] + " |\n" +
                        "-------------\n";

        initialisation(entrees, choixOrdinateur, choixUtilisateur);
        choixNombreUtilisateur = choisirnombre();
        utilisateurCommence = commencerJeu(choixNombreUtilisateur);
        if(utilisateurCommence){
            System.out.println("Tu vas jouer en premier !");
        }else {
            System.out.println("L'ordinateur va jouer en premier !");
        }

    }


}
