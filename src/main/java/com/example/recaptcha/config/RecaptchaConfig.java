package com.example.recaptcha.config;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonObject;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

@Component
public class RecaptchaConfig {

    private static String url;
	
    @Value("${google.recaptcha.url}")
	private void setUrl(String googleUrl) {
		url = googleUrl;
	}
    
	private static String secretKey;
	
	@Value("${google.recaptcha.key.secret}")
	private void setSecretkey(String key) {
		secretKey = key;
	}
	
	private final static String USER_AGENT = "Mozilla/5.0";

    public static boolean verify(String gRecaptchaResponse) throws IOException {
    	if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        try{
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            // add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + secretKey + "&response="
                    + gRecaptchaResponse;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + postParams);
            System.out.println("Response Code : " + responseCode);

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

            //parse JSON response and return 'success' value
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            
            return jsonObject.getBoolean("success");
        }catch(IOException e){
            e.printStackTrace();
            throw new IOException();
        }
    }
}
