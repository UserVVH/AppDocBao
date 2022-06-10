package com.dev.appdocbao;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewTitle;
    ArrayList<String> arrayTitle, arrayLink;
    ArrayAdapter arrayAdapter;
    Spinner spinnerTinTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.nếu không có Internet thì show ra thông báo yêu cầu kết nối
        if (!isConnected(this)) {
            showInternetDialog();
            return;
        }
        //END 1

        //2. chuyển tin tức vào ArrayAdapter -> Spiner -> (khi người chùng chọn -> ListView)
        listViewTitle = (ListView) findViewById(R.id.listViewTitle);
        spinnerTinTuc = (Spinner) findViewById(R.id.spinner);
        final ArrayList<String> strTinTuc = new ArrayList<String>();
        strTinTuc.add("Nổi bật");
        strTinTuc.add("Thế giới");
        strTinTuc.add("Thời sự");
        strTinTuc.add("Kinh doanh");
        strTinTuc.add("Giải trí");
        strTinTuc.add("Thể thao");
        strTinTuc.add("Pháp luật");
        strTinTuc.add("Giáo dục");
        strTinTuc.add("Mới nhất");
        strTinTuc.add("Sức khỏe");
        strTinTuc.add("Đời sống");
        strTinTuc.add("Du lịch");
        strTinTuc.add("Khoa học");
        strTinTuc.add("Số hóa");
        strTinTuc.add("Xe");
        strTinTuc.add("Ý kiến");
        strTinTuc.add("Tâm sự");
        strTinTuc.add("Cười");
        strTinTuc.add("Xem nhiều");
        ArrayAdapter arrayTinTuc = new ArrayAdapter(this, android.R.layout.simple_spinner_item, strTinTuc);
        spinnerTinTuc.setAdapter(arrayTinTuc);
        arrayTinTuc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTinTuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String name = strTinTuc.get(i);
                String selectTinTuc = "";
                switch (name) {
                    case "Nổi bật":
                        selectTinTuc = "https://vnexpress.net/rss/tin-noi-bat.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Thế giới":
                        selectTinTuc = "https://vnexpress.net/rss/the-gioi.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Thời sự":
                        selectTinTuc = "https://vnexpress.net/rss/thoi-su.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Kinh doanh":
                        selectTinTuc = "https://vnexpress.net/rss/kinh-doanh.rss";
                        getLinkRss(selectTinTuc);
                        ;
                        break;
                    case "Giải trí":
                        selectTinTuc = "https://vnexpress.net/rss/giai-tri.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Thể thao":
                        selectTinTuc = "https://vnexpress.net/rss/the-thao.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Pháp luật":
                        selectTinTuc = "https://vnexpress.net/rss/phap-luat.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Giáo dục":
                        selectTinTuc = "https://vnexpress.net/rss/giao-duc.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Mới nhất":
                        selectTinTuc = "https://vnexpress.net/rss/tin-moi-nhat.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Sức khỏe":
                        selectTinTuc = "https://vnexpress.net/rss/suc-khoe.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Đời sống":
                        selectTinTuc = "https://vnexpress.net/rss/gia-dinh.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Du lịch":
                        selectTinTuc = "https://vnexpress.net/rss/du-lich.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Khoa học":
                        selectTinTuc = "https://vnexpress.net/rss/khoa-hoc.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Số hóa":
                        selectTinTuc = "https://vnexpress.net/rss/so-hoa.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Xe":
                        selectTinTuc = "https://vnexpress.net/rss/oto-xe-may.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Ý kiến":
                        selectTinTuc = "https://vnexpress.net/rss/y-kien.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Tâm sự":
                        selectTinTuc = "https://vnexpress.net/rss/tam-su.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Cười":
                        selectTinTuc = "https://vnexpress.net/rss/cuoi.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    case "Xem nhiều":
                        selectTinTuc = "https://vnexpress.net/rss/tin-xem-nhieu.rss";
                        getLinkRss(selectTinTuc);
                        break;
                    default:
                        String author = "Vu Van Hai";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //END 2

        //3.chọn loại tin tức nổi bật làm mặc định khi chạy ứng dụng
        arrayTitle = new ArrayList<>();
        arrayLink = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayTitle);
        listViewTitle.setAdapter(arrayAdapter);
        new ReadRss().execute("https://vnexpress.net/rss/tin-noi-bat.rss");

        //chọn bài báo và chuyển sang intent mới(đọc báo trên trang VnExpress)
        listViewTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("linkTinTuc", arrayLink.get(i));
                startActivity(intent);
            }
        });
        //END 3
    }

    //4.Get các link bài báo từ link Rss
    private void getLinkRss(String str) {
        new ReadRss().execute(str);
        setListView();
    }
    //END 4

    //5.đọc và lấy kết quả từ RSS
    private class ReadRss extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String tieuDe = "";
            for (int i = 0; i < nodeList.getLength(); ++i) {
                Element element = (Element) nodeList.item(i);
                tieuDe = parser.getValue(element, "title");
                arrayTitle.add(tieuDe);
                arrayLink.add(parser.getValue(element, "link"));
            }
            arrayAdapter.notifyDataSetChanged();
        }
    }
    //END 5

    //6. xét listView từ Spinner DropDown list (gọi hàm này ở trong Oncreate)
    public void setListView() {
        arrayTitle = new ArrayList<>();
        arrayLink = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayTitle);
        listViewTitle.setAdapter(arrayAdapter);
    }
    //END 6

    //7. các hàm tạo thông báo khi không có kết nối Internet
    private void showInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_internet, findViewById(R.id.no_internet_layout));
        view.findViewById(R.id.try_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConnected(MainActivity.this)) {
                    showInternetDialog();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

        builder.setView(view);

        AlertDialog alertDialog = builder.create();

        alertDialog.show();

    }

    private boolean isConnected(MainActivity dashboardActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) dashboardActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());
    }
    //END 7


}