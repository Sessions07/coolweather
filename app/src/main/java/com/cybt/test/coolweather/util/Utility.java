package com.cybt.test.coolweather.util;


import android.text.TextUtils;

import com.cybt.test.coolweather.db.City;
import com.cybt.test.coolweather.db.County;
import com.cybt.test.coolweather.db.Province;
import com.cybt.test.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static Boolean handleProvinceReports(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);//实例化一个json数组，response作为入参传进来
                for (int i=0; i< allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);//遍历数组，创建一个json对象，去从数组里取数据
                    Province province = new Province();//实例化一个省
                    province.setProvinceCode(provinceObject.getInt("id"));//捯id
                    province.setProvinceName(provinceObject.getString("name"));//捯名字
                    province.save();//调用Datasupport存储方法
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的市级数据
     */
    public static Boolean handleCitiesReports(String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i=0; i< allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Boolean handleCountyReports(String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for(int i=0;i< allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);//返回一个weather类
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
