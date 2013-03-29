package com.folkol.paskhack;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame {

    private TiledMap tiledMap;
    private Scene woods;
    private Scene currentScene;

    public Game() {
        super("Hello World");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        woods = new Woods();
        currentScene = woods;
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        currentScene.update(gc, delta);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        currentScene.render(gc, g);
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