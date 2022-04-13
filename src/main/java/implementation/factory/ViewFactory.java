package implementation.factory;

import framework.view.View;
import implementation.view.PlayView;
import implementation.view.StartView;
import lombok.NoArgsConstructor;

import static implementation.constants.ViewConstants.PLAY;
import static implementation.constants.ViewConstants.START;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ViewFactory {

    private static ViewFactory INSTANCE;

    public static ViewFactory getInstance() {
        if (isNull(INSTANCE))
            INSTANCE = new ViewFactory();

        return INSTANCE;
    }

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
