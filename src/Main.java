import java.util.*;

interface Exam {
    String getType();
    void displayInfo();
}

class PracticeQuiz implements Exam
{
    public String getType()
    {
        return "Practice Quiz";
    }
    public void displayInfo() {
        System.out.println("Practice Quiz Exam");
    }
}

class MidtermExam implements Exam
{
    public String getType() {
        return "Midterm Exam";
    }
    public void displayInfo() {
        System.out.println("Midterm Exam");
    }
}

class FinalExam implements Exam {
    public String getType() {
        return "Final Exam";
    }
    public void displayInfo() {
        System.out.println("Final Exam");
    }
}


abstract class ExamFactory {
    public abstract Exam createExam();
}

class PracticeQuizFactory extends ExamFactory {


    public Exam createExam()
    {
        return new PracticeQuiz();
    }
}

class MidtermExamFactory extends ExamFactory {

    public Exam createExam() {
        return new MidtermExam();
    }
}

class FinalExamFactory extends ExamFactory
{
    public Exam createExam() {
        return new FinalExam();
    }
}


interface Question {


    String getType();
    int getPoints();
    String getDifficulty();
}

interface QuestionRenderer {

    void render(Question q);
}

interface QuestionEvaluator
{
    int evaluate(Question q, String answer);
}


interface QuestionSource {

    Question getQuestion();
}

class BankQuestionSource implements QuestionSource {
    private List<Question> bank;
    private Random rand = new Random();

    public BankQuestionSource(List<Question> bank)
    {
        this.bank = bank;
    }

    public void setBank(List<Question> bank)
    {
        this.bank = bank;
    }

    public List<Question> getBank()
    {
        return this.bank;
    }

    @Override
    public Question getQuestion() {

        return bank.get(rand.nextInt(bank.size()));
    }
}

class FakerQuestionSource implements QuestionSource {
    private static final String[] TYPES = {"MCQ", "TrueFalse", "Essay", "Programming"};

    private static final String[] DIFFICULTIES = {"Easy", "Medium", "Hard"};
    private Random rand = new Random();

    public void setSeed(long seed) {
        this.rand = new Random(seed);
    }

    @Override
    public Question getQuestion() {
        String type = TYPES[rand.nextInt(TYPES.length)];
        String difficulty = DIFFICULTIES[rand.nextInt(DIFFICULTIES.length)];

        switch (type) {
            case "MCQ":
                return new MCQQuestion(rand.nextInt(3) + 1, difficulty);
            case "TrueFalse":
                return new TrueFalseQuestion(1, difficulty);
            case "Essay":
                return new EssayQuestion(rand.nextInt(6) + 5, difficulty);
            case "Programming":
                return new ProgrammingQuestion(rand.nextInt(11) + 15, difficulty);
            default:
                return new MCQQuestion(2, "Medium");
        }
    }
}


class MCQQuestion implements Question {
    private int points;
    private String difficulty;

    public MCQQuestion(int points, String difficulty) {
        this.points = points;
        this.difficulty = difficulty;
    }

    public MCQQuestion()
    { this(2, "Medium"); }


    public void setPoints(int points)
    {

        this.points = points;
    }
    public void setDifficulty(String difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getType() {
        return "MCQ";
    }
    public int getPoints()
    {
        return points;
    }
    public String getDifficulty()
    {
        return difficulty;
    }
}

class TrueFalseQuestion implements Question {
    private int points;
    private String difficulty;

    public TrueFalseQuestion(int points, String difficulty) {
        this.points = points;
        this.difficulty = difficulty;
    }

    public TrueFalseQuestion() {
        this(1, "Easy");
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return "True/False";
    }
    public int getPoints() {
        return points;
    }
    public String getDifficulty() {
        return difficulty;
    }
}

class EssayQuestion implements Question {
    private int points;
    private String difficulty;

    public EssayQuestion(int points, String difficulty) {

        this.points = points;
        this.difficulty = difficulty;
    }

    public EssayQuestion() {
        this(10, "Hard");
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return "Essay";
    }
    public int getPoints() {
        return points;
    }
    public String getDifficulty() {
        return difficulty;
    }
}

class ProgrammingQuestion implements Question {
    private int points;
    private String difficulty;

