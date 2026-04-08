/**
 * Tic-Tac-Toe (Java Swing)
 *
 * A classic game implementation featuring a graphical interface,
 * single-player AI mode, and local two-player functionality.
 *
 * Features:
 * - CardLayout for seamless screen transitions (Menu, Mode Select, Game)
 * - Automatic turn-switching logic for local 2-player mode
 * - Simple AI using basic decision logic for 1-player mode
 * - Dynamic winning line highlights and draw detection
 *
 * @author Chiemela Onyenso
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton startButton, rulesButton, quitButton, player1Button, player2Button, modeBackButton, gameBackButton, resetButton;
    private JButton[][] boardButtons;
    private JPanel mainPanel, menuPanel, modeSelectPanel, gamePanel, boardPanel, centerWrapper;
    private CardLayout cardLayout;
    private JLabel turnLabel;
    private char currentPlayer;
    private boolean onePlayerMode = false;
    private final Random rng = new Random();

    // Screen Constants
    private final String MENU = "MENU";
    private final String MODE = "MODE";
    private final String GAME = "GAME";

    // Theme colors
    private final Color bgColor = new Color(30, 30, 30);
    private final Color panelColor = new Color(45, 45, 45);
    private final Color accentColor = new Color(70, 130, 180);
    private final Color xColor = new Color(220, 70, 70);
    private final Color oColor = new Color(70, 160, 220);
    private final Color winHighlight = new Color(50, 205, 50);

    private int[][] winningCoords; // Stores the buttons that formed the win

    TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 550);
        setLocationRelativeTo(null);

        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        Font titleFont = new Font("SansSerif", Font.BOLD, 26);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 16);

        JLabel welcomeLabel = new JLabel("Welcome to Tic-Tac-Toe!");
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setForeground(Color.WHITE);

        JLabel modeLabel = new JLabel("Choose a mode!");
        modeLabel.setFont(titleFont);
        modeLabel.setForeground(Color.WHITE);

        turnLabel = new JLabel("Turn: X", SwingConstants.CENTER);
        turnLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        turnLabel.setForeground(Color.WHITE);

        startButton = createStyledButton("Start", buttonFont);
        rulesButton = createStyledButton("Rules", buttonFont);
        quitButton = createStyledButton("Quit", buttonFont);
        player1Button = createStyledButton("1 Player", buttonFont);
        player2Button = createStyledButton("2 Player", buttonFont);
        modeBackButton = createStyledButton("Back", buttonFont);
        gameBackButton = createStyledButton("Back", buttonFont);
        resetButton = createStyledButton("Reset", buttonFont);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Menu Panel
        menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(bgColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        addToPanel(menuPanel, welcomeLabel, gbc, 0);
        addToPanel(menuPanel, startButton, gbc, 1);
        addToPanel(menuPanel, rulesButton, gbc, 2);
        addToPanel(menuPanel, quitButton, gbc, 3);

        // Mode Panel
        modeSelectPanel = new JPanel(new GridBagLayout());
        modeSelectPanel.setBackground(bgColor);
        addToPanel(modeSelectPanel, modeLabel, gbc, 0);
        addToPanel(modeSelectPanel, player1Button, gbc, 1);
        addToPanel(modeSelectPanel, player2Button, gbc, 2);
        addToPanel(modeSelectPanel, modeBackButton, gbc, 3);

        // Game Panel
        boardPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        boardPanel.setPreferredSize(new Dimension(400, 400));
        boardPanel.setBackground(panelColor);
        boardButtons = new JButton[3][3];

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                boardButtons[r][c] = new JButton("");
                boardButtons[r][c].addActionListener(this);
                boardButtons[r][c].setFont(new Font("SansSerif", Font.BOLD, 60));
                boardButtons[r][c].setFocusable(false);
                boardButtons[r][c].setBackground(new Color(60, 60, 60));
                boardButtons[r][c].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
                boardPanel.add(boardButtons[r][c]);
            }
        }

        gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(bgColor);
        gamePanel.add(turnLabel, BorderLayout.NORTH);
        centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(bgColor);
        centerWrapper.add(boardPanel);
        gamePanel.add(centerWrapper, BorderLayout.CENTER);

        JPanel gameBtnPnl = new JPanel();
        gameBtnPnl.setBackground(bgColor);
        gameBtnPnl.add(resetButton);
        gameBtnPnl.add(gameBackButton);
        gamePanel.add(gameBtnPnl, BorderLayout.SOUTH);

        mainPanel.add(menuPanel, MENU);
        mainPanel.add(modeSelectPanel, MODE);
        mainPanel.add(gamePanel, GAME);
        add(mainPanel);

        resetBoard();
    }

    private void addToPanel(JPanel p, JComponent c, GridBagConstraints gbc, int y) {
        gbc.gridy = y;
        p.add(c, gbc);
    }

    private JButton createStyledButton(String text, Font font) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setBackground(accentColor);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == startButton) cardLayout.show(mainPanel, MODE);
        else if (src == quitButton) System.exit(0);
        else if (src == modeBackButton) cardLayout.show(mainPanel, MENU);
        else if (src == resetButton) resetBoard();
        else if (src == rulesButton) showRules();
        else if (src == player1Button || src == player2Button) {
            onePlayerMode = (src == player1Button);
            resetBoard();
            cardLayout.show(mainPanel, GAME);
        } else if (src == gameBackButton) {
            cardLayout.show(mainPanel, MODE);
        } else {
            handleMove(src);
        }
    }

    private void handleMove(Object src) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (src == boardButtons[r][c] && boardButtons[r][c].getText().isEmpty()) {
                    makeMove(r, c);
                    if (isGameOver()) return;

                    // Immediate AI move
                    if (onePlayerMode && currentPlayer == 'O') {
                        aiMove();
                        isGameOver();
                    }
                }
            }
        }
    }

    private void makeMove(int row, int col) {
        boardButtons[row][col].setText(String.valueOf(currentPlayer));
        boardButtons[row][col].setForeground(currentPlayer == 'X' ? xColor : oColor);
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        turnLabel.setText("Turn: " + currentPlayer);
    }

    private boolean isGameOver() {
        if (checkWinner()) {
            highlightWin();
            showEndDialog(winningCoords[0][0] == -1 ? "It's a draw!" : boardButtons[winningCoords[0][0]][winningCoords[0][1]].getText() + " wins!");
            return true;
        }
        return false;
    }

    private boolean checkWinner() {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(i, 0, i, 1, i, 2)) return true;
            if (checkLine(0, i, 1, i, 2, i)) return true;
        }
        // Diagonals
        if (checkLine(0, 0, 1, 1, 2, 2)) return true;
        if (checkLine(0, 2, 1, 1, 2, 0)) return true;

        if (isBoardFull()) {
            winningCoords = new int[][]{{-1, -1}};
            return true;
        }
        return false;
    }

    private boolean checkLine(int r1, int c1, int r2, int c2, int r3, int c3) {
        String s1 = boardButtons[r1][c1].getText();
        if (!s1.isEmpty() && s1.equals(boardButtons[r2][c2].getText()) && s1.equals(boardButtons[r3][c3].getText())) {
            winningCoords = new int[][]{{r1, c1}, {r2, c2}, {r3, c3}};
            return true;
        }
        return false;
    }

    private void highlightWin() {
        if (winningCoords[0][0] == -1) return;
        for (int[] coord : winningCoords) {
            boardButtons[coord[0]][coord[1]].setBackground(winHighlight);
        }
    }

    private void aiMove() {
        List<Point> open = new ArrayList<>();
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (boardButtons[r][c].getText().isEmpty()) open.add(new Point(r, c));

        if (!open.isEmpty()) {
            Point p = open.get(rng.nextInt(open.size()));
            makeMove(p.x, p.y);
        }
    }

    private void showEndDialog(String msg) {
        int res = JOptionPane.showConfirmDialog(this, msg + " Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) resetBoard();
        else cardLayout.show(mainPanel, MENU);
    }

    private void resetBoard() {
        for (JButton[] row : boardButtons) {
            for (JButton btn : row) {
                btn.setText("");
                btn.setBackground(new Color(60, 60, 60));
            }
        }
        currentPlayer = 'X';
        turnLabel.setText("Turn: X");
    }

    private boolean isBoardFull() {
        for (JButton[] row : boardButtons)
            for (JButton btn : row)
                if (btn.getText().isEmpty()) return false;
        return true;
    }

    private void showRules() {
        JOptionPane.showMessageDialog(
                this,
                "Tic-Tac-Toe Rules\n\n" +
                        "• The game is played on a 3×3 grid\n" +
                        "• Player X always goes first\n" +
                        "• Players take turns placing their mark (X or O)\n" +
                        "• A player wins by getting 3 in a row\n" +
                        "• If the board is full with no winner, it's a draw",
                "Rules",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
