public class SongLine {
	private String keyword;
	private int lineNumber;
	private String words;
	
	public SongLine() {
		keyword = "Birthday";
		lineNumber = 1;
		words = "Happy Birthday to you!";
	}
	
	public SongLine(String keyword, int lineNumber, String words) {
		this.keyword = keyword;
		this.lineNumber = lineNumber;
		this.words = words;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public void setWords(String words) {
		this.words = words;
	}
	
	public String getWords() {
		return words;
	}

	public String toString() {
		return keyword+"\t"+lineNumber+"\t"+words;
		//return lineNumber+":\t"+words;		
	}
}