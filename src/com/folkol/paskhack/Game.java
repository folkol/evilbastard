package com.folkol.paskhack;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame {

    private TiledMap tiledMap;

    public Game() {
        super("Hello World");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        tiledMap = new TiledMap("/maps/woods.tmx");
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        tiledMap.render(0, 0);
        g.drawString("Hej världen", 100, 100);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setVSync(true);
        app.setDisplayMode(800, 600, false);
        // app.setShowFPS(false);
        app.start();

    }

}