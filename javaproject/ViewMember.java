package javaproject;

import Client.Client;
import Utils.Functions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author rsa38 mgs33
 */
public class ViewMember extends JPanel implements ActionListener {

    private static final long serialVersionUID = 15000000;
    static int width = Functions.getWidth();
    static int height = Functions.getHeight();
    static int x = Functions.getX();
    static int y = Functions.getY();
    private JLabel search, username, firstname, lastname, city, state, country;
    private JTextField username_t, firstname_t, lastname_t;
    private JComboBox city_t, state_t, country_t;
    private JButton searchBtn, editBtn, deleteBtn, newBtn,logoutBtn;
    private JTable table;
    private static JFrame frame = null;
    private static String[] columns = new String[]{
        "Id", "firstname", "lastname", "address", "city", "state", "country", "phone", "email"
    };
    private static JProgressBar progressBar;
    private static JFrame frame1;
    private static Class[] columnClass = new Class[]{
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class
    };
    private static DefaultTableModel model;

    public ViewMember() {

        model = new DefaultTableModel(null, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };


        JButton button = new JButton("Progress");

        progressBar = new JProgressBar();


        table = new JTable(model);
        //construct preComponents
        JScrollPane js = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        js.setBounds((width) / 3, 200, (width - (width / 3)) - 10, height - 200);

        //2 * width) / 3) , 125, (width / 3) - 10
        add(js);

        TableColumnModel colModel = table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(40);
        colModel.getColumn(1).setPreferredWidth(75);
        colModel.getColumn(2).setPreferredWidth(75);
        colModel.getColumn(3).setPreferredWidth(150);
        colModel.getColumn(4).setPreferredWidth(100);
        colModel.getColumn(5).setPreferredWidth(100);
        colModel.getColumn(6).setPreferredWidth(100);
        colModel.getColumn(7).setPreferredWidth(180);
        colModel.getColumn(8).setPreferredWidth(200);




        search = new JLabel("Search : ");
        search.setBounds(10, 200, 70, 25);
        add(search);
        


        username = new JLabel("Username : ");
        username.setBounds(10, 225, 70, 30);
        add(username);
        username_t = new JTextField(5);
        username_t.setBounds(80, 225, 180, 30);
        add(username_t);

        firstname = new JLabel("Firstname : ");
        firstname.setBounds(10, 260, 70, 30);
        add(firstname);
        firstname_t = new JTextField(5);
        firstname_t.setBounds(80, 260, 180, 30);
        add(firstname_t);


        lastname = new JLabel("Lastname : ");
        lastname.setBounds(10, 295, 70, 30);
        add(lastname);
        lastname_t = new JTextField(5);
        lastname_t.setBounds(80, 295, 180, 30);
        add(lastname_t);



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
            }
        }



        country = new JLabel("Country");
        add(country);
        country.setBounds(10, 330, 70, 30);
        country_t = new JComboBox();
        country_t.addItem("Select Country");
        country_t.setSelectedItem("Select Country");
        for (int i = 0; i < cotryitems.length; i++) {
            country_t.addItem(cotryitems[i]);
        }
        add(country_t);
        country_t.setBounds(80, 330, 180, 30);


        state = new JLabel("State");
        add(state);
        state.setBounds(10, 365, 70, 30);
        state_t = new JComboBox();
        state_t.addItem("Select State");
        state_t.setSelectedItem("Select State");
        add(state_t);
        state_t.setBounds(80, 365, 180, 30);


        city = new JLabel("City");
        add(city);
        city.setBounds(10, 400, 70, 30);
        city_t = new JComboBox();
        city_t.addItem("Select City");
        city_t.setSelectedItem("Select City");
        add(city_t);
        city_t.setBounds(80, 400, 180, 30);


        country_t.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Item: " + itemEvent.getItem());
                    Client c = new Client();
                    String response = c.getStateList(itemEvent.getItem().toString());
                    String[] stateitems = {"No data"};
                    if (!response.equalsIgnoreCase("error")) {
                        try {
                            stateitems = null;
                            JSONParser parser = new JSONParser();
                            Object obj = parser.parse(response);
                            JSONObject json = (JSONObject) obj;
                            state_t.removeAllItems();
                            state_t.addItem("Select State");
                            state_t.setSelectedItem("Select State");
                            city_t.removeAllItems();
                            city_t.addItem("Select City");
                            city_t.setSelectedItem("Select City");

                            if (json.containsKey("data") && !json.get("method").toString().trim().equalsIgnoreCase("409")) {
                                stateitems = json.get("data").toString().trim().split(",");
                                for (int i = 0; i < stateitems.length; i++) {
                                    state_t.addItem(stateitems[i]);
                                }
                            }
                        } catch (Exception ex) {
                            Functions.ExceptionPrinter(ex);
                        }
                    }

                }
            }
        });

        state_t.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {

                    System.out.println("Item: " + itemEvent.getItem());
                    Client c = new Client();
                    String response = c.getCityList(itemEvent.getItem().toString());
                    String[] cityitems = {"No data"};
                    if (!response.equalsIgnoreCase("error")) {
                        try {
                            cityitems = null;
                            JSONParser parser = new JSONParser();
                            Object obj = parser.parse(response);
                            JSONObject json = (JSONObject) obj;
                            city_t.removeAllItems();
                            city_t.addItem("Select City");
                            city_t.setSelectedItem("Select City");

                            if (json.containsKey("data") && !json.get("method").toString().trim().equalsIgnoreCase("409")) {
                                cityitems = json.get("data").toString().trim().split(",");

                                for (int i = 0; i < cityitems.length; i++) {
                                    city_t.addItem(cityitems[i]);
                                }
                            }
                        } catch (Exception ex) {
                            Functions.ExceptionPrinter(ex);
                        }
                    }
                }
            }
        });










        searchBtn = new JButton("Search");
        //searchBtn.setBorder(new RoundedBorder(50)); //10 is the radius
        add(searchBtn);
        searchBtn.setBounds(0, 440, width / 3, 50);
        searchBtn.addActionListener(this);
        searchBtn.doClick();
		
		logoutBtn = new JButton("LogOut");
        add(logoutBtn);
        logoutBtn.setBounds(((2 * width) / 3), 55, (width / 3) - 10, 50);
        logoutBtn.addActionListener(this);
        logoutBtn.doClick();


        editBtn = new JButton("Edit");
        add(editBtn);
        editBtn.setBounds(((2 * width) / 3), 125, (width / 3) - 10, 50);
        editBtn.addActionListener(this);

        Preferences prefs = Preferences.userNodeForPackage(javaproject.Main.class);

        if (prefs.get("user_type", "").toString().trim().equalsIgnoreCase("0")) {
            deleteBtn = new JButton("Delete");
            add(deleteBtn);
            deleteBtn.setBounds((width / 3), 125, (width / 3) - 10, 50);
            deleteBtn.addActionListener(this);


            newBtn = new JButton("Add");
            add(newBtn);
            newBtn.setBounds(0, 125, width / 3 - 10, 50);
            newBtn.addActionListener(this);
        }
        JSeparator js1= new JSeparator();
        add(js1);
        js1.setBounds(10, 180, width, 222);
        


        setPreferredSize(new Dimension(width, height));


        setLayout(null);

        add(Functions.welcomeNote());


    }

    public void search() {
        search1();
    }

    public void search1() {
        String errormsg = "";
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        String username = username_t.getText().toString().trim(),
                firstname = firstname_t.getText().toString().trim(),
                lastname = lastname_t.getText().toString().trim(),
                country = country_t.getSelectedItem().toString().trim(),
                state = state_t.getSelectedItem().toString().trim(),
                city = city_t.getSelectedItem().toString().trim();
        try {
            Client c = new Client();
            String response = c.sendSearchData(username, firstname, lastname, country, state, city);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response);
            JSONObject json = (JSONObject) obj;
            if (json.containsKey("method") && json.get("method").toString().equalsIgnoreCase("800")) {
                if (json.containsKey("status") && json.get("status").toString().equalsIgnoreCase("801")) {
                    errormsg = "Total search result " + json.get("end");
                    String response1 = json.get("data").toString().trim();
                    Object obj1 = parser.parse(response1);
                    JSONObject json1 = (JSONObject) obj1;
                    for (int i = 1; i <= Integer.valueOf(json.get("end").toString().trim()); i++) {

                        String response2 = json1.get("data_" + i).toString().trim();
                        Object obj2 = parser.parse(response2);
                        JSONObject json2 = (JSONObject) obj2;
                        String num = Functions.formatPhoneNumber(json2.get("phone").toString());
                        model.addRow(new Object[]{
                            json2.get("user_id").toString(),
                            json2.get("firstname").toString(),
                            json2.get("lastname").toString(),
                            json2.get("address").toString(),
                            json2.get("city").toString(),
                            json2.get("state").toString(),
                            json2.get("country").toString(),
                            num,
                            json2.get("email").toString()
                        });

                    }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String errormsg = "";
        if (e.getActionCommand().trim().equalsIgnoreCase("Search")) {
            search();
        }
        if (e.getActionCommand().trim().equalsIgnoreCase("Add")) {
            frame.dispose();
            frame = null;
            new AddMember().open();
        }
		
		if(e.getActionCommand().trim().equalsIgnoreCase("LogOut")){
			frame.dispose();
			Login.open();
		}
		
        if (e.getActionCommand().trim().equalsIgnoreCase("Delete")) {
            try {
                int column = 0;
                int row = table.getSelectedRow();
                String value = table.getModel().getValueAt(row, column).toString();

                Client c = new Client();
                String response = c.sendUserDeleteData(value);

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response);
                JSONObject json = (JSONObject) obj;
                if (json.containsKey("method") && json.get("method").toString().equalsIgnoreCase("600")) {
                    if (json.containsKey("status") && json.get("status").toString().equalsIgnoreCase("601")) {
                        errormsg = "Deleted Successfully";
                    } else if (json.containsKey("status") && json.get("status").toString().equalsIgnoreCase("602")) {
                        errormsg = "Operation failed";
                    } else {
                        errormsg = "Bad response";
                    }
                }

            } catch (Exception ex) {
                Functions.ExceptionPrinter(ex);
                errormsg = "Please Select Row to Delete";
            }
            JOptionPane.showMessageDialog(null, errormsg);
            searchBtn.doClick();

        }
        if (e.getActionCommand().trim().equalsIgnoreCase("Edit")) {

            Preferences prefs = Preferences.userNodeForPackage(javaproject.Main.class);
            String user_id = "";

            if (prefs.get("user_type", "1").equalsIgnoreCase("0")) {

                try {
                    int column = 0;
                    int row = table.getSelectedRow();
                    String value = table.getModel().getValueAt(row, column).toString();
                    user_id = value;
                } catch (Exception ex) {

                    user_id = prefs.get("user_id", "-1");
                    Functions.ExceptionPrinter(ex);
                }

            } else {
                user_id = prefs.get("user_id", "-1");

            }



            if (!user_id.equalsIgnoreCase("-1")) {
                frame.dispose();
                frame = null;
                EditMember.open(user_id);
            }

        }

    }

    public static void open() {
        frame = new JFrame("View Members : " + Functions.getAppName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ViewMember());
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