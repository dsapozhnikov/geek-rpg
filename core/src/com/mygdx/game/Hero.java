package com.mygdx.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

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

        this.hpLength = new Texture("hpLength.png");
        this.hpFrame = new Texture("frame.png");
        this.hpLabel = new Label("",labelStyle);
        this.missLabel = new Label("", labelStyle);


    }

//   public void render(SpriteBatch batch) {
//       batch.draw(hpFrame,position.x,position.y+175,0,0,104,24);
//       batch.draw(hpLength,position.x,position.y+175,0,0,102,22);
//    }
}