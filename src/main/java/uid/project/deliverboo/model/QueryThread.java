package uid.project.deliverboo.model;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueryThread extends Thread {

    private Task<?> task;
    public QueryThread(Task<?> task) {
        this.task = task;
    }

    @Override
    public void run() {
        try {
            while (true) {

                task.run();
            }
        } catch (InterruptedException e) {// Thread interrotto
        }

    }

    // Metodo per aggiungere un nuovo comando alla coda
    public void addCommand(Runnable command) {
        commandQueue.add(command);
    }}

