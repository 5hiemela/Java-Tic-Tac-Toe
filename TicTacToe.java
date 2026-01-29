/*
 * Chiemela Onyenso
 * This is my project that will implement JFrame to create a game of Tic-Tac-Toe.
 * The program will begin with a Start, Rules, and Exit option. It will have a 1 player and 2 player game mode - games will always begin with X first.
 * This project uses different JPanel's to establish a menu screen, mode select screen, and a game screen.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton startButton; // Starts the game
    private JButton rulesButton; // View the rules
    private JButton quitButton; // Exits application
    private JButton player1Button; // Chooses 1 player mode
    private JButton player2Button; // Chooses 2 player mode
    private JPanel menuPanel; // Holds the menu screen
    private JPanel modeSelectPanel; // Holds the mode select screen
    private JPanel gamePanel; // Holds the game screen

    TicTacToe() {

        // Used to define layout rules for components in GridBagLayout
        GridBagConstraints layoutConst = null;

        // Set frame's title
        setTitle("Tic-Tac-Toe");

        // Set frame's close behavior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame size
        setSize(400, 300);

        // Center JFrame on screen
        setLocationRelativeTo(null);

        // Create Labels
        JLabel welcomeLabel = new JLabel("Welcome to Tic-Tac-Toe!"); // Menu welcome message
        // More labels will be added soon

        // Create Buttons and add action listeners
        startButton = new JButton("Start");
        startButton.addActionListener(this);

        rulesButton = new JButton("Rules");
        rulesButton.addActionListener(this);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);

        player1Button = new JButton("1 Player");
        player1Button.addActionListener(this);

        player2Button = new JButton("2 Player");
        player2Button.addActionListener(this);

        // Create and Configure Panels
        menuPanel = new JPanel(new GridBagLayout());

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(5, 5, 5, 5);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        menuPanel.add(welcomeLabel, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        menuPanel.add(startButton, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        menuPanel.add(rulesButton, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        menuPanel.add(quitButton, layoutConst);

        add(menuPanel);

        modeSelectPanel = new JPanel();
        gamePanel = new JPanel();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // TODO:
        // This will handle all button clicks for the application.
        // Logic for Start, Rules, and Quit buttons will be added here.
    }

    public static void main(String[] args) {
        // Launches the application and creates the GUI
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
