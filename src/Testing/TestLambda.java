package Testing;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;

public class TestLambda {


    private static final String USER_AGENT = "Mozilla/5.0";

    // The function sendGET makes an https request and returns the response in string format
    private static String sendGET(String GET_URL) throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }

        return "no";
    }


    // The function checkAuthenticatefunc checks the lambda function authentication
    @Test
    public void checkAuthenticatefunc() throws IOException {
        String GET_URL = "https://kix7tx694g.execute-api.us-east-1.amazonaws.com/dev/authenticate/2494100525";
        String result = sendGET(GET_URL);
        assertEquals("{\"status\":true}", result);
    }

    // The function checkLogin checks the lambda function signIn
    @Test
    public void checkLogin() throws IOException {
        String GET_URL = "https://kix7tx694g.execute-api.us-east-1.amazonaws.com/dev/signIn/1001/Dilan/Dilan1";
        String result = sendGET(GET_URL);
        assertEquals("{\"status\":\"login successful\"}", result);
    }


}














