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

public class ThethaoAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraythethao;

    public ThethaoAdapter(Context context, ArrayList<Sanpham> arraythethao) {
        this.context = context;
        this.arraythethao = arraythethao;
    }

    @Override
    public int getCount() {
        return arraythethao.size();
    }

    @Override
    public Object getItem(int i) {
        return arraythethao.get( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView txttenthethao, txtgiathethao, txtmotathethao;
        public ImageView imagethethao;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate( R.layout.dong_thethao, null );
            viewHolder.txttenthethao = view.findViewById( R.id.textviewtenthethao );
            viewHolder.txtgiathethao = view.findViewById( R.id.textviewgiathethao );
            viewHolder.txtmotathethao = view.findViewById( R.id.textviewmotathethao );
            viewHolder.imagethethao = view.findViewById( R.id.imageviewthethao );
            view.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem( i );
        viewHolder.txttenthethao.setText( sanpham.getTensanpham() );
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiathethao.setText( "Giá : "+decimalFormat.format( sanpham.getGiasanpham())+" VNĐ" );
        viewHolder.txtmotathethao.setMaxLines( 1 );
        viewHolder.txtmotathethao.setEllipsize( TextUtils.TruncateAt.END );
        viewHolder.txtmotathethao.setText( sanpham.getMotasanpham() );
        Picasso.with( context ).load( sanpham.getHinhanhsanpham() )
                .placeholder( R.drawable.loading )
                .error( R.drawable.error )
                .into( viewHolder.imagethethao );
        return view;
    }
}
