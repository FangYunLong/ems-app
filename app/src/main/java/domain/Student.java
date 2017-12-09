package domain;

import java.util.Date;

public class Student {
    private String student_id;
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String Old_id;
    private String password;

    public String getOld_id() {
		return Old_id;
	}

	public void setOld_id(String old_id) {
		Old_id = old_id;
	}

	private String student_name;

    private String sex;

    private String nation;

    private String birthday;

    private String id_card;

    private String classid;

    private String admission_date;

    private String study_length;
    
    private int grade;
    
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getAdmission_date() {
		return admission_date;
	}

	public void setAdmission_date(String admission_date) {
		this.admission_date = admission_date;
	}

	public String getStudy_length() {
		return study_length;
	}

	public void setStudy_length(String study_length) {
		this.study_length = study_length;
	}

    
}