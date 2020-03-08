package ru.bdm.tinex;

import com.badlogic.gdx.Gdx;
import ru.bdm.tinex.logic.Map;
import ru.bdm.tinex.logic.Shepherd;

import java.util.concurrent.ArrayBlockingQueue;

public class ThreadManager extends Thread {

    ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(4);

    Shepherd shepherd;
    MapActor actor;

    public ThreadManager(Shepherd shepherd, MapActor actor) {
        this.shepherd = shepherd;
        this.actor = actor;
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                updateMap();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateMap() throws InterruptedException {
        int number = 0;

        number = queue.take();

        for (int i = 0; i < number; i++) {
            shepherd.nextTurn();
        }
        final Map map = shepherd.getMap().copy();

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                actor.updateMap(map);
            }
        });
    }

    public void put(int number) {
        try {
            queue.add(number);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
