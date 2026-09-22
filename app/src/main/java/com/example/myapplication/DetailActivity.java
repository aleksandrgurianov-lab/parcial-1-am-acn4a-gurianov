package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.util.Pair;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        TextView txtBreadcrumbs = findViewById(R.id.txtBreadcrumbs);
        if(txtBreadcrumbs != null){
            txtBreadcrumbs.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){
                    finish();
                }
            });
        }

        Button btnReserve = findViewById(R.id.btnReserve);
        LinearLayout dynamicInvoiceContainer = findViewById(R.id.dynamicInvoiceContainer);

        if (btnReserve != null && dynamicInvoiceContainer != null) {
            btnReserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dynamicInvoiceContainer.removeAllViews();

                    // Создаем динамический TextView в оперативной памяти Java
                    TextView dynamicCheck = new TextView(DetailActivity.this);
                    dynamicCheck.setText("¡Reserva procesada con éxito!");
                    dynamicCheck.setTextColor(getResources().getColor(R.color.orange_premium));
                    dynamicCheck.setTextSize(16);

                    // Пушим созданный элемент внутрь контейнера
                    dynamicInvoiceContainer.addView(dynamicCheck);

                    // Меняем состояние кнопки
                    btnReserve.setText("Confirmado");
                    btnReserve.setEnabled(false);
                }
            });
        }


        EditText inputCheckIn = findViewById(R.id.inputCheckIn);
        EditText inputNights = findViewById(R.id.inputNights);

        if (inputCheckIn != null && inputNights != null) {
            inputCheckIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
                    builder.setTitleText("Seleccione fechas de estadía");

                    final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();
                    materialDatePicker.show(getSupportFragmentManager(), "DATE_RANGE_PICKER");


                    materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                        @Override
                        public void onPositiveButtonClick(Pair<Long, Long> selection) {

                            Long startDateMs = selection.first;
                            Long endDateMs = selection.second;

                            if (startDateMs != null && endDateMs != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                                String startDateStr = sdf.format(new Date(startDateMs));


                                inputCheckIn.setText(startDateStr);

                                long differenceMs = endDateMs - startDateMs;
                                long nights = differenceMs / (1000 * 60 * 60 * 24);


                                inputNights.setText(String.valueOf(nights));

                                int priceNight = 130;
                                long total = priceNight * nights;

                                TextView lblCalculationDetails = findViewById(R.id.lblCalculationDetails);
                                TextView lblTotalResult = findViewById(R.id.lblTotalResult);

                                if(lblCalculationDetails != null && lblTotalResult != null){
                                    lblCalculationDetails.setText(String.format(Locale.getDefault(), "$%d x %d noches", priceNight, nights));
                                    lblTotalResult.setText(String.format(Locale.getDefault(), "TOTAL: $%d", total));
                                }
                            }
                        }
                    });
                }
            });
        }


    }
}