    public ProgrammingQuestion(int points, String difficulty) {
        this.points = points;
        this.difficulty = difficulty;
    }

    public ProgrammingQuestion() {

        this(20, "Hard"); }

    public void setPoints(int points) {
        this.points = points;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return "Programming";
    }
    public int getPoints() {
        return points;
    }
    public String getDifficulty() {
        return difficulty;
    }
}


class MCQRenderer implements QuestionRenderer {
    public void render(Question q) {
        System.out.println("Rendering MCQ: " + q.getType() + " (" + q.getPoints() + " pts)");
    }
}

class TrueFalseRenderer implements QuestionRenderer {
    public void render(Question q) {
        System.out.println("Rendering True/False: " + q.getType() + " (" + q.getPoints() + " pts)");
    }
}

class EssayRenderer implements QuestionRenderer {
    public void render(Question q) {
        System.out.println("Rendering Essay: " + q.getType() + " (" + q.getPoints() + " pts)");
    }
}

class ProgrammingRenderer implements QuestionRenderer {
    public void render(Question q) {
        System.out.println("Rendering Programming: " + q.getType() + " (" + q.getPoints() + " pts)");
    }
}


class MCQEvaluator implements QuestionEvaluator {
    public int evaluate(Question q, String answer) {
        return q.getPoints();

    }
}

class TrueFalseEvaluator implements QuestionEvaluator {
    public int evaluate(Question q, String answer) {
        return q.getPoints();
    }
}

class EssayEvaluator implements QuestionEvaluator {
    public int evaluate(Question q, String answer) {
        return q.getPoints();
    }
}

class ProgrammingEvaluator implements QuestionEvaluator {
    public int evaluate(Question q, String answer) {
        return q.getPoints();
    }
}


interface QuestionFactory {


    Question createQuestion();
    QuestionRenderer createRenderer();
    QuestionEvaluator createEvaluator();
    void setSource(QuestionSource source);
    QuestionSource getSource();
}

class MCQFactory implements QuestionFactory {
    private QuestionSource source;

    public MCQFactory(QuestionSource source) {
        this.source = source;
    }

    public void setSource(QuestionSource source) {
        this.source = source;
    }

    public QuestionSource getSource() {
        return this.source;
    }

    public Question createQuestion() {
        if (source == null) {
            throw new IllegalStateException("QuestionSource not injected");
        }
        return source.getQuestion();
    }

    public QuestionRenderer createRenderer()
    {
        return new MCQRenderer();
    }
    public QuestionEvaluator createEvaluator() {
        return new MCQEvaluator();
    }
}

class TrueFalseFactory implements QuestionFactory {
    private QuestionSource source;

    public TrueFalseFactory(QuestionSource source) {
        this.source = source;
    }

    public void setSource(QuestionSource source) {
        this.source = source;
    }

    public QuestionSource getSource() {
        return this.source;
    }

    public Question createQuestion() {
        return source.getQuestion();
    }

    public QuestionRenderer createRenderer()
    {
        return new TrueFalseRenderer();
    }
    public QuestionEvaluator createEvaluator() {
        return new TrueFalseEvaluator();
    }
}

class EssayFactory implements QuestionFactory {
    private QuestionSource source;

    public EssayFactory(QuestionSource source) {
        this.source = source;
    }

    public void setSource(QuestionSource source) {
        this.source = source;
    }

    public QuestionSource getSource() {
        return this.source;
    }

    public Question createQuestion() {
        return source.getQuestion();
    }

    public QuestionRenderer createRenderer() {
        return new EssayRenderer();
    }
    public QuestionEvaluator createEvaluator() {
        return new EssayEvaluator();
    }
}


class ProgrammingFactory implements QuestionFactory {
    private QuestionSource source;

    public ProgrammingFactory(QuestionSource source) {
        this.source = source;
    }

    public void setSource(QuestionSource source) {
        this.source = source;
    }

    public QuestionSource getSource() {
        return this.source;
    }

    public Question createQuestion() {

        return source.getQuestion();
    }

