package com.example.appshoe.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appshoe.R;
import com.example.appshoe.model.Giohang;
import com.example.appshoe.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Chitietsanpham extends AppCompatActivity {
    Toolbar toolbarchitietsp;
    ImageView imgchitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinnersoluong;
    Spinner spinnersize;
    Button btndatmua;

    int id = 0;
    String Tenchitiet ="";
    int Giachitiet =0;
    String Hinhanhct = "";
    String Motact ="";
    int Idsanpham =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chitietsanpham );
        Anhxa();
        ActionToolbar();
        getInformation();
        CatchEvenSpinnersoluong();
        CatchEvenSpinnersize();
        EvenButton();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.appshoe.activity.Giohang.class );
                startActivity( intent );
        }
        return super.onOptionsItemSelected( item );
    }
    private void EvenButton() {
        btndatmua.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() > 0){
                    int sl = Integer.parseInt (spinnersoluong.getSelectedItem().toString());
                    int sz = Integer.parseInt (spinnersize.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i =0; i<MainActivity.manggiohang.size(); i++){
                        if(MainActivity.manggiohang.get( i ).getIdsp() == id){
                            MainActivity.manggiohang.get( i ).setSoluongsp( MainActivity.manggiohang.get( i ).getSoluongsp() + sl);
                            if(MainActivity.manggiohang.get( i ).getSoluongsp() >= 10){
                                MainActivity.manggiohang.get( i ).setSoluongsp( 10 );
                            }
                            MainActivity.manggiohang.get( i ).setSizesp( MainActivity.manggiohang.get( i ).getSizesp() );
                            MainActivity.manggiohang.get(i).setGiasp( Giachitiet * MainActivity.manggiohang.get( i ).getSoluongsp() );
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int soluong = Integer.parseInt (spinnersoluong.getSelectedItem().toString());
                        int size = Integer.parseInt (spinnersize.getSelectedItem().toString());
                        long Giamoi = soluong * Giachitiet;
                        MainActivity.manggiohang.add( new Giohang( id,Tenchitiet,Giamoi,Hinhanhct,soluong,size ) );
                    }
                }else{
                    int soluong = Integer.parseInt (spinnersoluong.getSelectedItem().toString());
                    int size = Integer.parseInt (spinnersize.getSelectedItem().toString());
                    long Giamoi = soluong * Giachitiet;
                    MainActivity.manggiohang.add( new Giohang( id,Tenchitiet,Giamoi,Hinhanhct,soluong,size ) );
                }
                Intent intent = new Intent(getApplicationContext(), com.example.appshoe.activity.Giohang.class );
                startActivity( intent );
            }
        } );
    }

    private void CatchEvenSpinnersoluong() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinnersoluong.setAdapter( arrayAdapter );
    }
    private void CatchEvenSpinnersize() {
        Integer[] size = new Integer[]{38,39,40,41,42,43};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,size);
        spinnersize.setAdapter( arrayAdapter );
    }

    private void getInformation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra( "thongtinsanpham" );
        id = sanpham.getID();
        Tenchitiet = sanpham.getTensanpham();
        Giachitiet = sanpham.getGiasanpham();
        Hinhanhct = sanpham.getHinhanhsanpham();
        Motact = sanpham.getMotasanpham();
        Idsanpham = sanpham.getIDsanpham();
        txtten.setText( Tenchitiet );
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText( "Giá : "+decimalFormat.format( Giachitiet )+ "VNĐ" );
        txtmota.setText( Motact );
        Picasso.with( getApplicationContext()).load( Hinhanhct )
                .placeholder( R.drawable.loading )
                .error( R.drawable.error )
                .into( imgchitiet );
    }

    private void ActionToolbar() {
        setSupportActionBar( toolbarchitietsp );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbarchitietsp.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void Anhxa() {
        toolbarchitietsp = findViewById( R.id.toolbarchitietsp );
        imgchitiet = findViewById( R.id.imageviewchitietsp );
        txtten = findViewById( R.id.textviewtenchitietsp );
        txtgia = findViewById( R.id.textviewgiachitietsp );
        txtmota = findViewById( R.id.textviewmotachitietsp );
        spinnersoluong = findViewById( R.id.spinnersoluong );
        spinnersize = findViewById( R.id.spinnersize );
        btndatmua = findViewById( R.id.buttondamua );
    }
}