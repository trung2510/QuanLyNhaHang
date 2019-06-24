package com.example.quanlynhahang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {

    Context context;
    List<HoaDonModel> hoaDonModelList;
    HoaDonDAO hoaDonDAO;

    public HoaDonAdapter(Context context, List<HoaDonModel> hoaDonModelList) {
        this.context = context;
        this.hoaDonModelList = hoaDonModelList;
        this.hoaDonDAO = new HoaDonDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_hoa_don,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final HoaDonModel hoaDonModel = hoaDonModelList.get(i);
        viewHolder.tvID.setText(hoaDonModel.getBanSo());

        viewHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có muốn xóa không?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        hoaDonDAO = new HoaDonDAO(context);
                        hoaDonDAO.deleteHoaDon(hoaDonModelList.get(i).getBanSo());

                        //Xoa tren recyclerview
                        hoaDonModelList.remove(hoaDonModelList.get(i));

                        notifyDataSetChanged();

                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HoaDonModel hoaDonModel1 = hoaDonModelList.get(i);
                Intent intent = new Intent(context, HoaDonActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("banSo",hoaDonModel1.getBanSo());
                bundle.putString("doAn",hoaDonModel1.getDoAn());
                bundle.putDouble("gia", hoaDonModel1.getGia());
                intent.putExtra("bun",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hoaDonModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvID;
        private ImageView imgXoa;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvID = (TextView) itemView.findViewById(R.id.tvID);
            imgXoa = (ImageView) itemView.findViewById(R.id.imgXoa);

        }
    }
}
