package com.folkol.paskhack;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Splash extends Scene {
    private Music music;
    private Scene nextScene;
    private Image image;
    float x, y;
    List<String> text = new ArrayList<String>();

    public Splash() throws SlickException {
        music = new Music("/snd/woods.ogg");
        image = new Image("/gfx/splash.png");

        reset();
    }

    public void reset() throws SlickException {
        x = 400;
        y = 850;
        finished = false;
        music.loop(1.0f, 0.1f);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            finished = true;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        image.draw();
    }

    @Override
    public boolean checkWinConditions(GameContainer gc) {
        return false;
    }
}
