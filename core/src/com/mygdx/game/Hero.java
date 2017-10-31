package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class Hero extends AbstractUnit {

    public Hero() {
        this.texture = new Texture("charTank.tga");
        this.name = "Геральт";
        this.maxHealth= 100;
        this.health = this.maxHealth;
        this.level  =1;
        this.strenght = 10;
        this.agility = 10;
        this.endurance = 10;
        this.intilligence = 10;
        this.defense = 5;
        this.flip = false;

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,position.x,position.y
        );
    }
}