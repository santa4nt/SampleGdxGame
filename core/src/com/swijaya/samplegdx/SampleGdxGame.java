package com.swijaya.samplegdx;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SampleGdxGame extends Game {

    private static final String TAG = SampleGdxGame.class.getSimpleName();

    private Screen mainScreen;

    private Stage stage;

    private Texture badLogicImg;
    private Actor badLogicActor;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        badLogicImg = new Texture("badlogic.jpg");
        stage = new Stage(new ScreenViewport());

        badLogicActor = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(badLogicImg, 0, 0);
            }
            @Override
            public void act(float delta) {
                super.act(delta);
                setBounds(0, 0, badLogicImg.getWidth(), badLogicImg.getHeight());
            }
        };
        badLogicActor.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.debug(TAG, "[badLogicActor] touchDown: (" + x + "," + y + ")");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.debug(TAG, "[badLogicActor] touchUp: (" + x + "," + y + ")");
            }
        });
        stage.addActor(badLogicActor);

        mainScreen = new ScreenAdapter() {

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
        };

        setScreen(mainScreen);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        super.dispose();    // calls screen.hide()
        mainScreen.dispose();
    }
}
