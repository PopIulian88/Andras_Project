package com.beautySalon.apiCalls;

import com.beautySalon.entity.Appointment;
import com.beautySalon.entity.Employee;
import com.beautySalon.entity.Stock;
import com.beautySalon.help.IpConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.ManyToMany;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class EmployeeCalls {

    //		GET
//		List<Employee> myEmploies = EmployeeCalls.getEmployee();
//		System.out.println(myEmploies);

//		POST
//		List<Appointment> myAppointment = AppointmentCalls.getAppointment();
//		System.out.println(myAppointment);
//		List<Stock> myStock = StockCalls.getStocks();
//		System.out.println(myStock);
//
//		List<Stock> myStockList1 = new ArrayList<>();
//		myStockList1.add(myStock.get(0));
//		myStockList1.add(myStock.get(1));
//
//		List<Appointment> myAppointmentList1 = new ArrayList<>();
//		myAppointmentList1.add(myAppointment.get(0));

//		EmployeeCalls.postEmployee("Andra", "Malaescu", "andra@yahoo.com", "0752134", "", "Cel mai frumos mesaj", 16.69, myStockList1, myAppointmentList1);

//		DELETE
//		EmployeeCalls.deleteEmployeeById(1);

    //UPDATE
//		List<Appointment> myAppointment = AppointmentCalls.getAppointment();
//		System.out.println(myAppointment);
//		List<Stock> myStock = StockCalls.getStocks();
//		System.out.println(myStock);
//
//		List<Stock> myStockList1 = new ArrayList<>();
//		myStockList1.add(myStock.get(0));
//		myStockList1.add(myStock.get(1));
//
//		List<Appointment> myAppointmentList1 = new ArrayList<>();
//		myAppointmentList1.add(myAppointment.get(0));
//
//		EmployeeCalls.updateEmployeeById(2, "Andraaa", "Malaescuuu", "andra@yahoo.com", "0752134", "", "Cel mai frumos mesaj", 16.69, myStockList1, myAppointmentList1);


    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Pentru a lua employee-ul
    public static List<Employee> getEmployee(){
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/employees";

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
            List<List<Employee>> myEmployee = Arrays.asList(objectMapper.readValue(response.toString(), new TypeReference<List<Employee>>() {}));

            return myEmployee.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // Pentru a crea employee
    public static void postEmployee(String firstName, String lastName, String email, String telNo, String picURL, String massage, double wallet, List<Stock> myStock, List<Appointment> appointmentList) {
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea POST
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/addEmployee";
            URL url = new URL(apiUrl);
            // Deschide conexiunea către URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                // Setează metoda HTTP pe POST
                connection.setRequestMethod("POST");

                // Activează trimiterea de date către server
                connection.setDoOutput(true);

//                System.out.println(myStock);
//                System.out.println(appointmentList);

                // Definirea datelor pe care vrei să le trimiți (exemplu: JSON)
                String postData = "{\"firstName\":\"" + firstName + "\"," +
                        "            \"lastName\":\"" + lastName + "\"," +
                        "            \"email\":\"" + email + "\"," +
                        "            \"telNo\":\"" + telNo + "\"," +
                        "            \"picURL\":\"" + picURL + "\"," +
                        "            \"massage\":\"" + massage + "\"," +
                        "            \"wallet\":\"" + wallet + "\"," +
                        "            \"myStock\":" + myStock.toString() + "," +
                        "            \"appointmentList\":" + appointmentList.toString() + "}";

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

    //Pentru a sterge un employee-ul in fucntie de Id
    public static void deleteEmployeeById(int id) {
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea DELETE
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/employee/delete/" + id;
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

    //Pentru a updata un employee
    public static void updateEmployeeById(int id, String firstName, String lastName, String email, String telNo, String picURL, String massage, double wallet, List<Stock> myStock, List<Appointment> appointmentList) {
        IpConfig ipConfig = new IpConfig();

        try {
            // Specifică URL-ul API-ului către care faci cererea PUT (sau PATCH, în funcție de tipul de actualizare suportat)
            String apiUrl = "http://" + ipConfig.getMY_IP() + ":8080/employee/update";
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
                                    "\"picURL\":\"" + picURL + "\"," +
                                    "\"massage\":\"" + massage + "\"," +
                                    "\"wallet\":\"" + wallet + "\"," +
                                    "\"myStock\":" + myStock.toString() + "," +
                                    "\"appointmentList\":" + appointmentList.toString() + "}";

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
