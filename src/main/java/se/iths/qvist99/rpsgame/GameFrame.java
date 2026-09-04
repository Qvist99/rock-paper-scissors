package se.iths.qvist99.rpsgame;

import javax.swing.*;
import java.awt.*;

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


        cards.add(panelOne, "PANEL_ONE");

        add(cards);
        setVisible(true);
    }


    private JPanel buildPanelOne() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(bgColor);
        mainPanel.add(Box.createVerticalStrut(220));


        JLabel title = new JLabel("Select how many round wins is required to win the game!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.white);
        title.setFont(new Font("SansSerif", Font.PLAIN, 20));

        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));


        JPanel buttonPanel = createPanelOneButtons();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        return mainPanel;
    }

    private JPanel createPanelOneButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttonPanel.setBackground(bgColor);


        for (int i = 0; i <= 2; i++) {
            String btnTxt;

            switch (i) {
                case 0 -> btnTxt = "3";
                case 1 -> btnTxt = "5";
                case 2 -> btnTxt = "7";
                default -> btnTxt = "";
            }

            JButton firstToButton = new JButton("First to " + btnTxt);
            firstToButton.setFocusPainted(false);
            firstToButton.setBackground(bgColor);
            firstToButton.setForeground(Color.WHITE);
            firstToButton.setPreferredSize(new Dimension(100, 40));
            firstToButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            firstToButton.addActionListener(e -> onValueSelected(Integer.parseInt(btnTxt)));
            buttonPanel.add(firstToButton);
        }
        return buttonPanel;
    }

    private void onValueSelected(int value) {
        presenter.onGameLengthSelected(value);
    }


}
