package org.pandenutella.game.manager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.pandenutella.game.object.grid.CellPositioned;
import org.pandenutella.game.utility.Position;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GridManager {

    private static GridManager INSTANCE;

    public static GridManager getGlobalGridManager() {
        if (INSTANCE == null) {
            INSTANCE = new GridManager();
        }
        return INSTANCE;
    }

    private final List<CellPositioned> cellPositionedList = new ArrayList<>();
    private final List<Position> screenPositionList = new ArrayList<>();

    public void initializeScreenPositionList(Dimension gridDimension, int cellSize) {
        screenPositionList.clear();

        int columns = gridDimension.width / cellSize;
        int rows = gridDimension.height / cellSize;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                screenPositionList.add(new Position(c * cellSize, r * cellSize));
            }
        }
    }

    public void addCellPositioned(CellPositioned cellPositioned) {
        this.cellPositionedList.add(cellPositioned);
    }

    public List<Position> getVacantPositionList() {
        List<Position> occupiedPositionList = cellPositionedList.stream()
                .flatMap(cellPositioned -> cellPositioned.getPositions().stream())
                .toList();

        return screenPositionList.stream()
                .filter(position -> !occupiedPositionList.contains(position))
                .toList();
    }
}
