package ru.samsung.gamestudio;

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

    private int frameCounter;


    private Texture[] framesArray;


    public Bird(int x, int y, int speed, int width, int height){
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

    void draw(Batch batch) {
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
    void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }
}
