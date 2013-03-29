package com.folkol.paskhack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Hero {
    private Animation walk;
    private Animation stand;
    private Animation attack;
    private Animation currentAnimation;
    int x, y;
    private float maxspeed;

    public Hero() throws SlickException {
        SpriteSheet hero = new SpriteSheet("/gfx/hero.png", 32, 64);
        stand = new Animation(hero, 0, 0, 0, 0, true, 250, true);
        walk = new Animation(hero, 1, 0, 2, 0, true, 100, true);

        Image i1 = hero.getSprite(0, 0);
        Image i2 = hero.getSprite(3, 0);
        attack = new Animation(new Image[] {i1, i2}, 100);
        x = 200;
        y = 200;

        currentAnimation = stand;
        maxspeed = 0.2f;
    }

    public void update(GameContainer gc, int delta) {
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            x -= maxspeed * delta;
            currentAnimation = walk;
        } else if (gc.getInput().isKeyDown(Input.KEY_D)) {
            x += maxspeed * delta;
            currentAnimation = walk;
        } else if (gc.getInput().isKeyDown(Input.KEY_W)) {
            y -= maxspeed * delta;
            currentAnimation = walk;
        } else if (gc.getInput().isKeyDown(Input.KEY_S)) {
            y += maxspeed * delta;
            currentAnimation = walk;
        } else if (gc.getInput().isKeyDown(Input.KEY_SPACE)) {
            currentAnimation = attack;
        } else {
            currentAnimation = stand;
        }
    }

    public void render(int i, int j) {
        currentAnimation.draw(this.x - i, this.y - j);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
