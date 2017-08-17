package com.cubejello.conflict.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cubejello.conflict.utils.Constants;

/**
 * Created by Jake on 8/15/2017.
 */

public class Player extends Actor{
    private Sprite sprite;
    private Vector2 chunkCoords;

    public Player(Vector2 position) {
        Texture texture = new Texture(Gdx.files.internal("player.png"));
        sprite = new Sprite(texture);
        sprite.setSize(Constants.SPACE_SIZE, Constants.SPACE_SIZE);
        this.position = position;
        chunkCoords = new Vector2(0, 0);
    }

    public Vector2 getChunkCoords() {
        return chunkCoords;
    }

    public void setChunkCoords(Vector2 chunkCoords) {
        this.chunkCoords = chunkCoords;
    }

    public void update() {
        if(sprite.getX() != position.x || sprite.getY() != position.y) {
            sprite.setPosition(position.x, position.y);
            System.out.println(position);
        }
        handleInput();
    }

    public Sprite getSprite() {
        return sprite;
    }
}
