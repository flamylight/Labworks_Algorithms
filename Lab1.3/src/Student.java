public class Student {
    public String name;
    public String surname;
    public float height;
    public float weight;
    public int studentId;

    public Student(
            String name, String surname, float height, float weight, int studentId) throws Exception{
        this.name = name;
        this.surname = surname;
        this.height = height;
        this.weight = weight;

        if (Integer.toString(studentId).length() != 6) {
            throw new Exception("Студентський квиток має бути довжиною 6 символів!");
        }

        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + studentId;
    }
}
