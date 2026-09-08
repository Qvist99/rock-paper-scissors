package se.iths.qvist99.rpsgame;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class GameFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);
    private final Color bgColor = new Color(80, 90, 92);
    private final Color highlightColor = new Color(184, 207, 229);
    private final GamePresenter presenter;
    private final JLabel currScore = buildLabel("User 0 - 0 Computer", 16);
    private final JLabel firstToLabel = buildLabel("First to 3 wins", 20);
    private final JLabel outcomeLabel = buildLabel(" ", 14);
    private final String[] rpsButtonContent = {"Rock", "Paper", "Scissor"};
    private final JPanel rpsButtonPanel = createPanelButtons(rpsButtonContent, "", this::selectUserChoice);
    //private final JLabel outcomeLabel =


    public GameFrame(GamePresenter presenter) {
        this.presenter = presenter;

        setTitle("Rock, Paper, Scissors");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().setBackground(bgColor);
        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );


        JPanel panelOne = buildPanelOne();
        JPanel panelTwo = buildPanelTwo();


        cards.add(panelOne, "PANEL_ONE");
        cards.add(panelTwo, "PANEL_TWO");

        add(cards);
        setVisible(true);
    }


    private JPanel buildPanelOne() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(bgColor);
        mainPanel.add(Box.createVerticalStrut(220));


        JLabel title = buildLabel("Select how many round wins is required to win the game!", 20);

        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));


        String[] buttonContent = {"3", "5", "7"};
        JPanel buttonPanel = createPanelButtons(buttonContent, "First to", this::selectGameLength);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        return mainPanel;
    }

    private JPanel buildPanelTwo() {
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(bgColor);
        mainPanel.add(Box.createVerticalStrut(10));


        mainPanel.add(firstToLabel);

        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(currScore);

        mainPanel.add(Box.createVerticalStrut(100));

        outcomeLabel.setVisible(true);
        mainPanel.add(outcomeLabel);

        mainPanel.add(Box.createVerticalStrut(120));


        // Outcome label being displayed at each round end
        mainPanel.add(rpsButtonPanel);

        return mainPanel;

    }

    private JLabel buildLabel(String content, int fontSize) {
        JLabel title = new JLabel(content);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.white);
        title.setFont(new Font("SansSerif", Font.PLAIN, fontSize));

        return title;
    }

    private JButton createButton(String content) {
        JButton button = new JButton(content);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(100, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private JPanel createPanelButtons(String[] buttonContentArr, String buttonContentPrefix, Consumer<String> actionListenerMethod) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttonPanel.setBackground(bgColor);

        for (String buttonContent : buttonContentArr) {

            JButton firstToButton = createButton(buttonContentPrefix + " " + buttonContent);

            firstToButton.addActionListener(e -> actionListenerMethod.accept(buttonContent));
            buttonPanel.add(firstToButton);
        }
        return buttonPanel;
    }

    private void selectGameLength(String value) {
        presenter.onGameLengthSelected(Integer.parseInt(value));
    }

    private void selectUserChoice(String value) {
        Choice userChoice = Choice.valueOf(value.toUpperCase());

        presenter.onUserChoiceSelected(userChoice);
    }

    //Should probably enum type this param
    public void selectPanel(String panelName) {
        cardLayout.show(cards, panelName);
    }

    public void setCurrScore(int userScore, int computerScore) {
        currScore.setText("User " + userScore + " - " + computerScore + " Computer");
    }

    public void setFirstToLabel(int firstTo) {
        firstToLabel.setText("First to " + firstTo + " wins the game");
    }

    public void showRoundOutcome(String outcome, Choice computerChoice, Choice userChoice) {
        if (outcome.equals("Draw")) {
            outcomeLabel.setText("Computer chose " + computerChoice + ". The round is a draw!");
        } else {
            outcomeLabel.setText("Computer chose " + computerChoice + ". " + outcome + " wins the round!");
        }

        setRpsButtonPanelButtonsEnabled(false, userChoice);

        Timer timer = new Timer(4000, e -> {
            outcomeLabel.setText(" ");
            setRpsButtonPanelButtonsEnabled(true, userChoice);
        });

        timer.setRepeats(false);
        timer.start();
    }

    private void setRpsButtonPanelButtonsEnabled(boolean enabled, Choice userChoice) {
        for (Component component : rpsButtonPanel.getComponents()) {
            if (component instanceof JButton button) {
                button.setEnabled(enabled);
                String buttonText = button.getText().toUpperCase().trim();

                if (!enabled && buttonText.equalsIgnoreCase(userChoice.toString())) {
                    button.setBackground(highlightColor);
                } else if (enabled) {
                    button.setBackground(bgColor);
                }

            }
        }
    }


}
