package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Monster extends AbstractUnit {


    public Monster() {
        this.texture = new Texture("charSkeleton.tga");
        this.name = "Skelet";
        this.maxHealth= 50;
        this.health = this.maxHealth;
        this.level = 1;
        this.strenght = 15;
        this.agility = 5;
        this.endurance = 5;
        this.intilligence = 0;
        this.defense = 1;
        this.flip = true;
        this.hpFrame = new Texture("frame.png");
        this.hpLength = new Texture("hpLength.png");


    }
//    public void render(SpriteBatch batch) {
//        batch.draw(hpFrame,position.x,position.y+175,0,0,104,24);
//        batch.draw(hpLength,position.x,position.y+175,0,0,102,22);
//    }
}



