import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Client {

    private static final String HOST = "localhost";

    static private int N;                 // number of threads
    static private int workTime = 100_000; //

    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) {
            System.out.println("Wrong number of arguments");
            return;
        } else if (args.length == 2) {
            workTime = Integer.valueOf(args[1]);
        }
        N = Integer.valueOf(args[0]);


        try {
            Registry registry = LocateRegistry.getRegistry(HOST, Registry.REGISTRY_PORT);

            List<Future<Long>> results = new LinkedList<>();
            ExecutorService executor = Executors.newFixedThreadPool(N);

            final long startTime = System.currentTimeMillis();
            for(int i = 0; i < N; ++i) {
                Future<Long> submitted = executor.submit(new GetRandomNumberTask(registry));
                results.add(submitted);
            }
            Thread.sleep(workTime);
            executor.shutdownNow();
            final double executionTime = (System.currentTimeMillis() - startTime) / 1000.0;
            System.out.println(executionTime);

            long sum = results
                    .parallelStream()
                    .map(value -> {
                        try {
                            return value.get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 0;
                    })
                    .mapToLong(Number::longValue)
                    .sum();

            System.out.println();
            System.out.println(String.valueOf(N) +  " " +
                    String.format("%4.3f", sum / executionTime));

        } catch (Exception e) {
            System.err.println("Client error");
            e.printStackTrace();
        }
    }
}
