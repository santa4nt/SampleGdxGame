package com.swijaya.samplegdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SampleGdxGame extends ApplicationAdapter {

    private Stage stage;

    private Texture badLogicImg;
    private Actor badLogicActor;

    @Override
    public void create() {
        badLogicImg = new Texture("badlogic.jpg");

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        badLogicActor = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(badLogicImg, 0, 0);
            }
        };
        stage.addActor(badLogicActor);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
	}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
