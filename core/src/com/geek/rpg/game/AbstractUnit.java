package com.geek.rpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.effects.DefenceStanceEffect;
import com.geek.rpg.game.effects.Effect;
import com.geek.rpg.game.effects.RegenerationEffect;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUnit {
    protected GameScreen game;
    protected Texture texture;
    protected Texture textureHpBar;
    protected String name;
    protected int hp;
    protected int maxHp;

    protected int level;

    protected Rectangle rect;

    // Primary Stats
    protected int strength;
    protected int dexterity;
    protected int endurance;
    protected int defence;
    protected int spellpower;

    protected Vector2 position;

    protected boolean flip;

    protected float attackAction;
    protected float takeDamageAction;

   protected EffectSystem effectSystem;


    public int getLevel() {
        return level;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isAlive() {
        return hp > 0;
    }
   // Texture texture1;

    public AbstractUnit(GameScreen game, Vector2 position, Texture texture) {
        this.effectSystem = new EffectSystem();
        this.game = game;
        this.position = position;
        this.texture = texture;
        this.rect = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());

        Pixmap pixmap = new Pixmap(90, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        pixmap.setColor(1, 1, 1, 1);
        pixmap.fillRectangle(2, 2, 86, 16);

//        Pixmap pixmap1 = new Pixmap(50,50,Pixmap.Format.RGBA8888);
//        pixmap1.setColor(0,0,0,0);
//        pixmap1.fill();
//        texture1 = new Texture(pixmap1);
        this.textureHpBar = new Texture(pixmap);
    }

//    public void setPosition(Vector2 position) {
//        this.position = position;
//        this.rect = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
//    }

    public void takeDamage(int dmg) {
        this.takeDamageAction = 1.0f;
        hp -= dmg;
    }

    public void render(SpriteBatch batch) {
      //  batch.draw(texture1,500,500);
        if (takeDamageAction > 0) {
            batch.setColor(1f, 1f - takeDamageAction, 1f - takeDamageAction, 1f);
        }
        float dx = (200f * (float) Math.sin((1f - attackAction) * 3.14f));
        if (flip) dx *= -1;
        float ang = 0;
        if (!isAlive()) ang = 90;
        batch.draw(texture, position.x + dx, position.y, 0, 0, texture.getWidth(), texture.getHeight(), 1, 1, ang, 0, 0, texture.getWidth(), texture.getHeight(), flip, false);
        batch.setColor(1f, 1f, 1f, 1f);
    }

    public void renderInfo(SpriteBatch batch, BitmapFont font) {
        batch.setColor(0.5f, 0, 0, 1);
        batch.draw(textureHpBar, position.x, position.y +this.texture.getHeight());
        batch.setColor(0, 1, 0, 1);
        batch.draw(textureHpBar, position.x, position.y +this.texture.getHeight(), 0, 0, (int) ((float) hp / (float) maxHp * textureHpBar.getWidth()), 20);
        batch.setColor(1, 1, 1, 1);
        font.draw(batch, String.valueOf(hp), position.x,position.y +this.texture.getHeight()+19, 90, 1, false);
    }

    public void update(float dt) {
        if (takeDamageAction > 0) {
            takeDamageAction -= dt;
        }
        if (attackAction > 0) {
            attackAction -= dt;
        }
    }

//    public void getTurn() {                                выенесено в класс системы эффектов
//        for (int i = effects.size() - 1; i >= 0; i--) {
//            effects.get(i).tick();
//            if (effects.get(i).isEnded()) {
//                effects.get(i).end();
//                effects.remove(i);
//            }
//        }
//    }

    public void heal(float percent) {
        int prevHp = hp;
        hp += maxHp * percent;
        if (hp > maxHp) {
            hp = maxHp;
        }
        game.getInfoSystem().addMessage("HP +" + (hp - prevHp), this, FlyingText.Colors.GREEN);
    }

    public void defenceStance(int rounds) {
        DefenceStanceEffect dse = new DefenceStanceEffect();
        dse.start(game.getInfoSystem(), this, rounds);
        //effects.add(dse);
        effectSystem.addEffect(dse);

    }

    public void regenerate(int rounds) {
        RegenerationEffect re = new RegenerationEffect();
        re.start(game.getInfoSystem(), this, rounds);
       //effects.add(re);
        effectSystem.addEffect(re);
    }

    public void meleeAttack(AbstractUnit enemy) {
        attackAction = 1.0f;
        if (!Calculator.isTargetEvaded(this, enemy)) {
            int dmg = Calculator.getMeleeDamage(this, enemy);
            if (Calculator.isCritical(this, enemy)) {                                    // добавмл логику критического удара
                int critical = Calculator.doCritical(dmg,this, enemy);
                enemy.takeDamage(critical);
                game.getInfoSystem().addMessage("CRITICAL!!! "+critical,enemy,FlyingText.Colors.RED);
            }
            enemy.takeDamage(dmg);
            game.getInfoSystem().addMessage("-" + dmg, enemy, FlyingText.Colors.RED);
        } else {
            game.getInfoSystem().addMessage("MISS", enemy, FlyingText.Colors.WHITE);
        }
    }

    public static class EffectSystem {    //  просто создал доп класс и вынес туда логику управления эффектами

       private  List<Effect>effectsList;

        public EffectSystem() {
          effectsList =  new ArrayList<Effect>();
        }
        public void addEffect(Effect effect) {
                   effectsList.add(effect);
            }
            public  void getTurn() {
                for (int i = 0; i < effectsList.size(); i++) {
                    effectsList.get(i).tick();
                    if (effectsList.get(i).isEnded()) {
                        effectsList.get(i).end();
                         effectsList.remove(i);

                    }
                }
            }
        }
//////////////////////////////////////////////////////////////////
    public static class GameScreen implements Screen {
        private SpriteBatch batch;
        private BitmapFont font;
        private Background background;

        private List<AbstractUnit> units;
        private Hero player;
        private int currentUnit;
        private int selectedUnit;
        private Texture textureSelector;
        private List<Button> btnGUI;
        private InfoSystem infoSystem;

        private float animationTimer;

        public boolean canIMakeTurn() {
            return animationTimer <= 0.0f;
        }

        public InfoSystem getInfoSystem() {
            return infoSystem;
        }

        public GameScreen(SpriteBatch batch) {
            this.batch = batch;
        }

        @Override
        public void show() {
            background = new Background();
            font = new BitmapFont(Gdx.files.internal("font.fnt"));
            infoSystem = new InfoSystem();
            textureSelector = new Texture("selector.png");
            units = new ArrayList<AbstractUnit>();
            player = new Hero(this, new Vector2(200, 200));
            units.add(player);
            units.add(new Monster(this, new Vector2(700, 200), player));
            units.add(new Monster(this, new Vector2(800, 340), player));
            units.add(new Monster(this, new Vector2(850, 250), player));
            units.add(new Monster(this, new Vector2(900, 200), player));
            units.add(new Monster(this, new Vector2(640, 250), player));
            units.add(new Monster(this, new Vector2(600, 300), player));
            currentUnit = 0;
            selectedUnit = 0;
            prepareButtons();
            animationTimer = 0.0f;
        }

        public boolean isHeroTurn() {
            return units.get(currentUnit) instanceof Hero;
        }

        public void prepareButtons() {
            btnGUI = new ArrayList<Button>();
            Button btnAttack = new Button("Attack", new Texture("newAttack.png"), new Rectangle(200, 20, 80, 80)) {
                @Override
                public void action() {
                    if (units.get(selectedUnit) instanceof Monster) {
                        player.meleeAttack(units.get(selectedUnit));
                        nextTurn();
                    }
                }
            };
            Button btnHeal = new Button("Heal", new Texture("heal.png"), new Rectangle(320, 22, 80, 80)) {
                @Override
                public void action() {
                    player.heal(0.15f);
                    nextTurn();
                }
            };
            Button btnDefence = new Button("Defence", new Texture("schit.png"), new Rectangle(430, 25, 80, 80)) {
                @Override
                public void action() {
                    player.defenceStance(1);
                    nextTurn();
                }
            };
            Button btnRegeneration = new Button("Regeneration", new Texture("regeneration.png"), new Rectangle(555, 23, 80, 80)) {
                @Override
                public void action() {
                    player.regenerate(3);
                    nextTurn();
                }
            };
            btnGUI.add(btnAttack);
            btnGUI.add(btnHeal);
            btnGUI.add(btnDefence);
            btnGUI.add(btnRegeneration);
        }

        public void nextTurn() {
            do {
                currentUnit++;
                if (currentUnit >= units.size()) {
                    currentUnit = 0;
                }
            } while (!units.get(currentUnit).isAlive());
            //units.get(currentUnit).getTurn();
            units.get(currentUnit).effectSystem.getTurn();
            animationTimer = 1.0f;
        }

        public void update(float dt) {
            if (animationTimer > 0.0f) {
                animationTimer -= dt;
            }
            for (int i = 0; i < units.size(); i++) {
                units.get(i).update(dt);
                if (InputHandler.checkClickInRect(units.get(i).getRect()) && units.get(i).isAlive()) {
                    selectedUnit = i;
                }
            }
            if (isHeroTurn() && canIMakeTurn()) {
                for (int i = 0; i < btnGUI.size(); i++) {
                    btnGUI.get(i).checkClick();
                }
            }
            if (!isHeroTurn()) {
                if (((Monster) units.get(currentUnit)).ai(dt)) {
                    nextTurn();
                }
            }
            infoSystem.update(dt);
        }

        @Override
        public void render(float delta) {
            update(delta);
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            background.render(batch);
            for (int i = units.size()-1; i>=0; i--) {
                if (currentUnit == i) {
                    batch.setColor(1, 1, 0, 0.8f);
                    batch.draw(textureSelector, units.get(i).getPosition().x, units.get(i).getPosition().y - 5);
                }
                if (isHeroTurn() && selectedUnit == i) {
                    batch.setColor(1, 0, 0, 0.8f);
                    batch.draw(textureSelector, units.get(i).getPosition().x, units.get(i).getPosition().y - 5);
                }
                batch.setColor(1, 1, 1, 1);
                units.get(i).render(batch);
                if (units.get(i).isAlive()) {
                    units.get(i).renderInfo(batch, font);
                }
            }
            if (isHeroTurn()) { //  && InputHandler.getY() < 100
                for (int i = 0; i < btnGUI.size(); i++) {
                    btnGUI.get(i).render(batch);
                }
            }
            infoSystem.render(batch, font);
            batch.end();
        }

        @Override
        public void resize(int width, int height) {
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void hide() {
        }

        @Override
        public void dispose() {
        }
    }
}
