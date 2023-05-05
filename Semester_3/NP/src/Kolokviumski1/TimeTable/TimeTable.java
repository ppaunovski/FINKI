package Kolokviumski1.TimeTable;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TimeTable {
    List<Time> times;

    public TimeTable() {
        this.times = new ArrayList<>();
    }

    void readTimes(InputStream inputStream) throws UnsupportedFormatException, InvalidTimeException {
        Scanner sc = new Scanner(inputStream);
        String time;
        while (sc.hasNext()){
            time = sc.next();
            times.add(Time.createTime(time));
        }
    }

    void writeTimes(OutputStream outputStream, TimeFormat format){
        PrintWriter pw = new PrintWriter(outputStream);

        times.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(time -> pw.println(time.print(format)));

        pw.flush();
    }
}
