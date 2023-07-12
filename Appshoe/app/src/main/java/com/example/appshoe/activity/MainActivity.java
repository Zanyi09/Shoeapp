package com.example.appshoe.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appshoe.R;
import com.example.appshoe.adapter.LoaispAdapter;
import com.example.appshoe.adapter.SanphamAdapter;
import com.example.appshoe.model.Giohang;
import com.example.appshoe.model.Loaisp;
import com.example.appshoe.model.Sanpham;
import com.example.appshoe.ultil.CheckConnection;
import com.example.appshoe.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        Toolbar toolbar;
        ViewFlipper viewFlipper;
        RecyclerView recyclerViewmanhinhchinh;
        NavigationView navigationView;
        ListView listViewmanhinhchinh;
        DrawerLayout drawerLayout;
        ArrayList<Loaisp> mangloaisp;
        LoaispAdapter loaispAdapter;
        int id =0;
        String tenloaisp ="";
        String hinhanhloaisp ="";
        ArrayList<Sanpham> mangsanpham ;
        SanphamAdapter sanphamAdapter;
        public static ArrayList<Giohang> manggiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Anhxa();
        if(CheckConnection.haveNetworkConnection( getApplicationContext() )){
            ActionBar();
            ActionViewFlipper();
            Getdulieuloaisp();
            Getdulieusanpham();
            CatchonItemListview();
        }else{
            CheckConnection.ShowToast_Short( getApplicationContext(),"Vui lòng kiểm tra kết nối INTERNET!" );
            finish();
        }

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

    private void CatchonItemListview() {
        listViewmanhinhchinh.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                switch (i){
                    case 0:
                        if(CheckConnection.haveNetworkConnection( getApplicationContext() )){
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity( intent );
                        }else{
                            CheckConnection.ShowToast_Short( getApplicationContext(), "Vui lòng kiểm tra lại kết nối INTERNET!" );
                        }
                        drawerLayout.closeDrawer( GravityCompat.START );
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection( getApplicationContext() )){
                            Intent intent = new Intent(MainActivity.this, Giaythoitrang.class);
                            intent.putExtra( "idloaisanpham" ,mangloaisp.get( i ).getId());
                            startActivity( intent );
                        }else{
                            CheckConnection.ShowToast_Short( getApplicationContext(), "Vui lòng kiểm tra lại kết nối INTERNET!" );
                        }
                        drawerLayout.closeDrawer( GravityCompat.START );
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection( getApplicationContext() )){
                            Intent intent = new Intent(MainActivity.this, Giaythethao.class);
                            intent.putExtra( "idloaisanpham" ,mangloaisp.get( i ).getId());
                            startActivity( intent );
                        }else{
                            CheckConnection.ShowToast_Short( getApplicationContext(), "Vui lòng kiểm tra lại kết nối INTERNET!" );
                        }
                        drawerLayout.closeDrawer( GravityCompat.START );
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection( getApplicationContext() )){
                            Intent intent = new Intent(MainActivity.this, LienHe.class);
                            startActivity( intent );
                        }else{
                            CheckConnection.ShowToast_Short( getApplicationContext(), "Vui lòng kiểm tra lại kết nối INTERNET!" );
                        }
                        drawerLayout.closeDrawer( GravityCompat.START );
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection( getApplicationContext() )){
                            Intent intent = new Intent(MainActivity.this, ThongTin.class);
                            startActivity( intent );
                        }else{
                            CheckConnection.ShowToast_Short( getApplicationContext(), "Vui lòng kiểm tra lại kết nối INTERNET!" );
                        }
                        drawerLayout.closeDrawer( GravityCompat.START );
                        break;
                }
            }
        } );
    }

    private void Getdulieusanpham() {
        RequestQueue requestQueue = Volley.newRequestQueue( getApplicationContext() );
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Server.Duongdansanpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int ID = 0;
                    String tensanpham="";
                    Integer Giasanpham=0;
                    String Hinhanhsanpham="";
                    String Motasanpham="";
                    int IDsanpham=0;
                    for (int i =0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject( i );
                            ID = jsonObject.getInt( "id" );
                            tensanpham = jsonObject.getString( "tensp" );
                            Giasanpham = jsonObject.getInt( "giasp" );
                            Hinhanhsanpham = jsonObject.getString( "anhsp" );
                            Motasanpham = jsonObject.getString( "motasp" );
                            IDsanpham = jsonObject.getInt( "idsanpham" );
                            mangsanpham.add( new Sanpham( ID,tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,IDsanpham ) );
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue.add( jsonArrayRequest );
    }

    private void Getdulieuloaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue( getApplicationContext() );
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Server.Duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i =0 ; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject( i );
                            id = jsonObject.getInt( "id" );
                            tenloaisp = jsonObject.getString( "tenloaisp" );
                            hinhanhloaisp=jsonObject.getString( "anhloaisp" );
                            mangloaisp.add( new Loaisp( id,tenloaisp,hinhanhloaisp ) );
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add( 3, new Loaisp(0,"Liên Hệ","https://i.pinimg.com/originals/f0/a4/17/f0a4170e43fae6a84ff990b6df105199.png") );
                    mangloaisp.add( 4, new Loaisp(0,"Thông Tin","https://i.pinimg.com/736x/3f/94/70/3f9470b34a8e3f526dbdb022f9f19cf7.jpg") );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short( getApplicationContext(), error.toString() );
            }
        } );
        requestQueue.add( jsonArrayRequest );
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://i.pinimg.com/564x/2f/98/98/2f989840e731f7c67846d8dbd57cbb37.jpg");
        mangquangcao.add("https://i.pinimg.com/564x/25/95/d7/2595d7ecf0a6376a73748c3b6747b91b.jpg");
        mangquangcao.add("https://i.pinimg.com/564x/23/0f/ac/230fac5455345b2be5d8f0aaca1c1ffe.jpg");
        mangquangcao.add("https://i.pinimg.com/564x/e7/c5/0e/e7c50e4420e5fdcce62796306a12b574.jpg");
        mangquangcao.add("https://i.pinimg.com/564x/33/09/d5/3309d525d51ca14fe87e4d18b0edefc6.jpg");
        mangquangcao.add("https://i.pinimg.com/564x/6c/fb/3d/6cfb3d47992c6d9e863812d56e1f9014.jpg");
        mangquangcao.add("https://i.pinimg.com/564x/89/65/e0/8965e0d398b4bfa9334ebe17f916c8fa.jpg");
        mangquangcao.add("https://i.pinimg.com/564x/9c/b7/28/9cb728eda8a4adb6848fb2ded8fc6bbc.jpg");
        mangquangcao.add("https://i.pinimg.com/564x/c6/b6/33/c6b6335e8996285be4d60db58d0c0120.jpg");
        for(int i =0 ; i<mangquangcao.size(); i++){
            ImageView imageView = new ImageView( getApplicationContext() );
            Picasso.with(getApplicationContext()).load( mangquangcao.get( i ) ).into( imageView );
            imageView.setScaleType( ImageView.ScaleType.FIT_XY );
            viewFlipper.addView( imageView );
        }
        viewFlipper.setFlipInterval( 5000 );
        viewFlipper.setAutoStart( true );
        Animation animation_slide_in = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.slide_in_right );
        Animation animation_slide_out = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.slide_out_right );
        viewFlipper.setInAnimation( animation_slide_in );
        viewFlipper.setOutAnimation( animation_slide_out );
    }

    private void ActionBar() {
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbar.setNavigationIcon( android.R.drawable.ic_menu_sort_by_size );
        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer( GravityCompat.START );
            }
        } );
    }

    private void Anhxa() {
        toolbar = findViewById( R.id.toolbarmanhinhchinh );
        viewFlipper = findViewById( R.id.viewlipper );
        recyclerViewmanhinhchinh = findViewById( R.id.recycleview );
        navigationView = findViewById( R.id.navigationview );
        listViewmanhinhchinh = findViewById( R.id.listviewmanhinhchinh );
        drawerLayout = findViewById( R.id.drawerlayout );
        mangloaisp = new ArrayList<>();
        mangloaisp.add( 0, new Loaisp( 0,"Trang Chủ","https://img.flaticon.com/icons/png/512/25/25694.png?size=1200x630f&pad=10,10,10,10&ext=png&bg=FFFFFFFF" ) );
        loaispAdapter = new LoaispAdapter(mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter( loaispAdapter );
        mangsanpham = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize( true );
        recyclerViewmanhinhchinh.setLayoutManager( new GridLayoutManager( getApplicationContext(),2 ) );
        recyclerViewmanhinhchinh.setAdapter( sanphamAdapter );
        if(manggiohang != null){

        }else{
            manggiohang = new ArrayList<>();
        }
    }
}