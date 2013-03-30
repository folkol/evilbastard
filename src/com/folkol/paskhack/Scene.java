package com.folkol.paskhack;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

abstract public class Scene {
    public TiledMap map;
    public Hero hero;
    public List<Entity> entities = new ArrayList<Entity>();
    protected int screenPosX, screenPosY;
    protected boolean finished;
    private Scene nextScene;
    Music music;

    abstract public void update(GameContainer gc, int delta);

    public void render(GameContainer gc, Graphics g) {
        map.render(-screenPosX, -screenPosY);
        for (Entity e : entities) {
            e.render(screenPosX, screenPosY);
        }
    }

    public boolean finished() {
        return finished;
    }

    public void reset() throws SlickException {
    }

    public boolean checkWinConditions(GameContainer gc) {
        return true;
    }

    public Scene getNextScene() {
        return nextScene;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void start() {
        if (music != null) {
            music.loop(1.0f, 0.1f);
        }
    }
}
