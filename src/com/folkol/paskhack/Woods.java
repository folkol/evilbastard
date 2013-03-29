package com.folkol.paskhack;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Woods extends Scene {

    public Woods() throws SlickException {
        map = new TiledMap("/maps/woods.tmx");
    }
}
