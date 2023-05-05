package VEZBI.DynamicPrograming;

import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0){
            return 0;
        }
        if(triangle.size() == 1){
            return triangle.get(0).get(0);
        }

        int [][]matrix = new int[triangle.size()+1][triangle.get(triangle.size()-1).size()+1];
        // matrix[1][0] = triangle.get(0).get(0);
        // matrix[2][0] = matrix[1][0] + triangle.get(1).get(0);
        // matrix[2][1] = matrix[1][0] + triangle.get(1).get(1);
        int j=0;
        int i=2;
        for(i=0; i<triangle.size()+1; i++){
            for(j=0; j<triangle.size()+1; j++){
                if(i < triangle.size() && j < triangle.get(i).size()){
                    matrix[i][j] = triangle.get(i).get(j);
                }
                matrix[i][j] = 10001;
            }
        }
        matrix[1][0] = triangle.get(0).get(0);
        matrix[2][0] = matrix[1][0] + matrix[2][0];
        matrix[2][1] = matrix[1][0] + matrix[2][1];
        for(i=3; i<triangle.size(); i++){
            for(j=0; j<triangle.get(i).size(); j++){
                matrix[i][j] = Math.min(matrix[i-1][j] + matrix[i][j], matrix[i-1][j+1] + matrix[i-1][j+1]);
            }
        }
        int min = Integer.MAX_VALUE;
        for(int k=0; k<j; k++){
            if(matrix[i-1][k] < min){
                min = matrix[i-1][k];
            }
        }
        for(i=0; i<triangle.size(); i++){
            for(j=0; j<triangle.size(); j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        return min;
    }
}

public class triangle {
}
