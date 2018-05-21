import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {


    private static final String HOST = "localhost";

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, Registry.REGISTRY_PORT);

            int N = 3;
            ExecutorService executor = Executors.newCachedThreadPool();
            for(int i = 0; i < N; ++i) executor.execute(new GetRandomNumberTask(registry));


        } catch (Exception e) {
            System.err.println("Client error");
            e.printStackTrace();
        }
    }
}
