package intro.api.senai.com.apiusage.utils;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import intro.api.senai.com.apiusage.models.CEP;

public class HttpService {

    public HttpService() {
        // Standard Constructor
    }

    public <T extends Object> T getObject(final String url, final Class<T> type) {
        T obj = null;
        AsyncTask<Void, Void, T> task = new AsyncTask<Void, Void, T>() {
            @Override
            protected T doInBackground(Void... voids) {
                try {
                    StringBuilder responseBuilder = new StringBuilder();
                    URL urlObj = new URL(url);
                    try {
                        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("Accept", "application/json");
                        connection.setConnectTimeout(5000);
                        connection.setDoOutput(true);
                        connection.connect();
                        // Getting response from a Stream
                        Scanner scannerStream = new Scanner(urlObj.openStream());
                        while(scannerStream.hasNext()) {
                            responseBuilder.append(scannerStream.next() + " ");
                        }
                        ObjectMapper om = new ObjectMapper();
                        return om.readValue(responseBuilder.toString(), type);
                    } catch (IOException e) {
                        // CEP not found?
                        return null;
                    }
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        try {
            task.execute();
            obj = task.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

}
