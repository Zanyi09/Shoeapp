package com.example.appshoe.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appshoe.R;
import com.example.appshoe.adapter.ThoitrangAdapter;
import com.example.appshoe.model.Sanpham;
import com.example.appshoe.ultil.CheckConnection;
import com.example.appshoe.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Giaythoitrang extends AppCompatActivity {
    Toolbar toolbartt;
    ListView lvtt;
    ThoitrangAdapter thoitrangAdapter;
    ArrayList<Sanpham> mangtt;
    int idtt =0;
    int page =1;
    View footerview;
    boolean isLoading = false;
    boolean limitadata = false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_giaythoitrang );
        Anhxa();
        if(CheckConnection.haveNetworkConnection( getApplicationContext() )){
            GetIdloaisp();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }else{
            CheckConnection.ShowToast_Short( getApplicationContext(),"Bạn hãy kiểm tra lại INTERNET!" );
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
    private void LoadMoreData() {
        lvtt.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Chitietsanpham.class);
                intent.putExtra( "thongtinsanpham", mangtt.get( i ) );
                startActivity( intent );
            }
        } );
        lvtt.setOnScrollListener( new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem + VisibleItem == TotalItem && TotalItem !=0 && isLoading == false && limitadata == false){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        } );
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue( getApplicationContext() );
        String duongdan = Server.Duongdanthoitrang+String.valueOf( Page );
        StringRequest stringRequest = new StringRequest( Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Tentt="";
                int giatt =0;
                String Hinhanhtt ="";
                String Motatt ="";
                int Idsptt = 0;
                if (response != null && response.length() != 2){
                    lvtt.removeFooterView( footerview );
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i =0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject( i );
                            id = jsonObject.getInt( "id" );
                            Tentt = jsonObject.getString( "tensp" );
                            giatt = jsonObject.getInt( "giasp" );
                            Hinhanhtt = jsonObject.getString( "hinhanhsp" );
                            Motatt = jsonObject.getString( "motasp" );
                            Idsptt = jsonObject.getInt( "idsanpham" );
                            mangtt.add( new Sanpham( id,Tentt,giatt,Hinhanhtt,Motatt,Idsptt ) );
                            thoitrangAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limitadata = true;
                    lvtt.removeFooterView( footerview );
                    CheckConnection.ShowToast_Short( getApplicationContext(),"Đã hết dữ liệu!" );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put( "idsanpham",String.valueOf( idtt ) );
                return param;
            }
        };
        requestQueue.add( stringRequest );
    }

    private void ActionToolbar() {
        setSupportActionBar( toolbartt );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbartt.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }


    private void GetIdloaisp() {
        idtt = getIntent().getIntExtra( "idloaisanpham" ,-1);
        Log.d("giatriloaisanpham",idtt+"");
    }

    private void Anhxa() {
        toolbartt = findViewById( R.id.toolbarthoitrang );
        lvtt = findViewById( R.id.listviewthoitrang );
        mangtt = new ArrayList<>();
        thoitrangAdapter = new ThoitrangAdapter(getApplicationContext(),mangtt);
        lvtt.setAdapter( thoitrangAdapter );
        LayoutInflater inflater= (LayoutInflater) getSystemService( LAYOUT_INFLATER_SERVICE );
        footerview = inflater.inflate( R.layout.progressbar , null);
        mHandler = new mHandler();
    }
    public class mHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvtt .addFooterView( footerview );
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage( msg );
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage( 0 );
            try {
                Thread.sleep( 3000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage( message );
            super.run();
        }
    }
}