package com.folkol.paskhack;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

abstract public class Scene {
    public TiledMap map;
    public List<Entity> entities = new ArrayList<Entity>();

    abstract public void update(GameContainer gc, int delta);
    abstract public void render(GameContainer gc, Graphics g);

}
