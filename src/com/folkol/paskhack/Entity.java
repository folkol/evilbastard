package com.folkol.paskhack;

import org.newdawn.slick.Animation;

public class Entity {
    public float x, y;
    protected float dx, dy;
    public int width = 32, height = 32;
    protected float maxspeed;
    protected Animation currentAnimation;

    Direction direction;
    private Scene currentScene;

    public Entity(Scene scene) {
        currentScene = scene;
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
        x = attemptedX;
    }

    private void moveY(int delta) {
        float attemptedY = y + dy * delta;
        y = attemptedY;
    }

    public void render(int screenPosX, int screenPosY) {
        currentAnimation.draw(x - screenPosX - width, y - screenPosY - height);
    }
}
