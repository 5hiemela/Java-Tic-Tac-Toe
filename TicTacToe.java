/*
 * Chiemela Onyenso
 * This is my project that will implement JFrame to create a game of Tic-Tac-Toe.
 * The program will begin with a Start, Rules, and Exit option. It will have 1 player and 2 player - games will always begin with X first.
 * This project uses different JPanel's to establish a menu screen, mode select screen, and a game screen.
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton startButton; // Starts the game
    private JButton rulesButton; // View the rules
    private JButton leaveButton; // Exits application
    private JButton player1Button; // Chooses 1 player mode
    private JButton player2Button; // Chooses 2 player mode
    private JPanel menuPanel; // Holds the menu screen
    private JPanel modeSelectPanel; // Holds the mode select screen
    private JPanel gamePanel; // Holds the game screen

    TicTacToe() {
       GridBagConstraints layoutConst = null;

       // Set frame's title
       setTitle("Tic-Tac-Toe");

       // Create Labels
       JLabel welcomeMessage = new JLabel("Welcome to Tic-Tac-Toe!"); // Menu welcome message
       // More labels will be added soon

       // Create Panels
       menuPanel = new JPanel();
       modeSelectPanel = new JPanel();
       gamePanel = new JPanel();

    }
}

