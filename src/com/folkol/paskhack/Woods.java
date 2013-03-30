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

public class Woods extends Scene {
    private Scene nextScene;

    public Woods() throws SlickException {
        map = new TiledMap("/maps/woods.tmx");
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
            if ("monster".equals(objectType)) {
                Monster monster = new Monster(this);
                monster.setX(objectX);
                monster.setY(objectY);
                entities.add(monster);
            } else if ("hero".equals(objectType)) {
                hero = new Hero(this);
                hero.setX(objectX);
                hero.setY(objectY);
                entities.add(hero);
            } else if ("tree".equals(objectType)) {
                Tree tree = new Tree(this);
                tree.setX(objectX);
                tree.setY(objectY);
                entities.add(tree);
            } else {
                System.out.println("Unknown object type found");
            }
        }
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
        screenPosX = (int) (hero.getX() - 400);
        screenPosY = (int) (hero.getY() - 300);
        if (screenPosX < 0) {
            screenPosX = 0;
        }
        if (screenPosY < 0) {
            screenPosY = 0;
        }
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
        int numHostiles = 0;
        for (Entity e : entities) {
            if (!e.equals(hero) && e.isHostile()) {
                numHostiles++;
            }
        }
        return numHostiles;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        if (!hero.isAlive()) {
            g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            g.fillRect(0, 0, 800, 600);
        }
    }
}
