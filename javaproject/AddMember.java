package javaproject;

import Client.Client;
import Utils.Functions;
import java.awt.*;
import java.awt.event.*;
import java.util.prefs.Preferences;

import javax.swing.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author rsa38 mgs33
 */
public class AddMember extends JPanel implements ActionListener {

    private static final long serialVersionUID = 15000000;
    static int width = Functions.getWidth();
    static int height = Functions.getHeight();
    static int x = Functions.getX();
    static int y = Functions.getY();
    private JLabel username;
    private JLabel password;
    private JTextField username_tt;
    private JPasswordField password_tt;
    private JLabel firstname;
    private JLabel lastname;
    private JTextField firstname_tt;
    private JTextField lastname_tt;
    private JLabel address;
    private JTextArea address_tt;
    private JLabel country;
    private JComboBox country_tt;
    private JLabel state;
    private JComboBox state_tt;
    private JLabel city;
    private JComboBox city_tt;
    private JButton addBtn, bckBtn;
    private JLabel mobile;
    private JLabel email;
    private JTextField mobile_tt;
    private JTextField email_tt;
    private static JFrame frame = null;

    public AddMember() {




        username = new JLabel("Username");
        add(username);
        username.setBounds(width / 4, 110, 100, 25);
        username_tt = new JTextField(5);
        add(username_tt);
        username_tt.setBounds(width / 4 + 100, 110, 300, 25);


        password = new JLabel("Password");
        add(password);
        password.setBounds(width / 4, 140, 100, 25);
        password_tt = new JPasswordField(5);
        add(password_tt);
        password_tt.setBounds(width / 4 + 100, 140, 300, 25);





        firstname = new JLabel("Firstname");
        add(firstname);
        firstname.setBounds(width / 4, 170, 100, 25);
        firstname_tt = new JTextField(5);
        add(firstname_tt);
        firstname_tt.setBounds(width / 4 + 100, 170, 300, 25);


        lastname = new JLabel("Lastname");
        add(lastname);
        lastname.setBounds(width / 4, 200, 100, 25);
        lastname_tt = new JTextField(5);
        add(lastname_tt);
        lastname_tt.setBounds(width / 4 + 100, 200, 300, 25);




        address = new JLabel("Address");
        add(address);
        address.setBounds(width / 4, 230, 100, 25);
        address_tt = new JTextArea(5, 5);
        add(address_tt);
        address_tt.setBounds(width / 4 + 100, 230, 300, 75);




        Client c = new Client();
        String response = c.getCountryList();
        String[] cotryitems = {"No data"};
        if (!response.equalsIgnoreCase("error")) {
            try {
                cotryitems = null;
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response);
                JSONObject json = (JSONObject) obj;
                if (json.containsKey("data") && !json.get("method").toString().trim().equalsIgnoreCase("409")) {
                    cotryitems = json.get("data").toString().trim().split(",");
                }
            } catch (Exception ex) {
                Functions.ExceptionPrinter(ex);
            }
        }



        country = new JLabel("Country");
        add(country);
        country.setBounds(width / 4, 310, 100, 25);
        country_tt = new JComboBox();
        country_tt.addItem("Select Country");
        country_tt.setSelectedItem("Select Country");
        for (int i = 0; i < cotryitems.length; i++) {
            country_tt.addItem(cotryitems[i]);
        }
        add(country_tt);
        country_tt.setBounds(width / 4 + 100, 310, 300, 25);


        state = new JLabel("State");
        add(state);
        state.setBounds(width / 4, 340, 100, 25);
        state_tt = new JComboBox();
        state_tt.addItem("Select State");
        state_tt.setSelectedItem("Select State");
        add(state_tt);
        state_tt.setBounds(width / 4 + 100, 340, 300, 25);


