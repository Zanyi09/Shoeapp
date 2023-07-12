package com.example.appshoe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appshoe.R;
import com.example.appshoe.adapter.GiohangAdapter;
import com.example.appshoe.ultil.CheckConnection;

import java.text.DecimalFormat;

public class Giohang extends AppCompatActivity {
    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan,btntieptucmua;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_giohang );
        Anhxa();
        ActionToolbar();
        CheckData();
        EvenUtil();
        CacthonItemListView();
        EventButton();
    }

    private void EventButton() {
        btntieptucmua.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity( intent );
            }
        } );
        btnthanhtoan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size() >0){
                    Intent intent = new Intent(getApplicationContext(),Thongtinkhachhang.class);
                    startActivity( intent );
                }else{
                    CheckConnection.ShowToast_Short( getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm để thanh toán !" );
                }
            }
        } );
    }

    private void CacthonItemListView() {
        lvgiohang.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder( Giohang.this );
                builder.setTitle( "XÓA SẢN PHẨM" );
                builder.setMessage( "Bạn muốn xóa sản phẩm này ! " );
                builder.setPositiveButton( "Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(MainActivity.manggiohang.size() <= 0){
                            txtthongbao.setVisibility( View.VISIBLE );
                        }else {
                            MainActivity.manggiohang.remove( position );
                            giohangAdapter.notifyDataSetChanged();
                            EvenUtil();
                            if(MainActivity.manggiohang.size() <=0){
                                txtthongbao.setVisibility( View.VISIBLE );
                            }else {
                                txtthongbao.setVisibility( View.INVISIBLE );
                                giohangAdapter.notifyDataSetChanged();
                                EvenUtil();
                            }
                        }
                    }
                } );
                builder.setNegativeButton( "Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();
                        EvenUtil();
                    }
                } );
                builder.show();
                return true;
            }
        } );
    }

    public static void EvenUtil() {
        long tongtien =0;
        for(int i = 0; i<MainActivity.manggiohang.size(); i++){
            tongtien += MainActivity.manggiohang.get( i ).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText( decimalFormat.format( tongtien ) + " VNĐ" );
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size() <=0){
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility( View.VISIBLE );
            lvgiohang.setVisibility( View.INVISIBLE );
        }else{
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility( View.INVISIBLE );
            lvgiohang.setVisibility( View.VISIBLE );
        }
    }

    private void ActionToolbar() {
        setSupportActionBar( toolbargiohang );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbargiohang.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void Anhxa() {
        lvgiohang = findViewById( R.id.listviewgiohang );
        txtthongbao = findViewById( R.id.textviewthongbao );
        txttongtien = findViewById( R.id.textviewtongtien );
        btnthanhtoan = findViewById( R.id.buttonthanhtoan );
        btntieptucmua = findViewById( R.id.buttontieptucmuahang );
        toolbargiohang = findViewById( R.id.toolbargiohang );
        giohangAdapter = new GiohangAdapter( Giohang.this,MainActivity.manggiohang );
        lvgiohang.setAdapter( giohangAdapter );
    }
}