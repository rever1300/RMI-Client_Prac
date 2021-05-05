package Client;

import Common.ProfessorInt;
import Common.StudentInt;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Student {

    public static void main(String[] args){
        String host = (args.length <1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            StudentImpl student = new StudentImpl();
            ProfessorInt stub = (ProfessorInt) registry.lookup("Exam Online");
            System.out.println("Write your name; ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            stub.register(student, name);
            System.out.println("Student join, waiting to start de exam");

        }catch (Exception e){
            System.err.println("Client exception: " +e.toString()); e.printStackTrace();
        }
    }
}
