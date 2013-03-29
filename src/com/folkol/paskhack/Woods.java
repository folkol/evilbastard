package com.folkol.paskhack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Woods extends Scene {
    private Hero hero;
    private int screenPosX, screenPosY;
    private Music music;

    public Woods() throws SlickException {
        map = new TiledMap("/maps/woods.tmx");
        hero = new Hero();
        music = new Music("/snd/woods.ogg");
        music.loop(1.0f, 0.2f);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        hero.update(gc, delta);
        screenPosX = hero.getX();
        screenPosY = hero.getY();
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        map.render(0, 0);
        hero.render(200, 200);
    }
}
