package kr.co.jimmy.VO;

public class FileVO {

	private int no;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;
	private int user_no;

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public FileVO() {

	}

	public FileVO(String filePath, String orgName, String saveName, long fileSize, int user_no) {

		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.user_no = user_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "FileVO [no=" + no + ", filePath=" + filePath + ", orgName=" + orgName + ", saveName=" + saveName
				+ ", fileSize=" + fileSize + "]";
	}

}
