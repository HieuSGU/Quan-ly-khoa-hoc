import GUI.StudentDetails;
import GUI.StudentGradeDetails;
import GUI.StudentGradeMainForm;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    private static void createAndShowGUI() {
        // Replace this with your actual connection code
        
        JFrame frame = new JFrame("Student Grade Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Pass the connection object to StudentGradeMainForm
        frame.getContentPane().add(new StudentGradeMainForm());
        
        frame.pack();
        frame.setVisible(true);
    }
}
