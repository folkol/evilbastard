package com.folkol.paskhack;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Wanderer extends Entity {
    private enum Phase {
        IDLE, WALK_TO_HERO, SPEAK, WALK_BACK, STAY
    }

    private static final int CLOSE_ENOUGH_TO_HOME = 5;
    private static final int AGGRO_RANGE = 250;
    private static final int PREFERRED_FIGHTING_DISTANCE = 40;
    private Animation walk;
    private Animation stand;
    private long nextAction;
    private Animation dead;
    public float homeX, homeY;
    private Random rnd = new Random();
    private Animation speak;
    private Phase phase = Phase.IDLE;
    private long beganSpeech;
    private long speechLength = 7000;
    private boolean showSpeechBubble;
    private Image speechBubble;
    private Sound speech;

    public Wanderer(Scene scene) throws SlickException {
        super(scene);
        SpriteSheet hero = new SpriteSheet("/gfx/wanderer.png", 32, 64);
        stand = new Animation(hero, 0, 0, 0, 0, true, 250, true);
        walk = new Animation(hero, 0, 0, 1, 0, true, 100, true);
        speak = new Animation(hero, 2, 0, 3, 0, true, 500, true);
        speech = new Sound("/snd/speech.wav");
        setX(200);
        setY(250);
        maxspeed = 0.05f;
        currentAnimation = stand;
        speechBubble = makeSpeechBubble();
    }

    private Image makeSpeechBubble() throws SlickException {
        Image image = new Image(350, 100);
        Graphics graphics = image.getGraphics();
        graphics.clear();
        graphics.setColor(org.newdawn.slick.Color.white);
        graphics.fillRoundRect(0, 0, 350, 100, 10);
        graphics.setColor(org.newdawn.slick.Color.black);
        graphics.drawRoundRect(0, 0, 350, 100, 10);
        graphics.setColor(org.newdawn.slick.Color.black);

        graphics.drawString("The Evil Bastard took your beer!", 20, 20);
        graphics.drawString("You must kill him, but first you", 20, 40);
        graphics.drawString("will have to spank all his bitches.", 20, 60);

        graphics.flush();
        return image;
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
        float distance = distance(currentScene.hero);
        float homeDistance = (float) Math.sqrt(Math.pow(x - homeX, 2) + Math.pow(y - homeY, 2));
        dx = dy = 0;
        switch (phase) {
        case IDLE:
            if (distance < AGGRO_RANGE) {
                phase = Phase.WALK_TO_HERO;
                currentAnimation = walk;
            }
            break;
        case WALK_TO_HERO:
            if (distance > PREFERRED_FIGHTING_DISTANCE) {
                float signX = currentScene.hero.x - x;
                float signY = currentScene.hero.y - y;
                dx = Math.signum(signX) * maxspeed;
                dy = Math.signum(signY) * maxspeed;
                currentAnimation = walk;
            } else {
                phase = Phase.SPEAK;
                currentAnimation = speak;
                showSpeechBubble = true;
                beganSpeech = System.currentTimeMillis();
                speech.play();
            }
            break;
        case SPEAK:
            if (System.currentTimeMillis() > beganSpeech + speechLength) {
                phase = Phase.WALK_BACK;
                currentAnimation = walk;
                showSpeechBubble = false;
            }
            break;
        case WALK_BACK:
            if (homeDistance > CLOSE_ENOUGH_TO_HOME) {
                float signX = homeX - x;
                float signY = homeY - y;
                dx = (float) (Math.signum(signX) * maxspeed);
                dy = (float) (Math.signum(signY) * maxspeed);
            } else {
                phase = Phase.STAY;
                currentAnimation = stand;
            }
            break;
        case STAY:
            break;
        }
        move(delta);
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
    public void render(int screenPosX, int screenPosY) {
        super.render(screenPosX, screenPosY);
        if (showSpeechBubble) {
            speechBubble.draw(x - 250, y - 150);
        }
    }
}
