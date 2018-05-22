/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

/**
 *
 * @author rsa38 mgs33
 */
public class MyCustomProgressBarDialog extends JDialog {

    private static JProgressBar progressBar;

    public MyCustomProgressBarDialog(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public JProgressBar createProgressUI() {
        add(progressBar);
        setLocationRelativeTo(null);
        setSize(200, 100);
        setVisible(true);
        return progressBar;
    }
}