package com.cubejello.conflict.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cubejello.conflict.grid.GridSpace;
import com.cubejello.conflict.utils.Constants;

/**
 * Created by Jake on 8/15/2017.
 */

public class Actor {
    Vector2 position;
    Array<GridSpace> adjGridSpaces;

    Actor() {
        adjGridSpaces = new Array<GridSpace>();
    }

    void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && canMoveInDir(1))
            position = new Vector2(position.x - Constants.SPACE_SIZE, position.y);
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && canMoveInDir(2))
            position = new Vector2(position.x + Constants.SPACE_SIZE, position.y);
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && canMoveInDir(3))
            position = new Vector2(position.x, position.y + Constants.SPACE_SIZE);
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && canMoveInDir(4))
            position = new Vector2(position.x, position.y - Constants.SPACE_SIZE);
    }

    public void updateAdjGridSpaces(Array<GridSpace> gridSpaces) {
        this.adjGridSpaces.clear();
        for(GridSpace gridSpace : gridSpaces) {
            if(gridSpace.getPosition().x == position.x - Constants.SPACE_SIZE && gridSpace.getPosition().y == position.y || gridSpace.getPosition().x == position.x + Constants.SPACE_SIZE && gridSpace.getPosition().y == position.y
                    || gridSpace.getPosition().x == position.x && gridSpace.getPosition().y == position.y + Constants.SPACE_SIZE || gridSpace.getPosition().x == position.x && gridSpace.getPosition().y == position.y - Constants.SPACE_SIZE)
                adjGridSpaces.add(gridSpace);
        }
    }

    boolean canMoveInDir(int dir) {
        for(GridSpace gridSpace : adjGridSpaces) {
            if(gridSpace.getPosition().equals(new Vector2(position.x - Constants.SPACE_SIZE, position.y))
                    && gridSpace.isOccupied() && dir == 1 || gridSpace.getPosition().equals(new Vector2(position.x + Constants.SPACE_SIZE, position.y)) &&
                    gridSpace.isOccupied() && dir == 2 || gridSpace.getPosition().equals(new Vector2(position.x, position.y + Constants.SPACE_SIZE)) &&
                    gridSpace.isOccupied() && dir == 3 || gridSpace.getPosition().equals(new Vector2(position.x, position.y - Constants.SPACE_SIZE)) &&
                    gridSpace.isOccupied() && dir == 4)
                return false;
        }
        return true;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }
}
