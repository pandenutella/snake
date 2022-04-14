package framework.view;

import framework.control.ControllerManager;
import framework.control.UpdateManager;
import framework.display.PaintManager;
import framework.display.Painter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class View implements Painter {

    private ViewManager viewManager;
    private ControllerManager controllerManager;
    private UpdateManager updateManager;
    private PaintManager paintManager;

    public abstract void mount();

    public abstract void unmount();
}
