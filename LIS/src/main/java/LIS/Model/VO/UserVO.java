package LIS.Model.VO;

public class UserVO {
    private String userId;
    private String password;
    private String name;
    private int stuNum;
    private String dept;
    private String userStatus;
    private String phone;
    private String email;
    private int mileage;
    private int isManager;
    private String regDate;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getStuNum() { return stuNum; }
    public void setStuNum(int stuNum) { this.stuNum = stuNum; }
    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
    public String getUserStatus() { return userStatus; }
    public void setUserStatus(String userStatus) { this.userStatus = userStatus; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }
    public int getIsManager() { return isManager; }
    public void setIsManager(int isManager) { this.isManager = isManager; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
}
