//package lab2.DoubleMatrix;
//
//import java.io.ByteArrayInputStream;
//import java.text.DecimalFormat;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class DoubleMatrix {
//    private final double [][]matrix;
//    private final int m;
//    private final int n;
//
//    public DoubleMatrix(double[] a, int m, int n) throws InsufficientElementsException{
//        if(a.length < m*n) throw new InsufficientElementsException();
//        double tmp[][] = new double[m][n];
//        int startPoint = a.length - m*n;
//        for(int i = 0; i<m; i++){
//            for(int j=0; j<n; j++) {
//                tmp[i][j] = a[startPoint++];
//            }
//        }
//        this.matrix = tmp;
//        this.m = m;
//        this.n = n;
//    }
//
//    public String getDimensions(){
//        return "[" + m + "x" + n + "]";
//    }
//
//    public int rows(){
//        return m;
//    }
//    public int columns(){
//        return n;
//    }
//    public double maxElementAtRow(int row) throws InvalidRowNumberException{
//        if(row >= m) throw new InvalidRowNumberException();
//        double max = matrix[row][0];
//        for(int i=0; i<n; i++){
//            if(matrix[row][i] > max){
//                max = matrix[row][i];
//            }
//        }
//        return max;
//    }
//
//    public double maxElementAtColumn(int column) throws InvalidColumnNumberException{
//        if(column >= n) throw new InvalidColumnNumberException();
//        double max = matrix[0][column];
//        for(int i=0; i<n; i++){
//            if(matrix[i][column] > max){
//                max = matrix[i][column];
//            }
//        }
//        return max;
//    }
//
//    public double sum(){
//        double sum = 0.0;
//        for(int i=0; i<m; i++){
//            for(int j=0; j<n; j++){
//                sum += matrix[i][j];
//            }
//        }
//        return sum;
//    }
//
//    public double[] toSortedArray(){
//        double[] tmpA = new double[m*n];
//        int index = 0;
//        for(int i=0; i<m; i++){
//            for(int j=0; j<n; j++){
//                tmpA[index++] = matrix[i][j];
//            }
//        }
//        sortInDecreasing(tmpA);
//        return tmpA;
//    }
//
//    private void sortInDecreasing(double []arr){
//        for(int i=0; i<arr.length; i++){
//            for(int j=0; j<arr.length - i - 1; j++){
//                double tmp;
//                if(arr[j] > arr[j+1]){
//                    tmp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = tmp;
//                }
//            }
//        }
//        System.out.println();
//    }
//
//
//
//    @Override
//    public String toString() {
//        StringBuilder returnString = new StringBuilder();
//        for(int i=0; i<m; i++){
//            for(int j=0; j<n; j++){
//                returnString.append(String.format("%.2f", matrix[i][j]));
//                returnString.append("\t");
//            }
//            returnString.append("\n");
//        }
//        return returnString.toString();
//    }
//}
