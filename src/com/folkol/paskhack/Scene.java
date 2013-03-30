package com.folkol.paskhack;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

abstract public class Scene {
    protected Boss boss;
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

        if(hero.health < 100) {
            renderHeroHealthbar(gc);
        }

        if(boss != null) {
            renderBossHealthbar(gc);
        }
    }

    private void renderHeroHealthbar(GameContainer gc) {
        Graphics graphics = gc.getGraphics();
        graphics.setColor(Color.lightGray);
        int healthBarX = 150;
        int healthBarY = 550;
        int healthBarWidth = 500;
        int healthBarHeight = 20;
        float heroHealthFactor = hero.health / 100.0f;
        if(heroHealthFactor < 0) {
            heroHealthFactor = 0;
        }
        graphics.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);
        graphics.setColor(Color.red);
        graphics.fillRect(healthBarX, healthBarY, healthBarWidth*heroHealthFactor, healthBarHeight);
        graphics.setColor(Color.white);
        graphics.drawRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);

    }

    private void renderBossHealthbar(GameContainer gc) {
        Graphics graphics = gc.getGraphics();
        graphics.setColor(Color.lightGray);
        int healthBarX = 150;
        int healthBarY = 50;
        int healthBarWidth = 500;
        int healthBarHeight = 20;
        float heroHealthFactor = boss.health / 1000.0f;
        if(heroHealthFactor < 0) {
            heroHealthFactor = 0;
        }
        graphics.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);
        graphics.setColor(Color.yellow);
        graphics.fillRect(healthBarX, healthBarY, healthBarWidth*heroHealthFactor, healthBarHeight);
        graphics.setLineWidth(2);
        graphics.setColor(Color.black);
        graphics.drawRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);

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
