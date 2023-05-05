package lab2.Contact;

public abstract class Contact {
    private String date;
    //YYYY-MM-DD

    public Contact(String date) {
        this.date = date;
    }

    public int extractYear(){
        String year = date.substring(0, 4);
        return Integer.parseInt(year);
    }

    public int extractMonth(){
        String month = date.substring(5, 7);
        return Integer.parseInt(month);
    }

    public int extractDay(){
        String day = date.substring(8, 10);
        return Integer.parseInt(day);
    }

    public boolean isNewerThan(Contact c){
        if(this.extractYear() > c.extractYear()){
            return true;
        }
        if(this.extractYear() == c.extractYear() && this.extractMonth() > c.extractMonth()){
            return true;
        }
        return this.extractYear() == c.extractYear() && this.extractMonth() == c.extractMonth() && this.extractDay() > c.extractDay();
    }

    public String getDate() {
        return date;
    }

    @Override
    public abstract String toString();

    public abstract String getType();

}


