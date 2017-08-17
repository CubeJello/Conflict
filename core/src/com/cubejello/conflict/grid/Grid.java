package com.cubejello.conflict.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cubejello.conflict.enums.GridType;
import com.cubejello.conflict.occupants.Occupant;
import com.cubejello.conflict.occupants.Tree;
import com.cubejello.conflict.utils.Constants;
import com.cubejello.conflict.utils.RandomUtils;

/**
 * Created by Jake on 8/11/2017.
 */

public class Grid {
    private Array<GridSpace> gridSpaces;
    private Array<Occupant> occupants;
    private Vector2 chunkCoords;

    public void initialize(float width, float height, float spaceSize, Vector2 position) {
        gridSpaces = new Array<GridSpace>();
        occupants = new Array<Occupant>();
        this.chunkCoords = position;

        for(float y = 0; y < height; y += spaceSize) {
            for (float x = 0; x < width; x += spaceSize) {
                GridSpace gridSpace = new GridSpace(GridType.GRASS, x, y);
                if(RandomUtils.pseudoRandom(0, 1) < 0.01)
                    gridSpace.setType(GridType.WATER);

                for(GridSpace adjGridSpace : getAdjacentGridSpaces(gridSpace.getPosition())) {
                    if(adjGridSpace.getType() == GridType.WATER && RandomUtils.pseudoRandom(0, 1) < 0.5)
                        gridSpace.setType(GridType.WATER);
                }
                gridSpaces.add(gridSpace);
            }
        }
        fixWater();
        fixGridSpaces();
    }

    private void fixWater() {
        for(int i = 0; i < gridSpaces.size; i++) {
            if(gridSpaces.get(i).getType().equals(GridType.WATER)) {
                int i2 = 0;
                for (GridSpace adjGridSpace : getAdjacentGridSpaces(gridSpaces.get(i).getPosition())) {
                    if (adjGridSpace.getType() != GridType.WATER)
                        i2++;
                }
                if(i2 >= 4) {
                    gridSpaces.get(i).setType(GridType.GRASS);
                }
            }
        }
    }

    private void fixGridSpaces() {
        for(int i = 0; i < gridSpaces.size; i++) {
            /*if(gridSpaces.get(i).getType().equals(GridType.WATER)) {
                int i2 = 0;
                for (GridSpace adjGridSpace : getAdjacentGridSpaces(gridSpaces.get(i).getPosition())) {
                    if (adjGridSpace.getType() != GridType.WATER)
                        i2++;
                }
                if(i2 >= 4) {
                    gridSpaces.get(i).setType(GridType.GRASS);
                }
            } else */if(!gridSpaces.get(i).getType().equals(GridType.WATER)) {
                for(GridSpace gridSpace : getAdjacentGridSpaces(gridSpaces.get(i).getPosition())) {
                    if(gridSpace.getType().equals(GridType.WATER)) {
                        gridSpaces.get(i).setType(GridType.SAND);
                    }

                }
                if(gridSpaces.get(i).getType().equals(GridType.GRASS) && RandomUtils.pseudoRandom(0, 1) < 0.1) {
                    Tree tree = new Tree(gridSpaces.get(i).getPosition());
                    occupants.add(tree);
                }
            }
        }

        for(Occupant occupant : occupants) {
            gridSpaceAtPos(occupant.getPosition()).setOccupied(true);
        }
    }

    public Array<Sprite> getSprites() {
        Array<Sprite> spritesToReturn = new Array<Sprite>();

        for(GridSpace gridSpace : gridSpaces) {
            spritesToReturn.add(gridSpace.getSprite());
        }
        for(Occupant occupant : occupants) {
            spritesToReturn.add(occupant.getSprite());
        }
        return spritesToReturn;
    }

    private Array<GridSpace> getAdjacentGridSpaces(Vector2 targetPos) {
        Array<GridSpace> gridSpacesToReturn = new Array<GridSpace>();

        for(GridSpace gridSpace : gridSpaces) {
            if(gridSpace.getPosition().y == targetPos.y) {
                if (gridSpace.getPosition().x - Constants.SPACE_SIZE == targetPos.x || gridSpace.getPosition().x + Constants.SPACE_SIZE == targetPos.x)
                    gridSpacesToReturn.add(gridSpace);
            } else if(gridSpace.getPosition().x == targetPos.x) {
                if(gridSpace.getPosition().y - Constants.SPACE_SIZE == targetPos.y || gridSpace.getPosition().y + Constants.SPACE_SIZE == targetPos.y)
                    gridSpacesToReturn.add(gridSpace);
            }
        }

        return gridSpacesToReturn;
    }

    public Vector2 getChunkCoords() {
        return chunkCoords;
    }

    public GridSpace gridSpaceAtPos(Vector2 position) {
        for(GridSpace gridSpace : gridSpaces) {
            if(gridSpace.getPosition().equals(position))
                return gridSpace;
        }
        return null;
    }

    public Array<GridSpace> getGridSpaces() {
        return gridSpaces;
    }

    public Vector2 randomSpace() {
        return gridSpaces.random().getPosition();
    }

    public void dispose() {
        for(GridSpace gridSpace : gridSpaces) {
            gridSpace.dispose();
        }
    }
}