        city = new JLabel("City");
        add(city);
        city.setBounds(width / 4, 370, 100, 25);
        city_tt = new JComboBox();
        city_tt.addItem("Select City");
        city_tt.setSelectedItem("Select City");
        add(city_tt);
        city_tt.setBounds(width / 4 + 100, 370, 300, 25);


        country_tt.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    Client c = new Client();
                    String response = c.getStateList(itemEvent.getItem().toString());
                    String[] stateitems = {"No data"};
                    if (!response.equalsIgnoreCase("error")) {
                        try {
                            stateitems = null;
                            JSONParser parser = new JSONParser();
                            Object obj = parser.parse(response);
                            JSONObject json = (JSONObject) obj;
                            state_tt.removeAllItems();
                            state_tt.addItem("Select State");
                            state_tt.setSelectedItem("Select State");
                            city_tt.removeAllItems();
                            city_tt.addItem("Select City");
                            city_tt.setSelectedItem("Select City");

                            if (json.containsKey("data") && !json.get("method").toString().trim().equalsIgnoreCase("409")) {
                                stateitems = json.get("data").toString().trim().split(",");
                                for (int i = 0; i < stateitems.length; i++) {
                                    state_tt.addItem(stateitems[i]);
                                }
                            }
                        } catch (Exception ex) {
                            Functions.ExceptionPrinter(ex);
                        }
                    }

                }
            }
        });

        state_tt.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    Client c = new Client();
                    String response = c.getCityList(itemEvent.getItem().toString());
                    String[] cityitems = {"No data"};
                    if (!response.equalsIgnoreCase("error")) {
                        try {
                            cityitems = null;
                            JSONParser parser = new JSONParser();
                            Object obj = parser.parse(response);
                            JSONObject json = (JSONObject) obj;
                            city_tt.removeAllItems();
                            city_tt.addItem("Select City");
                            city_tt.setSelectedItem("Select City");

                            if (json.containsKey("data") && !json.get("method").toString().trim().equalsIgnoreCase("409")) {
                                cityitems = json.get("data").toString().trim().split(",");

                                for (int i = 0; i < cityitems.length; i++) {
                                    city_tt.addItem(cityitems[i]);
                                }
                            }
                        } catch (Exception ex) {
                            Functions.ExceptionPrinter(ex);
                        }
                    }
                }
            }
        });





        mobile = new JLabel("Mobile no:");
        add(mobile);
        mobile.setBounds(width / 4, 400, 100, 25);
        mobile_tt = new JTextField(5);
        add(mobile_tt);
        mobile_tt.setBounds(width / 4 + 100, 400, 300, 25);
        mobile_tt.setText("Phone Number Without Countrycode");
        mobile_tt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (mobile_tt.getText().equals("Phone Number Without Countrycode")) {
                    mobile_tt.setText("");
                    mobile_tt.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (mobile_tt.getText().isEmpty()) {
                    mobile_tt.setForeground(Color.GRAY);
                    mobile_tt.setText("Phone Number Without Countrycode");
                }
            }
        });



        email = new JLabel("Email :");
        add(email);
        email.setBounds(width / 4, 430, 100, 25);
        email_tt = new JTextField(5);
        add(email_tt);
        email_tt.setBounds(width / 4 + 100, 430, 300, 25);


        addBtn = new JButton("Add");
        addBtn.setBounds((width / 4 + 100) + 150, 460, 150, 30);
        add(addBtn);
        addBtn.addActionListener(this);


        bckBtn = new JButton("Back");
        bckBtn.setBounds(width / 4 + 100, 460, 140, 30);
        add(bckBtn);
        bckBtn.addActionListener(this);

        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        add(Functions.welcomeNote());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().trim().equalsIgnoreCase("Back")) {
            frame.dispose();
            frame = null;
            ViewMember.open();
        }
        if (e.getActionCommand().trim().equalsIgnoreCase("Add")) {
            String errormsg = "", username = username_tt.getText().toString().trim(),
                    password = new String(password_tt.getPassword()).trim().trim(),
                    firstname = firstname_tt.getText().toString().trim(),
                    lastname = lastname_tt.getText().toString().trim(),
                    address = address_tt.getText().toString().trim(),
                    country = country_tt.getSelectedItem().toString().trim(),
                    state = state_tt.getSelectedItem().toString().trim(),
                    city = city_tt.getSelectedItem().toString().trim(),
                    mobile = mobile_tt.getText().toString().trim(),
                    email = email_tt.getText().toString().trim();

            mobile = mobile.replace("(", "");
            mobile = mobile.replace(")", "");
            mobile = mobile.replace(".", "");
            mobile = mobile.replace("-", "");


            if (username.equalsIgnoreCase("")) {
                errormsg = "Enter Username";
                JOptionPane.showMessageDialog(null, errormsg);

            } else if (password.equalsIgnoreCase("")) {
                errormsg = "Enter Paasword";
                JOptionPane.showMessageDialog(null, errormsg);

            } else if (firstname.equalsIgnoreCase("")) {
                errormsg = "Enter Name";
                JOptionPane.showMessageDialog(null, errormsg);

            } else if (lastname.equalsIgnoreCase("")) {
                errormsg = "Enter Lastname";
                JOptionPane.showMessageDialog(null, errormsg);

            } else if (address.equalsIgnoreCase("")) {
                errormsg = "Enter Address";
                JOptionPane.showMessageDialog(null, errormsg);

            } else if (country.equalsIgnoreCase("Select Country")) {
                errormsg = "Select Country";
                JOptionPane.showMessageDialog(null, errormsg);

            } else if (state.equalsIgnoreCase("Select State")) {
                errormsg = "Select State";
                JOptionPane.showMessageDialog(null, errormsg);

            } else if (city.equalsIgnoreCase("Select City")) {
                errormsg = "Select City";
                JOptionPane.showMessageDialog(null, errormsg);

            } else if (email.equalsIgnoreCase("") || !Functions.validateEmail(email)) {
                errormsg = "Enter Valid Email Address";
                JOptionPane.showMessageDialog(null, errormsg);

            } else if (mobile.equalsIgnoreCase("Phone Number Without Countrycode") || !Functions.validatePhoneNumber(mobile)) {
                errormsg = "Enter Valid Mobile Number";
                JOptionPane.showMessageDialog(null, errormsg);
            } else {
                username_tt.setText("");
                firstname_tt.setText("");
                lastname_tt.setText("");
                address_tt.setText("");
                mobile_tt.setText("");
                email_tt.setText("");
                password_tt.setText("");
                city_tt.setSelectedIndex(0);
                state_tt.setSelectedIndex(0);
                country_tt.setSelectedIndex(0);
                password = Functions.MD5(password);
                try {
                    Client c = new Client();
                    String response = c.sendUserData(username, password, firstname, lastname, country, state, city, address, mobile, email);
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(response);
                    JSONObject json = (JSONObject) obj;
                    if (json.containsKey("method") && json.get("method").toString().equalsIgnoreCase("600")) {
                        if (json.containsKey("status") && json.get("status").toString().equalsIgnoreCase("601")) {
                            errormsg = "Successful";
                            JOptionPane.showMessageDialog(null, errormsg);
                        }
                        if (json.containsKey("status") && json.get("status").toString().equalsIgnoreCase("602")) {
                            errormsg = "Data Already exists or db error";
                            JOptionPane.showMessageDialog(null, errormsg);
                        }

                    } else {
                        errormsg = "Bad Response";
                        JOptionPane.showMessageDialog(null, errormsg);
                    }
                } catch (Exception ex) {
                    Functions.ExceptionPrinter(ex);
                    errormsg = "error";
                    JOptionPane.showMessageDialog(null, errormsg);
                }
            }
        }
    }

    public static void open() {
        frame = new JFrame("Add Member : " + Functions.getAppName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new AddMember());
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
