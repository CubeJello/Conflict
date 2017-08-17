package com.cubejello.conflict.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cubejello.conflict.enums.GridType;
import com.cubejello.conflict.utils.Constants;

/**
 * Created by Jake on 8/12/2017.
 */

public class GridSpace {
    private GridType type;
    private Vector2 position;
    private Sprite sprite;
    private Texture grassTexture = new Texture(Gdx.files.internal("grass.jpg"));
    private Texture waterTexture = new Texture(Gdx.files.internal("water.jpg"));
    private Texture sandTexture = new Texture(Gdx.files.internal("sand.png"));
    private boolean occupied;

    public GridSpace(GridType type, float x, float y) {
        this.position = new Vector2(x, y);
        this.type = type;
        if(type.equals(GridType.WATER))
            sprite = new Sprite(waterTexture);
        else if(type.equals(GridType.GRASS))
            sprite = new Sprite(grassTexture);
        else if(type.equals(GridType.SAND))
            sprite = new Sprite(sandTexture);
        sprite.setSize(Constants.SPACE_SIZE, Constants.SPACE_SIZE);
        sprite.setPosition(position.x, position.y);
        if(type.equals(GridType.WATER))
            occupied = true;
        else
            occupied = false;
    }

    public void setType(GridType type) {
        this.type = type;
        if(type.equals(GridType.WATER))
            sprite = new Sprite(waterTexture);
        else if(type.equals(GridType.GRASS))
            sprite = new Sprite(grassTexture);
        else if(type.equals(GridType.SAND))
            sprite = new Sprite(sandTexture);
        sprite.setSize(Constants.SPACE_SIZE, Constants.SPACE_SIZE);
        sprite.setPosition(position.x, position.y);
        if(type.equals(GridType.WATER))
            occupied = true;
        else
            occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public GridType getType() {
        return type;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        sandTexture.dispose();
        waterTexture.dispose();
        grassTexture.dispose();
    }
}
