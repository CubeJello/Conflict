package com.cubejello.conflict.occupants;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cubejello.conflict.enums.OccupantType;

/**
 * Created by Jake on 8/13/2017.
 */

public class Occupant {
    Sprite sprite;
    OccupantType type;
    Vector2 position;

    public OccupantType getType() {
        return type;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
