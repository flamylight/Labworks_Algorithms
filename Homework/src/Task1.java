import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

public class Task1 {
    public void run(){
        Equation eq = new Equation();
        eq.execute();
    }
}

class Equation{
    double [] [] A, L, U;
    double [] B;
    double [] x, y;
    int [] P;

    private void LUP(){
        int n = A.length;
        L = new double [n] [n];
        U = new double[n][n];
        P = new int [n];
        double [] [] A = new double[n][n];

        for (int i = 0; i < n; i++){
            A[i] = Arrays.copyOf(this.A[i], n);
        }

        for (int i = 0; i < n; i++){
            P[i] = i;
        }

        for (int i = 0; i < n; i++){
            double max = 0;
            int pos = i;
            for (int k = i; k < n; k++){
                if (Math.abs(A[k][i]) > max){
                    max = Math.abs(A[k][i]);
                    pos = k;
                }
            }
            int temp = P[i];
            P[i] = P[pos];
            P[pos] = temp;

            for (int k = 0; k < n; k++){
                double temp2 = A[i][k];
                A[i][k] = A[pos][k];
                A[pos][k] = temp2;
            }
            for (int k = i+1; k < n; k++){
                A[k][i] = A[k][i]/A[i][i];
                for(int j = i+1; j < n; j++){
                    A[k][j]=A[k][j]-A[k][i]*A[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++){
                U[i][j] = A[i][j];
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = i; j >= 0; j--){
                if(i==j)
                    L[i][j] = 1;
                else
                    L[i][j] = A[i][j];
            }
        }
    }

    private void solveSystem()
    {
        int n = B.length;
        x = new double[n];
        y = new double[n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < i; j++){
                y[i] = y[i] + L[i][j] * y[j];
            }
            y[i]=B[P[i]]-y[i];
        }
        for (int i = n - 1; i > -1; i--){
            double sum = 0;
            for (int j = i+1; j < n; j++){
                sum=sum+U[i][j]*x[j];
            }
            x[i]=(y[i]-sum)/U[i][i];
        }
    }

    private void input(){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of equations:");
        int n=s.nextInt();
        A=new double[n][n];
        B=new double[n];
        for(int i=0;i < n;i++){
            System.out.printf("Enter coefficients of %1d equation:%n",i+1);
            for(int j=0;j < n;j++){
                System.out.printf("x%1d: ",j+1);
                A[i][j]=s.nextDouble();
            }
            System.out.printf("b%1d: ",i+1);
            B[i]=s.nextDouble();
        }
        System.out.println();
    }

    private void outputMatrix(double[] mas,String Title){
        System.out.println(Title);
        for(int i=0;i < mas.length;i++){
            System.out.printf("%5.2f%n",mas[i]);
        }
        System.out.println();
    }

    private void outputMatrix(double[][] mas,String Title){
        System.out.println(Title);
        Formatter f=new Formatter();
        for(int i=0;i < mas.length;i++){
            for(int j=0;j < mas[i].length;j++){
                f.format("%10.2f", mas[i][j]);
            }
            f.format("%n");
        }
        System.out.println(f);
    }

    private void outputMatrix(int[] mas,String Title) {
        System.out.println(Title);
        for (int i = 0; i < mas.length; i++){
            System.out.println(mas[i]);
        }
        System.out.println();
    }

    private void outputEquations(){
        System.out.println("System of equations: ");
        Formatter f=new Formatter();
        int n = B.length;
        for(int i=0;i < n; i++){
            for(int j=0;j < n;j++){
                f.format("%+7.2fx%1d", A[i][j],j);
            }
            f.format(" = %7.2f", B[i]);
        }
        System.out.println(f);
    }

    public void execute()
    {
        input();
        outputEquations();
        LUP();
        solveSystem();
        outputMatrix(L, "L matrix:");
        outputMatrix(U, "U matrix:");
        outputMatrix(P, "P matrix:");
        outputMatrix(y, "y matrix:");
        outputMatrix(x, "x matrix:");
    }
}
