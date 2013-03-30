package com.folkol.paskhack;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Hero extends Entity {
    private Animation walk;
    private Animation stand;
    private Animation attack;
    private Sound miss;
    private Sound hit;
    private Sound deathSound;
    private long nextAction;
    Random rnd = new Random();
    private Animation victory;
    private Animation death;

    public Hero(Scene scene) throws SlickException {
        super(scene);
        SpriteSheet hero = new SpriteSheet("/gfx/hero.png", 32, 64);
        stand = new Animation(hero, 0, 0, 0, 0, true, 250, true);
        walk = new Animation(hero, 1, 0, 2, 0, true, 100, true);
        victory = new Animation(hero, 4, 0, 5, 0, true, 500, true);
        death = new Animation(hero, 6, 0, 7, 0, true, 500, true);
        death.stopAt(1);
        Image i1 = hero.getSprite(0, 0);
        Image i2 = hero.getSprite(3, 0);
        attack = new Animation(new Image[] { i1, i2 }, 100);
        setX(200);
        setY(300);
        currentAnimation = stand;
        maxspeed = 0.1f;
        miss = new Sound("/snd/attack_miss_0.wav");
        hit = new Sound("/snd/attack_hit_shield.wav");
        deathSound = new Sound("/snd/death.wav");
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (nextAction < System.currentTimeMillis()) {
            currentAnimation = stand;
        }
        if (gc.getInput().isKeyDown(Input.KEY_F)) {
            takeDamage(666);
        }

        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            dx = -maxspeed;
            if (nextAction < System.currentTimeMillis()) {
                currentAnimation = walk;
            }
            direction = Direction.LEFT;
        }
        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            dx = maxspeed;
            if (nextAction < System.currentTimeMillis()) {
                currentAnimation = walk;
            }
            direction = Direction.RIGHT;
        }
        if (gc.getInput().isKeyDown(Input.KEY_W)) {
            dy = -maxspeed;
            if (nextAction < System.currentTimeMillis()) {
                currentAnimation = walk;
            }
            direction = Direction.UP;
        }
        if (gc.getInput().isKeyDown(Input.KEY_S)) {
            dy = maxspeed;
            if (nextAction < System.currentTimeMillis()) {
                currentAnimation = walk;
            }
            direction = Direction.DOWN;
        }
        move(delta);
        if (nextAction > System.currentTimeMillis()) {
            return;
        }
        dx = dy = 0;
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
            attack();
        }
    }

    private void attack() {
        currentAnimation = attack;
        attack.restart();
        nextAction = System.currentTimeMillis() + 200;
        boolean hitSomething = false;
        for (Entity e : currentScene.entities) {
            if (!e.equals(this) && e.isAlive() && distance(e) < 50) {
                e.takeDamage(10 + rnd.nextInt(10));
                hitSomething = true;
                hit.play();
            }
        }
        if (!hitSomething) {
            miss.play();
        }
    }

    public void victory() {
        currentAnimation = victory;
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if(health <= 0) {
            deathSound.play();
            currentAnimation = death;
        }
    }
}
