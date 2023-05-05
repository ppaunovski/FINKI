package lab3.zadaca2;

import java.util.Scanner;

public class MinDistance {

    public static float minimalDistance(float points[][]) {
        //Vasiot kod ovde
        double minDistance = Math.sqrt(Math.pow(points[0][0] - points[1][0], 2) + Math.pow(points[0][1] - points[1][1], 2));

        for(int i=0; i<points.length-1; i++){
            if(minDistance > Math.sqrt(Math.pow(points[i][0] - points[i+1][0], 2) + Math.pow(points[i][1] - points[i+1][1], 2)))
                minDistance = Math.sqrt(Math.pow(points[i][0] - points[i+1][0], 2) + Math.pow(points[i][1] - points[i+1][1], 2));
        }
        return (float)minDistance;
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        float points[][] = new float[N][2];

        for(int i=0;i<N;i++) {
            points[i][0] = input.nextFloat();
            points[i][1] = input.nextFloat();
        }

        System.out.printf("%.2f\n", minimalDistance(points));
    }
}