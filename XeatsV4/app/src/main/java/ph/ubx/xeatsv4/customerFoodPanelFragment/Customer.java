package ph.ubx.xeatsv4.customerFoodPanelFragment;

public class Customer {

    private String city,FirstName,LastName,Password,ConfirmPassword,EmailId,MobileNumber,State,Subd,LocalAddress;

    public Customer() {

    }

    public Customer(String city, String firstName, String lastName, String password, String confirmPassword, String emailId, String mobileNumber, String state, String subd, String localAddress) {
        this.city = city;
        FirstName = firstName;
        LastName = lastName;
        Password = password;
        ConfirmPassword = confirmPassword;
        EmailId = emailId;
        MobileNumber = mobileNumber;
        State = state;
        Subd = subd;
        LocalAddress = localAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getSubd() {
        return Subd;
    }

    public void setSubd(String subd) {
        Subd = subd;
    }

    public String getLocalAddress() {
        return LocalAddress;
    }

    public void setLocalAddress(String localAddress) {
        LocalAddress = localAddress;
    }
}
