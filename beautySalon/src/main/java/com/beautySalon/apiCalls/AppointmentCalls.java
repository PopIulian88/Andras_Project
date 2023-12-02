package com.beautySalon.apiCalls;

import com.beautySalon.entity.Appointment;
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
import java.util.Date;
import java.util.List;

public class AppointmentCalls {

    //		List<Appointment> myAppointment = AppointmentCalls.getAppointment();
    //		System.out.println(myAppointment);
    //
    //		AppointmentCalls.postAppointment("Julian", "Pop", "iulian@yahoo.com", "0712345", 1, 10, 2023 , "Test", "Cel mai cool mesaj");
    //		AppointmentCalls.deleteAppointmentById(1);
    //		AppointmentCalls.updateAppointmentById(2, "Iulian", "Pop", "iulian@yahoo.com", "0712345", 1, 10, 2023 , "Test", "Cel mai cool mesaj");

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Pentru a lua programari
    public static List<Appointment> getAppointment(){
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/appointments";

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
            List<List<Appointment>> myAppointments = Arrays.asList(objectMapper.readValue(response.toString(), new TypeReference<List<Appointment>>() {}));

            return myAppointments.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Pentru a crea programari
    public static void postAppointment(String firstName, String lastName, String email, String telNo, int day, int month, int year, String service, String massage) {
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea POST
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/addAppointment";
            URL url = new URL(apiUrl);
            // Deschide conexiunea către URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                // Setează metoda HTTP pe POST
                connection.setRequestMethod("POST");

                // Activează trimiterea de date către server
                connection.setDoOutput(true);

                // Definirea datelor pe care vrei să le trimiți (exemplu: JSON)
                String postData = "{\"firstName\":\"" + firstName + "\"," +
                                    "\"lastName\":\"" + lastName + "\"," +
                                    "\"email\":\"" + email + "\"," +
                                    "\"telNo\":\"" + telNo + "\"," +
                                    "\"day\":\"" + day + "\"," +
                                    "\"month\":\"" + month + "\"," +
                                    "\"year\":\"" + year + "\"," +
                                    "\"service\":\"" + service + "\"," +
                                    "\"massage\":\"" + massage + "\"}";

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

    //Pentru a sterge un programarile in fucntie de Id
    public static void deleteAppointmentById(int id) {
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea DELETE
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/appointment/delete/" + id;
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

    //Pentru a updata o programare
    public static void updateAppointmentById(int id, String firstName, String lastName, String email, String telNo, int day, int month, int year, String service, String massage) {
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea PUT (sau PATCH, în funcție de tipul de actualizare suportat)
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/appointment/update";
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
                                    "\"firstName\":\"" + firstName + "\"," +
                                    "\"lastName\":\"" + lastName + "\"," +
                                    "\"email\":\"" + email + "\"," +
                                    "\"telNo\":\"" + telNo + "\"," +
                                    "\"day\":\"" + day + "\"," +
                                    "\"month\":\"" + month + "\"," +
                                    "\"year\":\"" + year + "\"," +
                                    "\"service\":\"" + service + "\"," +
                                    "\"massage\":\"" + massage + "\"}";

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
