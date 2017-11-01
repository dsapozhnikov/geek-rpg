package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.event.HierarchyEvent;
import java.util.LinkedList;
import java.util.List;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	//AbstractUnit unit;
	//Hero hero;
	//Monster monster;
	List<AbstractUnit>units = new LinkedList<AbstractUnit>();
	AbstractUnit currentUnit;
	private int heroIndex;
	private int monsterIndex;
	AbstractUnit a;
	
	@Override
	public void create () {

		batch = new SpriteBatch();
		background = new Background();
	//	hero = new Hero();
		

	//	monster = new Monster();
		units.add(new Monster());
		units.add(new Hero());
		units.add(new Monster());
		//hero.setPosition(new Vector2(400,200));
		//monster.setPosition(new Vector2(700,200));
		for (AbstractUnit a: units) {
			if (a instanceof Hero) {
				currentUnit=a;
			}
		}
		

	}

	@Override
	public void render () {

		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		for (AbstractUnit a: units) {
			a.render(batch);
		//			monster.render(batch);
		}
		
		batch.end();
	}
	public void update(float dt) {
		for (AbstractUnit a : units) {
			a.update(dt);
		}
		for (int i = 0; i <units.size(); i++) {
			if (currentUnit instanceof Hero) {
				a = currentUnit;
				if (units.get(i)instanceof Monster) {
					if (InputHandler.checkClickInRect(units.get(i).rect)) {
						currentUnit.meleeAttack(units.get(i));
						currentUnit=units.get(i);
					}
				}

			}if (InputHandler.checkClickInRect(a.rect)) {
				currentUnit.meleeAttack(a);
				currentUnit=a;
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

	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
