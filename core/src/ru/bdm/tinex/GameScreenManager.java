package ru.bdm.tinex;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.Stack;

/**
 * @author bdm
 * Управляет экранами
 */
public class GameScreenManager {

    private Stack<Screen> stack = new Stack<>();
    final Game game;
    public GameScreenManager(final Game game) {
        this.game = game;
    }

    private void setScreen(){
        game.setScreen(stack.peek());
    }
    public void push(Screen screen){
        stack.push(screen);
        setScreen();
    }
    public void change(Screen screen){
        stack.pop();
        stack.push(screen);
        setScreen();
    }
    public void back(){
        stack.pop();
        setScreen();
    }
}
