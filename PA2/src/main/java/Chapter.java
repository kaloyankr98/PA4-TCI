import java.util.Objects;

public class Chapter implements Comparable{
    
    private String name;
    private String number;
    private Object chapter;

    public Chapter(String name, String number){
        this.setName(name);
        this.setNumber(number);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if(number.contains(".")){
            if(number.indexOf(".") == 1 && number.length() == 3){
                this.number = number;
            }
        }
        else if(!number.contains(".") && number.length() == 1 && !number.equals("0")) {
            this.number = number;
        }
        else{ this.number = "0"; };
    }


    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        Chapter chapter = (Chapter) o;
        double oDouble = Double.parseDouble(chapter.number);
        Double currentDouble = Double.parseDouble(this.number);
        if(currentDouble < oDouble) {
            return -1;
        } else {return 1;}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        return getName().equals(chapter.getName()) &&
                getNumber().equals(chapter.getNumber());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNumber());
    }
}
