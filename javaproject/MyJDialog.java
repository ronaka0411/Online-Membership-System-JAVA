/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import Utils.Functions;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class MyJDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public MyJDialog() {
    }

    public JPanel MyJDialog(JFrame parent) {
//		super(parent, title);

        setLocation(Functions.getX_d(), Functions.getY_d());


        JPanel messagePane = new JPanel();
//		messagePane.add(new JLabel(message));
        // get content pane, which is usually the
        // Container of all the dialog's components.
        getContentPane().add(messagePane);

        // Create a button
        JPanel buttonPane = new JPanel();
        JButton button = new JButton("Close me");
        buttonPane.add(button);

        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        return messagePane;
    }

    // override the createRootPane inherited by the JDialog, to create the rootPane.
    // create functionality to close the window when "Escape" button is pressed
    public JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action action = new AbstractAction() {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                System.out.println("escaping..");
                setVisible(false);
                dispose();
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", action);
        return rootPane;
    }
}