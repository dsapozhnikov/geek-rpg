package com.geek.rpg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RpgGame extends Game {
    private SpriteBatch batch;
    private AbstractUnit.GameScreen gameScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameScreen = new AbstractUnit.GameScreen(batch);
        setScreen(gameScreen);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime()*1.8f;
        this.getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameScreen.dispose();
    }
}