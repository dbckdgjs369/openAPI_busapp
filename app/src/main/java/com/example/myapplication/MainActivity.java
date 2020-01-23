package com.example.myapplication;


import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStreamReader;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    EditText edit;
    TextView text;
    String key = "%2Fvx%2By1v3%2F%2BZ2ROElx9lgYq%2Fl47j%2BK6J8%2FBUyt8zupuWH6fDIzh3rNSgbiENI9H3i8vbmfx7wcesxRwAV4rRl1Q%3D%3D  ";
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //edit= (EditText)findViewById(R.id.edit);
        //text= (TextView)findViewById(R.id.text);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabHost tab_host = (TabHost) findViewById(R.id.tabhost);
        tab_host.setup();

        TabHost.TabSpec ts1 = tab_host.newTabSpec("tab1");
        ts1.setIndicator("즐겨찾기");
        ts1.setContent(R.id.tab1);
        tab_host.addTab(ts1);

        TabHost.TabSpec ts2 = tab_host.newTabSpec("tab2");
        ts2.setIndicator("노선");
        ts2.setContent(R.id.tab2);
        tab_host.addTab(ts2);

        TabHost.TabSpec ts3 = tab_host.newTabSpec("tab3");
        ts3.setIndicator("정류장");
        ts3.setContent(R.id.tab3);
        tab_host.addTab(ts3);


        tab_host.setCurrentTab(0);
       // StrictMode.enableDefaults();

        TextView status1 = (TextView) findViewById(R.id.textView1); //파싱된 결과확인!
        //new HTTPReqTask().execute();/////
        boolean initem = false, inAddr = false, inChargeTp = false, inCpId = false, inCpNm = false;
        boolean inCpStat = false, inCpTp = false, inCsId = false, inCsNm = false, inLat = false;
        boolean inLongi = false, inStatUpdateDatetime = false;

        String addr = null, chargeTp = null, cpId = null, cpNm = null, cpStat = null, cpTp = null, csId = null, csNm = null;
        String lat = null, longi = null, statUpdateDatetime = null;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String busUrl = "http://openapitraffic.daejeon.go.kr/api/rest/busRouteInfo/getStaionByRouteAll?serviceKey=%2Fvx%2By1v3%2F%2BZ2ROElx9lgYq%2Fl47j%2BK6J8%2FBUyt8zupuWH6fDIzh3rNSgbiENI9H3i8vbmfx7wcesxRwAV4rRl1Q%3D%3D&reqPage=1";
        String naver = "https://www.naver.com/";

        try {
            URL url = new URL(busUrl);
            //URLConnection busConnection= url.openConnection();
            //busConnection.connect();
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(15000);

            InputStream in = url.openStream();
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(new InputStreamReader(in, "UTF-8"));

            String tag;
            parser.next();

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("addr")){ //title 만나면 내용을 받을수 있게 하자
                            inAddr = true;
                        }
                        if(parser.getName().equals("chargeTp")){ //address 만나면 내용을 받을수 있게 하자
                            inChargeTp = true;
                        }
                        if(parser.getName().equals("cpId")){ //mapx 만나면 내용을 받을수 있게 하자
                            inCpId = true;
                        }
                        if(parser.getName().equals("cpNm")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCpNm = true;
                        }
                        if(parser.getName().equals("cpStat")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCpStat = true;
                        }
                        if(parser.getName().equals("cpTp")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCpTp = true;
                        }
                        if(parser.getName().equals("csId")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCsId = true;
                        }
                        if(parser.getName().equals("csNm")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCsNm = true;
                        }
                        if(parser.getName().equals("lat")){ //mapy 만나면 내용을 받을수 있게 하자
                            inLat = true;
                        }
                        if(parser.getName().equals("longi")){ //mapy 만나면 내용을 받을수 있게 하자
                            inLongi = true;
                        }
                        if(parser.getName().equals("statUpdateDatetime")){ //mapy 만나면 내용을 받을수 있게 하자
                            inStatUpdateDatetime = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inAddr){ //isTitle이 true일 때 태그의 내용을 저장.
                            addr = parser.getText();
                            inAddr = false;
                        }
                        if(inChargeTp){ //isAddress이 true일 때 태그의 내용을 저장.
                            chargeTp = parser.getText();
                            inChargeTp = false;
                        }
                        if(inCpId){ //isMapx이 true일 때 태그의 내용을 저장.
                            cpId = parser.getText();
                            inCpId = false;
                        }
                        if(inCpNm){ //isMapy이 true일 때 태그의 내용을 저장.
                            cpNm = parser.getText();
                            inCpNm = false;
                        }
                        if(inCpStat){ //isMapy이 true일 때 태그의 내용을 저장.
                            cpStat = parser.getText();
                            inCpStat = false;
                        }
                        if(inCpTp){ //isMapy이 true일 때 태그의 내용을 저장.
                            cpTp = parser.getText();
                            inCpTp = false;
                        }
                        if(inCsId){ //isMapy이 true일 때 태그의 내용을 저장.
                            csId = parser.getText();
                            inCsId = false;
                        }
                        if(inCsNm){ //isMapy이 true일 때 태그의 내용을 저장.
                            csNm = parser.getText();
                            inCsNm = false;
                        }
                        if(inLat){ //isMapy이 true일 때 태그의 내용을 저장.
                            lat = parser.getText();
                            inLat = false;
                        }
                        if(inLongi){ //isMapy이 true일 때 태그의 내용을 저장.
                            longi = parser.getText();
                            inLongi = false;
                        }
                        if(inStatUpdateDatetime){ //isMapy이 true일 때 태그의 내용을 저장.
                            statUpdateDatetime = parser.getText();
                            inStatUpdateDatetime = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"주소 : "+ addr +"\n 충전기 타입: "+ chargeTp +"\n 충전소ID : " + cpId
                                    +"\n 충전기 명칭 : " + cpNm +  "\n 충전기 상태 코드 : " + cpStat+ "\n 충전 방식 : " + cpTp
                                    +"\n 충전소 ID : " +csId + "\n 충전소 명칭 : " + csNm + "\n 위도 : " +lat
                                    +"\n 경도 : " +longi +"\n 충전기상태갱신시각 : " +statUpdateDatetime+"\n");
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            status1.setText("에러가..났습니다...");
        }

    }
        //////////////////////////////////////////
