package com.example.ngetichconverter.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceConfig {
    private static final String LIST_KEY="list_key";
    public static void writeToPreference(Context context, List<PreferenceItems> list){
        Gson gson=new Gson();
        String jsonString=gson.toJson(list);

        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(LIST_KEY,jsonString);
        editor.apply();
    }
    public static ArrayList<PreferenceItems> readeList(Context context){
        SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString=pref.getString(LIST_KEY,"");
        Gson gson=new Gson();
        Type type=new TypeToken<List<PreferenceItems>>(){}.getType();
        ArrayList<PreferenceItems> list=gson.fromJson(jsonString,type);
        return list;
    }

}
