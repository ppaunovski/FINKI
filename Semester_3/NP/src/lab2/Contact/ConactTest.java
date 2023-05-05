package lab2.Contact;

public class ConactTest {

    public static void main(String[] args) {
        Contact contact1 = new EmailContact("1999-12-21", "paunovskidavid@gmail.com");
        Contact contact2 = new PhoneContact("2002-10-16", "075/267-798");
        System.out.println(contact1.extractYear());
        System.out.println(contact1.extractMonth());
        System.out.println(contact1.extractDay());
        System.out.println(contact1.isNewerThan(contact2));
        System.out.println(contact1.getType());
        System.out.println(contact2.getType());

        Student student = new Student("pavel", "paun", "skopje", 20, 216049);
        student.addEmailContact("1999-12-21", "paunovskidavid@gmail.com");
        student.addPhoneContact("2002-10-16", "075/267-798");
        System.out.println(student);
        Student students[] = new Student[1];
        students[0] = student;
        Faculty faculty = new Faculty("FINKI", students);
        System.out.println(faculty);
    }
}
