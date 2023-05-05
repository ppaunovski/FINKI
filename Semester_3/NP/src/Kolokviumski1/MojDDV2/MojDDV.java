package Kolokviumski1.MojDDV2;

import Kolokviumski1.MojDDV1.AmountNotAllowedException;
import Kolokviumski1.MojDDV1.Receipt;

import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class MojDDV {
    List<Receipt> receipts;

    public MojDDV() {
        receipts = new ArrayList<>();
    }

    public void readRecords (InputStream inputStream){
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        receipts = br.lines()
                .map(line -> {
                    try {
                        return Receipt.createReceipt(line);
                    } catch (AmountNotAllowedException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    void printTaxReturns (OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);
        receipts.forEach(pw::println);
        pw.flush();
    }

    public void printStatistics(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);
        DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();
        doubleSummaryStatistics = receipts.stream()
                        .collect(Collectors.summarizingDouble(receipt -> Double.parseDouble(receipt.taxReturn())));
        pw.println(String.format("min:\t%5.3f", doubleSummaryStatistics.getMin()));
        pw.println(String.format("max:\t%5.3f", doubleSummaryStatistics.getMax()));
        pw.println(String.format("sum:\t%5.3f", doubleSummaryStatistics.getSum()));
        pw.println(String.format("count:\t%-5d", doubleSummaryStatistics.getCount()));
        pw.println(String.format("avg:\t%5.3f", doubleSummaryStatistics.getAverage()));
        pw.flush();
    }
}
