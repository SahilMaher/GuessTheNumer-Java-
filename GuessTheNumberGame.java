import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGame extends JFrame {

    private int randomNumber;
    private int score;
    private int attempts;

    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private JButton guessButton;

    public GuessTheNumberGame() {
        // Initialize the game state
        randomNumber = new Random().nextInt(100) + 1; // Random number between 1 and 100
        score = 100;
        attempts = 0;

        // Set up the JFrame
        setTitle("Guess the Number");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        messageLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        panel.add(messageLabel);

        guessField = new JTextField();
        panel.add(guessField);

        guessButton = new JButton("Guess");
        panel.add(guessButton);

        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        panel.add(scoreLabel);

        // Add the panel to the frame
        add(panel);

        // Add action listener to the guess button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the guess input
                try {
                    int userGuess = Integer.parseInt(guessField.getText());
                    attempts++;

                    // Check if the guess is correct
                    if (userGuess < randomNumber) {
                        messageLabel.setText("Too low! Try again.");
                        score -= 10;  // Deduct score for each incorrect guess
                    } else if (userGuess > randomNumber) {
                        messageLabel.setText("Too high! Try again.");
                        score -= 10;  // Deduct score for each incorrect guess
                    } else {
                        messageLabel.setText("Correct! You guessed it in " + attempts + " attempts.");
                        score += 50 - attempts * 5; // Give bonus for correct guess based on attempts
                        if (score < 0) score = 0; // Prevent score from going negative
                        guessButton.setEnabled(false); // Disable the button after correct guess
                    }

                    // Update the score label
                    scoreLabel.setText("Score: " + score);
                    guessField.setText(""); // Clear the input field
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Please enter a valid number!");
                }
            }
        });
    }

    public static void main(String[] args) {
        // Create and show the game GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuessTheNumberGame game = new GuessTheNumberGame();
                game.setVisible(true);
            }
        });
    }
}
