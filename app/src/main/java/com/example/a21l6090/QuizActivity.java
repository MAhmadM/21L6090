package com.example.a21l6090;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    String name;

    private TextView questionNumberTextView, questionTextView;
    private RadioGroup optionsGroup;
    private RadioButton option1, option2, option3, option4;
    private Button previousButton, nextButton;

    private String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest ocean on Earth?",
            "Who wrote 'Romeo and Juliet'?",
            "Which is the tallest mountain in the world?",
            "What year did World War II end?",
            "In what year did the United States host the FIFA World Cup for the first time?",
            "What is the chemical symbol for gold?",
            "Which country is known as the Land of the Rising Sun?",
            "Who discovered gravity?"
    };

    private String[][] options = {
            {"Paris", "London", "Rome", "Berlin"},
            {"Earth", "Mars", "Jupiter", "Venus"},
            {"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"},
            {"William Shakespeare", "Charles Dickens", "Leo Tolstoy", "Mark Twain"},
            {"K2", "Everest", "Kangchenjunga", "Makalu"},
            {"1918", "1945", "1939", "1965"},
            {"1986", "1994", "2002", "2010"},
            {"Au", "Ag", "Pb", "Fe"},
            {"China", "Japan", "South Korea", "Thailand"},
            {"Albert Einstein", "Isaac Newton", "Galileo Galilei", "Nikola Tesla"}
    };

    private int[] correctAnswers = {0, 1, 3, 0, 1, 1, 1, 0, 1, 1};
    private int[] selectedAnswers = new int[questions.length];

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent in = getIntent();
        name=in.getStringExtra("name");
        questionNumberTextView = findViewById(R.id.question_number);
        questionTextView = findViewById(R.id.question_text);
        optionsGroup = findViewById(R.id.option_group);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);

        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }

        loadQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectedAnswer();
                if(currentQuestionIndex==9){
                    nextButton.setText("Finish");
                }

                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    loadQuestion();
                }

                else {
                    calculateScore();
                    Intent intent = new Intent(QuizActivity.this,ResultScreen.class);
                    intent.putExtra("name", name);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    finish();
                    Toast.makeText(QuizActivity.this, "Quiz Completed! Your score: " + name +score + "/" + questions.length, Toast.LENGTH_LONG).show();
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectedAnswer();
                if(currentQuestionIndex!=9)
                {
                    nextButton.setText("Next");
                }
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    loadQuestion();
                }
            }
        });
    }

    private void loadQuestion() {
        questionNumberTextView.setText((currentQuestionIndex + 1) + "/" + questions.length);
        questionTextView.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);
        optionsGroup.clearCheck();

        if (selectedAnswers[currentQuestionIndex] != -1) {
            ((RadioButton) optionsGroup.getChildAt(selectedAnswers[currentQuestionIndex])).setChecked(true);
        }
    }

    private void saveSelectedAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        if (selectedId == -1) return;

        View selectedRadioButton = findViewById(selectedId);
        int selectedIndex = optionsGroup.indexOfChild(selectedRadioButton);
        selectedAnswers[currentQuestionIndex] = selectedIndex;
    }

    private void calculateScore() {
        score = 0;
        for (int i = 0; i < questions.length; i++) {
            if (selectedAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
    }
}
