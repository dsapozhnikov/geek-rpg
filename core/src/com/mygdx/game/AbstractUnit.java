package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractUnit {
   protected Texture texture;
    protected int health;
    protected String name;
    protected int maxHealth;
    protected int level;
    protected Rectangle rect;
//primary stats
    protected int strenght;
    protected int agility;
    protected int endurance;
    protected int intilligence;

    // secondary stats

    protected int defense;



    protected Vector2 position;
    protected boolean flip;
    protected float

    public void render(SpriteBatch batch) {
        batch.draw(texture,position.x,position.y,0,0,texture.getWidth(),texture.getHeight(),1,1,0,0,0,texture.getWidth(),texture.getHeight(),flip,false

        );

    public Rectangle getRect() {
        return rect;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.rect = new Rectangle(position.x,position.y,texture.getWidth(),texture.getHeight());
    }
    public  void takeDamage (int damage) {
        health -= damage;
    }
    public void meleeAttack(AbstractUnit enemy) {
        int dmg  =this.strenght-enemy.defense;
        if (dmg <0) {
            dmg   =0;
        }
        enemy.takeDamage(dmg);

    }

}
