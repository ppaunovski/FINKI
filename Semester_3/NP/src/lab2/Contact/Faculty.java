package lab2.Contact;

import java.util.Arrays;

public class Faculty {
    String name;
    Student []students;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public int countStudentsFromCity(String cityName){
        int count = 0;
        for (Student student : students) {
            if (student.getCity().equals(cityName)) {
                count++;
            }
        }
        return count;
    }

    public Student getStudent(long index){
        for (Student student : students) {
            if (student.getIndex() == index) {
                return student;
            }
        }
        return null;
    }

    public double getAverageNumberOfContacts(){
        double sum = 0.0;
        for(Student student : students){
            sum += student.getNumberOfContacts();
        }
        return sum / students.length;
    }

    public Student getStudentWithMostContacts(){
        Student max = students[0];
        for(Student student : students){
            if(student.getNumberOfContacts() > max.getNumberOfContacts() ||
                student.getNumberOfContacts() == max.getNumberOfContacts() && student.getIndex() > max.getIndex()){
                max = student;
            }
        }
        return max;
    }

    @Override
    public String toString() {
        return "{" +
                "\"fakultet\":" + "\"" + name + "\""  +
                ", \"studenti\":" + Arrays.toString(students) +
                '}';
    }
}
