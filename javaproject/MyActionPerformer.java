/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author rsa38 mgs33
 */
public class MyActionPerformer extends SwingWorker<String, Object> {

    JProgressBar fProgressBar;
    JFrame frame;
    JButton jb;
    ViewMember vm;

    public MyActionPerformer(JProgressBar progressBar, JFrame frame) {
        this.fProgressBar = progressBar;
        this.fProgressBar.setVisible(true);
        this.fProgressBar.setIndeterminate(true);
        this.frame = frame;
        this.jb = jb;

    }

    protected String doInBackground() throws Exception {
        vm.search1();
        return "Finished";
    }

    protected void done() {
        fProgressBar.setVisible(false);
      
        System.out.println("done ");
    }

    public void calculateResult() {
        for (int i = 0; i < 50000; i++) {
            System.out.println("Progress Bar: " + i);
        }
    }
}