public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;

    User(){};

    public void setuserID(int userID){
        this.userID = userID;
    }
    public void setfirstName(String firstName){
        this.firstName = firstName;
    }
    public void setlastName(String lastName){
        this.lastName = lastName;
    }
    public void setemail(String email){
        this.email = email;
    }
    public void setphone(String phone){
        this.phone = phone;
    }
    public void setpassword(String password){
        this.password = password;
    }
    public String getfirstName(){
        return firstName;
    }
    public String getlastName(){
        return lastName;
    }
    public String getemail(){
        return email;
    }
    public String getphone(){
        return phone;
    }
    public String getpassword(){
        return password;
    }
    public int getuserID(){
        return userID;
    }








}

