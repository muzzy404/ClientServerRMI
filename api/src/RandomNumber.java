import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RandomNumber extends Remote {

    String name = "number";

    Integer next() throws RemoteException;
}
