package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class InputHandler {

    public static float getX() {
        return Gdx.input.getX();
    }

    public static float getY() {
        return 720 - Gdx.input.getY();
    }

    public static boolean checkClickInRect(Rectangle rect) {
        if (Gdx.input.justTouched()) {
            if (rect.contains(getX(), getY())) {
                return true;
            }
        }
        return false;
    }
}



