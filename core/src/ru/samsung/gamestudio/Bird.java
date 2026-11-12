package ru.samsung.gamestudio;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    private int jumpHeight;
    private final int maxHeightOfJump = 200;
    private boolean jump;


    private int x, y;
    private int speed;
    private Texture texture;

    public Bird(int x, int y, Texture texture, int speed){
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.speed = speed;
    }

    public void fly() {
        if (y >= jumpHeight) {
            jump = false;
        }

        if (jump) {
            y += speed;
        } else {
            y -= speed;
        }
    }

   public void draw(Batch batch) {
        batch.draw( texture, x  , y);
    }

    public void dispose() {
        texture.dispose();
    }
    void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }
}
