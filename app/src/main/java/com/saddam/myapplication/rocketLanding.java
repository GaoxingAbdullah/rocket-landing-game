public class RocketLandingGame {

    // Game variables
    private float rocketY = 0f;         // Vertical position of the rocket
    private float rocketVelocity = 0f;  // Velocity of the rocket
    private float fuel = 100f;          // Fuel level
    private final float gravity = 0.1f; // Constant gravity affecting the rocket
    private final float thrust = 1f;    // Thrust force applied to the rocket
    private int score = 0;              // Player's score
    private final float landingPadY = 800f; // Y position of the landing pad

    // Apply thrust to the rocket
    public void applyThrust() {
        if (fuel > 0) {
            rocketVelocity -= thrust;  // Thrust counteracts gravity
            fuel -= thrust * 0.1f;     // Fuel consumption based on thrust
        }
    }

    // Update the rocket's position based on velocity and gravity
    public void update() {
        rocketY += rocketVelocity;
        rocketVelocity += gravity;

        // Check if the rocket has landed or crashed
        if (rocketY >= landingPadY) {
            if (Math.abs(rocketVelocity) < 2) {  // Threshold for safe landing
                landSuccessfully();
            } else {
                crashRocket();
            }
        }

        // Check if fuel has run out
        if (fuel <= 0) {
            crashRocket();
        }
    }

    // Handle successful landing
    private void landSuccessfully() {
        score += 10;  // Increase score on successful landing
    }

    // Handle rocket crash
    private void crashRocket() {
        // Log game over and reset game state
        System.out.println("Game Over. Final Score: " + score);
        resetGame();
    }

    // Reset the game state
    private void resetGame() {
        rocketY = 0f;
        rocketVelocity = 0f;
        fuel = 100f;
        score = 0;
    }

    // Getters for game state (to update UI)
    public float getRocketY() {
        return rocketY;
    }

    public float getFuel() {
        return fuel;
    }

    public int getScore() {
        return score;
    }
}
