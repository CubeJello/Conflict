package com.cubejello.conflict.occupants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.cubejello.conflict.enums.OccupantType;
import com.cubejello.conflict.utils.Constants;

/**
 * Created by Jake on 8/13/2017.
 */

public class Tree extends Occupant{

    public Tree(Vector2 position) {
        Texture treeTexture = new Texture(Gdx.files.internal("tree.png"));
        sprite = new Sprite(treeTexture);
        sprite.setSize(Constants.SPACE_SIZE, Constants.SPACE_SIZE);
        sprite.setPosition(position.x, position.y);
        this.position = new Vector2(position);
        type = OccupantType.TREE;
    }
}
