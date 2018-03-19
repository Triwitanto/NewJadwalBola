package jadwalbola.triwi.id.jadwalbola;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by TRW on 3/11/2018.
 */

public class BackgroundTask extends AsyncTask <Void,Void,Void>{
    ProgressDialog progressDialog;
    Context ctx;
    String json_url = "http://127.0.0.1/bolainfo/get_bola_details.php";

    public BackgroundTask (Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(ctx);
            progressDialog.setIndeterminate(true);
            progressDialog.setTitle("Please Wait...");
            progressDialog.setMessage("Download in Progress...");
            progressDialog.setCancelable(false);
            progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(json_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder =  new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine())!=null)
            {

                stringBuilder.append(line+"\n");
                Thread.sleep(500);

            }
            httpURLConnection.disconnect();
            String json_data = stringBuilder.toString().trim();
            JSONObject jsonObject = new JSONObject(json_data);
            JSONArray jsonArray = jsonObject.getJSONArray("bola_details");
            BolaDbHelper bolaDbHelper = new BolaDbHelper(ctx);
            SQLiteDatabase db = bolaDbHelper.getWritableDatabase();

            int count = 0;
            while (count<jsonArray.length())
            {

                JSONObject JO = jsonArray.getJSONObject(count);
                    count++;


                bolaDbHelper.putInformation(JO.getString("tanggal"), JO.getString("jadwal"), JO.getString("tv"),db);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();

    }
}
