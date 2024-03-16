/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author HP
 */
public class JDialogGUI extends JDialog {
    
    private String DialogTitle;

    public JDialogGUI(Component parentComponent, JPanel customPanel, String dialogTitle) {
        super(SwingUtilities.getWindowAncestor(parentComponent), dialogTitle);
        this.DialogTitle = dialogTitle;
        initialize(customPanel);
    }
    
    public JDialogGUI(JDialog parentDialog, JPanel customPanel, String dialogTitle) {
        super(parentDialog, dialogTitle, true);
        this.DialogTitle = dialogTitle;
        initialize(customPanel);
    }

    private void initialize(JPanel customPanel) {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(customPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    public void showDialog() {
        setVisible(true);
    }

}
