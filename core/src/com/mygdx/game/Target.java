package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Target {

    private float x;
    private float y;
    private boolean hit;
    private boolean active;
    private final float scale;
    private final Texture texture;

    public Target() {
        this.texture = new Texture("target.png");
        this.x = new Random().nextInt(Gdx.graphics.getWidth() - texture.getWidth());
        this.y = new Random().nextInt(Gdx.graphics.getHeight() - texture.getHeight());
        this.hit = false;
        this.active = true;
        this.scale = 2.0f;
    }

    public void render(SpriteBatch batch) {
        if (!hit) {
            batch.draw(texture, x, y, 0, 0, texture.getWidth(), texture.getHeight(), scale, scale, 0, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        } else {
            active = false;
        }

    }

    public void update() {
        if (!active) {
            this.x = new Random().nextInt(Gdx.graphics.getWidth() - texture.getWidth());
            this.y = new Random().nextInt(Gdx.graphics.getHeight() - texture.getHeight());
            this.active = true;
            this.hit = false;
        }
    }

    public void dispose() {
        texture.dispose();
    }

    public boolean isHit(float x, float y) {
        hit = x >= this.x && x <= this.x + texture.getWidth() &&
                y >= this.y && y <= this.y + texture.getHeight();
        return hit;
    }
}
