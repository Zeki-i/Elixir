package com.codebreakers.elixir;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ElixirGame extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}