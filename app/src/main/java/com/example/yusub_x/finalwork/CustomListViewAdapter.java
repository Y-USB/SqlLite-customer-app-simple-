package com.example.yusub_x.finalwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewAdapter extends BaseAdapter {
    Context context;
    List<model> list;

    public CustomListViewAdapter(){

    }

    public CustomListViewAdapter(Context context, List<model> list){
        this.context  = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public model getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.customlistviewdesign,null,false);
            viewHolder.txtFullName = convertView.findViewById(R.id.txtFullName);
            viewHolder.txtNumber = convertView.findViewById(R.id.txtnumber);
            viewHolder.Message = convertView.findViewById(R.id.message);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        model model = getItem(position);
        String sonluq =null;
        String gender = model.getGender();
       if (gender.equals("qadın")){
            sonluq = " qızı";
        }else if(gender.equals("kişi")){
           sonluq =" oğlu";
       }
        String  FullName = model.getName() +" " + model.getSurname() +" " + model.getFather() + sonluq;
        viewHolder.txtFullName.setText(FullName);
        viewHolder.txtNumber.setText(model.getOperator()+ " " + model.getNumber());
        viewHolder.Message.setText("əlavə məlumat üçün klikləyin...");
        return convertView;
    }
}
class  ViewHolder {
    TextView txtFullName;
    TextView txtNumber;
    TextView Message;
}
