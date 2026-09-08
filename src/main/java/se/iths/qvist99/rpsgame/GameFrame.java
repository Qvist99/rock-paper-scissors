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
    private final GamePresenter presenter;

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


        JLabel title = buildTitleLabel("Select how many round wins is required to win the game!");

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
        //mainPanel.add(Box.createVerticalStrut(220));

        JLabel currScore = buildTitleLabel("User 0 - 1 Computer");

        mainPanel.add(currScore);

        String[] buttonContent = {"Rock", "Paper", "Scissor"};
        JPanel buttonPanel = createPanelButtons(buttonContent, "", this::selectUserChoice);

        mainPanel.add(buttonPanel);

        return mainPanel;

    }

    // Need later maybe
    /*private JPanel buildMainPanel(){

    }*/

    private JLabel buildTitleLabel(String content) {
        JLabel title = new JLabel(content);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.white);
        title.setFont(new Font("SansSerif", Font.PLAIN, 20));

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

    public void alterScore(int userScore, int computerScore) {
        //Need to find the second panel and target the title created and alter it with a new value


    }


}
