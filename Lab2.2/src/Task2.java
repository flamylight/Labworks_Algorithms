import java.util.Scanner;

public class Task2 {
    public static void run(){
        Scanner scanner = new Scanner(System.in);

        IO.println("Синтаксичний аналізатор для виразу: \\/[a-z]*[F-K]+");
        IO.print("Введіть рядок (текстовий образ): ");
        String userInput = scanner.nextLine();

        // Створення автомата
        Automat automat = new Automat(userInput);

        // Запуск сканування рядка
        boolean isValid = automat.scanner();

        // Виведення результатів
        IO.println("\n--- РЕЗУЛЬТАТ АНАЛІЗУ ---");
        if (isValid) {
            IO.println("Статус: Рядок ПРАВИЛЬНИЙ (Відповідає регулярному виразу).");
            IO.println("Кінцевий стан автомата: " + automat.getState());
        } else {
            IO.println("Статус: Рядок НЕПРАВИЛЬНИЙ (Помилка синтаксису).");
            IO.println("Автомат зупинився у стані: " + automat.getState());
        }

        scanner.close();
    }
}

enum State { Initial, Q1, Q2, Success, Error }

enum Event { Slash, LowerLetter, SpecialUpper, EOS, ANY }

abstract class FSM {
    protected String text;
    protected State state;

    public FSM(String text) {
        this.text = text;
        this.state = State.Initial;
    }

    public abstract boolean scanner();
    public abstract Event recognizeEvent(char ev);
    public abstract void handleEvent(Event ev);
}

class Automat extends FSM {
    private Event event;

    public Automat(String text) {
        super(text);
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean scanner() {
        int count = 0;
        do {
            if (count < this.text.length()) {
                event = recognizeEvent(text.charAt(count));
            } else {
                event = Event.EOS;
            }

            handleEvent(event);
            count++;
        } while (state != State.Success && state != State.Error);

        return state == State.Success;
    }

    @Override
    public Event recognizeEvent(char ch) {
        if (ch == '/') {
            return Event.Slash;
        }
        if (ch >= 'a' && ch <= 'z') {
            return Event.LowerLetter;
        }
        // Твій діапазон великих літер: від F до K
        if (ch >= 'F' && ch <= 'K') {
            return Event.SpecialUpper;
        }
        return Event.ANY;
    }

    @Override
    public void handleEvent(Event event) {
        switch (state) {
            case Initial:
                switch (event) {
                    case Slash:
                        state = State.Q1;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;

            case Q1:
                switch (event) {
                    case LowerLetter:
                        state = State.Q1; // Петля для маленьких літер [a-z]*
                        break;
                    case SpecialUpper:
                        state = State.Q2; // Перехід на обов'язкову велику літеру [F-K]+
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;

            case Q2:
                switch (event) {
                    case SpecialUpper:
                        state = State.Q2; // Петля для наступних літер [F-K]+
                        break;
                    case EOS:
                        state = State.Success; // Успішний кінець рядка
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;

            default:
                state = State.Error;
                break;
        }
    }
}
