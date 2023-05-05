package Kolokviumski2.Faculty;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OperationNotAllowedException extends Exception{
    public OperationNotAllowedException(String message) {
        super(message);
    }
}

class Course{
    List<Integer> grades;
    int term;
    String courseName;
    List<Student> studentsOnCourse;

    public Course(int term, String courseName) {
        this.grades = new ArrayList<>();
        this.term = term;
        this.courseName = courseName;
        studentsOnCourse = new ArrayList<>();
    }

    public Double getGrade() {
        return grades.stream()
                .mapToDouble(grade -> grade)
                .average()
                .orElse(5.0);
    }

    public int getTerm() {
        return term;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return term == course.term && Objects.equals(grades, course.grades) && Objects.equals(courseName, course.courseName) && Objects.equals(studentsOnCourse, course.studentsOnCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grades, term, courseName, studentsOnCourse);
    }

    public int students(){
        return studentsOnCourse.size();
    }

    public void addStudent(Student student) {
        studentsOnCourse.add(student);
    }

    public void removeStudent(Student student) {
        studentsOnCourse.remove(student);
    }
}

class Student {
    String id;
    int yearsOfStudy;
    Map<Integer, Map<String, Course>> coursesByTerm;
    int passedCourses;
    Map<String, Integer> gradeByCourse;

    public Student(String id, int yearsOfStudy) {
        this.id = id;
        this.yearsOfStudy = yearsOfStudy;
        coursesByTerm = new HashMap<>();
        passedCourses = 0;
        gradeByCourse = new HashMap<>();
    }

    boolean addGrade(Course course, int grade) throws OperationNotAllowedException {
        //Course course = new Course(grade, term, courseName);
        coursesByTerm.putIfAbsent(course.term, new HashMap<>());
        if (coursesByTerm.get(course.term).size() == 3) {
            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d", id, course.term));
        }

        if (yearsOfStudy == 3 && course.term > 6) {
            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s", course.term, id));
        }

        gradeByCourse.putIfAbsent(course.courseName, grade);
        course.grades.add(grade);
        if (coursesByTerm.get(course.term).putIfAbsent(course.courseName, course) == null){
            passedCourses++;
            course.addStudent(this);
        }


        return hasGraduated();
    }

    public int getPassedCourses() {
        return passedCourses;
    }

    private boolean hasGraduated() {
        if (yearsOfStudy == 3 && passedCourses == 18) {
            removeStudentFromCourses();
            return true;
        }
        if (yearsOfStudy == 4) if (passedCourses == 24) {
            removeStudentFromCourses();
            return true;
        }
        return false;
    }

    private void removeStudentFromCourses() {
        coursesByTerm.values().stream()
                .map(stringCourseMap -> stringCourseMap.values())
                .reduce(new ArrayList<>(), (list1, list2) -> Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList()))
                .stream()
                .forEach(course -> course.removeStudent(this));
    }

    public String getId() {
        return id;
    }

    public int getYearsOfStudy() {
        return yearsOfStudy;
    }

    public String createLog() {
        return String.format("Student with ID %s graduated with average grade %.2f in %d years.", id, averageGrade(), yearsOfStudy);
    }

    public Double averageGrade() {
        return gradeByCourse.values().stream()
                .mapToDouble(grade -> grade)
                .average().orElse(5.0);

    }

    public String detailedReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student: ").append(id).append("\n");
        coursesByTerm.entrySet().stream()
                .forEach(integerMapEntry -> {
                    sb.append("Term ").append(integerMapEntry.getKey()).append("\n");
                    sb.append("Courses: ").append(integerMapEntry.getValue().size()).append("\n");
                    sb.append("Average grade for term: ").append(String.format("%.2f", integerMapEntry.getValue().values().stream()
                            .mapToDouble(course -> gradeByCourse.get(course))
                            .average().orElse(0))).append("\n");

                });
        sb.append("Average grade: ").append(String.format("%.2f", averageGrade())).append("\n");
        sb.append("Courses attended: ");
        coursesByTerm.values().stream()
                .map(stringCourseMap -> stringCourseMap.values())
                .reduce(new ArrayList<>(), (list1, list2) -> Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList()))
                .stream()
                .sorted(Comparator.comparing(Course::getCourseName))
                .forEach(course -> sb.append(course.courseName).append(","));
        return sb.toString().substring(0, sb.toString().length()-1);
    }


    public String shortReport() {
        return String.format("Student: %s Courses passed: %d Average grade: %.2f", id, passedCourses, averageGrade());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return yearsOfStudy == student.yearsOfStudy && passedCourses == student.passedCourses && Objects.equals(id, student.id) && Objects.equals(coursesByTerm, student.coursesByTerm) && Objects.equals(gradeByCourse, student.gradeByCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, yearsOfStudy, coursesByTerm, passedCourses, gradeByCourse);
    }

    public double gradeForCourse(Course key) {
        return gradeByCourse.get(key.courseName);
    }
}

class Faculty{
    List<Student> students;
    Map<String, Student> studentById;
    List<String> logs;
    Map<Course, List<Student>> studentsPerCourse;
    Map<String, Course> courseByName;
    public Faculty() {
        students = new ArrayList<>();
        studentById = new HashMap<>();
        logs = new ArrayList<>();
        studentsPerCourse = new HashMap<>();
        courseByName = new HashMap<>();
    }
    void addStudent(String id, int yearsOfStudies){
        Student student = new Student(id, yearsOfStudies);
        students.add(student);
        studentById.put(id, student);
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        Course course = new Course(term, courseName);
        courseByName.putIfAbsent(courseName, course);
        studentsPerCourse.putIfAbsent(courseByName.get(courseName), new ArrayList<>());
        studentsPerCourse.get(courseByName.get(courseName)).add(studentById.get(studentId));
        if(studentById.get(studentId).addGrade(courseByName.get(courseName), grade)){
            logs.add(studentById.get(studentId).createLog());
            studentById.remove(studentId);
        }
    }

    String getFacultyLogs (){
        StringBuilder sb = new StringBuilder();
        logs.stream()
                .forEach(log -> sb.append(log).append("\n"));
        return sb.toString();
    }

    String getDetailedReportForStudent (String id){
        return studentById.get(id).detailedReport();
    }

    void printFirstNStudents (int n){
        studentById.values().stream()
                .sorted(Comparator.comparing(Student::getPassedCourses).thenComparing(Student::averageGrade).thenComparing(Student::getId).reversed())
                .limit(n)
                .forEach(student -> System.out.println(student.shortReport()));
    }

    void printCourses (){
        studentsPerCourse.entrySet().stream()
                .sorted(Comparator.comparing(courseListEntry -> courseListEntry.getKey().students()))
                .forEach(courseListEntry -> System.out.println(String.format("%s %d %.2f", courseListEntry.getKey().courseName, courseListEntry.getValue().size(), courseListEntry.getKey().getGrade())));
    }
}


public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase==10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase==11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i=11;i<15;i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}
