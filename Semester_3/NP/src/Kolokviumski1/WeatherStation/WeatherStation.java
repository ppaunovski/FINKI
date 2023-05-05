//package Kolokviumski1.WeatherStation;
//
//import java.util.*;
//
//public class WeatherStation {
//    List<Measurement> measurements;
//    int days;
//
//    public WeatherStation(int days) {
//        this.days = days;
//        measurements = new ArrayList<>();
//    }
//
//    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date){
//        Optional<Measurement> latest = measurements.stream()
//                .max(Comparator.naturalOrder());
//        if(latest.isPresent()){
//            if(date.after(latest.get().getDate())){
//
//            }
//        }
//        if(date.after())
//    }
//
//}
