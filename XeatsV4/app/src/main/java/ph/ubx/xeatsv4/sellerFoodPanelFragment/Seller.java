package ph.ubx.xeatsv4.sellerFoodPanelFragment;

public class Seller {

    private String Subd,City,Fname,Lname,EmailId,Password,ConfirmPassword,Mobile,House,ZipCode,State;

    public Seller(String subd, String city, String fname, String lname, String emailId, String password, String confirmPassword, String mobile, String house, String zipCode, String state) {
        this.Subd = subd;
        City = city;
        Fname = fname;
        Lname = lname;
        EmailId = emailId;
        Password = password;
        ConfirmPassword = confirmPassword;
        Mobile = mobile;
        House = house;
        ZipCode = zipCode;
        State = state;
    }

    public Seller() {
    }

    public String getSubd() {
        return Subd;
    }

    public String getCity() {

        return City;
    }

    public String getFname() {

        return Fname;
    }

    public String getLname() {

        return Lname;
    }

    public String getEmailId() {
        return EmailId;
    }

    public String getPassword() {
        return Password;
    }

    public String getConfirmPassword() {

        return ConfirmPassword;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getHouse() {

        return House;
    }

    public String getZipCode() {

        return ZipCode;
    }

    public String getState() {

        return State;
    }
}
