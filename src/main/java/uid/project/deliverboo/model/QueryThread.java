package uid.project.deliverboo.model;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class QueryThread extends Thread {

    private BlockingQueue<Runnable> commandQueue = new LinkedBlockingQueue<>(); //conterrà i comandi che il thread dovrà eseguire

    @Override
    public void run() {
        try {
            while (true)
            {
                Runnable command = commandQueue.take(); // Prende il prossimo comando dalla coda, poi lo esegue
                command.run();
            }
        }
        catch (InterruptedException e)
        {// Thread interrotto
        }

    }
    // Metodo per aggiungere un nuovo comando alla coda
    public void addCommand(Runnable command) {
        commandQueue.add(command);
    }
}
