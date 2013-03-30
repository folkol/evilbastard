package com.folkol.paskhack;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

abstract public class Scene {
    public TiledMap map;
    public Entity hero;
    public List<Entity> entities = new ArrayList<Entity>();
    protected int screenPosX, screenPosY;

    abstract public void update(GameContainer gc, int delta);

    public void render(GameContainer gc, Graphics g) {
        map.render(-screenPosX, -screenPosY);
        for (Entity e : entities) {
            e.render(screenPosX, screenPosY);
        }
    }

}
