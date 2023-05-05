package Kolokviumski2.Avtomobili;

import java.util.*;
import java.util.stream.Collectors;

class Car{
    String manufacturer;
    String model;
    Integer price;
    Float power;

    public Car(String manufacturer, String model, Integer price, Float power) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.power = power;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public Integer getPrice() {
        return price;
    }

    public Float getPower() {
        return power;
    }

    @Override
    public String toString() {
        return manufacturer + " " + model + " (" + power.intValue() + "KW) " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(manufacturer, car.manufacturer) && Objects.equals(model, car.model) && Objects.equals(price, car.price) && Objects.equals(power, car.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, price, power);
    }
}

class CarCollection{
    Map<String, List<Car>> carsByManufacturer;
    List<Car> allCars;

    public CarCollection() {
        carsByManufacturer = new HashMap<>();
        allCars = new ArrayList<>();
    }

    public void addCar(Car car){
        carsByManufacturer.putIfAbsent(car.getManufacturer().toLowerCase(), new ArrayList<>());
        carsByManufacturer.get(car.getManufacturer().toLowerCase()).add(car);
        allCars.add(car);
    }
    public List<Car> filterByManufacturer(String manufacturer){
        return carsByManufacturer.get(manufacturer.toLowerCase()).stream()
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }
    public void sortByPrice(boolean ascending){
        Comparator<Car> comparator = Comparator.comparing(Car::getPrice).thenComparing(Car::getPower);
        if(!ascending)
            comparator = comparator.reversed();
        allCars = allCars.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Car> getList(){
        return allCars;
    }
}

public class CarTest {
    public static void main(String[] args) {
        CarCollection carCollection = new CarCollection();
        String manufacturer = fillCollection(carCollection);
        carCollection.sortByPrice(true);
        System.out.println("=== Sorted By Price ASC ===");
        print(carCollection.getList());
        carCollection.sortByPrice(false);
        System.out.println("=== Sorted By Price DESC ===");
        print(carCollection.getList());
        System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturer);
        List<Car> result = carCollection.filterByManufacturer(manufacturer);
        print(result);
    }

    static void print(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    static String fillCollection(CarCollection cc) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if(parts.length < 4) return parts[0];
            Car car = new Car(parts[0], parts[1], Integer.parseInt(parts[2]),
                    Float.parseFloat(parts[3]));
            cc.addCar(car);
        }
        scanner.close();
        return "";
    }
}

