package Antot_12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeLogin extends JFrame {
    private final JTextField playerXTextField;
    private final JTextField playerOTextField;

    public TicTacToeLogin() {
        setTitle("Tic-Tac-Toe Login");
        setSize(300, 120);
        setLayout(new GridLayout(3, 2, 5, 5));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel playerXLabel = new JLabel(" Player X Name:");
        playerXLabel.setForeground(Color.CYAN);
        playerXLabel.setFont(new Font("Consolas", Font.BOLD, 12));
        add(playerXLabel);

        playerXTextField = new JTextField();
        add(playerXTextField);

        JLabel playerOLabel = new JLabel(" Player O Name:");
        playerOLabel.setForeground(Color.CYAN);
        playerOLabel.setFont(new Font("Consolas", Font.BOLD, 12));
        add(playerOLabel);

        playerOTextField = new JTextField();
        add(playerOTextField);

        JButton startButton = new JButton("Start Game");
        startButton.setForeground(Color.BLACK); // Text color
        startButton.setBackground(Color.CYAN); // Button color
        startButton.setFont(new Font("Consolas", Font.BOLD, 12)); // Make text bold
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerX = playerXTextField.getText().trim();
                String playerO = playerOTextField.getText().trim();
                if (!playerX.isEmpty() && !playerO.isEmpty()) {
                    TicTacToe game = new TicTacToe(playerX, playerO);
                    game.setVisible(true);
                    dispose(); // Close the login form
                } else {
                    JOptionPane.showMessageDialog(TicTacToeLogin.this, "Please enter names for both players.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(new JLabel()); // Dummy label for alignment
        add(startButton);

        getContentPane().setBackground(Color.BLACK); // Background color
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeLogin().setVisible(true));
    }
}
