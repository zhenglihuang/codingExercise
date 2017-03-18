package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostJsonHelper {
    final String URL = "https://2016.api.levelmoney.com/api/v2/core/get-all-transactions";
    final String ALL_TRANSACTION_BODY = "{\"args\": {\"uid\":  1110590645, \"token\":  \"87530B5E4C2BA27DE5CAFF503CE39481\", \"api-token\":  \"AppTokenForInterview\", \"json-strict-mode\": false, \"json-verbose-response\": false}}";
    final int TIME_OUT = 1000;

    public String postAllTransactions() throws Exception {
        System.out.println("\nSend Http POST request to get all transactions");
        String jsonInString = postJSON(URL, ALL_TRANSACTION_BODY, TIME_OUT, "POST");

        return jsonInString;
    }

    private String postJSON(String url, String json, int timeout, String method) {
        HttpURLConnection connection = null;
        try {

            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod(method);

            //set the sending type and receiving type to json
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setAllowUserInteraction(false);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            if (json != null) {
                //set the content length of the body
                connection.setRequestProperty("Content-length", json.getBytes().length + "");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);

                //send the json as body of the request
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(json.getBytes("UTF-8"));
                outputStream.close();
            }

            //Connect to the server
            connection.connect();

            int status = connection.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();

                    //return received string
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            System.out.println("HTTP Client: Error in http connection" + ex.toString());
        } catch (IOException ex) {
            System.out.println("HTTP Client: Error in http connection" + ex.toString());
        } catch (Exception ex) {
            System.out.println("HTTP Client: Error in http connection" + ex.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception ex) {
                    System.out.println("HTTP Client: Error in http connection" + ex.toString());
                }
            }
        }
        return null;
    }

}