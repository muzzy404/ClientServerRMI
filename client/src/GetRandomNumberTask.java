import java.rmi.registry.Registry;
import java.util.concurrent.Callable;

public class GetRandomNumberTask implements Callable<Long> {

    private Registry registry;
    private long requests = 0;

    public GetRandomNumberTask(Registry registry) {
        this.registry = registry;
    }

    @Override
    public Long call() {
        try {
            RandomNumber randomNumber = (RandomNumber) registry.lookup(Constants.NAME);
            while(!Thread.interrupted()) {
                randomNumber.next();
                ++requests;
                //System.out.println(Thread.currentThread().getName() + " " + String.valueOf(requests));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requests;
    }
}
