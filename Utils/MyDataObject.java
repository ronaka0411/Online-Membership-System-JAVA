package Utils;

import org.json.simple.JSONObject;

/**
 *
 * @author rsa38 mgs33
 */
public class MyDataObject extends DataObject {

    public void setUsernamePassword(String username, String password) {
        JSONObject json = new JSONObject();
        json.put("method", 100);
        json.put("username", username);
        json.put("password", password);
        setMessage(json.toString().trim());
    }

    public void setUserGetRequest(String user_id) {
        JSONObject json = new JSONObject();
        json.put("method", 900);
        json.put("user_id", user_id);
        setMessage(json.toString().trim());
    }

    public void setSearchData(
            String username,
            String firstname,
            String lastname,
            String country,
            String state,
            String city) {
        JSONObject json = new JSONObject();
        json.put("method", 700);
        json.put("username", username);
        json.put("firstname", firstname);
        json.put("lastname", lastname);
        json.put("country", country);
        json.put("state", state);
        json.put("city", city);
        setMessage(json.toString().trim());
    }

    public void setUserData(
            String username,
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
        json.put("method", 500);
        json.put("status", 501);
        json.put("username", username);
        json.put("password", password);
        json.put("firstname", firstname);
        json.put("lastname", lastname);
        json.put("country", country);
        json.put("state", state);
        json.put("city", city);
        json.put("address", address);
        json.put("mobile", mobile);
        json.put("email", email);
        setMessage(json.toString().trim());
    }

    public void setUserEditData(String user_id,
            String username,
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
        json.put("method", 500);
        json.put("status", 502);
        json.put("user_id", user_id);
        json.put("username", username);
        json.put("password", password);
        json.put("firstname", firstname);
        json.put("lastname", lastname);
        json.put("country", country);
        json.put("state", state);
        json.put("city", city);
        json.put("address", address);
        json.put("mobile", mobile);
        json.put("email", email);
        setMessage(json.toString().trim());
    }

    public void setUserDeleteData(String user_id) {
        JSONObject json = new JSONObject();
        json.put("method", 500);
        json.put("status", 503);
        json.put("user_id", user_id);
        setMessage(json.toString().trim());
    }

    public void setCountryList() {
        JSONObject json = new JSONObject();
        json.put("method", 300);
        json.put("status", 301);
        setMessage(json.toString().trim());
    }

    public void setStateList(String country_id) {
        JSONObject json = new JSONObject();
        json.put("method", 300);
        json.put("status", 302);
        json.put("country_id", country_id);
        setMessage(json.toString().trim());
    }

    public void setCityList(String state_id) {
        JSONObject json = new JSONObject();
        json.put("method", 300);
        json.put("status", 303);
        json.put("state_id", state_id);
        setMessage(json.toString().trim());
    }

    public MyDataObject() {
        DataObject dataObject = new DataObject();
    }
}
