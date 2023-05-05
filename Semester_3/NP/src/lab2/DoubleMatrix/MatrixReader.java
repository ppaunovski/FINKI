//package lab2.DoubleMatrix;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Scanner;
//
//public class MatrixReader {
//
//    public static DoubleMatrix read(InputStream input){
//        Scanner scanner = new Scanner(input);
//        int m, n;
//        m=scanner.nextInt();
//        n=scanner.nextInt();
////        try {
////
////            m = input.read();
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////        try {
////            n = input.read();
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
//        double []arr = new double[m*n];
//        for(int i=0; i<m; i++){
//            for(int j=0; j<n; j++){
////                try {
////                    arr[i] = input.read();
////                } catch (IOException e) {
////                    throw new RuntimeException(e);
////                }
//                arr[i] = scanner.nextDouble();
//            }
//        }
//        try {
//            return new DoubleMatrix(arr, m, n);
//        } catch (InsufficientElementsException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
//}
