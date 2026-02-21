import java.util.Random;

public class Circle {
    double x;
    double y;
    double radius;

    public Circle(double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Circle(){
        Random rand = new Random();

        this.x = rand.nextDouble() * 10;
        this.y = rand.nextDouble() * 10;

        while (radius == 0){
            this.radius = rand.nextDouble() * 10;
        }
    }

    public double area(){
        return Math.PI * radius * radius;
    }

    public double perimeter(){
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString(){

        if (x == -1 & y == -1){
            return "DELETE";
        }
        return String.format(
                "Circle(x=%.2f, y=%.2f, r=%.2f, S=%.2f, P=%.2f)",
                x, y, radius, area(), perimeter()
        );
    }
}
