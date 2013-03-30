package com.folkol.paskhack;

import java.util.Collections;
import java.util.Comparator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Intro extends Scene {
    private Music music;
    private Scene nextScene;

    public Intro() throws SlickException {
        map = new TiledMap("/maps/intro.tmx");
        music = new Music("/snd/woods.ogg");
        nextScene = this;
        reset();
    }

    public void reset() throws SlickException {
        finished = false;
        entities.clear();
        for (int i = 0; i < map.getObjectCount(0); i++) {
            String objectType = map.getObjectType(0, i);
            int objectX = map.getObjectX(0, i);
            int objectY = map.getObjectY(0, i);
            if ("wanderer".equals(objectType)) {
                Wanderer monster = new Wanderer(this);
                monster.setX(objectX);
                monster.setY(objectY);
                entities.add(monster);
            } else if ("hero".equals(objectType)) {
                hero = new Hero(this);
                hero.setX(objectX);
                hero.setY(objectY);
                entities.add(hero);
            } else {
                System.out.println("Unknown object type found");
            }
        }
        music.loop(1.0f, 0.1f);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            if (!checkWinConditions(gc)) {
                finished = true;
            }
            if(!checkLoseConditions(gc)) {
                finished = true;
            }
        }
        if(!checkWinConditions(gc)) {
            return;
        }
        if(!checkLoseConditions(gc)) {
            return;
        }
        hero.update(gc, delta);
        for (Entity e : entities) {
            e.update(gc, delta);
        }
        screenPosX = 0;
        screenPosY = 0;
        Collections.sort(entities, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {
                return (int) (e1.y - e2.y);
            }
        });
    }

    public boolean checkWinConditions(GameContainer gc) {
        if (monstersAlive() == 0) {
            hero.victory();
            return false;
        }
        return true;
    }

    public boolean checkLoseConditions(GameContainer gc) {
        if (hero.health <= 0) {
            return false;
        }
        return true;
    }

    private int monstersAlive() {
        int numAlive = 0;
        for (Entity e : entities) {
            if (!e.equals(hero) && e.isAlive()) {
                numAlive++;
            }
        }
        return numAlive;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        if (monstersAlive() == 0 || !hero.isAlive()) {
            g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            g.fillRect(0, 0, 800, 600);
        }
    }
}
