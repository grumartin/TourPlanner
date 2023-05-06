package at.fhtw.spring.services;

import at.fhtw.spring.exception.ApiRequestException;
import at.fhtw.spring.exception.InternalServerException;
import at.fhtw.spring.persistence.entities.DistanceEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.json.JSONObject;
import org.w3c.dom.Document;

@Service
public class MapServiceImpl implements MapService{
    private String getKey(){
        Properties prop = new Properties();
        try{
            FileReader reader = new FileReader("src/main/resources/config.properties");
            prop.load(reader);

            return prop.getProperty("mapquest.key");
        }catch(IOException e){
            throw new InternalServerException("Error Fetching Properties");
        }
    }

    private HttpURLConnection getConnection(String endpointUrl, String query) {
        try{
            HttpURLConnection conn;
            URL url = new URL(endpointUrl + "?" + query);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " + responseCode);
            return conn;
        }catch(Exception e){
            throw new InternalServerException("Failed to get Connection to Mapquest API");
        }
    }

    @Override
    public ResponseEntity<byte[]> fetchMap(String start, String end) {
        String endpointUrl = "https://www.mapquestapi.com/staticmap/v5/map";
        String queryString = "key=" + this.getKey() + "&start=" + start + "&end=" + end;
        try{
            HttpURLConnection conn = this.getConnection(endpointUrl, queryString);

            InputStream inputStream = conn.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            outputStream.close();
            inputStream.close();

            return new ResponseEntity<>(imageBytes, HttpStatus.OK);
        }catch(Exception e){
            throw new InternalServerException("Error Fetching Map");
        }
    }

    @Override
    public ResponseEntity<DistanceEntity> fetchDistance(String from, String to, String routeType) {
        String endpointUrl = "https://www.mapquestapi.com/directions/v2/route";
        String queryString = "key=" + this.getKey() + "&from=" + from + "&to=" + to + "&routeType=" + routeType;

        try {
            HttpURLConnection conn = this.getConnection(endpointUrl, queryString);

            // Read the response body as a string
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder responseBodyBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseBodyBuilder.append(inputLine);
            }
            in.close();
            String responseBody = responseBodyBuilder.toString();

            // Parse the JSON response and extract the desired fields
            JSONObject responseJson = new JSONObject(responseBody);
            DistanceEntity distanceEntity = DistanceEntity.builder()
                    .distance(responseJson.getJSONObject("route").getDouble("distance"))
                    .time(responseJson.getJSONObject("route").getInt("time"))
                    .build();

            return new ResponseEntity<>(distanceEntity, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerException("Error Fetching Distance");
        }
    }
}
