package framework.display;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PaintManager {
    private final Map<PaintLevel, List<Painter>> painterMap = new HashMap<>();

    public PaintManager() {
        for (PaintLevel paintLevel : PaintLevel.values())
            painterMap.put(paintLevel, new ArrayList<>());
    }

    public void addPainter(PaintLevel paintLevel, Painter painter) {
        painterMap.get(paintLevel).add(painter);
    }

    public void removePainter(PaintLevel paintLevel, Painter painter) {
        painterMap.get(paintLevel).remove(painter);
    }
}
