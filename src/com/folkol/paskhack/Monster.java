package com.folkol.paskhack;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Monster extends Entity {
    private static final int CLOSE_ENOUGH_TO_HOME = 5;
    private static final int AGGRO_RANGE  = 250;
    private static final int PREFERRED_FIGHTING_DISTANCE = 40;
    private Animation walk;
    private Animation stand;
    private Animation attack;
    private Sound miss;
    private Sound hit;
    private Sound death;
    private long nextAction;
    private Animation dead;
    public float homeX, homeY;
    private Random rnd = new Random();

    public Monster(Scene scene) throws SlickException {
        super(scene);
        SpriteSheet hero = new SpriteSheet("/gfx/monster.png", 32, 64);
        stand = new Animation(hero, 0, 0, 0, 0, true, 250, true);
        walk = new Animation(hero, 1, 0, 2, 0, true, 100, true);
        dead = new Animation(hero, 4, 0, 7, 0, true, 500, true);
        dead.stopAt(3);
        Image i1 = hero.getSprite(0, 0);
        Image i2 = hero.getSprite(3, 0);
        attack = new Animation(new Image[] { i1, i2 }, 100);
        setX(200);
        setY(250);
        maxspeed = 0.05f;
        miss = new Sound("/snd/attack_miss_0.wav");
        hit = new Sound("/snd/attack_hit_shield.wav");
        death = new Sound("/snd/death.wav");
        currentAnimation = stand;
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (health <= 0) {
            currentAnimation = dead;
            return;
        }
        if (nextAction > System.currentTimeMillis()) {
            return;
        }
        currentAnimation = stand;
        float distance = distance(currentScene.hero);
        float homeDistance = (float) Math.sqrt(Math.pow(x - homeX, 2) + Math.pow(y - homeY, 2));
        dx = dy = 0;
        if (distance > PREFERRED_FIGHTING_DISTANCE && distance < AGGRO_RANGE && currentScene.hero.isAlive()) {
            float signX = currentScene.hero.x - x;
            float signY = currentScene.hero.y - y;
            dx = Math.signum(signX) * maxspeed;
            dy = Math.signum(signY) * maxspeed;
            currentAnimation = walk;
        } else if (homeDistance > CLOSE_ENOUGH_TO_HOME) {
            float signX = homeX - x;
            float signY = homeY - y;
            dx = (float) (Math.signum(signX) * maxspeed * 0.5);
            dy = (float) (Math.signum(signY) * maxspeed * 0.5);
            currentAnimation = walk;
        }
        move(delta);
        if (distance(currentScene.hero) < 50) {
            attack();
        }
    }

    private void attack() {
        currentAnimation = attack;
        attack.restart();
        nextAction = System.currentTimeMillis() + 200;
        currentScene.hero.takeDamage(rnd .nextInt(10));
        hit.play();
    }

    @Override
    public void setX(float x) {
        homeX = x;
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        homeY = y;
        super.setY(y);
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (health <= 0) {
            death.play();
        }
    }

    @Override
    public boolean isHostile() {
        return isAlive();
    }
}
