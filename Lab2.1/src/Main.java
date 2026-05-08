void main(){
    IO.println("TASK 1");
    IO.println("–".repeat(5) + "Rectangle Method (Left)" + "–".repeat(5));
    IO.println(rectangleMethodL(0, 10, 0.2) + "\n");

    IO.println("–".repeat(5) + "Rectangle Method (Right)" + "–".repeat(5));
    IO.println(rectangleMethodR(0, 10, 0.2) + "\n");

    IO.println("–".repeat(5) + "Rectangle Method (Middle)" + "–".repeat(5));
    IO.println(rectangleMethodM(0, 10, 0.2) + "\n");

    IO.println("–".repeat(5) + "Trapezoid Method" + "–".repeat(5));
    IO.println(trapezoidMethod(0, 10, 0.2) + "\n");

    IO.println("–".repeat(5) + "Simpsons Method" + "–".repeat(5));
    IO.println(simpsonsMethod(0, 10, 0.2) + "\n");

    IO.println("TASK 2");
    IO.println("–".repeat(5) + "Half division" + "–".repeat(5));
    IO.println(halfDivision(3, 5, 0.001) + "\n");

    IO.println("–".repeat(5) + "Newton method" + "–".repeat(5));
    IO.println(methodNewton(3, 5, 0.001) + "\n");

    IO.println("–".repeat(5) + "Chord method" + "–".repeat(5));
    IO.println(methodChord(3, 5, 0.001) + "\n");

    IO.println("TASK 3");
    IO.println("–".repeat(5) + "Runge Kutta 3" + "–".repeat(5));
    rungeKutta3(1, 2, 0.1, 2);
}

public double f1(double x){
    return Math.sqrt(6 * x + 5);
}

public double f2(double x){
    return Math.pow((x - 2), 2) - x;
}

public double f3(double x, double y) {
    return (y * y - y) / x;
}

public double rectangleMethodL(double a, double b, double step){
    double sum = 0.0;
    double x = a;
    int n = (int)((b-a)/step);

    for (int i = 0; i < n; i++){
        sum += f1(x + i * step);
    }

    return sum * step;
}

public double rectangleMethodR(double a, double b, double step){
    double sum = 0.0;
    double x = a;
    int n = (int)((b-a)/step);

    for (int i = 0; i <= n; i++){
        sum += f1(x + i * step);
    }

    return sum * step;
}

public double rectangleMethodM(double a, double b, double step) {
    double sum = 0.0;
    double x = a+step/2;
    int n = (int)((b-a)/step);

    for(int i=0; i<n; i++){
        sum+= f1(x+i*step);
    }
    return sum * step;
}

public double trapezoidMethod(double a, double b, double step){
    double sum = (f1(b) + f1(a))/2;
    int n = (int)((b - a) / step);
    double current;

    for (int i = 1; i < n; i++){
        current = a + step * i;
        sum += f1(current);
    }
    return sum * step;
}

public double simpsonsMethod(double a, double b, double step) {
    int n = (int) ((b - a) / step);
    double sum = f1(b) + f1(a);
    double subSum = 0;
    double x;

    for (int i = 1; i < n; i += 2) {
        x = a + step * i;
        subSum += f1(x);
    }

    subSum *= 4;
    sum += subSum;
    subSum = 0;

    for (int i = 2; i < n - 1; i += 2) {
        x = a + step * i;
        subSum += f1(x);
    }

    subSum *= 2;
    sum += subSum;
    return sum * step / 3;
}

public double halfDivision(double a, double b, double eps){
    if (f2(a) * f2(b) > 0) {
        throw new IllegalArgumentException("No root on this interval");
    }

    double x= (a+b)/2;

    while(Math.abs(f2(x))>eps){
        if(f2(a)*f2(x)<0){
            b = x;
        }
        else if(f2(b)*f2(x)<0){
            a = x;
        }
        x = (a+b)/2;
    }

    return x;
}

public double firstDerivative(double x){
    return 2*x-2+1/x;
}

public double secondDerivative(double x){
    return 2-1/(x*x);
}

public double methodNewton(double a, double b, double eps){
    if (f2(a) * f2(b) > 0) {
        throw new IllegalArgumentException("No root on interval");
    }

    double x;

    if(f2(a)*secondDerivative(a)>0){
        x=a;
    }
    else{
        x=b;
    }

    while(Math.abs(f2(x))>eps){
        x = x - f2(x) / firstDerivative(x);
    }

    return x;
}

public double methodChord(double a, double b, double eps){
    if (f2(a) * f2(b) > 0) {
        throw new IllegalArgumentException("No root on interval");
    }

    double x;

    if (f2(a) * secondDerivative(a) > 0) {
        x = b;
    } else {
        x = a;
    }

    while (Math.abs(f2(x)) > eps) {

        if (x == b) {
            x = x - (f2(x) * (x - a)) / (f2(x) - f2(a));
        } else {
            x = x - (f2(x) * (b - x)) / (f2(b) - f2(x));
        }
    }

    return x;
}

public void rungeKutta3(double x0, double y0, double h, double xn) {

    double x = x0;
    double y = y0;

    IO.println("x\t\ty");

    while (x <= xn) {

        IO.println(String.format("%.2f\t%.6f", x, y));
        double k1 = h * f3(x, y);

        double k2 = h * f3(
                x + h / 2,
                y + k1 / 2
        );

        double k3 = h * f3(
                x + h,
                y - k1 + 2 * k2
        );

        y = y + (k1 + 4 * k2 + k3) / 6;

        x += h;
    }
}