package se.iths.qvist99.rpsgame;


public class Main {
    public static void main(String[] args) {
        GameController controller = new GameController();
        GamePresenter presenter = new GamePresenter(controller);
        GameFrame frame = new GameFrame(presenter);
        presenter.setGameFrame(frame);

    }
}
