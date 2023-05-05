package Kolokviumski2.WeatherApplication;

import java.util.*;

interface Subscriber{
    void notifySubs();
}

class WeatherDispatcher{
    float temperature;
    float humidity;
    float pressure;
    List<Subscriber> subscribers;

    public WeatherDispatcher() {
        subscribers = new ArrayList<>();
    }

    void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        subscribers.forEach(subscriber -> subscriber.notifySubs());
    }

    void register(Subscriber subscriber){
        if(!subscribers.contains(subscriber)){
            subscribers.add(subscriber);
        }
    }

    void remove(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

class CurrentConditionsDisplay implements Subscriber{
    WeatherDispatcher weatherDispatcher;


    public CurrentConditionsDisplay(WeatherDispatcher weatherDispatcher) {
        this.weatherDispatcher = weatherDispatcher;
        weatherDispatcher.register(this);

    }

    @Override
    public void notifySubs() {

        System.out.println(String.format("Temperature: %.1fF", weatherDispatcher.getTemperature()));
        System.out.println(String.format("Humidity: %.1f%%", weatherDispatcher.getHumidity()));
    }
}

class ForecastDisplay implements Subscriber{
    WeatherDispatcher weatherDispatcher;
    float lastPressure;

    public ForecastDisplay(WeatherDispatcher weatherDispatcher) {
        this.weatherDispatcher = weatherDispatcher;
        weatherDispatcher.register(this);
        lastPressure = 0;
    }

    @Override
    public void notifySubs() {
        if(lastPressure < weatherDispatcher.getPressure()){
            System.out.println("Forecast: Improving");
        }
        if(lastPressure == weatherDispatcher.getPressure()){
            System.out.println("Forecast: Same");
        }
        if(lastPressure > weatherDispatcher.getPressure()){
            System.out.println("Forecast: Cooler");
        }
        lastPressure = weatherDispatcher.getPressure();
        System.out.println();
    }
}

public class WeatherApplication {

    public static void main(String[] args) {
        WeatherDispatcher weatherDispatcher = new WeatherDispatcher();

        CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherDispatcher);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDispatcher);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            weatherDispatcher.setMeasurements(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
            if(parts.length > 3) {
                int operation = Integer.parseInt(parts[3]);
                if(operation==1) {
                    weatherDispatcher.remove(forecastDisplay);
                }
                if(operation==2) {
                    weatherDispatcher.remove(currentConditions);
                }
                if(operation==3) {
                    weatherDispatcher.register(forecastDisplay);
                }
                if(operation==4) {
                    weatherDispatcher.register(currentConditions);
                }

            }
        }
    }
}