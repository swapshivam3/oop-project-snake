package snake;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class MainMenu implements ActionListener {

    public JFrame frame=new JFrame("Snake");
    public Container con;
    JPanel titleNamePanel;
    JLabel titleNameLabel;
    JPanel loginButtonPanel;
    JButton loginButton;
    JPanel startButtonPanel;
    JButton startButton;
    JPanel registerButtonPanel;
    JButton registerButton;
    JPanel logoutButtonPanel;
    JButton logoutButton;
    JPanel leaderboardButtonPanel;
    JButton leaderboardButton;
    JPanel easyButtonPanel;
    JButton easyButton;
    JPanel mediumButtonPanel;
    JButton mediumButton;
    JPanel hardButtonPanel;
    JButton hardButton;
    JFrame gameFrame;
    JLabel scoreText;
    public String state;
    Player player;
    LoginPopup l1;
    Leaderboard leaderboard;
    JPanel sizePanel;
    JTextField sizex;
    JTextField sizey;
    JLabel sizeText;
    MainMenu()
    {
        state=new String();
        state="MainMenu";
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.setResizable(false);
        con = frame.getContentPane();
        con.setBackground(Color.black);
        frame.setSize(800, 600);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GREEN));
        drawLoggedOut();
    }

    private void drawLoggedOut()
    {
        frame.setVisible(false);
        try{
            con.remove(logoutButtonPanel);
            con.remove(startButtonPanel);
        }
        catch(Exception e)
        {
            System.out.println("No logout/start buttons");
        }
        try {
            con.remove(scoreText);
        } catch (Exception e) {
            System.out.println("No score panel");
        }
        try{
            con.remove(leaderboardButtonPanel);
            con.remove(titleNamePanel);

        }
        catch(Exception e){
            System.out.println("No Leaderboard/title");
        }
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Snake");
        titleNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 60));
        titleNameLabel.setForeground(Color.white);
        
        loginButtonPanel = new JPanel();
        loginButtonPanel.setBounds(300, 400, 200, 100);
        loginButtonPanel.setBackground(Color.black);
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(170  , 80));
        loginButton.setFont(new Font("MONOSPACED", Font.PLAIN, 20));

        loginButton.setBackground(Color.black);
        loginButton.setForeground(Color.white);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);

        registerButtonPanel = new JPanel();
        registerButtonPanel.setBounds(300, 430, 200, 100);
        registerButtonPanel.setBackground(Color.black);
        registerButton = new JButton("Register");
        
        registerButton.setPreferredSize(new Dimension(170, 80));
        registerButton.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        registerButton.setBackground(Color.black);
        registerButton.setForeground(Color.white);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(this);
        

        leaderboardButtonPanel = new JPanel();
        leaderboardButtonPanel.setBounds(300, 460, 200, 100);
        leaderboardButtonPanel.setBackground(Color.black);
        leaderboardButton = new JButton("Leaderboard");
        
        leaderboardButton.setPreferredSize(new Dimension(170, 80));
        leaderboardButton.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        leaderboardButton.setBackground(Color.black);
        leaderboardButton.setForeground(Color.white);
        leaderboardButton.setFocusPainted(false);
        leaderboardButton.addActionListener(this);

        titleNamePanel.add(titleNameLabel);
        loginButtonPanel.add(loginButton);
        registerButtonPanel.add(registerButton);
        leaderboardButtonPanel.add(leaderboardButton); 
        con.add(titleNamePanel);
        con.add(loginButtonPanel);
        con.add(registerButtonPanel);
        con.add(leaderboardButtonPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void drawLoggedIn()
    {
        try{
            con.remove(easyButtonPanel);
            con.remove(mediumButtonPanel);
            con.remove(hardButtonPanel);
            con.remove(sizePanel);
        }
        catch(Exception e)
        {
            System.out.println("No difficulty buttons");
        }
        try{
            con.remove(loginButtonPanel);
            con.remove(registerButtonPanel);
            con.remove(leaderboardButtonPanel);
        }
        catch(Exception e)
        {
            System.out.println("Bo Login Register Leaderboard buttons");
        }
        try{
            player=l1.getPlayer();
        }
        catch(Exception e)
        {
            System.out.println("No Player found");
        }
        frame.setVisible(false);
        
        leaderboardButtonPanel = new JPanel();
        leaderboardButtonPanel.setBounds(300, 460, 200, 100);
        leaderboardButtonPanel.setBackground(Color.black);
        leaderboardButton = new JButton("Leaderboard");
        
        leaderboardButton.setPreferredSize(new Dimension(170, 80));
        leaderboardButton.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        leaderboardButton.setBackground(Color.black);
        leaderboardButton.setForeground(Color.white);
        leaderboardButton.setFocusPainted(false);
        leaderboardButton.addActionListener(this);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
        startButton = new JButton("Start");
        
        startButton.setPreferredSize(new Dimension(170, 80));
        startButton.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFocusPainted(false);
        startButton.addActionListener(this);

        logoutButtonPanel = new JPanel();
        logoutButtonPanel.setBounds(300, 430, 200, 100);
        logoutButtonPanel.setBackground(Color.black);
        logoutButton = new JButton("Logout");
        
        logoutButton.setPreferredSize(new Dimension(170, 80));
        logoutButton.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        logoutButton.setBackground(Color.black);
        logoutButton.setForeground(Color.white);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(this);

        startButtonPanel.add(startButton);
        logoutButtonPanel.add(logoutButton);
        leaderboardButtonPanel.add(leaderboardButton);
        
        con.add(startButtonPanel);
        con.add(logoutButtonPanel);
        con.add(leaderboardButtonPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.setVisible(true);
        
    }
    private void drawLeaderboard()
    {
        ResultSet rs=Player.fetchScoreList();
        leaderboard = new Leaderboard(rs);   
    }

    private void drawDifficultyScreen()
    {
        frame.setVisible(false);
        try{
            con.remove(scoreText);
        }
        catch(Exception e)
        {
            System.out.println("No Score panel");
        }
        con.remove(startButtonPanel);
        con.remove(logoutButtonPanel);
        con.remove(leaderboardButtonPanel);

        sizePanel=new JPanel();
        sizePanel.setBounds(300, 300, 200, 100);
        sizePanel.setBackground(Color.black);
        
        
        sizeText = new JLabel("Select Size (10-40)");
        sizeText.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        sizeText.setForeground(Color.white);
        
        sizex=new JTextField();
        sizex.setBounds(300, 20, 30, 30);
        sizex.setBackground(Color.black);
        sizex.setForeground(Color.white);
        sizex.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        sizex.setText("40");
        sizex.setPreferredSize(new Dimension(30, 30));
        sizex.setMaximumSize(sizex.getPreferredSize());

        sizey = new JTextField();
        sizey.setBounds(300, 20, 30, 30);
        sizey.setBackground(Color.black);
        sizey.setForeground(Color.white);
        sizey.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        sizey.setText("30");
        sizey.setPreferredSize(new Dimension(30, 30));
        sizey.setMaximumSize(sizey.getPreferredSize());

        sizePanel.add(sizeText);
        sizePanel.add(sizex);
        sizePanel.add(sizey);

        easyButtonPanel = new JPanel();
        easyButtonPanel.setBounds(300, 400, 200, 100);
        easyButtonPanel.setBackground(Color.black);
        easyButton = new JButton("Easy");
        
        easyButton.setPreferredSize(new Dimension(170, 80));
        easyButton.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        easyButton.setBackground(Color.black);
        easyButton.setForeground(Color.white);
        easyButton.setFocusPainted(false);
        easyButton.addActionListener(this);
        easyButtonPanel.add(easyButton);

        mediumButtonPanel = new JPanel();
        mediumButtonPanel.setBounds(300, 430, 200, 100);
        mediumButtonPanel.setBackground(Color.black);
        mediumButton = new JButton("Medium");
        
        mediumButton.setPreferredSize(new Dimension(170, 80));
        mediumButton.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        mediumButton.setBackground(Color.black);
        mediumButton.setForeground(Color.white);
        mediumButton.setFocusPainted(false);
        mediumButton.addActionListener(this);
        mediumButtonPanel.add(mediumButton);

        hardButtonPanel = new JPanel();
        hardButtonPanel.setBounds(300, 460, 200, 100);
        hardButtonPanel.setBackground(Color.black);
        hardButton = new JButton("Hard");
        hardButton.setPreferredSize(new Dimension(170, 80));
        hardButton.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        hardButton.setBackground(Color.black);
        hardButton.setForeground(Color.white);
        hardButton.setFocusPainted(false);
        hardButton.addActionListener(this);
        hardButtonPanel.add(hardButton);

        con.add(sizePanel);
        con.add(easyButtonPanel);
        con.add(mediumButtonPanel);
        con.add(hardButtonPanel);
        frame.setVisible(true);
        

    }
    public Boolean checkNumber(String s)
    {
        try{
            int i=Integer.parseInt(s);
            if(i>=10 && i<=40)
                return true;
            else
                return false;
        }
        catch(Exception e){
            return false;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==loginButton){
            l1=new LoginPopup(this);
        }
        else if(source == registerButton){
            l1= new LoginPopup(this);
            l1.drawSignup();
        }
        else if(source==leaderboardButton)
        {
            drawLeaderboard();
        }
        else if(source==logoutButton)
        {
            this.state="LoggedOut";
            drawLoggedOut();
        }
        else if(source==startButton)
        {
            drawDifficultyScreen();
        }
        else if(source==easyButton && checkNumber(sizex.getText()) && checkNumber(sizey.getText()))
        {
        
            int w=Integer.parseInt(sizex.getText());
            int h=Integer.parseInt(sizey.getText());
            
            frame.setVisible(false);
            gameFrame=new JFrame("Snake");            
            Game g1 = new Game(this,player,0,w,h);
            // g1.setEasy();
            // gameFrame.add(g1.graphics);
            // gameFrame.remove(g1.graphics);
            gameFrame.add(g1.graphics);
            gameFrame.setResizable(false);
            gameFrame.setPreferredSize(new Dimension(Game.width*Game.sizemultiplier, Game.height*Game.sizemultiplier));
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.pack();
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);

        }
        else if (source == mediumButton&& checkNumber(sizex.getText()) && checkNumber(sizey.getText())) {

            int w = Integer.parseInt(sizex.getText());
            int h = Integer.parseInt(sizey.getText());

            frame.setVisible(false);
            gameFrame = new JFrame("Snake");
            Game g1 = new Game(this,player,1, w,h);
            // g1.setMedium();
            // gameFrame.add(g1.graphics);
            // gameFrame.remove(g1.graphics);
            gameFrame.add(g1.graphics);
            gameFrame.setResizable(false);
            gameFrame.setPreferredSize(new Dimension(Game.width * Game.sizemultiplier, Game.height * Game.sizemultiplier));
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.pack();
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
        }
        else if (source == hardButton && checkNumber(sizex.getText()) && checkNumber(sizey.getText())) {

            int w = Integer.parseInt(sizex.getText());
            int h = Integer.parseInt(sizey.getText());

            frame.setVisible(false);
            gameFrame = new JFrame("Snake");
            Game g1 = new Game(this,player,2, w,h);
            // g1.setHard();
            // gameFrame.add(g1.graphics);
            // gameFrame.remove(g1.graphics);
            gameFrame.add(g1.graphics);
            gameFrame.setResizable(false);
            gameFrame.setPreferredSize(new Dimension(Game.width * Game.sizemultiplier, Game.height * Game.sizemultiplier));
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.pack();
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
        }

    }
    public void drawGameOver(Game g1)
    {
        gameFrame.setVisible(false);
        float score=g1.getSnake().getBody().size()-3;
        score=score*g1.multiplier;
        // System.out.println(score);
        g1=null;

        try{
            con.remove(scoreText);
        }
        catch(Exception e)
        {
            System.out.println("No Score panel");
        }
        finally{
            frame.setVisible(false);
            scoreText = new JLabel("Your score was " + score);
            scoreText.setFont(new Font("MONOSPACED",Font.PLAIN,20));
            scoreText.setBounds(400,200,200,100);
            scoreText.setBorder(new EmptyBorder(10,10,10,10));
            scoreText.setForeground(Color.white);   
            scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);         
            con.add(scoreText);
            frame.setVisible(true);
            drawLoggedIn();
        }
    }
}
