package Common;

import Client.StudentImpl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProfessorInt extends Remote {
    void register(StudentImpl student, String name) throws RemoteException;

}
