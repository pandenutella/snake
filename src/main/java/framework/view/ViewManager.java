package framework.view;

import framework.control.ControllerManager;
import framework.display.PaintManager;
import implementation.factory.ViewFactory;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static framework.display.PaintLevel.VIEW;
import static java.util.Objects.isNull;

@Data
public class ViewManager {

    private final Map<String, View> viewMap = new HashMap<>();

    private PaintManager paintManager;
    private ControllerManager controllerManager;
    private String selectedViewName;

    public void setViews(List<String> viewNameList) {
        viewNameList.forEach(viewName -> {
            View view = ViewFactory.getInstance().getView(viewName);

            if (isNull(view))
                return;

            view.setViewManager(this);
            view.setControllerManager(controllerManager);

            viewMap.put(viewName, view);
        });
    }

    public void goToView(String viewName) {
        paintManager.removePainter(VIEW, viewMap.get(selectedViewName));
        paintManager.addPainter(VIEW, viewMap.get(viewName));

        selectedViewName = viewName;

        viewMap.get(selectedViewName).unmount();
        viewMap.get(viewName).mount();
    }

    public void update() {
        viewMap.get(selectedViewName).update();
    }
}
