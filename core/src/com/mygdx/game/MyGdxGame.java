package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.*;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mygdx.game.AbstractUnit.labelStyle;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	public  static final int NULL = 0;
	//AbstractUnit unit;
	//Hero hero;
	//Monster monster;
	private List<AbstractUnit>units;
	private AbstractUnit currentUnit;
	private int index = 0;



	
	@Override
	public void create () {
		units = new LinkedList<AbstractUnit>();
		batch = new SpriteBatch();
		background = new Background();
	//	hero = new Hero();
		

	//	monster = new Monster();
		units.add(new Monster());
		units.add(new Monster());
		units.add(new Monster());
		units.add(new Monster());
		units.add(new Hero());
		units.add(new Hero());
		units.add(new Hero());
		units.add(new Monster());
     	units.add(new Monster());
     	int heroPlacementIndex = 400;
     	int MonsterPLacementIndex =700;
     	int updateIndex = 100;
     	int Yplacementt = 200;
     	int YupdateIndex = 30;
		for (int i = 0; i <units.size(); i++) {

			if (units.get(i) instanceof Hero) {
				units.get(i).setPosition(new Vector2(heroPlacementIndex,Yplacementt));
				currentUnit=units.get(i);
				heroPlacementIndex+=updateIndex;
				Yplacementt+=YupdateIndex;
			}
			if (units.get(i) instanceof Monster) {
				units.get(i).setPosition(new Vector2(MonsterPLacementIndex,Yplacementt));
				MonsterPLacementIndex+=updateIndex;
				Yplacementt+=YupdateIndex;
			}

//			MonsterPLacementIndex+=updateIndex;
//			Yplacementt+=YupdateIndex;
		}

		//hero.setPosition(new Vector2(400,200));
		//monster.setPosition(new Vector2(700,200));

	}

	@Override
	public void render () {

		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		for (AbstractUnit a:units) {
			a.render(batch);
		}
		//			monster.render(batch);

		batch.end();
	}

	public void update(float dt) {
		for (AbstractUnit unit : units) {
			unit.update(dt);
		}

		currentUnit = units.get(index);

		if (currentUnit instanceof Hero) {
			for (AbstractUnit unit : units) {
				if (unit instanceof Monster) {
					if (InputHandler.checkClickInRect(unit.rect)) {
						if(index == units.size()-1) {index = 0;break;}
					    currentUnit.meleeAttack(unit);
						index++;
						if(index == units.size()) {index = 0;
							currentUnit = units.get(index);}


						if (unit.health<=0) {
							index++;
							currentUnit = units.get(index);
							currentUnit.meleeAttack(unit);
							unit.strenght=0;
							unit.defense=0;
						}
					    break;
					}
				}
			}
		}
		if (currentUnit instanceof Monster) {
			for (AbstractUnit unit : units) {
				if (unit instanceof Hero) {
					if (InputHandler.checkClickInRect(unit.rect)) {

						currentUnit.meleeAttack(unit);
						index++;
						if(index == units.size()) {index = 0;
						currentUnit = units.get(index);}

						if (unit.health<=0) {
							index++;
							currentUnit = units.get(index);
							currentUnit.meleeAttack(unit);
							//unit.attackAction=NULL;
							unit.strenght=0;
							unit.defense=0;

						}
						break;


					}
				}
			}
		}
	}

		//		monster.update(dt);
//		hero.update(dt);
//		if (currentUnit==hero) {
//			if (InputHandler.checkClickInRect(monster.rect)) {
//				hero.meleeAttack(monster);
//				currentUnit=monster;
//			}
//		}
//		if (InputHandler.checkClickInRect(hero.rect)) {
//			monster.meleeAttack(hero);
//			currentUnit=hero;
//		}


	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
