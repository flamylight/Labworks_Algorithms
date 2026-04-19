public class Student {
    public String Surname;
    public int Course;
    public String Faculty;
    public int MissedClasses;

    public Student(String surname, int course, String faculty, int missedClasses){
        Surname = surname;
        Course = course;
        Faculty = faculty;
        MissedClasses = missedClasses;
    }

    @Override
    public String toString(){
        return String.format(
                "Surname: %-15s | Course: %d | Faculty: %-20s | Missed: %d",
                Surname, Course, Faculty, MissedClasses
        );
    }
}
