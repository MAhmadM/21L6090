package com.example.a21l6090;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultScreen extends AppCompatActivity {
    private TextView resultName, resultScore;
    private Button shareScoreButton;
    String name, score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        Intent in = getIntent();
        name = in.getStringExtra("name");
        resultName = findViewById(R.id.result_name);
        resultScore = findViewById(R.id.result_score);
        shareScoreButton = findViewById(R.id.share_score_button);


        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 10);

        resultName.setText(name);
        resultScore.setText(score + "/" + totalQuestions);

        // Share score button functionality
        shareScoreButton.setOnClickListener(v -> {
            String shareMessage = name + " scored " + score + " out of " + totalQuestions + " in the quiz!";
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
}
