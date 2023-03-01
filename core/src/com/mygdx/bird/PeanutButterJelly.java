package com.mygdx.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

public class PeanutButterJelly extends Actor {
    Rectangle bounds;
    AssetManager assetManager;
    private float initialY;
    private long startTime; // agregar esta línea
    float speedx;
    float speedy;

    public PeanutButterJelly(){
        bounds = new Rectangle();
        setVisible(true);
        initialY = getY();
        startTime = TimeUtils.nanoTime(); // inicializar startTime con el valor actual
    }

    @Override
    public void act(float delta) {
        moveBy(-200 * delta, 0); // mueve la banana hacia la izquierda
        bounds.set(getX(), getY(), getWidth(), getHeight());

        // Actualiza la posición vertical
        float newY = initialY + 190 * MathUtils.sinDeg(360 * (TimeUtils.nanoTime() - startTime) / 1000000000f);
        if (newY < 0) { // Si la nueva posición está debajo del límite inferior
            newY = 0; // Mueve el objeto hasta el límite inferior
        } else if (newY > Gdx.graphics.getHeight() - getHeight()) { // Si la nueva posición está sobre el límite superior
            newY = Gdx.graphics.getHeight() - getHeight(); // Mueve el objeto hasta el límite superior
        }
        setY(newY);

        if(!isVisible())
            setVisible(true);
        if (getX() < -64)
            remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(assetManager.get("peanut_butter.png", Texture.class), getX(), getY());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public float getSpeedx() {
        return speedx;
    }

    public void setSpeedx(float speedx) {
        this.speedx = speedx;
    }

    public float getSpeedy() {
        return speedy;
    }

    public void setSpeedy(float speedy) {
        this.speedy = speedy;
    }
}
