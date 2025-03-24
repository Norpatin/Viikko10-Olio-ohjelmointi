package com.example.viikko10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class SearchActivity extends AppCompatActivity {

    private EditText CityName;
    private EditText Year;
    private TextView Information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CityName = findViewById(R.id.CityNameEdit);
        Year = findViewById(R.id.YearEdit);
        Information = findViewById(R.id.StatusText);
    }

    public void SwitchToListInfoActivity(View view){
        Intent intent = new Intent(this, ListInfoActivity.class);
        startActivity(intent);
    }

    public void onFindBtnClick(View view) {
        Log.d("Lut", "Nappula toimii");
        Context context = this;

        String city = CityName.getText().toString();

        String yearString = Year.getText().toString();
        int IntYear = Integer.parseInt(yearString); // uusi


        if (city.isEmpty()){
            Information.setText("Haku epäonnistui, kaupunkia ei olemassa tai se on kirjoitettu väärin.");
            return;
        }

        if (yearString.isEmpty()) {
            Information.setText("Haku epäonnistui, vuotta ei olemassa tai se on kirjoitettu väärin.");
            return;
        }

        int year = 0;
        try {
            year = Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            Information.setText("Et antaunut oikeaa vuosilukua.");
        }

        ExecutorService service = Executors.newSingleThreadExecutor();
        CarDataStorage.getInstance().setCity(city); //uusi
        CarDataStorage.getInstance().setYear(IntYear); //uusi

        int Year = year;
        service.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<CarData> car1Data = getData(context, city, Year);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (car1Data == null || car1Data.isEmpty()){
                            Information.setText("Haku epäonnistui, kaupunkia ei olemassa tai se on kirjoitettu väärin.");
                            return;
                        }

                        for(CarData data : car1Data) {
                            if(data.getAmount() == Year) {
                                //data.setType(CarDataStorage);
                                String test = (data.getAmount() + " " + data.getCar1() + " " + data.getPakettiauto() + " " + data.getKuormaAuto() + " " + data.getLinjaAuto() + " " + data.getErikoisAuto());
                                //CityName.setText(test);
                                Information.setText("Haku onnistui");
                                CarData cd = new CarData(data.getType(), data.getAmount(), data.getCar1(), data.getPakettiauto(), data.getKuormaAuto(), data.getLinjaAuto(), data.getErikoisAuto());
                                CarDataStorage.getInstance().addCarData(cd);
                            }
                        }
                    }
                });
                Log.d("LUT2", "Data haettu");
            }
        });

    }

    public ArrayList<CarData> getData(Context context, String city, int year) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode areas = null;
        try {
            areas = objectMapper.readTree(new URL("https://pxdata.stat.fi/PxWeb/api/v1/fi/StatFin/mkan/statfin_mkan_pxt_11ic.px"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        ArrayList<String> keys = new ArrayList<>();
        ArrayList <String> values = new ArrayList<>();

        for (JsonNode node : areas.get("variables").get(0).get("values")) {
            values.add(node.asText());
        }

        for (JsonNode node : areas.get("variables").get(0).get("valueTexts")) {
            keys.add(node.asText());
        }

        HashMap<String, String> municipalityCodes = new HashMap<>();

        for(int i = 0; i < keys.size(); i++) {
            municipalityCodes.put(keys.get(i), values.get(i));
        }

        //String municipality = "";
        //String code = null;
        //String code = municipalityCodes.get(municipality);
        String code = municipalityCodes.get(city);


        try {
            URL url = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/mkan/statfin_mkan_pxt_11ic.px");

            HttpURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(R.raw.query));

            ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(code);

            byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            JsonNode municipalityData = objectMapper.readTree(response.toString());


            ArrayList<String> years = new ArrayList<>();
            ArrayList<String> car1s = new ArrayList<>();
            ArrayList<String> pakettiautot = new ArrayList<>();
            ArrayList<String> kuormaAutot = new ArrayList<>();
            ArrayList<String> linjaAutot = new ArrayList<>();
            ArrayList<String> erikoisAutot = new ArrayList<>();
            //ArrayList<String> types = new ArrayList<>();

            for (JsonNode node : municipalityData.get("dimension").get("Vuosi").get("category").get("label")) {
                years.add(node.asText());
            }
            for (JsonNode node : municipalityData.get("value")) {
                car1s.add(node.asText());
            }
            for (JsonNode node : municipalityData.get("value")) {
                pakettiautot.add(node.asText());
            }
            for (JsonNode node : municipalityData.get("value")) {
                kuormaAutot.add(node.asText());
            }
            for (JsonNode node : municipalityData.get("value")) {
                linjaAutot.add(node.asText());
            }
            for (JsonNode node : municipalityData.get("value")) {
                erikoisAutot.add(node.asText());
            }
            /*for (JsonNode node : municipalityData.get("value")) {
                types.add(node.asText());
            }
            /**/
             ArrayList<CarData> car1Data = new ArrayList<>();
            String type = CityName.getText().toString();
             for(int i = 0; i < years.size(); i++) {
                 car1Data.add(new CarData(type, Integer.valueOf(years.get(i)), Integer.valueOf(car1s.get(i)),Integer.valueOf(pakettiautot.get(i+14)),
                         Integer.valueOf(kuormaAutot.get(i+(14*2))), Integer.valueOf(linjaAutot.get(i+(14*3))), Integer.valueOf(erikoisAutot.get(i+(14*4)))));
             }

            return car1Data;


            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        return null;
        }

}