package Client;

import Common.ProfessorInt;
import Common.StudentInt;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import ExamModels.QuestionsLike;

public class Client {

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
            System.out.println("Client join, waiting to start de exam");

            synchronized (student){
                student.wait();
                while(true){
                    QuestionsLike questionsLike = student.setAnswer();
                    stub.getAnswer(name, questionsLike);

                }
            }




        }catch (Exception e){
            System.err.println("Client exception: " +e.toString()); e.printStackTrace();
        }
    }
}
