import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuterServiceImlp {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executer = Executors.newFixedThreadPool(9);
        Future<?> future = null;

        for(int i=0; i<10; i++){ 
            int it = i;
            future = executer.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }                

                System.out.println("Executor..." + it);
            });
        }
        
        System.out.println("Future Result..." + future.get());
        System.out.println("Future Done..." + future.isDone());
        executer.shutdown();
    }
}
