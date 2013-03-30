package com.folkol.paskhack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Monster extends Entity {
    private Animation walk;
    private Animation stand;
    private Animation attack;
    private Sound miss;
    private Sound hit;
    private long nextAction;
    private Animation dead;
    public float homeX, homeY;

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
        currentAnimation = stand;
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (health < 0) {
            currentAnimation = dead;
            return;
        }
        if (nextAction > System.currentTimeMillis()) {
            return;
        }

        float distance = distance(currentScene.hero);
        float homeDistance = (float) Math.sqrt(Math.pow(x - homeX, 2) + Math.pow(y - homeY, 2));

        dx = dy = 0;
        if(distance > 50 && distance < 250 && homeDistance < 400) {
            float signX = currentScene.hero.x - x;
            float signY = currentScene.hero.y - y;
            dx = Math.signum(signX) * maxspeed;
            dy = Math.signum(signY) * maxspeed;
        } else if (homeDistance > 20) {
            float signX = homeX - x;
            float signY = homeY - y;
            dx = (float) (Math.signum(signX) * maxspeed * 0.5);
            dy = (float) (Math.signum(signY) * maxspeed * 0.5);
        }
        move(delta);
        currentAnimation = stand;
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
}
