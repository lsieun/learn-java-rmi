package lsieun.rmi.client;

import lsieun.rmi.common.RemoteUtility;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.ZonedDateTime;

public class RemoteClient {
    public static void main(String[] args) {
        SecurityManager secManager = System.getSecurityManager();
        if (secManager == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            // Locate the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            String name = "MyRemoteUtility";
            RemoteUtility remoteUtilStub = (RemoteUtility) registry.lookup(name);

            // Call the echo() method
            String reply = remoteUtilStub.echo("Hello from the RMI client.");
            System.out.println("Reply: " + reply);
            ZonedDateTime serverTime = remoteUtilStub.getServerTime();
            System.out.println(serverTime);
            int result = remoteUtilStub.add(10, 20);
            System.out.println(result);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
