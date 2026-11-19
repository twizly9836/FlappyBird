package ru.samsung.gamestudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {


    private boolean isGameOver;
    private int tubeCount = 3;
    private Tube[] tubes;


    MyGdxGame myGdxGame;
    Bird bird;
    ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;


        bird = new Bird(0, MyGdxGame.SCR_HEIGHT / 2, 10, 250, 200);

        initTubes();

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);


        myGdxGame.batch.begin();
        bird.draw(myGdxGame.batch);


        for (Tube tube : tubes) {
            tube.draw(myGdxGame.batch);
        }
    }
    @Override
    public void show() {
        isGameOver = false;
    }


    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        bird.fly();
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.batch.begin();
        bird.draw(myGdxGame.batch);
        myGdxGame.batch.end();
        for (Tube tube : tubes) {
            tube.move();
            if (tube.isHit(bird)) {
                if (tube.isHit(bird)) {
                    System.out.println("hit");
                    isGameOver = true;
                    bird.fly();
                    if (!bird.isInField()) {
                        System.out.println("not in field");
                        isGameOver = true;
                    }
                }

            }
        }
    }


    @Override
    public void resize(int width, int height) {}


    @Override
    public void pause() {}


    @Override
    public void resume() {}


    @Override
    public void hide() {}


    @Override
    public void dispose() {
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }



    }

    private void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }
}

