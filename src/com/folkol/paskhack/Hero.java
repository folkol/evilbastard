package com.folkol.paskhack;

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
    private long nextAction;

    public Hero(Scene scene) throws SlickException {
        super(scene);
        SpriteSheet hero = new SpriteSheet("/gfx/hero.png", 32, 64);
        stand = new Animation(hero, 0, 0, 0, 0, true, 250, true);
        walk = new Animation(hero, 1, 0, 2, 0, true, 100, true);
        Image i1 = hero.getSprite(0, 0);
        Image i2 = hero.getSprite(3, 0);
        attack = new Animation(new Image[] { i1, i2 }, 100);
        setX(200);
        setY(300);
        currentAnimation = stand;
        maxspeed = 0.2f;
        miss = new Sound("/snd/attack_miss_0.wav");
        hit = new Sound("/snd/attack_hit_shield.wav");
    }

    public void update(GameContainer gc, int delta) {
        if (nextAction > System.currentTimeMillis()) {
            return;
        }
        dx = dy = 0;
        currentAnimation = stand;
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
            currentAnimation = attack;
            hit.play();
            attack.restart();
            nextAction = System.currentTimeMillis() + 200;
        }
        if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
            miss.play();
            currentAnimation = attack;
            attack.restart();
            nextAction = System.currentTimeMillis() + 200;
        }
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            dx = -maxspeed;
            currentAnimation = walk;
            direction = Direction.LEFT;
        }
        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            dx = maxspeed;
            currentAnimation = walk;
            direction = Direction.RIGHT;
        }
        if (gc.getInput().isKeyDown(Input.KEY_W)) {
            dy = -maxspeed;
            currentAnimation = walk;
            direction = Direction.UP;
        }
        if (gc.getInput().isKeyDown(Input.KEY_S)) {
            dy = maxspeed;
            currentAnimation = walk;
            direction = Direction.DOWN;
        }
        move(delta);
    }
}
