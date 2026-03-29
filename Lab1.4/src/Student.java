public class Student {
    public String surname;
    public int dayOfBirth;
    public int monthOfBirth;
    public int yearOfBirth;

    public Student(String surname, int dayOfBirth, int monthOfBirth, int yearOfBirth){
        this.surname = surname;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString(){
        return "Surname: %s, day: %d, month: %d, year: %d".formatted(surname, dayOfBirth, monthOfBirth, yearOfBirth);
    }
}