    public QuestionRenderer createRenderer()
    {
        return new ProgrammingRenderer();
    }
    public QuestionEvaluator createEvaluator() {
        return new ProgrammingEvaluator();
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

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    public void setPassingScore(int score) {
        this.passingScore = score;
    }
    public void setNegativeMarking(boolean val) {
        this.negativeMarking = val;
    }
    public void setQuestionShuffle(boolean val) {
        this.questionShuffle = val;
    }
    public void setAutoSubmit(boolean val) {
        this.autoSubmit = val;
    }
    public void setCalculatorAllowed(boolean val) {
        this.calculatorAllowed = val;
    }

    public String getTitle() {
        return title;
    }
    public int getDuration() {
        return duration;
    }
    public int getPassingScore() {
        return passingScore;
    }
    public boolean isNegativeMarkingEnabled() {
        return negativeMarking;
    }
    public boolean isQuestionShuffleEnabled() {
        return questionShuffle;
    }
    public boolean isAutoSubmitEnabled() {
        return autoSubmit;
    }
    public boolean isCalculatorAllowed() {
        return calculatorAllowed;
    }
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
    public void render(Exam exam, ExamConfiguration config, List<Question> questions, String sourcingMode) {
        System.out.println("=====================================================================");
        System.out.println("EXAM CREATED SUCCESSFULLY");
        System.out.println("=====================================================================");

        System.out.println("Type: " + exam.getType());
        System.out.println("Title: " + config.title);
        System.out.println("Duration: " + config.duration + " minutes");
        System.out.println("Passing Score: " + config.passingScore + " Marks");

        System.out.println("Configuration Profiles:");
        System.out.println((config.negativeMarking   ? "✓" : "✗") + " Negative Marking " + (config.negativeMarking ? "Enabled" : "Disabled"));
        System.out.println((config.questionShuffle   ? "✓" : "✗") + " Question Shuffle " + (config.questionShuffle ? "Activated" : "Off"));
        System.out.println((config.calculatorAllowed ? "✓" : "✗") + " Embedded Calculator Allowed");
        System.out.println((config.autoSubmit        ? "✓" : "✗") + " Auto-Submit On Timeout");

        System.out.println("Question Sourcing Strategy: " + sourcingMode);

        System.out.println("Compiled Structural Components:");
        for (Question q : questions) {
            System.out.println("- [Type: " + q.getType() + "] Points: " + q.getPoints() + " Difficulty: " + q.getDifficulty());
        }

        System.out.println("=====================================================================");
    }
}


public class Main {
    public static void main(String[] args) {
        ExamFactory examFactory = new MidtermExamFactory();
        Exam exam = examFactory.createExam();

        List<Question> bank = Arrays.asList(
                new MCQQuestion(2, "Medium"),
                new MCQQuestion(2, "Easy"),
                new EssayQuestion(10, "Hard"),
                new ProgrammingQuestion(20, "Hard"),
                new TrueFalseQuestion(1, "Easy")
        );
        QuestionSource source = new BankQuestionSource(bank);
        String sourcingMode = "Question Bank Mode";

        QuestionFactory mcqFactory         = new MCQFactory(source);
        QuestionFactory trueFalseFactory   = new TrueFalseFactory(source);
        QuestionFactory essayFactory       = new EssayFactory(source);
        QuestionFactory programmingFactory = new ProgrammingFactory(source);

        ExamConfiguration config = new ExamBuilder()
                .setTitle("OOP Midterm Exam")
                .setDuration(60)
                .setPassingScore(50)
                .setNegativeMarking(true)
                .setQuestionShuffle(true)
                .setAutoSubmit(true)
                .setCalculatorAllowed(false)
                .build();

        List<Question> questions = Arrays.asList(
                new MCQQuestion(2, "Medium"),
                new MCQQuestion(2, "Easy"),
                new EssayQuestion(10, "Hard"),
                new ProgrammingQuestion(20, "Hard"),
                new TrueFalseQuestion(1, "Easy")
        );
//hii

        System.out.println("munni");
        ExamRenderer renderer = new ExamRenderer();
        renderer.render(exam, config, questions, sourcingMode);
    }
}