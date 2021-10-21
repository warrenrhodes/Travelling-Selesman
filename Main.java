import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static double poidMinimal = Double.MAX_VALUE;
    double poidCourant = 0;
    static ArrayList<String> Ensemble = new ArrayList<String>();
    static double[][] city;
    static ArrayList<String> copieCity = new ArrayList<String>();
    static ArrayList<String> finalTraject = new ArrayList<String>();

    public static void genCity(int nbrVille) {
        city = City.RandomArrayCity(nbrVille);
        for (int i = 1; i <= city.length; i++)
            copieCity.add("V" + i);
        Ensemble.addAll(copieCity);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // define the number of City
        System.out.println("Veuillez Entrer le nombre de ville:");
        int nbrVille = sc.nextInt();

        // set list E <---{V1.....Vn}
        genCity(nbrVille);

        // The user enters the initial city in the form V1 or V2 ..... Vn
        System.out.println("Veuillez Entrer la ville initiale sous la forme V1 ou V2.....Vn:");
        sc.nextLine();
        String initialCity = sc.nextLine();
        sc.close();
        String element = initialCity;

        if (!Ensemble.contains(element)) {
            System.out.println("City enter is not in a list of city");
        } else {

            LocalTime time;
            ArrayList<String> ancetre = new ArrayList<String>();
            ArrayList<String> fils = new ArrayList<String>();

            fils.addAll(Ensemble);
            ancetre.add(element);
            fils.remove(element);

            time = LocalTime.now();

            multiTarget(ancetre, fils, 0);

            System.out.println(finalTraject.toString().replace("[", "").replace(",", " --> ").replace("]", ""));
            System.out.println("poids du chemin: " + poidMinimal);
            System.out.println("Execution Time:");
            System.out.println(LocalTime.now() + "  " + time + " " + ChronoUnit.MILLIS.between(time, LocalTime.now()));
        }
    }

    public static void multiTarget(ArrayList<String> ancetre, ArrayList<String> fils, double poidCourant) {
        String curenNode = ancetre.get(ancetre.size() - 1);
        if (poidCourant >= poidMinimal) {
            return;
        }

        if (ancetre.size() >= Ensemble.size()) {
            poidMinimal = poidCourant;
            finalTraject = ancetre;
            return;
        }
        for (String e : fils) {

            ArrayList<String> newAncetre = new ArrayList<String>();
            ArrayList<String> newFils = new ArrayList<String>();

            newAncetre.addAll(ancetre);
            newAncetre.add(e);

            newFils.addAll(fils);
            newFils.remove(e);

            double newWeight = poidCourant + city[copieCity.indexOf(curenNode)][copieCity.indexOf(e.toString())];

            // System.out.println("poids du chemin: "+ newAncetre +" "+ newWeight );

            multiTarget(newAncetre, newFils, newWeight);
        }

    }

}
