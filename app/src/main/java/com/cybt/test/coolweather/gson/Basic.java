package com.cybt.test.coolweather.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    //因为json的一些字段可能不太适合直接作为Java字段来命名，所以使用@SerializedName注解来将对象里的属性跟json里字段对应值匹配起来。
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updateTime;
    }
}
