import java.rmi.registry.Registry;

public class GetRandomNumberTask implements Runnable {

    private Registry registry;
    private int requests = 0;

    public GetRandomNumberTask(Registry registry) {
        this.registry = registry;
    }

    @Override
    public void run() {
        try {
            RandomNumber randomNumber = (RandomNumber) registry.lookup(Constants.NAME);
            while(true) {
                randomNumber.next();
                ++requests;
                System.out.println(Thread.currentThread().getName() + " " + String.valueOf(requests));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
