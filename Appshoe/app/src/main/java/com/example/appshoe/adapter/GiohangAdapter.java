package com.example.appshoe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appshoe.R;
import com.example.appshoe.activity.MainActivity;
import com.example.appshoe.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<com.example.appshoe.model.Giohang> arraygiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txttengiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus,btnvales,btnplus,btnminus1,btnvales1,btnplus1;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate( R.layout.dong_giohang, null );
            viewHolder.txttengiohang = view.findViewById( R.id.textviewtengiohang );
            viewHolder.txtgiagiohang = view.findViewById( R.id.textviewgiagiohang );
            viewHolder.imggiohang = view.findViewById( R.id.imageviewgiohang );
            viewHolder.btnminus = view.findViewById( R.id.buttonminus );
            viewHolder.btnvales = view.findViewById( R.id.buttonvalue );
            viewHolder.btnplus = view.findViewById( R.id.buttonplus );
            viewHolder.btnminus1 = view.findViewById( R.id.buttonminus1 );
            viewHolder.btnvales1 = view.findViewById( R.id.buttonvalue1 );
            viewHolder.btnplus1 = view.findViewById( R.id.buttonplus1 );
            view.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        com.example.appshoe.model.Giohang giohang = (com.example.appshoe.model.Giohang) getItem( i );
        viewHolder.txttengiohang.setText( giohang.getTensp() );
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###" );
        viewHolder.txtgiagiohang.setText( decimalFormat.format( giohang.getGiasp() ) + " VNĐ" );
        Picasso.with( context ).load( giohang.getHinhsp() )
                .placeholder( R.drawable.loading )
                .error( R.drawable.error )
                .into( viewHolder.imggiohang );
        viewHolder.btnvales.setText( giohang.getSoluongsp() + "" );
        viewHolder.btnvales1.setText( giohang.getSizesp() + "" );
        int sl = Integer.parseInt( viewHolder.btnvales.getText().toString() );
        if(sl>=10){
            viewHolder.btnplus.setVisibility( view.INVISIBLE );
            viewHolder.btnminus.setVisibility( View.VISIBLE );
        }else if(sl <=1){
            viewHolder.btnminus.setVisibility( View.INVISIBLE );
        }else if(sl >=1){
            viewHolder.btnminus.setVisibility( View.VISIBLE );
            viewHolder.btnplus.setVisibility( View.VISIBLE );
        }
        int sz = Integer.parseInt( viewHolder.btnvales1.getText().toString() );
        if(sz>=43){
            viewHolder.btnplus1.setVisibility( view.INVISIBLE );
            viewHolder.btnminus1.setVisibility( View.VISIBLE );
        }else if(sl <=38){
            viewHolder.btnminus1.setVisibility( View.INVISIBLE );
        }else if(sl >=39){
            viewHolder.btnminus1.setVisibility( View.VISIBLE );
            viewHolder.btnplus1.setVisibility( View.VISIBLE );
        }
        ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.btnplus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt( finalViewHolder1.btnvales.getText().toString() ) +1;
                int slht = MainActivity.manggiohang.get( i ).getSoluongsp();
                long giaht = MainActivity.manggiohang.get( i ).getGiasp();
                MainActivity.manggiohang.get( i ).setSoluongsp( slmoinhat );
                long giamoinhat = (giaht * slmoinhat) / slht;
                MainActivity.manggiohang.get( i ).setGiasp( giamoinhat );
                DecimalFormat decimalFormat = new DecimalFormat( "###,###,###" );
                finalViewHolder1.txtgiagiohang.setText( decimalFormat.format( giamoinhat) + " VNĐ" );
                com.example.appshoe.activity.Giohang.EvenUtil();
                if(slmoinhat > 9){
                    finalViewHolder1.btnplus.setVisibility( View.INVISIBLE );
                    finalViewHolder1.btnminus.setVisibility( View.VISIBLE );
                    finalViewHolder1.btnvales.setText( String.valueOf( slmoinhat ));
                }else{
                    finalViewHolder1.btnminus.setVisibility( View.VISIBLE );
                    finalViewHolder1.btnplus.setVisibility( View.VISIBLE );
                    finalViewHolder1.btnvales.setText( String.valueOf( slmoinhat ));
                }
            }
        });
        ViewHolder finalViewHolder4 = viewHolder;
        viewHolder.btnplus1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int szmoinhat = Integer.parseInt( finalViewHolder4.btnvales1.getText().toString() ) +1;
                if(szmoinhat > 42){
                    finalViewHolder4.btnplus1.setVisibility( View.INVISIBLE );
                    finalViewHolder4.btnminus1.setVisibility( View.VISIBLE );
                    finalViewHolder4.btnvales1.setText( String.valueOf( szmoinhat ));
                }else{
                    finalViewHolder4.btnminus1.setVisibility( View.VISIBLE );
                    finalViewHolder4.btnplus1.setVisibility( View.VISIBLE );
                    finalViewHolder4.btnvales1.setText( String.valueOf( szmoinhat ));
                }
            }
        });
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnminus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt( finalViewHolder.btnvales.getText().toString() ) -1;
                int slht = MainActivity.manggiohang.get( i ).getSoluongsp();
                long giaht = MainActivity.manggiohang.get( i ).getGiasp();
                MainActivity.manggiohang.get( i ).setSoluongsp( slmoinhat );
                long giamoinhat = (giaht * slmoinhat) / slht;
                MainActivity.manggiohang.get( i ).setGiasp( giamoinhat );
                DecimalFormat decimalFormat = new DecimalFormat( "###,###,###" );
                finalViewHolder.txtgiagiohang.setText( decimalFormat.format( giamoinhat) + " VNĐ" );
                com.example.appshoe.activity.Giohang.EvenUtil();
                if(slmoinhat < 2){
                    finalViewHolder.btnminus.setVisibility( View.INVISIBLE );
                    finalViewHolder.btnplus.setVisibility( View.VISIBLE );
                    finalViewHolder.btnvales.setText( String.valueOf( slmoinhat ));
                }else{
                    finalViewHolder.btnminus.setVisibility( View.VISIBLE );
                    finalViewHolder.btnplus.setVisibility( View.VISIBLE );
                    finalViewHolder.btnvales.setText( String.valueOf( slmoinhat ));
                }
            }
        } );
        ViewHolder finalViewHolder3 = viewHolder;
        viewHolder.btnminus1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int szmoinhat = Integer.parseInt( finalViewHolder.btnvales1.getText().toString() ) -1;
                if(szmoinhat < 39){
                    finalViewHolder3.btnminus1.setVisibility( View.INVISIBLE );
                    finalViewHolder3.btnplus1.setVisibility( View.VISIBLE );
                    finalViewHolder3.btnvales1.setText( String.valueOf( szmoinhat ));
                }else{
                    finalViewHolder.btnminus1.setVisibility( View.VISIBLE );
                    finalViewHolder.btnplus1.setVisibility( View.VISIBLE );
                    finalViewHolder.btnvales1.setText( String.valueOf( szmoinhat ));
                }
            }
        } );
        return view;
    }
}
