package lab2.Contact;

public class EmailContact extends Contact{
    private String email;

    public EmailContact(String date, String email) {
        super(date);
        this.email = email;
    }

    public EmailContact(EmailContact contact){
        super(contact.getDate());
        this.email = contact.email;
    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString() {
        return "\"" + email + "\"";
    }

    @Override
    public String getType() {
        return "Email";
    }
}
