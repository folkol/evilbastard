package com.folkol.paskhack;

import java.util.Collections;
import java.util.Comparator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Woods extends Scene {
    private Hero hero;
    private Music music;

    public Woods() throws SlickException {
        map = new TiledMap("/maps/woods.tmx");
        hero = new Hero(this);
        entities.add(hero);
        for (int i = 0; i < map.getObjectCount(0); i++) {
            String objectType = map.getObjectType(0, i);
            int objectX = map.getObjectX(0, i);
            int objectY = map.getObjectY(0, i);
            if (objectType.equals("monster")) {
                Monster monster = new Monster(this);
                monster.setX(objectX);
                monster.setY(objectY);
                entities.add(monster);
            } else {
                System.out.println("Unknown object type found");
            }
        }
        music = new Music("/snd/woods.ogg");
        music.loop(1.0f, 0.1f);
    }

    @Override
    public void update(GameContainer gc, int delta) {
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
}
