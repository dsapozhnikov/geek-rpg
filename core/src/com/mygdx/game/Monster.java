package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Monster extends AbstractUnit {


    public Monster() {
        this.texture = new Texture("charSkeleton.tga");
        this.name = "Skelet";
        this.maxHealth= 10;
        this.health = this.maxHealth;
        this.level  =1;
        this.strenght = 5;
        this.agility = 5;
        this.endurance = 5;
        this.intilligence = 0;
        this.defense = 1;
        this.flip = true;

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,position.x,position.y,0,0,texture.getWidth(),texture.getHeight(),1,1,0,0,0,texture.getWidth(),texture.getHeight(),true,false

        );
    }
}
