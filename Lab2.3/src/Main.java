void main(){
    CombinatoricsTask1.run();
    CombinatoricsTask2.run();
    int[] teachers = {1,2,3,4,5,6,7};

    new Level3(teachers);
}

public class CombinatoricsTask1 {

    public static long factorial(int num) {
        long fact = 1;
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Завдання 1: Вибірки без повторювань ===");

        System.out.print("Введіть загальну кількість викладачів (n): ");
        int n = scanner.nextInt();

        System.out.print("З кількох викладачів обирають куратора англомовного проєкту? (m): ");
        int m = scanner.nextInt();

        if (n > 0 && m <= n) {
            long restFactorial = factorial(n - 1);
            long totalWays = m * restFactorial;

            System.out.println("\n--- РЕЗУЛЬТАТ ---");
            System.out.println("Способів обрати англомовного куратора: " + m);
            System.out.println("Перестановок для решти кураторів (" + (n - 1) + "!): " + restFactorial);
            System.out.println("Загальна кількість способів призначити кураторів: " + totalWays);
        } else {
            System.out.println("Помилка: введені некоректні дані!");
        }
    }
}

public class CombinatoricsTask2 {

    public static long power(int base, int exponent) {
        long result = 1;

        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }

        return result;
    }

    public static void run() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Завдання 2: Вибірки з повтореннями ===");

        System.out.print("Введіть кількість букв алфавіту (n): ");
        int n = scanner.nextInt();

        System.out.print("Введіть довжину слова (k): ");
        int k = scanner.nextInt();

        if (n > 0 && k > 0) {

            long result = power(n, k);

            System.out.println("\n--- РЕЗУЛЬТАТ ---");

            System.out.println("Формула: n^k");

            System.out.println("Кількість способів:");
            System.out.println(n + "^" + k + " = " + result);

        } else {

            System.out.println("Помилка: введіть додатні числа!");
        }

        scanner.close();
    }
}

public class Level3 {

    LinkedList<String> teacherList;

    public Level3(int[] sequence) {

        teacherList = new LinkedList<>();

        lexicographicPermutations(sequence);

        writeToFile();
    }

    private void lexicographicPermutations(int[] sequence) {

        while (true) {
            if (sequence[0] == 1 || sequence[0] == 2) {

                String s = "";

                for (int i = 0; i < sequence.length; i++) {
                    s += sequence[i] + " ";
                }
                teacherList.addLast(s);
            }

            int m;

            for (m = sequence.length - 2; m >= 0; m--) {

                if (sequence[m] < sequence[m + 1]) {
                    break;
                }
            }

            if (m < 0) {
                return;
            }

            int minPos = m + 1;

            for (int i = m + 1; i < sequence.length; i++) {

                if (sequence[m] < sequence[i]
                        && sequence[i] <= sequence[minPos]) {

                    minPos = i;
                }
            }

            // swap
            int temp = sequence[m];
            sequence[m] = sequence[minPos];
            sequence[minPos] = temp;

            // reverse
            int i = m + 1;
            int k = sequence.length - 1;

            while (k > i) {

                int tmp = sequence[i];
                sequence[i] = sequence[k];
                sequence[k] = tmp;

                i++;
                k--;
            }
        }
    }

    public void writeToFile() {

        try (PrintWriter writer =
                     new PrintWriter("task3_output.txt")) {

            for (String s : teacherList) {

                writer.println(s);
            }

            System.out.println(
                    "Перестановки записані у файл!"
            );

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
