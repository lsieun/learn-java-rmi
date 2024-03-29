package lsieun.rmi.server;

import lsieun.rmi.common.RemoteUtility;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteServer {
    public static void main(String[] args) {
        SecurityManager secManager = System.getSecurityManager();
        if (secManager == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            RemoteUtilityImpl remoteUtility = new RemoteUtilityImpl();
            // Export the object as a remote object
            int port = 0; // An anonymous port
            RemoteUtility remoteUtilityStub = (RemoteUtility) UnicastRemoteObject.exportObject(remoteUtility, port);

            // Locate the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Bind the exported remote reference in the registry
            String name = "MyRemoteUtility";
            registry.rebind(name, remoteUtilityStub);
            System.out.println("Remote server is ready...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
