package com.example.appshoe.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appshoe.R;
import com.example.appshoe.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThoitrangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraythoitrang;

    public ThoitrangAdapter(Context context, ArrayList<Sanpham> arraythoitrang) {
        this.context = context;
        this.arraythoitrang = arraythoitrang;
    }

    @Override
    public int getCount() {
        return arraythoitrang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraythoitrang.get( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView txttenthoitrang, txtgiathoitrang, txtmotathoitrang;
        public ImageView imagethoitrang;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate( R.layout.dong_thoitrang, null );
            viewHolder.txttenthoitrang = view.findViewById( R.id.textviewthoitrang );
            viewHolder.txtgiathoitrang = view.findViewById( R.id.textviewgiathoitrang );
            viewHolder.txtmotathoitrang = view.findViewById( R.id.textviewmotathoitrang );
            viewHolder.imagethoitrang = view.findViewById( R.id.imageviewthoitrang );
            view.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem( i );
        viewHolder.txttenthoitrang.setText( sanpham.getTensanpham() );
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiathoitrang.setText( "Giá : "+decimalFormat.format( sanpham.getGiasanpham())+" VNĐ" );
        viewHolder.txtmotathoitrang.setMaxLines( 1 );
        viewHolder.txtmotathoitrang.setEllipsize( TextUtils.TruncateAt.END );
        viewHolder.txtmotathoitrang.setText( sanpham.getMotasanpham() );
        Picasso.with( context ).load( sanpham.getHinhanhsanpham() )
                .placeholder( R.drawable.loading )
                .error( R.drawable.error )
                .into( viewHolder.imagethoitrang );
        return view;
    }
}

