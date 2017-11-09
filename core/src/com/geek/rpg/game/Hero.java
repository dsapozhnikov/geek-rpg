package com.geek.rpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero extends AbstractUnit {
    public Hero(GameScreen game, Vector2 position) {
        super(game, position, new Texture("cenarius.png"));
        this.name = "Alexander";
        this.maxHp = 100;
        this.hp = this.maxHp;
        this.level = 100;
        this.strength = 100;
        this.dexterity = 100;
        this.endurance = 10;
        this.spellpower = 10;
        this.defence = 5;
        this.flip = false;
    }
}
