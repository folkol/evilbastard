package com.folkol.paskhack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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

    public Monster(Scene scene) throws SlickException {
        super(scene);
        SpriteSheet hero = new SpriteSheet("/gfx/monster.png", 32, 64);
        stand = new Animation(hero, 0, 0, 0, 0, true, 250, true);
        walk = new Animation(hero, 1, 0, 2, 0, true, 100, true);
        Image i1 = hero.getSprite(0, 0);
        Image i2 = hero.getSprite(3, 0);
        attack = new Animation(new Image[] { i1, i2 }, 100);
        setX(200);
        setY(250);
        maxspeed = 0.2f;
        miss = new Sound("/snd/attack_miss_0.wav");
        hit = new Sound("/snd/attack_hit_shield.wav");
        currentAnimation = stand;
    }

    public void update(GameContainer gc, int delta) {
        if(nextAction > System.currentTimeMillis()) {
            return;
        }
        boolean actionPerformed = false;
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
            currentAnimation = attack;
            hit.play();
            attack.restart();
            nextAction = System.currentTimeMillis() + 200;
            actionPerformed = true;
        }
        if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
            miss.play();
            currentAnimation = attack;
            attack.restart();
            nextAction = System.currentTimeMillis() + 200;
            actionPerformed = true;
        }
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            setX((int) (getX() - maxspeed * delta));
            currentAnimation = walk;
            direction = Direction.LEFT;
            actionPerformed = true;
        }
        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            setX((int) (getX() + maxspeed * delta));
            currentAnimation = walk;
            direction = Direction.RIGHT;
            actionPerformed = true;
        }
        if (gc.getInput().isKeyDown(Input.KEY_W)) {
            setY((int) (getY() - maxspeed * delta));
            currentAnimation = walk;
            direction = Direction.UP;
            actionPerformed = true;
        }
        if (gc.getInput().isKeyDown(Input.KEY_S)) {
            setY((int) (getY() + maxspeed * delta));
            currentAnimation = walk;
            direction = Direction.DOWN;
            actionPerformed = true;
        }

        if (actionPerformed == false) {
            currentAnimation = stand;
        }
    }

}
