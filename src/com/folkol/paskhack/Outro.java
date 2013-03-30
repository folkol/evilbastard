package com.folkol.paskhack;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Outro extends Scene {
    private Music music;
    private Scene nextScene;
    private Image image;
    float x, y;
    List<String> text = new ArrayList<String>();

    public Outro() throws SlickException {
        music = new Music("/snd/outro.ogg");
        image = new Image("/gfx/outro.png");
        nextScene = this;
        text.add("Congratulations!");
        text.add("");
        text.add("");
        text.add("You have finally killed the evil bastard.");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("Programmer:           Folkol");
        text.add("");
        text.add("");
        text.add("Sound artist:         Folkol");
        text.add("");
        text.add("");
        text.add("Graphics artist:      Folkol");
        text.add("");
        text.add("");
        text.add("Game designer:        Folkol");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("");
        text.add("        *** Glad pŒsk! :-) ***");

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
        if(y > -700) {
            y -= 0.05 * delta;
        }
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        image.draw();
        g.setColor(Color.white);
        for(int i = 0; i < text.size(); i++) {
            g.drawString(text.get(i), x, y + i * 20);
        }
    }
}
