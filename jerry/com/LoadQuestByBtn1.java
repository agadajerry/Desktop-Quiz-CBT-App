package quiz.jerry.com;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadQuestByBtn1 {
	
	
	public ArrayList<Questions> allBtn1Question( String subName) {
		ArrayList<Questions> button1Q = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = DBconnect.getConnection().prepareStatement(subName);
			rs = ps.executeQuery();
			while (rs.next()) {

				String question = rs.getString("Question");
				String optionA = rs.getString("OptionA");
				String optionB = rs.getString("OptionB");
				String optionC = rs.getString("OptionC");
				String optionD = rs.getString("OptionD");
				String answer = rs.getString("Answer");

				Questions questObj = new Questions();
				questObj.setEngQuestion(question);
				questObj.setOptionA(optionA);
				questObj.setOptionB(optionB);
				questObj.setOptionC(optionC);
				questObj.setOptionD(optionD);
				questObj.setAnswer(answer);
				button1Q.add(questObj);

			}
			rs.close();

		} catch (SQLException ex) {

		}
		return button1Q;
	}

}
