package ru.samsung.gamestudio;

import static javax.swing.text.StyleConstants.Background;
import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {
    MovingBackground background;
    PointCounter pointCounter;
    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;

    private int gamePoints;
    private boolean isGameOver;
    private int tubeCount = 3;
    private Tube[] tubes;


    MyGdxGame myGdxGame;
    Bird bird;
    ScreenGame(MyGdxGame myGdxGame) {

        this.myGdxGame = myGdxGame;


        bird = new Bird(10, SCR_HEIGHT / 2, 10, 250, 200);

        initTubes();
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);

        background = new MovingBackground();


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
                tube.setPointReceived();
                System.out.println(gamePoints);
            }

        }


        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints);

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

