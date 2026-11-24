package ru.samsung.gamestudio;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MovingBackground {
    Texture texture;
    MovingBackground() {
        texture = new Texture("background/game_bg.png");

    }

    public void draw(Batch batch) {
        batch.draw(texture, 0, 0, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);

    }


}
