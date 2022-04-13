package framework.factory;

import framework.view.View;

public interface ViewFactory {

    View getView(String id);
}
