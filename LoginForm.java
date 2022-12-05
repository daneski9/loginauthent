import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog{
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton submitButton;
    private JButton cancelButton;
    private JPanel loginPanel;


    public LoginForm(JFrame parent){
        super(parent); //constructor of JDialog class
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent); //middle of frame
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        submitButton.addActionListener(new ActionListener() { //authentication on submit button
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                user = getAuthenticatedUser(email, password); //validates info

                if (user != null){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Email or password Invalid",
                            "Enter information again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
        public User user;
        private User getAuthenticatedUser(String email, String password) { //returns valid user if found in database, else null
            User user = null;
            final String myschema = "javaswinglogin";
            final String servername = "localhost";
            final String dburl = "jdbc:mysql://" + servername + "/" + myschema;
            final String uname = "root";
            final String dbpass = "dane";
            try {
                Connection connection = DriverManager.getConnection(dburl, uname, dbpass);

                Statement stmt = connection.createStatement();
                String sql = "SELECT * FROM USERS WHERE email=? AND password=?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                 if(resultSet.next()){
                    user = new User();
                    user.setuserID(resultSet.getInt("userID"));
                    user.setfirstName(resultSet.getString("fname"));
                    user.setlastName(resultSet.getString("lname"));
                    user.setemail(resultSet.getString("email"));
                    user.setphone(resultSet.getString("phone"));
                    user.setpassword(resultSet.getString("password"));
                 }

                 stmt.close();
                 connection.close();

            }
            catch(Exception e){
                e.printStackTrace();
            }




            return user;
        }


    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user1 = loginForm.user;
        if (user1!=null){
            System.out.println("Success! Welcome: "+ user1.getfirstName() + " "+ user1.getlastName());
            System.out.println("  User ID: "+ user1.getuserID());
            System.out.println("  Email: "+ user1.getemail());
            System.out.println("  Phone: "+ user1.getphone());
        }
        else{
            System.out.println("Wrong username or password");
        }
    }



}
