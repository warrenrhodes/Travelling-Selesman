
import java.util.Random;

//This class automatically Generate a  City
//For  city for exemple we goind to gereneted this matrice
/*
          A    B     C    D     E    F
     A |  0 | 41  | 72 | 131 | 166 | 184
     B | 41 | 0   | 105| 137 | 202 | 181
     C | 72 | 105 | 0  | 110 | 99  | 216
     D |131 | 137 | 110| 0   | 96  | 91
     E |166 | 202 | 99 | 96  | 0   | 184
     F |184 | 181 | 216| 91  | 184 | 0

*/


public class City {
    public static double[][] RandomArrayCity(int n) {
        double[][] matrixCity = new double[n][n];
        Random rand = new Random();
//        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                Integer r = rand.nextInt() % 100;
                if (i == j) {
                    matrixCity[i][j] = 0;
                } else {
                    if (i > j) {
                        matrixCity[i][j] = matrixCity[j][i];
                    } else {
                        matrixCity[i][j] = Math.abs(r);
                    }
                }
            }

        }

        return matrixCity;
    }
}