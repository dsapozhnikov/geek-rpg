package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import javax.xml.soap.Text;

public abstract class AbstractUnit extends HpLevelBar{
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
    protected float attackAction;
    protected float takeDamageAction;


    public void render(SpriteBatch batch) {

        if (takeDamageAction>0) {
            batch.setColor(1f,1f-takeDamageAction,1f-takeDamageAction,1f);

        }
        float dx = (200f*(float) Math.sin((1f - attackAction)* 3.14f));
        if (flip)dx*= -1;


       // batch.draw(texture,position.x+dx,position.y,0,0,texture.getWidth(),texture.getHeight(),1,1,0,0,0,texture.getWidth(),texture.getHeight(),flip,false
     //             );
        batch.draw(hpFrame,position.x+dx,position.y+175,0,0,104,24);
        batch.draw(hpLength,position.x+2f+dx,position.y+176,0,0,101,22,(float)health / maxHealth,1,0,0,0,104,22,false,false);

        batch.setColor(1f,1f,1f,1f);
        if (health!=0) {
            batch.draw(texture,position.x+dx,position.y,0,0,texture.getWidth(),texture.getHeight(),1,1,0,0,0,texture.getWidth(),texture.getHeight(),flip,false
            );

        }else {
            batch.draw(texture,position.x+dx,position.y,0,0,texture.getWidth(),texture.getHeight(),1,1,230,0,0,texture.getWidth(),texture.getHeight(),flip,false
            );
            hpFrame. // killing monstesrrs!

        }


//        batch.draw(hpLength,position.x,position.y+150,0,0,);

    }

    public Rectangle getRect() {
        return rect;
    }
    public void update(float dt) {


        if (takeDamageAction>0) {
            takeDamageAction -= 3*dt;
        }
        if (attackAction>0) {
            attackAction -= (float)3*dt;

        }

    }


    public void setPosition(Vector2 position) {
        this.position = position;
        this.rect = new Rectangle(position.x,position.y,texture.getWidth(),texture.getHeight());
    }
    public  void takeDamage (int damage) {
        this.takeDamageAction=1.0f;
        health -= damage;
        if (health-damage<0) {
            health=0;
        }
        System.out.println("damage "+damage);
        System.out.println("health "+health);
    }
    public void meleeAttack(AbstractUnit enemy) {
        this.attackAction=1.0f;

        int dmg  =this.strenght-enemy.defense;
        if (dmg <0) {
            dmg   =0;
        }
        enemy.takeDamage(dmg);

    }

}
