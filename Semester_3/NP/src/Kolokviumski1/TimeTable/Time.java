package Kolokviumski1.TimeTable;

public class Time implements Comparable<Time>{
    private Integer hour;
    private Integer minute;

    public Time(Integer hour, Integer minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public static Time createTime(String time) throws InvalidTimeException, UnsupportedFormatException{
        int ind1 = time.indexOf(':');
        int ind2 = time.indexOf('.');

        if(ind1 != -1){
            String []parts = time.split(":");
            return getTime(time, parts);
        }
        else if(ind2 != -1){
            String []parts = time.split("\\.");
            return getTime(time, parts);
        }

        throw new UnsupportedFormatException(time);
    }

    private static Time getTime(String time, String[] parts) throws InvalidTimeException {
        if((Integer.parseInt(parts[0]) > 23 || Integer.parseInt(parts[0]) < 0) &&
                (Integer.parseInt(parts[1]) > 59 || Integer.parseInt(parts[1]) < 0))
            throw new InvalidTimeException(time);
        return new Time(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    @Override
    public int compareTo(Time o) {
        if(Integer.compare(this.getHour(), o.getHour()) == 0){
            return Integer.compare(this.getMinute(), o.getMinute());
        }
        return Integer.compare(this.getHour(), o.getHour());
    }

    public String print(TimeFormat format){
        if(format == TimeFormat.FORMAT_AMPM){
            switch (this.hour){
                case 0: return String.format("12:%02d AM", this.minute);
                case 12: return String.format("12:%02d PM", this.minute);
                case 13: return String.format(" 1:%02d PM", this.minute);
                case 14: return String.format(" 2:%02d PM", this.minute);
                case 15: return String.format(" 3:%02d PM", this.minute);
                case 16: return String.format(" 4:%02d PM", this.minute);
                case 17: return String.format(" 5:%02d PM", this.minute);
                case 18: return String.format(" 6:%02d PM", this.minute);
                case 19: return String.format(" 7:%02d PM", this.minute);
                case 20: return String.format(" 8:%02d PM", this.minute);
                case 21: return String.format(" 9:%02d PM", this.minute);
                case 22: return String.format("10:%02d PM", this.minute);
                case 23: return String.format("11:%02d PM", this.minute);
                default: return String.format("%2d:%02d AM", this.hour, this.minute);
            }
        }
        else if(format == TimeFormat.FORMAT_24){
            return String.format("%2d:%02d", this.hour, this.minute);
        }
        return "";
    }
}
