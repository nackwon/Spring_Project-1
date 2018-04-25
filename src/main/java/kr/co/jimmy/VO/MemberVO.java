package kr.co.jimmy.VO;

public class MemberVO {

	private int no;
	private String name;
	private String email;
	private String password;
	private String gender;

	public MemberVO() {

	}

	public MemberVO(String name, String password, String gender) {
		this.name = name;
		this.password = password;
		this.gender = gender;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
