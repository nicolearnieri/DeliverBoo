package uid.project.deliverboo.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorProvider {
    private static final ExecutorService executor = Executors.newFixedThreadPool(1);

    public static ExecutorService getExecutor() {
        return executor;
    }
}
