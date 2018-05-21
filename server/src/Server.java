import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            RandomNumberImplementation randomNumberImpl = new RandomNumberImplementation();
            RandomNumber randomNumber =
                    (RandomNumber) UnicastRemoteObject.exportObject(randomNumberImpl, 0);

            registry.rebind(Constants.NAME, randomNumber);
            System.out.println("Server is running");

        } catch (RemoteException e) {
            System.err.println("Server error");
            e.printStackTrace();
        }
    }
}
