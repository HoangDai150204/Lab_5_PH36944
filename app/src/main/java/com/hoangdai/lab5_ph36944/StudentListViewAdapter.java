package com.hoangdai.lab5_ph36944;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListViewAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Student> list;

    public StudentListViewAdapter(Context context, ArrayList<Student> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {return list.size();}
    @Override
    public Object getItem(int i) {return list.get(i);}
    @Override
    public long getItemId(int i) {return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroud){
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(R.layout.item_listview, viewGroud, false);

        TextView txt_coso = view.findViewById(R.id.txtcoso);
        TextView txt_name = view.findViewById(R.id.txtname);
        TextView txt_address = view.findViewById(R.id.txtaddress);
        Button btndelete=view.findViewById(R.id.btndelete);
        Button btnupdate=view.findViewById(R.id.btnupdate);


        txt_coso.setText("FPoly "+ list.get(i).getBranch());
        txt_name.setText("Họ tên: "+ list.get(i).getName());
        txt_address.setText("Địa chỉ: "+ list.get(i).getAddress());

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;

    }
}
