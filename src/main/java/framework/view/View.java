package framework.view;

import framework.control.ControllerManager;
import framework.display.PaintManager;
import framework.display.Painter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class View implements Painter {

    private ViewManager viewManager;
    private ControllerManager controllerManager;
    private PaintManager paintManager;

    public abstract void mount();

    public abstract void unmount();

    public abstract void update();
}
