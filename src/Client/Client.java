package Client;

import Common.ProfessorInt;
import Common.StudentInt;
import ExamModels.QuestionsLike;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String host = (args.length < 1) ? null : args[0];
        try {
            /*Get registry from the locateRegistry*/
            Registry registry = LocateRegistry.getRegistry(host);
            System.out.print("Enter your student id:");
            String id = scanner.nextLine();
            System.out.print("Exam will start soon...");

            /*Creating instances of both integrates*/
            ProfessorInt professorInt = (ProfessorInt) registry.lookup("EXAM");
            StudentImpl student = new StudentImpl(id, professorInt);

            /*Remote call for registering*/
            professorInt.register(student, id);

            /*Synchoronize block for the student when the exams is about to start*/
            synchronized (student){
                /*This wait for the registry time*/
                student.wait();
                while(true){
                    /*This wait for the question receiving time*/
                    student.wait();
                    QuestionsLike question = student.obtainAnswer();
                    professorInt.sendAnswer(id, question);
                }
            }

        } catch (RemoteException | InterruptedException e) {
            e.printStackTrace();
        } catch(NotBoundException e){
            System.out.print("The exam session has not started yet!");
        }
        System.exit(0);
    }
}
