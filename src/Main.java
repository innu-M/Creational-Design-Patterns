import  java.util.*;


interface Exam {
    String getType();
    void displayInfo();
}

class PracticeQuiz implements Exam {
    public String getType() { return "Practice Quiz"; }

    public void displayInfo() {
        System.out.println("Practice Quiz Exam");
    }
}

class MidtermExam implements Exam {
    public String getType() { return "Midterm Exam"; }

    public void displayInfo() {
        System.out.println("Midterm Exam");
    }
}

class FinalExam implements Exam {
    public String getType() { return "Final Exam"; }

    public void displayInfo() {
        System.out.println("Final Exam");
    }
}

abstract class ExamFactory {
    public abstract Exam createExam();
}

class PracticeQuizFactory extends ExamFactory {
    public Exam createExam() { return new PracticeQuiz(); }
}

class MidtermExamFactory extends ExamFactory {
    public Exam createExam() { return new MidtermExam(); }
}

class FinalExamFactory extends ExamFactory {
    public Exam createExam() { return new FinalExam(); }
}


interface Question {
    String getType();
}

interface QuestionRenderer {
    void render(Question q);
}

interface QuestionEvaluator {
    int evaluate(Question q, String answer);
}

interface QuestionSource {
    Question getQuestion();
}


class MCQQuestion implements Question {
    public String getType() { return "MCQ"; }
}

class TrueFalseQuestion implements Question {
    public String getType() { return "TrueFalse"; }
}

class EssayQuestion implements Question {
    public String getType() { return "Essay"; }
}

class ProgrammingQuestion implements Question {
    public String getType() { return "Programming"; }
}


class MCQRenderer implements QuestionRenderer {
    public void render(Question q) { System.out.println("MCQ Render"); }
}

class TrueFalseRenderer implements QuestionRenderer {
    public void render(Question q) { System.out.println("TF Render"); }
}

class EssayRenderer implements QuestionRenderer {
    public void render(Question q) { System.out.println("Essay Render"); }
}

class ProgrammingRenderer implements QuestionRenderer {
    public void render(Question q) { System.out.println("Programming Render"); }
}

class MCQEvaluator implements QuestionEvaluator {
    public int evaluate(Question q, String answer) { return 2; }
}

class TrueFalseEvaluator implements QuestionEvaluator {
    public int evaluate(Question q, String answer) { return 1; }
}

class EssayEvaluator implements QuestionEvaluator {
    public int evaluate(Question q, String answer) { return 10; }
}

class ProgrammingEvaluator implements QuestionEvaluator {
    public int evaluate(Question q, String answer) { return 20; }
}


interface QuestionFactory {
    Question createQuestion();
    QuestionRenderer createRenderer();
    QuestionEvaluator createEvaluator();
}


class MCQFactory implements QuestionFactory {
    public Question createQuestion() { return new MCQQuestion(); }
    public QuestionRenderer createRenderer() { return new MCQRenderer(); }
    public QuestionEvaluator createEvaluator() { return new MCQEvaluator(); }
}




class TrueFalseFactory implements QuestionFactory {
    public Question createQuestion() { return new TrueFalseQuestion(); }
    public QuestionRenderer createRenderer() { return new TrueFalseRenderer(); }
    public QuestionEvaluator createEvaluator() { return new TrueFalseEvaluator(); }
}


class EssayFactory implements QuestionFactory {
    public Question createQuestion() { return new EssayQuestion(); }
    public QuestionRenderer createRenderer() { return new EssayRenderer(); }
    public QuestionEvaluator createEvaluator() { return new EssayEvaluator(); }
}


class ProgrammingFactory implements QuestionFactory {
    public Question createQuestion() { return new ProgrammingQuestion(); }
    public QuestionRenderer createRenderer() { return new ProgrammingRenderer(); }
    public QuestionEvaluator createEvaluator() { return new ProgrammingEvaluator(); }
}




class BankQuestionSource implements QuestionSource {
    private List<Question> bank;
    private Random rand = new Random();

