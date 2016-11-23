package com.example.administrator.easyshopdemo.main.me.personInfo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.easyshopdemo.R;
import com.example.administrator.easyshopdemo.model.PersonItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/23.
 */

public class PersonAdapter extends BaseAdapter {
    public PersonAdapter(List<PersonItem> list) {
        this.list = list;
    }

    List<PersonItem> list=new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public PersonItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder h;
        if (convertView==null){
            convertView=View.inflate(parent.getContext(), R.layout.item_person_info,null);
            h=new Holder(convertView);
            convertView.setTag(h);
        }else{
            h = (Holder) convertView.getTag();
        }
        h.tv_item_name.setText(list.get(position).getTitle());
        h.tv_person.setText(list.get(position).getContent());
        return convertView;
    }
    class Holder{
        @BindView(R.id.tv_item_name)
        TextView tv_item_name;
        @BindView(R.id.tv_person)
        TextView tv_person;
        public Holder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
