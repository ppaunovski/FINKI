package Kolokviumski1.WeatherStation;

import java.util.Date;

public class Measurement implements Comparable<Measurement> {
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double visibility;
    private Date date;

    public Measurement(double temperature, double humidity, double windSpeed, double visibility, Date date) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.date = date;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getVisibility() {
        return visibility;
    }

    public Date getDate() {
        return date;
    }


    @Override
    public int compareTo(Measurement o) {
        return 0;
    }
}
