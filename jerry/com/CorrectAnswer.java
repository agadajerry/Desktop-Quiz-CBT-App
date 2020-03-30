package quiz.jerry.com;

public class CorrectAnswer {

	private String correctAns;
	private int questionNo;
	public CorrectAnswer(String correctAns, int questionNo) {
		//super();
		this.correctAns = correctAns;
		this.questionNo = questionNo;
	}
	public String getCorrectAns() {
		return correctAns;
	}
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	
}
