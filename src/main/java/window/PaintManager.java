package window;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaintManager {
    private final List<Painter> painterList = new ArrayList<>();

    public void addPainter(Painter painter) {
        painterList.add(painter);
    }
}
