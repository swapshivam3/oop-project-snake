package snake;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginPopup implements ActionListener{
    public JFrame frame =new JFrame("Login");
    JTextField nameText = new JTextField();
    JPasswordField passwordText=new JPasswordField();
    JLabel logo = new JLabel("Please Login");
    JLabel registerlogo=new JLabel("Please Signup");
    JLabel nameLabel = new JLabel("Name:");
    JLabel passwordLabel = new JLabel("Password:");
    JButton submit = new JButton("Submit");
    JLabel error=new JLabel("Error");
    Container container;
    MainMenu menu;
    Game g1;
    private Player player;
    private String state="Login";
    LoginPopup(MainMenu m)
    {
        menu=m;
        frame.setBounds(200,100,400,200);
        container=frame.getContentPane();
        container.setLayout(null);
        logo.setBounds(60,5,250,30);
        nameLabel.setBounds(20,30,250,30);
        nameText.setBounds(80,30,250,30);
        passwordLabel.setBounds(20,60,250,30);
        passwordText.setBounds(80,60,250,30);
        submit.setBounds(150,90,100,30);
        error.setBounds(130,110,200,30);
        submit.addActionListener(this);
        
        container.add(logo);
        container.add(nameLabel);
        container.add(nameText);
        container.add(submit);
        container.add(passwordLabel);
        container.add(passwordText);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);   
    }

    void drawSignup()
    {
        state="Signup";
        frame.setVisible(false);
        container.remove(logo);
        registerlogo.setBounds(60,5,250,30);
        container.add(registerlogo);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public Player getPlayer(){
        return player;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
        if (source == submit && nameText.getText().length()!=0 && passwordText.getText().length()!=0 && state.equals("Login")) {
            // g1 = new Game();
            player= new Player(nameText.getText());
            if(player.check(nameText.getText(), passwordText.getText()))
            {
                frame.dispose();
                menu.state = "LoggedIn";
                menu.drawLoggedIn();
            }
            else{
                frame.setVisible(false);
                error.setText("Credentials do not match");
                container.add(error);
                frame.setVisible(true);
            }
        }
        else if(source==submit && nameText.getText().length()!=0 && passwordText.getText().length()!=0 && state.equals("Signup"))
        {
            player= new Player(nameText.getText());
            if(!player.checkExist(nameText.getText()))
            {
                player.register(nameText.getText(), passwordText.getText());
                frame.dispose();
            }
            else
            {
                frame.setVisible(false);
                error.setText("Account already exists");
                container.add(error);
                frame.setVisible(true);
            }
        }

	    	}
    }
