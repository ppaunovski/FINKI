package Kolokviumski1.F1_Trka;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class F1Race {
    List<Driver> drivers;
    public F1Race() {
        this.drivers = new ArrayList<Driver>();
    }
    void readResults(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

            this.drivers = bufferedReader.lines()
                    .map(Driver::new)
                    .collect(Collectors.toList());

        bufferedReader.close();
    }
    void printSorted(OutputStream outputStream) throws IOException {
        PrintWriter pw = new PrintWriter(outputStream);
        List<Driver> sortedDrivers = this.drivers.stream()
                .sorted(Driver::compareTo)
                        .collect(Collectors.toList());
        sortedDrivers.forEach(driver -> pw.println(String.format("%d. %s", sortedDrivers.lastIndexOf(driver)+1, driver)));


        pw.flush();
    }

    @Override
    public String toString() {
        return "F1Race{" +
                "drivers=" + drivers +
                '}';
    }
}
