package com.example.quanlynhahang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.List;

public class ListHoaDonActivity extends AppCompatActivity {

    private RecyclerView rcView;
    HoaDonAdapter hoaDonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);

        rcView =  findViewById(R.id.rcView);

        HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
        List<HoaDonModel> hoaDonModels = hoaDonDAO.getAllHoaDon();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        hoaDonAdapter = new HoaDonAdapter(this,hoaDonModels);
        rcView.setLayoutManager(linearLayoutManager);

        rcView.setAdapter(hoaDonAdapter);
    }
}
