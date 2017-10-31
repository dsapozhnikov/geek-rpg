package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Monster {
    private Texture texture;
    private int health;
    private String name;
    private int maxHealth;
    private int level;

    private int strenght;
    private int agility;
    private int endurance;
    private int intilligence;

    private Vector2 position;

    public void setPosition(Vector2 position) {
        this.position = position;
    }

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

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,position.x,position.y
        );
    }
}