//
//    //Button을 클릭했을 때 자동으로 호출되는 callback method
//    public void mOnClick(View v){
//
//        switch( v.getId() ){
//            case R.id.button:
//
//                // 급하게 짜느라 소스가 지저분해요..
//                new Thread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        data= getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기
//
//
//                        runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                // TODO Auto-generated method stub
//                                text.setText(data); //TextView에 문자열  data 출력
//                            }
//                        });
//
//                    }
//                }).start();
//
//                break;
//        }
//
//    }
//
//
//    String getXmlData(){
//
//        StringBuffer buffer=new StringBuffer();
//
//        String str= edit.getText().toString();//EditText에 작성된 Text얻어오기
//        String location = URLEncoder.encode(str);//한글의 경우 인식이 안되기에 utf-8 방식으로 encoding     //지역 검색 위한 변수
//
//
//        String queryUrl="http://openapi.kepco.co.kr/service/evInfoService/getEvSearchList?"//요청 URL
//                +"addr="+location
//                +"&pageNo=1&numOfRows=1000&ServiceKey=" + key;
//
//        try {
//            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
//            InputStream is= url.openStream(); //url위치로 입력스트림 연결
//
//            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
//            XmlPullParser xpp= factory.newPullParser();
//            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기
//
//            String tag;
//
//            xpp.next();
//            int eventType= xpp.getEventType();
//
//            while( eventType != XmlPullParser.END_DOCUMENT ){
//                switch( eventType ){
//                    case XmlPullParser.START_DOCUMENT:
//                        buffer.append("파싱 시작...\n\n");
//                        break;
//
//                    case XmlPullParser.START_TAG:
//                        tag= xpp.getName();//태그 이름 얻어오기
//
//                        if(tag.equals("item")) ;// 첫번째 검색결과
//                        else if(tag.equals("addr")){
//                            buffer.append("주소 : ");
//                            xpp.next();
//                            buffer.append(xpp.getText());//addr 요소의 TEXT 읽어와서 문자열버퍼에 추가
//                            buffer.append("\n"); //줄바꿈 문자 추가
//                        }
//                        else if(tag.equals("chargeTp")){
//                            buffer.append("충전소타입 : ");
//                            xpp.next();
//                            buffer.append(xpp.getText());
//                            buffer.append("\n");
//                        }
//                        else if(tag.equals("cpId")){
//                            buffer.append("충전소ID :");
//                            xpp.next();
//                            buffer.append(xpp.getText());//cpId
//                            buffer.append("\n");
//                        }
//                        else if(tag.equals("cpNm")){
//                            buffer.append("충전기 명칭 :");
//                            xpp.next();
//                            buffer.append(xpp.getText());//cpNm
//                            buffer.append("\n");
//                        }
//                        else if(tag.equals("cpStat")){
//                            buffer.append("충전기 상태 코드 :");
//                            xpp.next();
//                            buffer.append(xpp.getText());//
//                            buffer.append("\n");
//                        }
//                        else if(tag.equals("cpTp")){
//                            buffer.append("충전 방식 :");
//                            xpp.next();
//                            buffer.append(xpp.getText());//
//                            buffer.append("  ,  ");
//                        }
//                        else if(tag.equals("csId")){
//                            buffer.append("충전소 ID :");
//                            xpp.next();
//                            buffer.append(xpp.getText());//csId
//                            buffer.append("\n");
//                        }
//                        else if(tag.equals("cpNm")){
//                            buffer.append("충전소 명칭 :");
//                            xpp.next();
//                            buffer.append(xpp.getText());
//                            buffer.append("\n");
//                        }
//                        else if(tag.equals("lat")){
//                            buffer.append("위도 :");
//                            xpp.next();
//                            buffer.append(xpp.getText());//
//                            buffer.append("\n");
//                        }
//                        else if(tag.equals("longi")){
//                            buffer.append("경도 :");
//                            xpp.next();
//                            buffer.append(xpp.getText());//
//                            buffer.append("\n");
//                        }
//                        else if(tag.equals("statUpdateDatetime")){
//                            buffer.append("충전기상태갱신시각 :");
//                            xpp.next();
//                            buffer.append(xpp.getText());//
//                            buffer.append("\n");
//                        }
//                        break;
//
//                    case XmlPullParser.TEXT:
//                        break;
//
//                    case XmlPullParser.END_TAG:
//                        tag= xpp.getName(); //태그 이름 얻어오기
//
//                        if(tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
//
//                        break;
//                }
//
//                eventType= xpp.next();
//            }
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch blocke.printStackTrace();
//        }
//
//        buffer.append("파싱 끝\n");
//
//        return buffer.toString();//StringBuffer 문자열 객체 반환
//
//    }
//
//
//
//    //////////////////////
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            MenuItem searchItem = menu.findItem(R.id.toolbar_search);
//            SearchView searchView = (SearchView) searchItem.getActionView();
//            searchView.setQueryHint("정류장, 버스 번호, 노선 이름");
//
////            //리스너 구현
////            searchView.setOnQueryTextListener(queryTextListener);
////            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
////            if(null!=searchManager ) {
////                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
////            }
////            searchView.setIconifiedByDefault(true);
//
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.toolbar_search) {
//            Toast.makeText(this, "검색버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
