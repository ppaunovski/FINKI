package Kolokviumski1.LogProcessor;

import java.util.ArrayList;

public interface LogProcessor<T extends ILog> {
        ArrayList<T> processLogs(ArrayList<T> logs);
}