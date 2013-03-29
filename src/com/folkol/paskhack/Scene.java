package com.folkol.paskhack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

public class Scene {
    protected TiledMap map;

    public void update(GameContainer gc, int delta) {
        // TODO Auto-generated method stub

    }

    public void render(GameContainer gc, Graphics g) {
        map.render(0, 0);
    }
}
