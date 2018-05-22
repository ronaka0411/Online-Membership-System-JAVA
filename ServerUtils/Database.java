package ServerUtils;

import Utils.Functions;
import java.sql.*;
import org.json.simple.JSONObject;

/**
 *
 * @author rsa38 mgs33
 */
public class Database {

    static Connection con = null;

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
 //           con = DriverManager.getConnection(
 //                   "jdbc:mysql://localhost/db2", "root", "root");
			con = DriverManager.getConnection(
                    "jdbc:mysql://sql2.njit.edu:3306/rsa38", "rsa38", "rsa38");
        } catch (Exception e) {
            Functions.ExceptionPrinter(e);
        }
    }

    public void disconnect() {
        if (con != null) {

            try {
                con.close();
            } catch (SQLException ex) {
                Functions.ExceptionPrinter(ex);
            }
        }
    }

    public JSONObject getCountryList() {
        JSONObject json = new JSONObject();
        Connect();
        json.put("method", 400);
        json.put("status", 409);
        String data = "";
        try {
            PreparedStatement statement = con.prepareStatement("select * from countries");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                json.put("status", 401);
                data += rs.getString("name") + ",";
            }
            data = data.substring(0, data.length() - 1);
            json.put("data", data);
        } catch (Exception e) {
            Functions.ExceptionPrinter(e);
        }
        disconnect();
        return json;
    }

    public JSONObject getStateList(String country) {
        JSONObject json = new JSONObject();
        Connect();
        json.put("method", 400);
        json.put("status", 409);
        String data = "";
        try {
            PreparedStatement statement = con.prepareStatement("select * from states as s,countries as c where s.country_id=c.id and c.name= ? ");
            statement.setString(1, country.toString().trim());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                json.put("status", 402);
                data += rs.getString("s.name") + ",";
            }
            data = data.substring(0, data.length() - 1);
            json.put("data", data);
        } catch (Exception e) {
            Functions.ExceptionPrinter(e);
        }
        disconnect();
        return json;
    }

    public JSONObject getCityList(String state) {
        JSONObject json = new JSONObject();
        Connect();
        json.put("method", 400);
        json.put("status", 409);
        String data = "";
        try {
            PreparedStatement statement = con.prepareStatement("select * from cities as c,states as s where c.state_id=s.id and s.name= ? ");
            statement.setString(1, state.toString().trim());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                json.put("status", 402);
                data += rs.getString("c.name") + ",";
            }
            data = data.substring(0, data.length() - 1);
            json.put("data", data);

        } catch (Exception e) {
            Functions.ExceptionPrinter(e);
        }
        disconnect();
        return json;
    }

    public JSONObject addUser(String username,
            String password,
            String firstname,
            String lastname,
            String country,
            String state,
            String city,
            String address,
            String mobile,
            String email) {
        JSONObject json = new JSONObject();
        json.put("method", "600");
        try {
            Connect();
            String query = "insert into java_users ("
                    + "username,"
                    + "password,"
                    + "firstname,"
                    + "lastname,"
                    + "country,"
                    + "state,"
                    + "city,"
                    + "address,"
                    + "phone,"
                    + "email) "
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, password);
            preparedStmt.setString(3, firstname);
            preparedStmt.setString(4, lastname);
            preparedStmt.setString(5, country);
            preparedStmt.setString(6, state);
            preparedStmt.setString(7, city);
            preparedStmt.setString(8, address);
            preparedStmt.setString(9, mobile);
            preparedStmt.setString(10, email);
            preparedStmt.execute();
            disconnect();
            json.put("status", "601");
        } catch (SQLException ex) {
            Functions.ExceptionPrinter(ex);
            json.put("status", "602");
        }
        return json;
    }

    public JSONObject updateUser(String user_id, String username,
            String password,
            String firstname,
            String lastname,
            String country,
            String state,
            String city,
            String address,
            String mobile,
            String email) {
        Connect();
        JSONObject json = new JSONObject();
        int status = 0;
        json.put("method", 600);
        json.put("status", 602); // Login failed
        try {
            PreparedStatement statement = con.prepareStatement("update  java_users set username = ?,password=?,firstname=?,lastname=?,country=?,state=?,city=?,address=?,phone=?,email=?  where user_id = ? ");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstname);
            statement.setString(4, lastname);
            statement.setString(5, country);
            statement.setString(6, state);
            statement.setString(7, city);
            statement.setString(8, address);
            statement.setString(9, mobile);
            statement.setString(10, email);
            statement.setString(11, user_id);
            int rs = statement.executeUpdate();
            if (rs == 1) {
                json.put("status", 601);
            }
        } catch (Exception e) {
            Functions.ExceptionPrinter(e);
        }
        disconnect();
        return json;
    }

    public JSONObject getUser(String user_id) {
        Connect();
        JSONObject json = new JSONObject();
        int status = 0;
        json.put("method", 1000);
        json.put("status", 1002);
        try {
            PreparedStatement statement = con.prepareStatement("select * from java_users where user_id = ? limit 0,1");
            statement.setString(1, user_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                status = rs.getInt("status");
                json.put("status", 1001);
                json.put("firsttime", status);
                json.put("user_id", user_id);
                json.put("username", rs.getString("username"));
                json.put("password", rs.getString("password"));
                json.put("firstname", rs.getString("firstname"));
                json.put("lastname", rs.getString("lastname"));
                json.put("address", rs.getString("address"));
                json.put("city", rs.getString("city"));
                json.put("state", rs.getString("state"));
                json.put("country", rs.getString("country"));
                json.put("credits", rs.getInt("credits"));
                json.put("email", rs.getString("email"));
                json.put("phone", rs.getString("phone"));
            }
        } catch (Exception e) {
            Functions.ExceptionPrinter(e);
        }
        disconnect();
        return json;
    }

    public JSONObject deleteUser(String user_id) {
        JSONObject json = new JSONObject();
        json.put("method", "600");
        try {
            Connect();
            String query = "delete from java_users  where user_id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Integer.valueOf(user_id));
            preparedStmt.execute();
            disconnect();
            json.put("status", "601");
        } catch (SQLException ex) {
            Functions.ExceptionPrinter(ex);
            json.put("status", "602");
        }
        return json;
    }

    public JSONObject UserLogin(String username, String password) {
        Connect();
        JSONObject json = new JSONObject();
        int credits = 0;
        int user_type = 1;  //1 is member //0 is admin
        int user_id = 0;
        int status = 0;
        json.put("method", 200);
        json.put("status", 202); // Login failed
        try {
            PreparedStatement statement = con.prepareStatement("select * from java_users where username = ? and password = ? limit 0,1");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user_id = rs.getInt("user_id");
                username = rs.getString("username");
                user_type = rs.getInt("user_type");
                status = rs.getInt("status");
                json.put("status", 201);
                json.put("firsttime", status);
                json.put("user_id", user_id);
                json.put("user_type", user_type);
                json.put("username", username);
                json.put("firstname", rs.getString("firstname"));
                json.put("lastname", rs.getString("lastname"));
                json.put("address", rs.getString("address"));
                json.put("city", rs.getString("city"));
                json.put("state", rs.getString("state"));
                json.put("country", rs.getString("country"));
                json.put("credits", rs.getInt("credits"));
                json.put("email", rs.getString("email"));
                json.put("phone", rs.getString("phone"));
            }

        } catch (Exception e) {
            Functions.ExceptionPrinter(e);
        }
        disconnect();
        return json;
    }

    public JSONObject searchUser(String username,
            String firstname,
            String lastname,
            String country,
            String state,
            String city) {
        JSONObject json = new JSONObject();
        json.put("method", "800");
        try {
            Connect();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM java_users,countries c  where c.name=java_users.country ";
            if (!username.equalsIgnoreCase("")) {
                query += " AND username LIKE '%" + username + "%'";
            }
            if (!firstname.equalsIgnoreCase("")) {
                query += " AND firstname LIKE '%" + firstname + "%'";
            }
            if (!lastname.equalsIgnoreCase("")) {
                query += " AND lastname LIKE '%" + lastname + "%'";
            }
            if (!country.equalsIgnoreCase("Select Country")) {
                query += " AND country LIKE '%" + country + "%'";
            }
            if (!state.equalsIgnoreCase("Select State")) {
                query += " AND state LIKE '%" + state + "%'";
            }
            if (!city.equalsIgnoreCase("Select City")) {
                query += " AND city LIKE '%" + city + "%'";
            }
            ResultSet rows = stmt.executeQuery(query);
            int count = 0;
            JSONObject data = new JSONObject();
            while (rows.next()) {
                JSONObject js = new JSONObject();
                count++;
                js.put("user_id", rows.getString("user_id"));
                js.put("firstname", rows.getString("firstname"));
                js.put("lastname", rows.getString("lastname"));
                js.put("address", rows.getString("address"));
                js.put("city", rows.getString("city"));
                js.put("state", rows.getString("state"));
                js.put("country", rows.getString("country"));
                js.put("email", rows.getString("email"));
                js.put("phone", "+" + rows.getString("phonecode") + " " + rows.getString("phone"));
                data.put("data_" + count, js);
            }
            json.put("start", 0);
            json.put("end", count);
            json.put("data", data);
            disconnect();
            json.put("status", "801");
        } catch (SQLException ex) {
            Functions.ExceptionPrinter(ex);
            json.put("status", "802");
        }
        return json;
    }
}
