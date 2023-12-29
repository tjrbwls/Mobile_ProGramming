package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView listview;
    Button button_prev, button_next, button_history;
    LinearLayout linearlayout_progressbar;
    //ArrayAdapter adapter;
    MyAdapter adapter;
    ArrayList titleList=new ArrayList();
    ArrayList imageUrlList=new ArrayList();
    int LOAD=0, PREV=1, NEXT=2;
    int pageIndex=1, pageUnit=10, pageIndexMax=1000000; // pageIndexMax 설정 코드 구현 필요
    SharedPreferences pref;
    private String listItemSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 직전 앱 종료 시 표시되었던 페이지 번호를 공유프레퍼런스에서 로드
        pref=getSharedPreferences("pref", MODE_PRIVATE);
        pageIndex=pref.getInt("pageIndex", 1);
        listItemSize=pref.getString("listItemSize", "medium");

        button_prev=findViewById(R.id.button_prev);
        button_next=findViewById(R.id.button_next);
        button_history=findViewById(R.id.button_history);
        linearlayout_progressbar=findViewById(R.id.linearlayout_progressbar);
        listview=findViewById(R.id.listview);
        //adapter=new MyAdapter(this, titleList, imageUrlList);
        adapter=new MyAdapter(this, titleList, imageUrlList, listItemSize);
        listview.setAdapter(adapter);
        new Thread(()->displayData(LOAD)).start();
        button_prev.setOnClickListener((v)->new Thread(()->displayData(PREV)).start());
        button_next.setOnClickListener((v)->new Thread(()->displayData(NEXT)).start());
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addToHistory((String) titleList.get(position), (String) imageUrlList.get(position));
                Intent intent=new Intent(getApplicationContext(), ContentActivity.class);
                intent.putExtra("TITLE", (String) titleList.get(position));
                intent.putExtra("IMAGE_URL", (String) imageUrlList.get(position));
                startActivity(intent);
            }
        });
        button_history.setOnClickListener(v->startActivity(new Intent(getApplicationContext(), HistoryActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_settings){
            startActivity(new Intent(this,SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void addToHistory(String title, String imageUrl) {
        try {
            FileOutputStream oF=openFileOutput("history.txt", MODE_APPEND);
            String s=title+"\t"+imageUrl+"\n";
            oF.write(s.getBytes());
            oF.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 앱 종료 전, 표시되었던 페이지 번호를 공유프레퍼런스에 저장
    @Override
    protected void onStop() {
        super.onStop();
        pref.edit().putInt("pageIndex", pageIndex).apply();
    }
    private void displayData(int action) {
        if(action==PREV) {
            if(pageIndex==1) return;
            pageIndex--;
        }
        if(action==NEXT) {
            if(pageIndex==pageIndexMax) return;
            pageIndex++;
        }
        runOnUiThread(()->{
            linearlayout_progressbar.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
        });
        // SystemClock.sleep(1000); // 불필요 코드(교육 목적을 위해 삽입. progressbar 표시 확인용)
        String xmlString=downloadXml();
        getInfoFromXml(xmlString);
        runOnUiThread(()->{
            adapter.notifyDataSetChanged();
            setTitle(pageIndex+" / "+pageIndexMax);
            linearlayout_progressbar.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        });
    }
    private void getInfoFromXml(String xmlString) {
        titleList.clear();
        imageUrlList.clear();
        String data="", title="", imageUrl="";
        boolean jpgYN=false;
        int totalCnt=0;
        XmlPullParser parser= Xml.newPullParser();
        try {
            parser.setInput(new StringReader(xmlString));
            for(int event=parser.getEventType(); event!=XmlPullParser.END_DOCUMENT; event=parser.next()){
                if(event==XmlPullParser.TEXT) { data=parser.getText(); continue; }
                if(event!=XmlPullParser.END_TAG) continue;
                String tag=parser.getName();
                if(tag.equals("totalCnt")) totalCnt=Integer.parseInt(data);
                if(tag.equals("pageUnit")) pageUnit=Integer.parseInt(data);
                if(tag.equals("title")) title=data;
                if(tag.equals("fileNm")) jpgYN=data.endsWith(".jpg");
                if(tag.equals("linkUrl") && jpgYN) imageUrl=data.trim();
                if(tag.equals("list")) {
                    titleList.add(title);
                    imageUrlList.add(imageUrl);
                }
            }
            pageIndexMax=totalCnt/pageUnit;
            if(totalCnt%pageUnit>0) pageIndexMax++;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String downloadXml() {
        StringBuilder sb=new StringBuilder();
        try {
            handleSSL();
            URL url=new URL("https://www.gogung.go.kr/openApiPublication.do?pageIndex="+pageIndex);
            BufferedReader si=new BufferedReader(new InputStreamReader(url.openStream()));
            for(String line=si.readLine(); line!=null; line=si.readLine()) sb.append(line);
            si.close();
        } catch (Exception e) { throw new RuntimeException(e); }
        return sb.toString();
    }
    // Reference: https://stackoverflow.com/questions/2642777/trusting-all-certificates-using-httpclient-over-https/5297100#5297100
    private void handleSSL() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslContext= SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{
                new X509TrustManager() {
                    @Override public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
                    @Override public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
                    @Override public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[]{};}
                }
        },null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }
}