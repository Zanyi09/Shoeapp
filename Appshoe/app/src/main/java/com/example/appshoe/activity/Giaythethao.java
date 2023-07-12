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
import com.example.appshoe.adapter.ThethaoAdapter;
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

public class Giaythethao extends AppCompatActivity {
    Toolbar toolbartthao;
    ListView lvtthao;
    ThethaoAdapter thethaoAdapter;
    ArrayList<Sanpham> mangtthao;
    int idtthao =0;
    int page =1;
    View footerview;
    boolean isLoading = false;
    boolean limitadata = false;
    mHandler mHandler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_giaythethao );
        if (CheckConnection.haveNetworkConnection( getApplicationContext() )){
            Anhxa();
            GetIdloaisp();
            ActionToolbar();
            GetData( page );
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_Short( getApplicationContext(),"Vui lòng kiểm tra INTERNET!" );
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
        lvtthao.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Chitietsanpham.class);
                intent.putExtra( "thongtinsanpham", mangtthao.get( i ) );
                startActivity( intent );
            }
        } );
        lvtthao.setOnScrollListener( new AbsListView.OnScrollListener() {
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
    private void Anhxa() {
        toolbartthao = findViewById( R.id.toolbarthethao );
        lvtthao = findViewById( R.id.listviewthethao );
        mangtthao = new ArrayList<>();
        thethaoAdapter = new ThethaoAdapter(getApplicationContext(),mangtthao);
        lvtthao.setAdapter( thethaoAdapter );
        LayoutInflater inflater= (LayoutInflater) getSystemService( LAYOUT_INFLATER_SERVICE );
        footerview = inflater.inflate( R.layout.progressbar , null);
        mHandler = new mHandler();
    }
    private void GetIdloaisp() {
        idtthao = getIntent().getIntExtra( "idloaisanpham" ,-1);
    }
    private void ActionToolbar() {
        setSupportActionBar( toolbartthao );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        toolbartthao.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                String Tentthao="";
                int giatthao =0;
                String Hinhanhtthao ="";
                String Motatthao ="";
                int Idsptthao = 0;
                if (response != null && response.length() != 2){
                    lvtthao.removeFooterView( footerview );
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i =0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject( i );
                            id = jsonObject.getInt( "id" );
                            Tentthao = jsonObject.getString( "tensp" );
                            giatthao = jsonObject.getInt( "giasp" );
                            Hinhanhtthao = jsonObject.getString( "hinhanhsp" );
                            Motatthao = jsonObject.getString( "motasp" );
                            Idsptthao = jsonObject.getInt( "idsanpham" );
                            mangtthao.add( new Sanpham( id,Tentthao,giatthao,Hinhanhtthao,Motatthao,Idsptthao ) );
                            thethaoAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limitadata = true;
                    lvtthao.removeFooterView( footerview );
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
                param.put( "idsanpham",String.valueOf( idtthao ) );
                return param;
            }
        };
        requestQueue.add( stringRequest );
    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvtthao .addFooterView( footerview );
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