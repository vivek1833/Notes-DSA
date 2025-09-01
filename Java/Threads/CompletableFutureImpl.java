import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureImpl {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                while(true) {
                    Thread.sleep(1000);
                    System.out.println("Inside try...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Done";
        });  

        Thread.sleep(5000);
        System.out.println("Inside Main...");
    }
}