    public BankQuestionSource(List<Question> bank) {
        this.bank = bank;
    }

    public Question getQuestion() {
        return bank.get(rand.nextInt(bank.size()));
    }
}

class FakerQuestionSource implements QuestionSource {
    public Question getQuestion() {
        return new MCQQuestion();
    }
}



class ExamConfiguration {
    String title;
    int duration;
    int passingScore;
    boolean negativeMarking;
    boolean questionShuffle;
    boolean autoSubmit;
    boolean calculatorAllowed;
}




class ExamBuilder {
    private ExamConfiguration config = new ExamConfiguration();

    public ExamBuilder setTitle(String title) {
        config.title = title;
        return this;
    }

    public ExamBuilder setDuration(int duration) {
        config.duration = duration;
        return this;
    }

    public ExamBuilder setPassingScore(int score) {
        config.passingScore = score;
        return this;
    }

    public ExamBuilder setNegativeMarking(boolean val) {
        config.negativeMarking = val;
        return this;
    }

    public ExamBuilder setQuestionShuffle(boolean val) {
        config.questionShuffle = val;
        return this;
    }

    public ExamBuilder setAutoSubmit(boolean val) {
        config.autoSubmit = val;
        return this;
    }

    public ExamBuilder setCalculatorAllowed(boolean val) {
        config.calculatorAllowed = val;
        return this;
    }

    public ExamConfiguration build() {
        return config;
    }
}



class ExamRenderer {
    public void render(Exam exam, ExamConfiguration config) {

        System.out.println("=====================================================================");
        System.out.println("EXAM CREATED SUCCESSFULLY");
        System.out.println("=====================================================================");

        System.out.println("Type: " + exam.getType());
        System.out.println("Title: " + config.title);
        System.out.println("Duration: " + config.duration + " minutes");
        System.out.println("Passing Score: " + config.passingScore + " Marks");

        System.out.println("Configuration Profiles:");
        System.out.println("✓ Negative Marking " + (config.negativeMarking ? "Enabled" : "Disabled"));
        System.out.println("✓ Question Shuffle " + (config.questionShuffle ? "Activated" : "Off"));
        System.out.println((config.calculatorAllowed ? "✓" : "✗") + " Embedded Calculator Allowed");
        System.out.println("✓ Auto-Submit On Timeout " + (config.autoSubmit ? "Enabled" : "Disabled"));

        System.out.println("Question Sourcing Strategy: Question Bank Mode");

        System.out.println("Compiled Structural Components:");
        System.out.println("- [Type: MCQ] Points: 2 Difficulty: Medium");
        System.out.println("- [Type: MCQ] Points: 2 Difficulty: Easy");
        System.out.println("- [Type: Essay] Points: 10 Difficulty: Hard");
        System.out.println("- [Type: Programming] Points: 20 Difficulty: Hard");
        System.out.println("- [Type: True/False] Points: 1 Difficulty: Easy");

        System.out.println("=====================================================================");
    }
}








public class Main {
    public static void main(String[] args) {

        // 1. Factory for Exam
        ExamFactory examFactory = new MidtermExamFactory();
        Exam exam = examFactory.createExam();

        // 2. Question Source (IoC)
        List<Question> bank = Arrays.asList(
                new MCQQuestion(),
                new EssayQuestion(),
                new TrueFalseQuestion(),
                new ProgrammingQuestion()
        );

        QuestionSource source = new BankQuestionSource(bank);

        // 3. Abstract Factory (example usage)
        QuestionFactory mcqFactory = new MCQFactory();
        mcqFactory.createQuestion();

        // 4. Builder
        ExamConfiguration config = new ExamBuilder()
                .setTitle("OOP Midterm Exam")
                .setDuration(60)
                .setPassingScore(50)
                .setNegativeMarking(true)
                .setQuestionShuffle(true)
                .setAutoSubmit(true)
                .setCalculatorAllowed(false)
                .build();

        // 5. Render Output
        ExamRenderer renderer = new ExamRenderer();
        renderer.render(exam, config);
    }
}