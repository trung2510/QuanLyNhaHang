package com.example.quanlynhahang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HoaDonActivity extends AppCompatActivity {

    private EditText edtId;
    private EditText edtDoAn;
    private Button btnluu;
    private Button btnsua;
    private EditText edtGia;
    HoaDonDAO hoaDonDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        edtId = (EditText) findViewById(R.id.edtId);
        edtDoAn = (EditText) findViewById(R.id.edt_do_an);
        edtGia = (EditText) findViewById(R.id.edt_gia);
        btnluu = (Button) findViewById(R.id.btnluu);
        btnsua = (Button) findViewById(R.id.btnsua);

        btnsua.setVisibility(View.INVISIBLE);


        try{
            Intent intent = getIntent();
            if (intent != null){
                Bundle bundle = intent.getBundleExtra("bun");
                edtId.setText(bundle.getString("banSo"));
                edtDoAn.setText(bundle.getString("doAn"));
                edtGia.setText(bundle.getDouble("gia") +"");
                btnsua.setVisibility(View.VISIBLE);
                btnluu.setVisibility(View.INVISIBLE);

            }}catch (Exception e){

        }

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);

                String id = edtId.getText().toString();
                String doAn = edtDoAn.getText().toString();
                Double gia = Double.valueOf(edtGia.getText().toString());

                HoaDonModel hoaDonModel = new HoaDonModel(id,doAn,gia);

                if (hoaDonDAO.updateHoaDon(hoaDonModel) > 0) {
                    Toast.makeText(HoaDonActivity.this, "Update thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HoaDonActivity.this,ListHoaDonActivity.class));
                }
                else {
                    Toast.makeText(HoaDonActivity.this, "Update thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);

                String id = edtId.getText().toString();
                String doAn = edtDoAn.getText().toString();
                Double gia = Double.valueOf(edtGia.getText().toString());

//                if (id.equals("")){
//                    edtIdTypeBook.setError("Nhập id");
//                }
//                if (name.equals("")){
//                    edtNameTypeBook.setError("Nhập thể loại sách");
//                }
//                if (vt.equals("")){
//                    edtViTri.setError("Nhập vị trí");
//                }

                HoaDonModel hoaDonModel = new HoaDonModel(id,doAn,gia);

                if (hoaDonDAO.insertHoaDon(hoaDonModel) > 0) {
                    Toast.makeText(HoaDonActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HoaDonActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(HoaDonActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
