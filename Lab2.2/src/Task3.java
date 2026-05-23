import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Task3 {
    public static void run(){
        ArrayList<String> words = TransitTable.readFileWithSplittingTextInWords("task3_input.txt");

        System.out.println("=== АНАЛІЗ СЛІВ ЗА ТАБЛИЦЕЮ ПЕРЕХОДІВ ===");
        //Перевіряємо кожне слово автоматом
        for (String word : words) {
            TransitTable machine = new TransitTable(word);
            boolean isValid = machine.scanner();
            System.out.println("Слово: [" + word + "] -> " + (isValid ? "ПРАВИЛЬНЕ" : "ПОМИЛКА"));
        }
    }
}


// Клас Переходу
class Transition {
    State startState;
    State endState;
    Event trigger;

    public Transition(State start, State end, Event trig) {
        this.startState = start;
        this.endState = end;
        this.trigger = trig;
    }
}

class TransitTable extends FSM {
    private ArrayList<Transition> transitions;

    public TransitTable(String _word) {
        super(_word);
        transitions = new ArrayList<Transition>();
    }

    @Override
    public boolean scanner() {
        Event event;
        buildTransitionTable();
        int count = 0;

        do {
            if (count < text.length())
                event = recognizeEvent(text.charAt(count));
            else
                event = Event.EOS;

            handleEvent(event);
            count++;
        } while ((state != State.Success) && (state != State.Error));

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
        if (ch >= 'F' && ch <= 'K') {
            return Event.SpecialUpper;
        }
        return Event.ANY;
    }

    @Override
    public void handleEvent(Event event) {
        //Опис синтаксичного аналізатора за допомогою оператора FOR
        int length = transitions.size();
        for (int i = 0; i < length; i++) {
            Transition transition = transitions.get(i);
            if ((state == transition.startState) && (event == transition.trigger)) {
                state = transition.endState;
                return;
            }
        }
        state = State.Error;
    }

    // Унікальна таблиця переходів для виразу \/[a-z]*[F-K]+
    private void buildTransitionTable() {
        // Initial
        transitions.add(new Transition(State.Initial, State.Q1, Event.Slash));
        transitions.add(new Transition(State.Initial, State.Error, Event.LowerLetter));
        transitions.add(new Transition(State.Initial, State.Error, Event.SpecialUpper));
        transitions.add(new Transition(State.Initial, State.Error, Event.ANY));
        transitions.add(new Transition(State.Initial, State.Error, Event.EOS));

        // Q1
        transitions.add(new Transition(State.Q1, State.Q1, Event.LowerLetter)); // Петля [a-z]*
        transitions.add(new Transition(State.Q1, State.Q2, Event.SpecialUpper)); // Перехід на [F-K]+
        transitions.add(new Transition(State.Q1, State.Error, Event.Slash));
        transitions.add(new Transition(State.Q1, State.Error, Event.ANY));
        transitions.add(new Transition(State.Q1, State.Error, Event.EOS));

        // Q2
        transitions.add(new Transition(State.Q2, State.Q2, Event.SpecialUpper)); // Петля [F-K]+
        transitions.add(new Transition(State.Q2, State.Success, Event.EOS));    // Успішний фініш
        transitions.add(new Transition(State.Q2, State.Error, Event.Slash));
        transitions.add(new Transition(State.Q2, State.Error, Event.LowerLetter));
        transitions.add(new Transition(State.Q2, State.Error, Event.ANY));
    }

    public static ArrayList<String> readFileWithSplittingTextInWords(String nameFile) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nameFile))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }

        String pattern = "[\\+][\\-]|[\\-][\\+]";
        ArrayList<String> words = new ArrayList<>();

        for (String line : list) {
            String[] splitResult = line.split(pattern);
            for (String word : splitResult) {
                if (!word.trim().isEmpty()) {
                    words.add(word.trim());
                }
            }
        }
        return words;
    }
}
