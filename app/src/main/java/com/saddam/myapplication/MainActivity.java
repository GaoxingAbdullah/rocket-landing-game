package com.saddam.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView rocketImage;
    private ProgressBar fuelIndicator;
    private TextView scoreText;
    private Button thrustButton;
    private RocketLandingGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        rocketImage = findViewById(R.id.rocket);
        fuelIndicator = findViewById(R.id.fuelIndicator);
        scoreText = findViewById(R.id.score);
        thrustButton = findViewById(R.id.thrustButton);

        // Create the game object
        game = new RocketLandingGame();

        // Set up the thrust button to apply thrust
        thrustButton.setOnClickListener(v -> game.applyThrust());

        // Start the game loop
        startGameLoop();
    }

    // Game loop to update the game state and UI
    private void startGameLoop() {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable gameRunnable = new Runnable() {
            @Override
            public void run() {
                // Update the game logic
                game.update();

                // Update the UI elements based on the current game state
                updateUI();

                // Repeat the game loop
                handler.postDelayed(this, 20);  // Update every 20 milliseconds
            }
        };

        handler.post(gameRunnable);
    }

    // Update UI components (rocket position, fuel, score)
    private void updateUI() {
        // Update rocket's vertical position based on the game state
        rocketImage.setTranslationY(game.getRocketY());

        // Update the fuel progress bar
        fuelIndicator.setProgress((int) game.getFuel());

        // Update the score display
        scoreText.setText("Score: " + game.getScore());
    }
}
