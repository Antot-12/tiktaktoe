package Antot_12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToe extends JFrame {
    private JPanel panel;
    private JButton[] buttons = new JButton[9];
    private boolean turn = true; // true for X's turn, false for O's
    private int turnCount = 0;
    private final String playerX;
    private final String playerO;

    public TicTacToe(String playerX, String playerO) {
        this.playerX = playerX;
        this.playerO = playerO;

        setTitle("Tic-Tac-Toe: " + playerX + " vs " + playerO);
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 5, 5));
        panel.setBackground(Color.BLACK);
        add(panel);

        initializeButtons();
    }
    private void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Consolas", Font.BOLD, 40));
            buttons[i].setForeground(Color.CYAN); // Змінити колір тексту кнопок на бірюзовий
            buttons[i].setBackground(Color.BLACK);
            buttons[i].addActionListener(e -> {
                JButton clickedButton = (JButton) e.getSource();
                clickedButton.setText(turn ? "X" : "O");

                if (clickedButton.getText().equals("X") || clickedButton.getText().equals("O")) {
                    clickedButton.setForeground(Color.CYAN); // Бірюзовий колір
                }
                clickedButton.setEnabled(false);
                turnCount++;
                checkForWin();
                turn = !turn;
            });
            panel.add(buttons[i]);
        }
    }




    private void checkForWin() {
        if (turnCount >= 5 && (checkRows() || checkColumns() || checkDiagonals())) {
            JOptionPane.showMessageDialog(this, "Game Over. " + (turn ? playerX + " (X)" : playerO + " (O)") + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            askForAnotherRound();
        } else if (turnCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!", "Draw", JOptionPane.INFORMATION_MESSAGE);
            askForAnotherRound();
        }
    }

    private void askForAnotherRound() {
        int response = JOptionPane.showConfirmDialog(this, "Would you like to play another round?", "Play Again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            dispose(); // Close the game window
        }
    }

    private boolean checkRows() {
        for (int i = 0; i < 9; i += 3) {
            if (check(buttons[i], buttons[i + 1], buttons[i + 2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (check(buttons[i], buttons[i + 3], buttons[i + 6])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return check(buttons[0], buttons[4], buttons[8]) || check(buttons[2], buttons[4], buttons[6]);
    }

    private boolean check(JButton b1, JButton b2, JButton b3) {
        return b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText()) && !b1.getText().isEmpty();
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        turn = true;
        turnCount = 0;
    }
}
