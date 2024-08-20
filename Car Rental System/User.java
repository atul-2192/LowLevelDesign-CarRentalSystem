
public class User {
    static int count = 0;
    private int userId;
    private String userName;
    private String drivingLicense;

    public User(String userName, String drivingLicense) {
        count++;
        userId = count;
        this.userName = userName;
        this.drivingLicense = drivingLicense;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

}
