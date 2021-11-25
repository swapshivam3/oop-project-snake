package snake;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Lead extends JPanel{
    public JLabel name;
    public JLabel score;
    Lead(String name, String score)
    {
        this.name = new JLabel(name);
        this.name.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        this.score= new JLabel(score);
        this.score.setFont(new Font("MONOSPACED", Font.PLAIN, 20));
        this.add(this.name);
        this.add(this.score);
    }

}

public class Leaderboard implements ActionListener {
    public JFrame frame = new JFrame("Leaderboard");
    JButton okButton = new JButton("Close");
    public JLabel title = new JLabel("High Scores");

    Leaderboard(ResultSet rs) {
        int count=0;
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.setResizable(false);
        
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        frame.add(title);
        try{
            frame.add(new Lead("Name","Score"));
        while(rs.next() & count<=10){
    
            frame.add(new Lead(rs.getString("name"), rs.getString("score")));
            count++;
        }}
        catch(Exception e)  
        {
            System.out.println("QuerySet Exception");
        }
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == okButton)
        {
            frame.dispose();
        }
    }
}
