package implementation.factory;

import framework.factory.ViewFactory;
import framework.view.View;
import implementation.view.PlayView;
import implementation.view.StartView;

import static implementation.constants.ViewConstants.PLAY;
import static implementation.constants.ViewConstants.START;

public class SnakeViewFactory implements ViewFactory {

    public View getView(String id) {
        switch (id) {
            case START:
                return new StartView();
            case PLAY:
                return new PlayView();
            default:
                return null;
        }
    }
}
