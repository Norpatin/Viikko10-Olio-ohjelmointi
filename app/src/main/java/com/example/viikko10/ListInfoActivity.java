package com.example.viikko10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListInfoActivity extends AppCompatActivity {

    private TextView carInfo;
    private TextView yearText;
    private TextView cityText;

    private String city;
    //private CarDataStorage carDataStorage;
    //private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        carInfo = findViewById(R.id.CarInfoText);
        yearText = findViewById(R.id.YearText);
        cityText = findViewById(R.id.CityText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        InformationToActivityList();
    }
    private void InformationToActivityList() {
        ArrayList<CarData> carData = CarDataStorage.getInstance().getCarData();

        StringBuilder dataText = new StringBuilder();
        StringBuilder TextCity = new StringBuilder();
        StringBuilder dataYearText = new StringBuilder();

        int vuosi = CarDataStorage.getInstance().getYear();
        String paikka = CarDataStorage.getInstance().getCity();

        for (CarData data : carData) {
            //if(data.getType() == getCity()){//CarDataStorage.getInstance().getCity()) {
                int yhteensa = data.getCar1() + data.getPakettiauto() + data.getKuormaAuto() + data.getLinjaAuto() + data.getErikoisAuto();
                dataText.append("Henkilöautot: ").append(data.getCar1()).append("\n").append("Pakettiautot: ").append(data.getPakettiauto()).append("\n").append("Kuoma-autot: ").
                        append(data.getKuormaAuto()).append("\n").append("Linja-autot: ").append(data.getLinjaAuto()).append("\n").append("Erikoisautot: ").append("\n").
                        append(data.getErikoisAuto()).append("\n").append("\n").append("Yhteensä: ").append(yhteensa);
                TextCity.append(data.getType());
                dataYearText.append(data.getAmount());


            //}
        }

            carInfo.setText(dataText);
            //cityText.setText(TextCity.toString());
            cityText.setText(paikka.toString());
            //yearText.setText(dataYearText.toString());
            yearText.setText(vuosi);

    }

    public void SwitchToMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}