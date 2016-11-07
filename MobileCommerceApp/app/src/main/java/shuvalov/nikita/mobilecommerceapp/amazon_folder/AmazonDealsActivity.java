package shuvalov.nikita.mobilecommerceapp.amazon_folder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.ExecutionException;

import shuvalov.nikita.mobilecommerceapp.R;
import shuvalov.nikita.mobilecommerceapp.amazon_folder.DownloadTask;

public class AmazonDealsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amazon_deals);

        String result="Nothing happened";

        DownloadTask downloadTask = new DownloadTask();
        try {
            result = downloadTask.execute("https://www.amazon.com/gp/goldbox").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }
}
