package ca.ualberta.appfive;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Omar on 3/14/2016.
 */
public class FileParser {
    public static String FILENAME = "books.json";
    Context context;
    final AppController ac = AppFiveApp.getAppController();
    public FileParser(Context context){
        this.context = context;
    }

    public void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(ac.getMyBookArray(), out);
            out.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    public void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Book>>() {}.getType();
            ac.setMyBookArray((ArrayList<Book>) gson.fromJson(in, listType));
            if (ac.getMyBookArray() == null){
                ac.setMyBookArray(new ArrayList<Book>());
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            ac.setMyBookArray(new ArrayList<Book>());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
