package org.pandenutella.game.object.eat;

import org.pandenutella.game.constant.UpdatePriority;
import org.pandenutella.game.global.object.ObjectManager;
import org.pandenutella.game.object.Updatable;

import java.util.ArrayList;
import java.util.List;

public class EatingManager implements Updatable {

    private static EatingManager INSTANCE;

    public static void initialize() {
        INSTANCE = new EatingManager();
    }

    public static EatingManager getInstance() {
        return INSTANCE;
    }

    private final List<Eater> eaterList = new ArrayList<>();
    private final List<Food> foodList = new ArrayList<>();

    public EatingManager() {
        ObjectManager.getInstance().addUpdatable(UpdatePriority.POST_PROCESS, this);
    }

    @Override
    public void update() {
        for (Eater eater : eaterList) {
            if (eater == null || eater.getPosition() == null) {
                continue;
            }

            for (Food food : foodList) {
                if (food == null
                        || food.getPosition() == null
                        || !eater.getFoodList().contains(food.getClass())
                        || !eater.getPosition().equals(food.getPosition())
                ) {
                    continue;
                }

                eater.eat(food);
                food.eaten(eater);
            }
        }
    }

    public void addEater(Eater eater) {
        eaterList.add(eater);
    }

    public void addFood(Food food) {
        foodList.add(food);
    }
}
