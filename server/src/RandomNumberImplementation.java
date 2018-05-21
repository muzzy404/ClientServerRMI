import java.rmi.RemoteException;
import java.util.Random;

public class RandomNumberImplementation implements RandomNumber {

    private static final Random random = new Random();
    private static final int BOUND = 10_000;

    @Override
    public Integer next() throws RemoteException {
        return random.nextInt(BOUND);
    }
}
