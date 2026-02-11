/*
 * Chiemela Onyenso
 * This is my project that will implement JFrame to create a game of Tic-Tac-Toe.
 * The program will begin with a Start, Rules, and Exit option. It will have a 1 player and 2 player game mode - games will always begin with X first.
 * This project uses different JPanel's to establish different screens.
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
    private JButton backButton; // Sends user back to the previous screen
    private JButton[][] boardButtons; // 3x3 2D array to hold buttons for boardPanel
    private JPanel mainPanel; // The panel that uses CardLayout
    private JPanel menuPanel; // Holds the menu screen
    private JPanel modeSelectPanel; // Holds the mode select screen
    private JPanel gamePanel; // Holds the game screen
    private JPanel boardPanel; // Holds the Tic Tac Toe grid
    private JPanel centerWrapper; // Centers the game board without stretching it
    private CardLayout cardLayout; // The CardLayout manager
    private char currentPlayer; // Holds the player who has the turn - either x or o

    TicTacToe() {

        // Used to define layout rules for components in GridBagLayout
        GridBagConstraints layoutConst = null;

        // Set frame's title
        setTitle("Tic-Tac-Toe");

        // Set frame's close behavior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame size
        setSize(600, 500);

        // Center JFrame on screen
        setLocationRelativeTo(null);

        // Create Labels
        JLabel welcomeLabel = new JLabel("Welcome to Tic-Tac-Toe!"); // Menu welcome message
        JLabel modeLabel = new JLabel("Choose a mode!"); // Mode select message
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

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        // Create the CardLayout
        cardLayout = new CardLayout();

        // Create and Configure Panels

        // Main Container Panel
        mainPanel = new JPanel(cardLayout);

        // Menu Panel
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

        // Mode Select Panel
        modeSelectPanel = new JPanel(new GridBagLayout());

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(5, 5, 5, 5);

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        modeSelectPanel.add(modeLabel, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        modeSelectPanel.add(player1Button, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        modeSelectPanel.add(player2Button, layoutConst);

        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        modeSelectPanel.add(backButton, layoutConst);

        // Board Panel
        boardPanel = new JPanel(new GridLayout(3, 3));

        // Set the preferred size
        boardPanel.setPreferredSize(new Dimension(450, 450));

        // 3x3 2D array to hold buttons
        boardButtons = new JButton[3][3];

        // Creates and adds the buttons to boardPanel
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardButtons[row][col] = new JButton(""); // Each button starts blank

                // Add action listeners & font
                boardButtons[row][col].addActionListener(this);
                boardButtons[row][col].setFont(new Font("Serif", Font.BOLD, 60));

                // Disables the focus outline
                boardButtons[row][col].setFocusable(false);

                boardPanel.add(boardButtons[row][col]);
            }
        }

        // Game Panel
        gamePanel = new JPanel(new BorderLayout());

        // Wraps around boardPanel to prevent stretching
        centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(boardPanel);

        // Adds the game board to game panel & centers it
        gamePanel.add(centerWrapper, BorderLayout.CENTER);

        // Add the panels to mainPanel
        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(modeSelectPanel, "MODE");
        mainPanel.add(gamePanel, "GAME");

        // Add mainPanel to JFrame and show menu screen
        add(mainPanel);
        cardLayout.show(mainPanel, "MENU");

        currentPlayer = 'X';

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // TODO:
        // This will handle all button clicks for the application.

        if (event.getSource() == startButton) {
            cardLayout.show(mainPanel, "MODE");
        } else if (event.getSource() == rulesButton) {
            JOptionPane.showMessageDialog(
                    this,
                    "Tic-Tac-Toe Rules\n" +
                            "\n" +
                            "• The game is played on a 3×3 grid\n" +
                            "• Player X always goes first\n" +
                            "• Players take turns placing their mark (X or O)\n" +
                            "• A player wins by getting 3 marks in a row\n" +
                            "   (horizontally, vertically, or diagonally)\n" +
                            "• If all spaces are filled and no one wins, the game is a draw",
                    "Rules",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (event.getSource() == quitButton) {
            System.exit(0); // Ends the program
        } else if (event.getSource() == player1Button) {
            // TODO:
        } else if (event.getSource() == player2Button) {
            currentPlayer = 'X';
            cardLayout.show(mainPanel, "GAME");

        } else if (event.getSource() == backButton) {
            cardLayout.show(mainPanel, "MENU");
        }

        // Board buttons behavior:
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (event.getSource() == boardButtons[row][col]) {
                    boardButtons[row][col].setText(String.valueOf(currentPlayer)); // Marks an X or O in the square
                    boardButtons[row][col].setEnabled(false); // Disables the button

                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switches X -> O or O -> X for next player
                    return; // Exits the method
                }
            }
        }
    }

    public static void main(String[] args) {
        // Launches the application and creates the GUI
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
