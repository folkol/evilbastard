package com.folkol.paskhack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.tiled.TiledMap;

public class Entity {
    public float x, y;
    protected float dx, dy;
    public int width = 32, height = 32;
    protected float maxspeed;
    protected Animation currentAnimation;
    protected Scene currentScene;
    public float health = 100;

    public Entity(Scene scene) {
        currentScene = scene;
    }

    public void update(GameContainer gc, int delta) {

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void move(int delta) {
        moveY(delta);
        moveX(delta);
    }

    private void moveX(int delta) {
        float attemptedX = x + dx * delta;
        if (!collision(attemptedX, y)) {
            x = attemptedX;
        }
    }

    private void moveY(int delta) {
        float attemptedY = y + dy * delta;
        if (!collision(x, attemptedY)) {
            y = attemptedY;
        }
    }

    private boolean isWalkable(float attemptedX, float attemptedY) {
        boolean walkable = true;
        TiledMap map = currentScene.map;
        int worldWidth = map.getWidth() * map.getTileWidth();
        int worldHeight = map.getHeight() * map.getTileHeight();
        if (attemptedX < 0)
            walkable = false;
        if (attemptedX > worldWidth)
            walkable = false;
        if (attemptedY < 0)
            walkable = false;
        if (attemptedY > worldHeight)
            walkable = false;
        int destinationTileX = (int) (attemptedX / map.getTileWidth());
        int destinationTileY = (int) (attemptedY / map.getTileHeight());
        String tileWalkable = map.getTileProperty(map.getTileId(destinationTileX, destinationTileY, 0), "movable", "true");
        if (Boolean.parseBoolean(tileWalkable) == false) {
            walkable = false;
        }

        return walkable;
    }

    private boolean collision(float attemptedX, float attemptedY) {
        return !isWalkable(attemptedX, attemptedY) || !isWalkable(attemptedX + width, attemptedY) || !isWalkable(attemptedX, attemptedY + height)
                || !isWalkable(attemptedX + width, attemptedY + height);
    }

    public float distance(Entity e) {
        return (float) Math.sqrt(Math.pow(x - e.x, 2) + Math.pow(y - e.y, 2));
    }

    public void render(int screenPosX, int screenPosY) {
        currentAnimation.draw(x - screenPosX, y - screenPosY - height);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int amount) {
        health -= amount;
    }

    public boolean isHostile() {
        return false;
    }

}
