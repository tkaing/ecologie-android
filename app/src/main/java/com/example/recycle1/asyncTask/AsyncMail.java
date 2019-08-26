package com.example.recycle1.asyncTask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.recycle1.data.service.GMailSender;

public class AsyncMail extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            GMailSender sender = new GMailSender("mohamedennoun@gmail.com", "shuyebie1415.");
            sender.sendMail("This is Subject",
                    "This is Body",
                    "mohamedennoun@gmail.com",
                    "mohamedennoun@gmail.com");
            Log.d("sendmail","that's works !");
            return sender;
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
            return null;
        }

    }
}
