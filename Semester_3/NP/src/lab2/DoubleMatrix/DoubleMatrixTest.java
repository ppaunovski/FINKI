//package lab2.DoubleMatrix;
//
//import java.io.ByteArrayInputStream;
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.DecimalFormat;
//import java.util.*;
//
//public class DoubleMatrixTest {
//    public static void main(String[] args) {
//        DoubleMatrix matrix;
//        Scanner input = new Scanner(System.in);
//
//        int m, n;
//        m = input.nextInt();
//        n = input.nextInt();
//
//        double a[] = new double[16];
//        for(int i=0; i<16; i++){
//            a[i] = input.nextDouble();
//        }
//
//        try {
//            matrix = new DoubleMatrix(a, m ,n);
//        } catch (InsufficientElementsException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("getDimension tester");
//        System.out.println(matrix.getDimensions());
//
//        System.out.println("rows tester");
//        System.out.println(matrix.rows());
//        System.out.println("columns tester");
//        System.out.println(matrix.columns());
//
////        System.out.println("maxElemetRow tester");
////        try {
////            System.out.println(matrix.maxElementAtRow(2));
////        } catch (InvalidRowNumberException e) {
////            throw new RuntimeException(e);
////        }
////        try {
////            System.out.println(matrix.maxElementAtRow(4));
////        } catch (InvalidRowNumberException e) {
////            throw new RuntimeException(e);
////        }
////        try {
////            System.out.println(matrix.maxElementAtRow(5));
////        } catch (InvalidRowNumberException e) {
////            throw new RuntimeException(e);
////        }
////
////        System.out.println("maxElementCol tester");
////        try {
////            System.out.println(matrix.maxElementAtColumn(2));
////        } catch (InvalidColumnNumberException e) {
////            throw new RuntimeException(e);
////        }
////        try {
////            System.out.println(matrix.maxElementAtColumn(4));
////        } catch (InvalidColumnNumberException e) {
////            throw new RuntimeException(e);
////        }
////        try {
////            System.out.println(matrix.maxElementAtColumn(5));
////        } catch (InvalidColumnNumberException e) {
////            throw new RuntimeException(e);
////        }
//
//        System.out.println("sum tester");
//        System.out.println(matrix.sum());
//
//        System.out.println("to sorted array tester");
//        double []sorted = matrix.toSortedArray();
//        for(int i=0; i<sorted.length; i++){
//            System.out.print(sorted[i]);
//        }
//
//        System.out.println("toString tets");
//        System.out.println(matrix.toString());
//
//    }
//}
//
//class DoubleMatrix {
//    private double [][]matrix;
//    private int m;
//    private int n;
//
//    public DoubleMatrix(double a[], int m, int n) throws InsufficientElementsException{
//        if(m*n > a.length){
//            throw new InsufficientElementsException();
//        }
//
//        if(a.length >= m * n){
//            int startPoint =  a.length - m*n;
//            double tmp[][] = new double[m][n];
//            for(int i=0; i<m; i++){
//                for(int j=0; j<n; j++){
//                    //matrix[i][j] = new double [a[startPoint++]];
//                    tmp[i][j] = a[startPoint++];
//                }
//            }
//            this.matrix = tmp;
//            this.m = m;
//            this.n = n;
//        }
//    }
//
//    public String getDimensions(){
//        return "[" + this.m + " x " + this.n + "]";
//    }
//
//    public int rows(){
//        return m;
//    }
//
//    public int columns(){
//        return n;
//    }
//
//    public double maxElementAtRow(int row) throws InvalidRowNumberException {
//        if(row < 0 || row > m){
//            throw new InvalidRowNumberException();
//        }
//        double max = this.matrix[row][0];
//        for(int i=1; i<this.n; i++){
//            if(max < this.matrix[row][i])   max = this.matrix[row][i];
//        }
//        return max;
//    }
//
//    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
//        if(column < 0 || column > n){
//            throw new InvalidColumnNumberException ();
//        }
//        double max = this.matrix[column][0];
//        for(int i=1; i<this.m; i++){
//            if(max < this.matrix[i][column])   max = this.matrix[i][column];
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
//
//    public double[] toSortedArray(){
//        double []sortedArray = new double[m*n];
//        int index = 0;
//        for(int i=0; i<m; i++){
//            for(int j=0; j<n; j++){
//                sortedArray[index++] = matrix[i][j];
//            }
//        }
//
//        Arrays.sort(sortedArray);
//        double []returnArr = new double[m*n];
//        for(int i=0; i<sortedArray.length; i++){
//            returnArr[i] = sortedArray[sortedArray.length - i];
//        }
//
//        return returnArr;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder string = new StringBuilder();
//        for(int i=0; i<m; i++){
//            for(int j=0; j<n; j++){
//                string.append(String.format("%.2f\t", matrix[i][j]));
//            }
//            string.append("\n");
//        }
//        return string.toString();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DoubleMatrix that = (DoubleMatrix) o;
//        return m == that.m && n == that.n && Arrays.equals(matrix, that.matrix);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(m, n);
//        result = 31 * result + Arrays.hashCode(matrix);
//        return result;
//    }
//}
//
//class MatrixReader{
//    public static DoubleMatrix read(InputStream input){
//        int m, n;
//        double a[];
//        try {
//            m = input.read();
//            n = input.read();
//            a = new double[m*n];
//            for(int i=0; i<m*n; i++){
//                a[i] = input.read();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        DoubleMatrix matrix;
//        try {
//            matrix = new DoubleMatrix(a, m, n);
//        } catch (InsufficientElementsException e) {
//            throw new RuntimeException(e);
//        }
//        return matrix;
//    }
//}
//
//class InsufficientElementsException extends Exception{
//    public InsufficientElementsException() {
//        super("Insufficient number of elements");
//    }
//}
//
//class InvalidRowNumberException extends Exception{
//    public InvalidRowNumberException() {
//        super("Invalid row number");
//    }
//}
//
//class InvalidColumnNumberException extends Exception{
//    public InvalidColumnNumberException() {
//        super("Invalid column number");
//    }
//}
//
