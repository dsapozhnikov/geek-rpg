package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.Random;


public abstract class AbstractUnit extends HpLevelBar {
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

    public AbstractUnit(Vector2 position, Texture texture) {
        this.position=position;
        this.texture= texture;
        this.rect = new Rectangle(position.x, position.y, texture.getWidth(),texture.getHeight());
    }

    protected Vector2 position;
    protected boolean flip;
    protected float attackAction;
    protected float takeDamageAction;
    protected static Label.LabelStyle labelStyle =  new Label.LabelStyle(new BitmapFont(), Color.WHITE );
//    protected static Label.LabelStyle labelStyleforMIss =  new Label.LabelStyle(new BitmapFont(),Color. );
    protected Label hpLabel;
    protected Label missLabel;
    protected Random random;



    public void render(SpriteBatch batch) {
        float dt = Gdx.graphics.getDeltaTime();
        float dx = (200f * (float) Math.sin((1.0f - attackAction) * 3.14f));

        this.hpLabel.setText(""+health);


        if (takeDamageAction > 0) {
            missLabel.draw(batch,(float)Math.sin((1.0f-takeDamageAction) * 3.14f));
            batch.setColor(1f, 1f - takeDamageAction, 1f - takeDamageAction, 1f);
            missLabel.setFontScale(2f,2f);

            missLabel.setText("Hit!");
            missLabel.setY(position.y+210+dt);
            missLabel.setX(position.x);
           ;

        }

        if (flip) dx *= -1;

        // batch.draw(texture,position.x+dx,position.y,0,0,texture.getWidth(),texture.getHeight(),1,1,0,0,0,texture.getWidth(),texture.getHeight(),flip,false
        //             );
        batch.draw(hpFrame, position.x + dx, position.y + 175, 0, 0, 104, 24);

        batch.draw(hpLength, position.x + 2f + dx, position.y + 176, 0, 0, 101, 22, (float) health / maxHealth, 1, 0, 0, 0, 104, 22, false, false);
        hpLabel.setY(position.y+187);
        hpLabel.setX(position.x+41f+dx);
        hpLabel.draw(batch,150);


        if (health != 0) {
            batch.draw(texture, position.x + dx, position.y, 0, 0, texture.getWidth(), texture.getHeight(), 1, 1, 0, 0, 0, texture.getWidth(), texture.getHeight(), flip, false
            );
            batch.setColor(1f, 1f, 1f, 1f);

        } else {

            batch.draw(texture, position.x + dx, position.y, 42, 45, texture.getWidth(), texture.getHeight(), 1, 1, 250, 0, 0, texture.getWidth(), texture.getHeight(), flip, false
            );

            }

           // batch.draw(hpFrame, position.x + dx, position.y + 175, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, false, false);
            // killing monstesrs!

//        batch.draw(hpLength,position.x,position.y+150,0,0,);
    }

    public Rectangle getRect() {
        return rect;
    }

    public void update(float dt) {


        if (takeDamageAction > 0) {
            takeDamageAction -= 1 * dt;
        }
        if (attackAction > 0) {
            attackAction -=  3 * dt;

        }

    }


    public void setPosition(Vector2 position) {
        this.position = position;
        this.rect = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());


    }

    public void takeDamage(int damage) {
        this.takeDamageAction = 1.0f;
        health -= damage;
        if (health - damage < 0) {
            health = 0;
        }
        System.out.println("damage " + damage);
        System.out.println("health " + health);
    }

    public void meleeAttack(AbstractUnit enemy) {
        this.attackAction = 1.0f;

        int dmg = this.strenght - enemy.defense;
        if (dmg < 0) {
            dmg = 0;
        }
        enemy.takeDamage(dmg);

    }
    public void missTheHit(int dex) {

        int chanceToMiss = random.nextInt(101)*dex;
        int randomValue = random.nextInt(1001);

        //не успел доделать.. :)
    }
}