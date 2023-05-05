//package Kolokviumski2.PayRoll;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.util.*;
//import java.util.stream.Collectors;
//
//class PayrollSystem{
//    Map<String,Double> hourlyRateByLevel;
//    Map<String,Double> ticketRateByLevel;
//    List<Employee> employees;
//
//    PayrollSystem(Map<String,Double> hourlyRateByLevel, Map<String,Double> ticketRateByLevel){
//        this.hourlyRateByLevel = hourlyRateByLevel;
//        this.ticketRateByLevel = ticketRateByLevel;
//        employees = new ArrayList<>();
//    }
//
//    void readEmployeesData (InputStream is){
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//        employees = br.lines()
//                .map(line -> Employee.createEmployee(line))
//                .collect(Collectors.toList());
//    }
//    Map<String, Collection<Employee>> printEmployeesByLevels (OutputStream os, Set<String> levels){
//        Map<String, Set<Employee>> employeesByLevel = new HashMap<>();
//
//        for (Employee employee : employees) {
//            Comparator<Employee> comparator = Comparator.comparing(employee1 -> employee1.getCoefficientForSalary() * employee1.getType() == 0 ? hourlyRateByLevel.get(employee1.getLevel()) : ticketRateByLevel.get(employee1.getLevel()));
//            employeesByLevel.putIfAbsent(employee.getLevel(), new TreeSet<>(comparator));
//            employeesByLevel.get(employee.getLevel()).add(employee);
//        }
//    }
//}
//
//abstract class Employee{
//    String ID;
//    String level;
//
//    public Employee(String ID, String level) {
//        this.ID = ID;
//        this.level = level;
//    }
//
//     public String getID() {
//         return ID;
//     }
//
//     abstract Double getCoefficientForSalary();
//     abstract Double getType();
//
//     public String getLevel() {
//         return level;
//     }
//
//     static Employee createEmployee(String line) {
//        String []parts = line.split(";");
//        if(parts[0].equals("H")){
//            return HourlyEmployee.createHourlyEmployee(line);
//        }
//        return FreelanceEmployee.createFreelanceEmployee(line);
//    }
//}
//
//class HourlyEmployee extends Employee {
//    int hours;
//    public HourlyEmployee(String ID, String level, int hours) {
//        super(ID, level);
//        this.hours = hours;
//    }
//
//    public static Employee createHourlyEmployee(String line) {
//        String []parts = line.split(";");
//        return new HourlyEmployee(parts[1], parts[2], Integer.parseInt(parts[3]));
//    }
//
//    public int getHours() {
//        return hours;
//    }
//    private double bonus(){
//        return Math.max(hours - 40, 0);
//    }
//    @Override
//    Double getCoefficientForSalary() {
//        return 1.5*bonus() + Math.min(40, hours);
//    }
//
//    @Override
//    Double getType() {
//        return 0.0;
//    }
//}
//
//class FreelanceEmployee extends Employee{
//    List<Integer> ticketPoints;
//    public FreelanceEmployee(String ID, String level, List<Integer> ticketPoints) {
//        super(ID, level);
//        this.ticketPoints = ticketPoints;
//    }
//
//    public List<Integer> getTicketPoints() {
//        return ticketPoints;
//    }
//
//    public static Employee createFreelanceEmployee(String line) {
//        String []parts = line.split(";");
//        List<Integer> list = Arrays.stream(parts).toList().subList(2, parts.length)
//                .stream()
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());
//        return new FreelanceEmployee(parts[0], parts[1], list);
//    }
//
//    @Override
//    Double getCoefficientForSalary() {
//        return ticketPoints.stream().mapToDouble(i -> i).sum();
//    }
//
//    @Override
//    Double getType() {
//        return 1.0;
//    }
//}
//
//
//public class PayrollSystemTest {
//}
