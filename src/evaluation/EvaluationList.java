package evaluation;

import java.util.ArrayList;

public class EvaluationList {
	
	private ArrayList<Integer> evaluationIDList = new ArrayList<Integer>();	
	private ArrayList<String> userIDList = new ArrayList<String>();
	private ArrayList<String> bookNameList = new ArrayList<String>();
	private ArrayList<String> authorNameList = new ArrayList<String>();
	private ArrayList<String> evaluationTitleList = new ArrayList<String>();
	private ArrayList<String> evaluationContentList = new ArrayList<String>();
	private ArrayList<String> totalScoreList = new ArrayList<String>();
	private ArrayList<String> standardList = new ArrayList<String>();
	private ArrayList<String> contentList = new ArrayList<String>();
	private ArrayList<String> bookConditionList = new ArrayList<String>();
	
	public EvaluationList() {
	}
	
	public void setEvaluationID (int index, Integer evaluationID) {
		this.evaluationIDList.add(index, evaluationID);
	}
	public void setUserID (int index, String userID) {
		this.userIDList.add(index, userID);
	}
	public void setBookName (int index, String bookName) {
		this.bookNameList.add(index, bookName);
	}
	public void setAuthorName(int index, String authorName) {
		this.authorNameList.add(index, authorName);
	}
	public void setEvaluationTitle(int index, String evaluationTitle) {
		this.evaluationTitleList.add(index, evaluationTitle);
	}
	public void setEvaluationContent(int index, String evaluationContent) {
		this.evaluationContentList.add(index, evaluationContent);
	}
	public void setTotalScore(int index, String totalScore) {
		this.totalScoreList.add(index, totalScore);
	}
	public void setStandard(int index, String standard) {
		this.standardList.add(index, standard);
	}
	public void setContent(int index, String content) {
		this.contentList.add(index, content);
	}
	public void setBookCondition(int index, String bookCondition) {
		this.bookConditionList.add(index, bookCondition);
	}
	
	public Integer[] getEvaluationID () {
		return evaluationIDList.toArray(new Integer[evaluationIDList.size()]);
	}
	public String[] getUserID () {
		return userIDList.toArray(new String[userIDList.size()]);
	}
	public String[] getBookName() {
		return bookNameList.toArray(new String[bookNameList.size()]);
	}
	public String[] getAuthorName() {
		return authorNameList.toArray(new String[authorNameList.size()]);
	}
	public String[] getEvaluationTitle() {
		return evaluationTitleList.toArray(new String[evaluationTitleList.size()]);
	}
	public String[] getEvaluationContent() {
		return evaluationContentList.toArray(new String[evaluationContentList.size()]);
	}
	public String[] getTotalScore() {
		return totalScoreList.toArray(new String[totalScoreList.size()]);
	}
	public String[] getStandard() {
		return standardList.toArray(new String[standardList.size()]);
	}
	public String[] getContent() {
		return contentList.toArray(new String[contentList.size()]);
	}
	public String[] getBookCondition() {
		return bookConditionList.toArray(new String[bookConditionList.size()]);
	}
	
	public int getListSize() {
		return evaluationIDList.size();
	}

}