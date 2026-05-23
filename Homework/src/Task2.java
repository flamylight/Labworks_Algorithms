public class Task2 {
    public static void run(){
        double[] y0 = {1, 0, 2};

        RungeKutta3.rungeKutta3(
                0,
                y0,
                0.1,
                1
        );
    }
}

class RungeKutta3 {

    public static double[] f(double x, double[] y) {

        double[] result = new double[3];

        result[0] = y[1];

        result[1] = y[2];

        result[2] = x + y[2] - y[0];

        return result;
    }

    public static void rungeKutta3(
            double x0,
            double[] y0,
            double h,
            double xn
    ) {

        double x = x0;
        double[] y = y0.clone();

        System.out.println("x\t\ty\t\ty'\t\ty''");

        while (x <= xn) {

            System.out.printf(
                    "%.2f\t%.6f\t%.6f\t%.6f%n",
                    x,
                    y[0],
                    y[1],
                    y[2]
            );

            double[] k1 = f(x, y);

            double[] temp1 = new double[3];

            for (int i = 0; i < 3; i++) {
                temp1[i] = y[i] + h * k1[i] / 2;
            }

            double[] k2 = f(x + h / 2, temp1);

            double[] temp2 = new double[3];

            for (int i = 0; i < 3; i++) {
                temp2[i] = y[i] - h * k1[i] + 2 * h * k2[i];
            }

            double[] k3 = f(x + h, temp2);

            for (int i = 0; i < 3; i++) {

                y[i] = y[i] + h * (k1[i] + 4 * k2[i] + k3[i]) / 6;
            }

            x += h;
        }
    }
}
