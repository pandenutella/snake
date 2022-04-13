package framework.view;

import framework.display.PaintManager;
import framework.display.Painter;
import lombok.Data;

@Data
public abstract class View implements Painter {

    private ViewManager viewManager;
    private PaintManager paintManager;

    public abstract void mount();

    public abstract void unmount();

    public abstract void update();
}
