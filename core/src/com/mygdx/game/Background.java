package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture texture;

    public Background() {
        this.texture = new Texture("background.png");

    }
    public void render (SpriteBatch batch) {
     batch.draw(texture,0,0);
    }
}
