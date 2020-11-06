package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import org.graalvm.compiler.loop.MathUtil;

public class Tank {
    private Texture texture;
    private Texture textureWeapon;
    private float x;
    private float y;
    private float speed;
    private float angle;
    private float angleWeapon;
    private Projectile projectile;
    private float scale;

    public Tank() {
        this.texture = new Texture("tank.png");
        this.textureWeapon = new Texture("weapon.png");
        this.projectile = new Projectile();
        this.x = 100.0f;
        this.y = 100.0f;
        this.speed = 240.0f;
        this.scale = 3.0f;
    }

    public void update(float dt, Target target) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += 90.0f * dt;
        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
//            angle -= 90.0f;
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
//            angle += 90.0f;
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {

            if (x + this.texture.getWidth() > Gdx.graphics.getWidth()) {
                x += Gdx.graphics.getWidth() - (x + this.texture.getWidth());
            } else if (x - this.texture.getWidth() < 0) {
                x += 0 - (x - this.texture.getWidth());
            } else {
                x += speed * MathUtils.cosDeg(angle) * dt;
            }

            if (y + this.texture.getHeight() > Gdx.graphics.getHeight()) {
                y += Gdx.graphics.getHeight() - (y + this.texture.getHeight());
            } else if (y - this.texture.getHeight() < 0) {
                y += 0 - (y - this.texture.getHeight());
            } else {
                y += speed * MathUtils.sinDeg(angle) * dt;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {

            //1
            if (x + this.texture.getWidth() > Gdx.graphics.getWidth()) {
                x -= (x + this.texture.getWidth() - Gdx.graphics.getWidth());
            } else if (x - this.texture.getWidth() < 0) {
                x -= 0 + (x - this.texture.getWidth());
            } else {
                x -= speed * MathUtils.cosDeg(angle) * dt * 0.2f;
            }

            //1
            if (y + this.texture.getHeight() > Gdx.graphics.getHeight()) {
                y -= (y + this.texture.getHeight()) - Gdx.graphics.getWidth();
            } else if (y - this.texture.getHeight() < 0) {
                y -= 0 - (this.texture.getHeight() - y);
            } else {
                y -= speed * MathUtils.sinDeg(angle) * dt * 0.2f;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            angleWeapon -= 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            angleWeapon += 90.0f * dt;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !projectile.isActive()) {
            projectile.shoot(x + 16 * scale * MathUtils.cosDeg(angle), y + 16* scale * MathUtils.sinDeg(angle), angle + angleWeapon);
        }
        if (projectile.isActive()) {
            projectile.update(dt, target);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - 20, y - 20, 20, 20, 40, 40, scale, scale, angle, 0, 0, 40, 40, false, false);
        batch.draw(textureWeapon, x - 20, y - 20, 20, 20, 40, 40, scale, scale, angle + angleWeapon, 0, 0, 40, 40, false, false);
        if (projectile.isActive()) {
            projectile.render(batch);
        }
    }

    public void dispose() {
        texture.dispose();
        projectile.dispose();
    }
}
