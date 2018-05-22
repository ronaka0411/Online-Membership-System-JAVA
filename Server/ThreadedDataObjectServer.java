package Server;

import Utils.DataObject;
import ServerUtils.Database;
import Utils.Functions;
import Utils.MyDataObject;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ThreadedDataObjectServer {

    static int port = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Connection Port Number : ");
            port = sc.nextInt();
        } catch (Exception ex) {
            Functions.ExceptionPrinter(ex);
            port = 21315; // any random port between 1024 to 65536
        }
        Functions.setPort(port);

        port = Functions.getPort();
        System.out.println("The Server is Running on  "+port);
        try {
            ServerSocket s = new ServerSocket(port);
            for (;;) {
                Socket incoming = s.accept();
                String ip = (((InetSocketAddress) incoming.getRemoteSocketAddress()).getAddress()).toString().replace("/", "");
                System.out.println("Client with IP : " + ip + " Connected on Port : " + incoming.getPort() + " via Port :" + incoming.getLocalPort());
                new ThreadedDataObjectHandler(incoming).start();

            }
        } catch (Exception e) {
            Functions.ExceptionPrinter(e);
        }
    }
}

class ThreadedDataObjectHandler extends Thread {

    MyDataObject mdo = new MyDataObject();

    public ThreadedDataObjectHandler(Socket i) {
        incoming = i;
    }

    public void ProcessMessages(String message) {
        JSONObject obj;
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(incoming.getOutputStream());
            JSONParser parser = new JSONParser();
            Object obj1 = parser.parse(message);
            JSONObject json = (JSONObject) obj1;
            if (json.containsKey("method") && json.get("method").toString().trim().equals("100")) {
                String username = json.get("username").toString().trim(), password = json.get("password").toString().trim();
                Database d = new Database();
                obj = d.UserLogin(username, password);
                mdo.setMessage(obj.toString().trim());
                out.writeObject(mdo);
            }
            if (json.containsKey("method") && json.get("method").toString().trim().equals("900")) {
                String user_id = json.get("user_id").toString().trim();
                Database d = new Database();
                obj = d.getUser(user_id);
                mdo.setMessage(obj.toString().trim());
                out.writeObject(mdo);
            }
            if (json.containsKey("method") && json.get("method").toString().trim().equals("700")) {
                String errormsg = "", username = json.get("username").toString().trim(),
                        firstname = json.get("firstname").toString().trim(),
                        lastname = json.get("lastname").toString().trim(),
                        country = json.get("country").toString().trim(),
                        state = json.get("state").toString().trim(),
                        city = json.get("city").toString().trim();
                Database d = new Database();
                obj = d.searchUser(username, firstname, lastname, country, state, city);
                mdo.setMessage(obj.toString().trim());
                out.writeObject(mdo);
            }
            if (json.containsKey("method") && json.get("method").toString().trim().equals("300")) {
                if (json.containsKey("status") && json.get("status").toString().trim().equals("301")) {
                    Database d = new Database();
                    obj = d.getCountryList();
                    mdo.setMessage(obj.toString().trim());
                    out.writeObject(mdo);
                } else if (json.containsKey("status") && json.get("status").toString().trim().equals("302")) {
                    Database d = new Database();
                    obj = d.getStateList(json.get("country_id").toString().trim());
                    mdo.setMessage(obj.toString().trim());
                    out.writeObject(mdo);
                } else if (json.containsKey("status") && json.get("status").toString().trim().equals("303")) {
                    Database d = new Database();
                    obj = d.getCityList(json.get("state_id").toString().trim());
                    mdo.setMessage(obj.toString().trim());
                    out.writeObject(mdo);
                }
            }
            if (json.containsKey("method") && json.get("method").toString().trim().equals("500")) {
                if (json.containsKey("status") && json.get("status").toString().trim().equals("501")) {
                    String errormsg = "", username = json.get("username").toString().trim(),
                            password = json.get("password").toString().trim(),
                            firstname = json.get("firstname").toString().trim(),
                            lastname = json.get("lastname").toString().trim(),
                            address = json.get("address").toString().trim(),
                            country = json.get("country").toString().trim(),
                            state = json.get("state").toString().trim(),
                            city = json.get("city").toString().trim(),
                            mobile = json.get("mobile").toString().trim(),
                            email = json.get("email").toString().trim();
                    Database d = new Database();
                    obj = d.addUser(username, password, firstname, lastname, country, state, city, address, mobile, email);
                    mdo.setMessage(obj.toString().trim());
                    out.writeObject(mdo);
                } else if (json.containsKey("status") && json.get("status").toString().trim().equals("502")) {
                    String errormsg = "",
                            user_id = json.get("user_id").toString().trim(),
                            username = json.get("username").toString().trim(),
                            password = json.get("password").toString().trim(),
                            firstname = json.get("firstname").toString().trim(),
                            lastname = json.get("lastname").toString().trim(),
                            address = json.get("address").toString().trim(),
                            country = json.get("country").toString().trim(),
                            state = json.get("state").toString().trim(),
                            city = json.get("city").toString().trim(),
                            mobile = json.get("mobile").toString().trim(),
                            email = json.get("email").toString().trim();
                    Database d = new Database();
                    obj = d.updateUser(user_id, username, password, firstname, lastname, country, state, city, address, mobile, email);
                    mdo.setMessage(obj.toString().trim());
                    out.writeObject(mdo);
                } else if (json.containsKey("status") && json.get("status").toString().trim().equals("503")) {
                    String errormsg = "",
                            user_id = json.get("user_id").toString().trim();
                    Database d = new Database();
                    obj = d.deleteUser(user_id);
                    mdo.setMessage(obj.toString().trim());
                    out.writeObject(mdo);
                }
            }
        } catch (IOException | ParseException ex) {
            Functions.ExceptionPrinter(ex);
        } finally {
            try {
                out.close();
            } catch (Exception ex) {
                Functions.ExceptionPrinter(ex);
            }
        }
    }

    public void run() {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(incoming.getInputStream());
            myObject = (DataObject) in.readObject();
            ProcessMessages(myObject.getMessage());
            in.close();
            incoming.close();
        } catch (IOException | ClassNotFoundException e) {
            Functions.ExceptionPrinter(e);
        }
    }
    DataObject myObject = null;
    private Socket incoming;
}
