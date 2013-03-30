package com.folkol.paskhack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tree extends Entity {

    public Tree(Scene scene) throws SlickException {
        super(scene);
        height = 224;
        width = 128;
        Image image = new Image("/gfx/tree.png");
        currentAnimation = new Animation(new Image[] {image}, 1000);
    }
}
