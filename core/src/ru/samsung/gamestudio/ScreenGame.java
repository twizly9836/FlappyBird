package ru.samsung.gamestudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {

    private int gamePoints;
    private boolean isGameOver;
    private int tubeCount = 3;
    private Tube[] tubes;


    MyGdxGame myGdxGame;
    Bird bird;
    ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;


        bird = new Bird(0, MyGdxGame.SCR_HEIGHT / 2, 10, 250, 200);

        initTubes();




    }
    @Override
    public void show() {
        gamePoints = 0;
        isGameOver = false;
    }


    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }

        bird.fly();
        if (!bird.isInField()) {
            System.out.println("not in field");
            isGameOver = true;
        }
        for (Tube tube : tubes) {
            tube.move();
            if (tube.isHit(bird)) {
                System.out.println("hit");
                isGameOver = true;

            } else if (tube.needAddPoint(bird)) {
                gamePoints += 1;
                System.out.println(gamePoints);
            }

        }


        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.batch.begin();
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) {
            tube.draw(myGdxGame.batch);
        }
        myGdxGame.batch.end();

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

