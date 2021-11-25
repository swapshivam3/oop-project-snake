package snake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Player {
    public String currentPlayer;
    public int scoreCurrent;
    public Game g1;
    Player(String s){
        currentPlayer=s;
        // this.g1=g1;
    }
    public void setGame(Game g1)
    {
        this.g1=g1;
    }
    public void getScore()
    {
        scoreCurrent=(g1.getSnake().getBody().size()-3);
    }
    public boolean check(String name, String password)
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root", "mysql@123");
        Statement stm=con.createStatement();
        String sql="select * from login where name='"+name+"' and password='"+password+"'";
        ResultSet rs=stm.executeQuery(sql);
        if(rs.next())
        {
            con.close();
            return true;

        }
        else {con.close(); return false;}
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean checkExist(String name)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root",
                    "mysql@123");
            Statement stm = con.createStatement();
            String sql = "select * from login where name='" + name + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                con.close();
                return true;

            } else {
                con.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false; 
    }
    void register(String name, String password)
    {
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root", "mysql@123");
        Statement stm=con.createStatement();
        stm.executeUpdate("insert into login VALUES('"+ name + "','"+password+"')");
        con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void addScore(float score)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root",
                    "mysql@123");
            Statement stm = con.createStatement();
            stm.executeUpdate("insert into scores VALUES('" + currentPlayer + "','" + score + "')");
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());

        }
    }
    public static ResultSet fetchScoreList()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root",
                    "mysql@123");
            Statement stm = con.createStatement();
            String sql = "select * from scores ORDER BY score DESC";
            ResultSet rs = stm.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
