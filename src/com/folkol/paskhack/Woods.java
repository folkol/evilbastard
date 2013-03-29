package com.folkol.paskhack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Woods extends Scene {
    private Image hero;

    public Woods() throws SlickException {
        map = new TiledMap("/maps/woods.tmx");
        hero = new Image("/gfx/hero.png");
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        map.render(0, 0);
        hero.draw(200, 200);
    }
}
