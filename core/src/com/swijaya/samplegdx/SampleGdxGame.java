package com.swijaya.samplegdx;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SampleGdxGame extends Game {

    private static final String TAG = SampleGdxGame.class.getSimpleName();

    public class BadLogicSpriteActor extends Actor {

        private Texture badLogicImg;
        private Vector2 position;

        public BadLogicSpriteActor() {
            badLogicImg = new Texture("badlogic.jpg");
            position = new Vector2(0, 0);

            setPosition(position.x, position.y);
            setBounds(getX(), getY(), badLogicImg.getWidth(), badLogicImg.getHeight());

            addListener(new DragListener() {
                @Override
                public void dragStart(InputEvent event, float x, float y, int pointer) {
                    Gdx.app.debug(TAG, "[badLogicActor] dragStart: (" + x + "," + y + ")");
                }

                @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    Gdx.app.debug(TAG, "[badLogicActor] dragStop: (" + x + "," + y + ")");
                }
            });
        }

        @Override
        public void draw(Batch batch, float alpha) {
            batch.draw(badLogicImg, getX(), getY());
        }

        @Override
        public void act(float delta) {
            super.act(delta);
        }

    }

    public class MainScreen extends ScreenAdapter {

        private Stage stage;

        public MainScreen(Stage stage) {
            this.stage = stage;
        }

        @Override
        public void render(float delta) {
            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stage.act(delta);
            stage.draw();
        }

        @Override
        public void resize(int width, int height) {
            Gdx.app.debug(TAG, "mainScreen.resize(" + width + "," + height + ")");
            stage.getViewport().update(width, height, true);
        }

        @Override
        public void dispose() {
            Gdx.app.debug(TAG, "mainScreen.dispose()");
            stage.dispose();
        }
    }

    private Screen mainScreen;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Stage stage = new Stage(new ScreenViewport());
        stage.addActor(new BadLogicSpriteActor());

        mainScreen = new MainScreen(stage);
        setScreen(mainScreen);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        super.dispose();    // calls screen.hide()
        mainScreen.dispose();
    }
}
