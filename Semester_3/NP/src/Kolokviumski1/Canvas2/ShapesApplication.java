package Kolokviumski1.Canvas2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShapesApplication {
    List<Canvas> canvases;
    double maxArea;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
        canvases = new ArrayList<>();
    }

    void readCanvases (InputStream inputStream){
        Scanner sc = new Scanner(inputStream);
        String line;
        while(sc.hasNext()){
            line = sc.nextLine();
            if(line.equals("end")){
                break;
            }
            try {
                canvases.add(Canvas.createCanvas(line, maxArea));
            } catch (IrregularCanvasException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void printCanvases (OutputStream os){
        PrintWriter pw = new PrintWriter(os);
        canvases.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(pw::println);
        pw.flush();
    }
}
