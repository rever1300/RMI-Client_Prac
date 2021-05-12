package ExamModels;

import java.io.Serializable;
import java.util.List;


public class QuestionsLike implements Serializable {

    private int questionNum;
    private String stat;
    private List<String> choices;
    private int answer;

    public QuestionsLike(int questionNum, String stat, List<String> choices){
        this.questionNum = questionNum;
        this.stat = stat;
        this.choices = choices;
        this.answer = 0;
    }

    public void setAnswer(int answer){
        this.answer = answer;
    }

    public int getAnswer() {
        return this.answer;
    }

    public int getQuestionNum(){
        return this.questionNum;
    }

    public boolean correctAnswerType(int answer){
        return answer > 0 &&
                answer < this.choices.size();
    }



    @Override
    public String toString(){
        String sendQT = new String("\n" + "Question number " + this.questionNum + ": " + this.stat + "\n");
        for(int i = 0; i<this.choices.size(); ++i){
            sendQT = sendQT + "     " + i+1 + ". " + this.choices.get(i) + "\n";
        }
        return sendQT;
    }
}
