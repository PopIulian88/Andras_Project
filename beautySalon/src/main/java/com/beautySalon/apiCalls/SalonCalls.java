package com.beautySalon.apiCalls;

import com.beautySalon.entity.Salon;
import com.beautySalon.entity.Stock;
import com.beautySalon.help.IpConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class SalonCalls {
    //		List<Salon> mySalon =  SalonCalls.getSalon();
    //		System.out.println(mySalon);
    //
    //		SalonCalls.postSalon("Adresa", "fataCartii", "ista");
    //		SalonCalls.deleteSalonById(1);
    //		SalonCalls.updateSalonById(2, "Adresa", "fataCartii", "@iulian352");

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Pentru a lua salon-ul
    public static List<Salon> getSalon(){
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/salons";

            URL url = new URL(apiUrl);

            // Deschide conexiunea către URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Setează metoda HTTP pe GET (sau POST, etc., în funcție de necesități)
            connection.setRequestMethod("GET");

            // Obține codul de răspuns HTTP(200 = OK)
//            int responseCode = connection.getResponseCode();
//            System.out.println("Cod de răspuns: " + responseCode);

            // Citește răspunsul de la server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Afișează răspunsul
//            System.out.println("Răspuns de la server: " + response.toString());

            // Închide conexiunea
            connection.disconnect();

//            return response.toString();
            List<List<Salon>> mySalon = Arrays.asList(objectMapper.readValue(response.toString(), new TypeReference<List<Salon>>() {}));

            return mySalon.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // Pentru a crea salon
    public static void postSalon(String address, String facebook, String instagram) {
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea POST
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/addSalon";
            URL url = new URL(apiUrl);
            // Deschide conexiunea către URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                // Setează metoda HTTP pe POST
                connection.setRequestMethod("POST");

                // Activează trimiterea de date către server
                connection.setDoOutput(true);

                // Definirea datelor pe care vrei să le trimiți (exemplu: JSON)
                String postData = "{\"address\":\"" + address + "\"," +
                        "            \"facebook\":\"" + facebook + "\"," +
                        "            \"instagram\":\"" + instagram + "\"}";

                byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

                // Setează tipul de conținut al cererii
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

                // Trimite datele către server
                try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                    wr.write(postDataBytes);
                }

//                // Obține codul de răspuns HTTP
//                int responseCode = connection.getResponseCode();
//                System.out.println("Cod de răspuns: " + responseCode);

                // Citește răspunsul de la server
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Afișează răspunsul
//                System.out.println("Răspuns de la server: " + response.toString());

            } finally {
                // Închide conexiunea
                connection.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Pentru a sterge un salon in fucntie de Id
    public static void deleteSalonById(int id) {
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea DELETE
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/salon/delete/" + id;
            URL url = new URL(apiUrl);
            // Deschide conexiunea către URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                // Setează metoda HTTP pe DELETE
                connection.setRequestMethod("DELETE");

                // Activează trimiterea de date către server (nu este necesară pentru DELETE, dar o păstrăm pentru consistență)
                connection.setDoOutput(true);

                // Setează tipul de conținut al cererii (nu este necesar pentru DELETE, dar o păstrăm pentru consistență)
                connection.setRequestProperty("Content-Type", "application/json");

//                // Obține codul de răspuns HTTP
//                int responseCode = connection.getResponseCode();
//                System.out.println("Cod de răspuns: " + responseCode);

                // Citește răspunsul de la server (în cazul în care serverul returnează un mesaj)
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

//                // Afișează răspunsul (în cazul în care serverul returnează un mesaj)
//                System.out.println("Răspuns de la server: " + response.toString());

            } finally {
                // Închide conexiunea
                connection.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Pentru a updata un salon
    public static void updateSalonById(int id, String address, String facebook, String instagram) {
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea PUT (sau PATCH, în funcție de tipul de actualizare suportat)
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/salon/update";
            URL url = new URL(apiUrl);
            // Deschide conexiunea către URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                // Setează metoda HTTP pe PUT (sau PATCH)
                connection.setRequestMethod("PUT");

                // Activează trimiterea de date către server
                connection.setDoOutput(true);

                // Definirea datelor pe care vrei să le trimiți pentru actualizare (exemplu: JSON)
                String updateData = "{\"id\":\"" + id + "\"," +
                                    "\"address\":\"" + address + "\"," +
                                    "\"facebook\":\"" + facebook + "\"," +
                                    "\"instagram\":\"" + instagram + "\"}";

                byte[] updateDataBytes = updateData.getBytes(StandardCharsets.UTF_8);

                // Setează tipul de conținut al cererii
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Content-Length", String.valueOf(updateDataBytes.length));

                // Trimite datele către server
                try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                    wr.write(updateDataBytes);
                }

//                // Obține codul de răspuns HTTP
//                int responseCode = connection.getResponseCode();
//                System.out.println("Cod de răspuns: " + responseCode);

                // Citește răspunsul de la server (în cazul în care serverul returnează un mesaj)
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

//                // Afișează răspunsul (în cazul în care serverul returnează un mesaj)
//                System.out.println("Răspuns de la server: " + response.toString());

            } finally {
                // Închide conexiunea
                connection.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
