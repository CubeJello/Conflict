package com.cubejello.conflict;

import com.badlogic.gdx.Game;
import com.cubejello.conflict.screens.GameScreen;

public class Conflict extends Game {
	
	@Override
	public void create () {
		this.setScreen(new GameScreen());
	}

	@Override
	public void render() {
		super.render();
	}
}
