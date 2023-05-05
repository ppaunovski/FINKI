package Kolokviumski1.Canvas1;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShapesApplication {
    List<Canvas> canvases;

    public ShapesApplication() {
        canvases = new ArrayList<>();
    }

    public int readCanvases(InputStream inputStream){
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

         canvases = br.lines()
                .map(Canvas::createCanvas)
                 .collect(Collectors.toList());
         return canvases.stream()
                 .mapToInt(Canvas::countSquares)
                 .sum();
    }

    public void printLargestCanvasTo (OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);
        Optional<Canvas> max = canvases.stream()
                        .max(Comparator.naturalOrder());
        pw.println(max.orElse(null));
        pw.flush();
    }
}
