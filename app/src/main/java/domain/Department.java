package domain;

public class Department {
    private String deptid;

    private String dept_name;

    private String dean;

    private String Old_id;

    public String getOld_id() {
		return Old_id;
	}

	public void setOld_id(String old_id) {
		Old_id = old_id;
	}

    public String getDeptid() {
        return deptid;
    }

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDean() {
		return dean;
	}

	public void setDean(String dean) {
		this.dean = dean;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

    
}