package Client;

import Common.ProfessorInt;
import Common.StudentInt;
import ExamModels.QuestionsLike;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class StudentImpl extends UnicastRemoteObject implements StudentInt {
    private Scanner answer;
    private String studentID;
    private QuestionsLike questionsLike;

    protected StudentImpl(String studentID, ProfessorInt professorInt) throws RemoteException {
        super();
        this.studentID = studentID;
        this.answer = new Scanner(System.in);
    }

    public QuestionsLike obtainAnswer(){
        int answer;
        do{
            System.out.print("Your answer is: " + "\n");
            answer = this.answer.nextInt();
            if(!this.questionsLike.correctAnswerType(answer)){
                System.out.print("This answer is not possible. Try again!" + "\n");
            }
        }while (!this.questionsLike.correctAnswerType(answer));
        this.questionsLike.setAnswer(answer);
        return questionsLike;
    }

    @Override
    public void sendQuestion(QuestionsLike questions) {
        System.out.print(questions);
        this.questionsLike = questions;
        /*Notify because the server reacts to send us another question*/
        notify();
    }

    @Override
    public void startExam(String message) {
        System.out.print(message);
        /*Notify because the start of the exam waiting time*/
        notify();
    }

    @Override
    public void examFinished(int mark, String message) {
        System.out.print(message + "\n");
        System.out.print("Your final mark is: " + mark + "\n");
    }

    @Override
    public void RegisteredFailed(String message) {
        System.out.print(message);
    }
}
