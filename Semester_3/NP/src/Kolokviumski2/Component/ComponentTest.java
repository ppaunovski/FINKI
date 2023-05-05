package Kolokviumski2.Component;

import java.util.*;

//interface Composite{
//    String print();
//    String getType();
//}

class Component implements Comparable<Component>{
    String color;
    Integer weight;
    TreeSet<Component> components;

    public Component(String color, int weight){
        this.color = color;
        this.weight =weight;
        this.components = new TreeSet<>();
    }

    void addComponent(Component component){
        this.components.add(component);
    }

    @Override
    public int compareTo(Component o) {
        return (int) this.weight == o.weight ? this.color.compareTo(o.color) : Integer.compare(this.weight, o.weight) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Objects.equals(color, component.color) && Objects.equals(weight, component.weight) && Objects.equals(components, component.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, weight, components);
    }

    @Override
    public String toString() {
        return print();
    }

    private String print(){
        return printRecursive(0, this);
    }

    private String printRecursive(int indent, Component component) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<indent; i++){
            sb.append("---");
        }
        sb.append(component.weight).append(":").append(component.color).append("\n");
        Iterator<Component> iterator = component.components.iterator();
        for(int i=0; i<component.components.size(); i++){
            sb.append(printRecursive(indent+1, iterator.next()));
        }

        return sb.toString();
    }

    public void changeColor(int weight, String color) {
        if(this.weight < weight){
            this.color = color;
        }

        components.stream()
                .forEach(component -> component.changeColor(weight, color));
    }

    //    @Override
//    public String print() {
//        return weight + ":" + color;
//    }
//
//    @Override
//    public String getType() {
//        return "component";
//    }
}

class InvalidPositionException extends Exception{
    public InvalidPositionException(int pos) {
        super(String.format("Invalid position %d, alredy taken!", pos));
    }
}
class Window{
    String name;
    TreeMap<Integer, Component> componentsByPosition;

    public Window(String name) {
        this.name = name;
        componentsByPosition = new TreeMap<>();
    }

    void addComponent(int position, Component component) throws InvalidPositionException {
        if(componentsByPosition.putIfAbsent(position, component) != null){
            throw new InvalidPositionException(position);
        }
    }

    void changeColor(int weight, String color){
        componentsByPosition.values().stream()
                .forEach(component -> component.changeColor(weight, color));
    }

    void swichComponents(int pos1, int pos2){
        Component tmp = componentsByPosition.get(pos1);
        componentsByPosition.put(pos1, componentsByPosition.get(pos2));
        componentsByPosition.put(pos2, tmp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WINDOW ").append(name).append("\n");
        componentsByPosition.entrySet().stream()
                .forEach(integerComponentEntry -> sb.append(integerComponentEntry.getKey()).append(":").append(integerComponentEntry.getValue()));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Window window = (Window) o;
        return Objects.equals(name, window.name) && Objects.equals(componentsByPosition, window.componentsByPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, componentsByPosition);
    }

    //    @Override
//    public String print() {
//        return null;
//    }
//
//    @Override
//    public String getType() {
//        return null;
//    }
}


public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if(what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.swichComponents(pos1, pos2);
        System.out.println(window);
    }
}

// вашиот код овде