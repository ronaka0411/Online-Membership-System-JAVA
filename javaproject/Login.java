package javaproject;

import Client.Client;
import Utils.Functions;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Login extends JPanel implements ActionListener {

    private static final long serialVersionUID = 15000000;
    static int width = Functions.getWidth();
    static int height = Functions.getHeight();
    static int x = Functions.getX();
    static int y = Functions.getY();
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JTextField jcomp3;
    private JTextField jcomp4;
    private JButton jcomp5;
    private static JFrame frame = new JFrame("Login : " + Functions.getAppName());

    public Login() {
        jcomp1 = new JLabel("Username");
        jcomp2 = new JLabel("Password");
        jcomp3 = new JTextField(5);
        jcomp4 = new JPasswordField(5);
        jcomp5 = new JButton("Login");

        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);
        jcomp5.addActionListener(this);


        jcomp1.setBounds(210, 160, 65, 25);
        jcomp2.setBounds(210, 215, 65, 20);
        jcomp3.setBounds(325, 160, 215, 30);
        jcomp4.setBounds(325, 215, 215, 25);
        jcomp5.setBounds(325, 255, 215, 30);
        add(Functions.welcomeNote());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().trim().equalsIgnoreCase("login")) {
            String errormsg = "", username = jcomp3.getText().toString().trim(), password = jcomp4.getText().toString().trim();

            if (username.equalsIgnoreCase("")) {
                errormsg = "Enter Username";
            } else if (password.equalsIgnoreCase("")) {
                errormsg = "Enter Paasword";
            } else {
                password = Functions.MD5(password);
                try {
                    errormsg = LoginMeIn(username, password);
                } catch (Exception ex) {
                    Functions.ExceptionPrinter(ex);
                }
            }

            JOptionPane.showMessageDialog(null, errormsg);
            Preferences prefs = Preferences.userNodeForPackage(javaproject.Main.class);
            prefs.get("user_type", "");
            if (prefs.get("user_type", "").toString().trim().equalsIgnoreCase("1")) {
				// for members
                ViewMember.open();
                frame.dispose();
            } else if (prefs.get("user_type", "").toString().trim().equalsIgnoreCase("0")) {
                //for admin
                ViewMember.open();
                frame.dispose();
            }

        }

    }

    public String LoginMeIn(String username, String password) {
        try {
            Functions.setUserTypeID(-1);
            Client c = new Client();
            String response = c.sendLoginInfo(username, password);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response);
            JSONObject json = (JSONObject) obj;


            if (json.containsKey("method") && json.get("method").toString().equalsIgnoreCase("200")) {
                if (json.containsKey("status") && json.get("status").toString().equalsIgnoreCase("201")) {
                    Functions.setUserTypeID(Integer.valueOf(json.get("user_type").toString().trim()));
                    Functions.setUserID(Integer.valueOf(json.get("user_id").toString().trim()));

                    Preferences prefs = Preferences.userNodeForPackage(javaproject.Main.class);
                    prefs.put("firstname", json.get("firstname").toString().trim());
                    prefs.put("lastname", json.get("lastname").toString().trim());
                    prefs.put("city", json.get("city").toString().trim());
                    prefs.put("state", json.get("state").toString().trim());
                    prefs.put("country", json.get("country").toString().trim());
                    prefs.put("user_id", json.get("user_id").toString().trim());
                    prefs.put("credits", json.get("credits").toString().trim());
                    prefs.put("address", json.get("address").toString().trim());
                    prefs.put("user_type", json.get("user_type").toString().trim());
                    prefs.put("email", json.get("email").toString().trim());
                    prefs.put("phone", json.get("phone").toString().trim());
                    prefs.put("firsttime", json.get("firsttime").toString().trim());
                    return "Login Successful";
                } else if (json.containsKey("status") && json.get("status").toString().equalsIgnoreCase("202")) {
                    return "Login failed";
                } else {
                    return "Bad Response";
                }
            } else {
                return "Bad Response";
            }

        } catch (Exception ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
    }

    public static void open() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(new Login());
        frame.pack();
        frame.setLocation(x, y);
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are You Sure to Close ?", "",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    Preferences prefs = Preferences.userNodeForPackage(javaproject.Main.class);
                    try {
                        prefs.clear();
                    } catch (Exception ex) {
                        Functions.ExceptionPrinter(ex);
                    }
                    System.exit(0);
                }
            }
        });
    }
}