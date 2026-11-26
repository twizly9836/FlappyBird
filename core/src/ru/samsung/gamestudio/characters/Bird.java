package ru.samsung.gamestudio.characters;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    private int jumpHeight;
    private final int maxHeightOfJump = 200;
    private boolean jump;

    private int width;
    private int height;
    private int x, y;
    private int speed;
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    private int frameCounter;


    private Texture[] framesArray;


    public Bird(int x, int y, int speed, int width, int height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        framesArray = new Texture[]{
                new Texture("birdTiles/bird0.png"),
                new Texture("birdTiles/bird1.png"),
                new Texture("birdTiles/bird2.png"),
                new Texture("birdTiles/bird1.png"),
        };
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
        int frameMultiplier = 10;

        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);

        frameCounter++;

        if (frameCounter == framesArray.length * frameMultiplier) {
            frameCounter = 0;
        }
    }

    public void dispose() {
        for (Texture frame : framesArray) {
            frame.dispose();
        }
    }
    public void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }
    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > SCR_HEIGHT) return false;

        return true;
    }


}
