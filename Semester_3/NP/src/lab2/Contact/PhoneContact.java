package lab2.Contact;

public class PhoneContact extends Contact{
    String phone;
    //07X/YYY-ZZZ
    Operator operator;

    public PhoneContact(String date, String phone) {
        super(date);
        this.phone = phone;
        setOperator();
    }

    public PhoneContact(PhoneContact contact) {
        super(contact.getDate());
        this.phone = contact.getPhone();
        this.operator = contact.getOperator();
    }

    private void setOperator(){
        switch (phone.substring(0, 3)) {
            case "070", "071", "072" -> this.operator = Operator.TMOBILE;
            case "075", "076" -> this.operator = Operator.ONE;
            case "077", "078" -> this.operator = Operator.VIP;
        }
    }

    public Operator getOperator() {
        return operator;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "\"" + phone + "\"";
    }

    @Override
    public String getType() {
        return "Phone";
    }
}
