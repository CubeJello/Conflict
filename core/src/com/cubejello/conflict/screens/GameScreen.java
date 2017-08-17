package com.cubejello.conflict.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cubejello.conflict.actors.Player;
import com.cubejello.conflict.grid.Grid;
import com.cubejello.conflict.utils.Constants;

/**
 * Created by Jake on 8/11/2017.
 */

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Grid grid;
    private Array<Grid> savedGrids;

    private Player player;

    private boolean firstTime = true;

    @Override
    public void show() {
        batch = new SpriteBatch();
        savedGrids = new Array<Grid>();
        newGrid(new Vector2(0, 0));
        player = new Player(grid.randomSpace());
        camera = new OrthographicCamera(Constants.APP_WIDTH, Constants.APP_HEIGHT);
        viewport = new FitViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT, camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0f);
        camera.update();
    }

    private void newGrid(Vector2 position) {
        if(!firstTime) {
            savedGrids.add(grid);
            for (Grid grid : savedGrids) {
                if(grid != null && grid.getChunkCoords().equals(position)) {
                    this.grid = grid;
                    return;
                }
            }
        }
        grid = new Grid();
        grid.initialize(Constants.GRID_WIDTH, Constants.GRID_HEIGHT, Constants.SPACE_SIZE, position);
        firstTime = false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        playerUpdate();

        batch.begin();

        for(Sprite sprite : grid.getSprites()) {
            sprite.draw(batch);
        }

        player.getSprite().draw(batch);

        batch.end();
    }

    private void playerUpdate() {
        player.update();
        player.updateAdjGridSpaces(grid.getGridSpaces());

        if(player.getPosition().x < 0) {
            newGrid(new Vector2(player.getChunkCoords().x - 1, player.getChunkCoords().y));
            player.setChunkCoords(new Vector2(player.getChunkCoords().x - 1, player.getChunkCoords().y));
            player.setPosition(new Vector2(580, player.getPosition().y));
        } else if(player.getPosition().x > 580) {
            newGrid(new Vector2(player.getChunkCoords().x + 1, player.getChunkCoords().y));
            player.setChunkCoords(new Vector2(player.getChunkCoords().x + 1, player.getChunkCoords().y));
            player.setPosition(new Vector2(0, player.getPosition().y));
        } else if(player.getPosition().y < 0) {
            newGrid(new Vector2(player.getChunkCoords().x, player.getChunkCoords().y - 1));
            player.setChunkCoords(new Vector2(player.getChunkCoords().x, player.getChunkCoords().y - 1));
            player.setPosition(new Vector2(player.getPosition().x, 580));
        } else if(player.getPosition().y > 580) {
            newGrid(new Vector2(player.getChunkCoords().x, player.getChunkCoords().y + 1));
            player.setChunkCoords(new Vector2(player.getChunkCoords().x, player.getChunkCoords().y + 1));
            player.setPosition(new Vector2(player.getPosition().x, 0));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        grid.dispose();
    }
}
