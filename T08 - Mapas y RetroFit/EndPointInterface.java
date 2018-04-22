package com.alex.helyer.mapsretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by alexchapa on 4/13/18.
 */

public interface EndPointInterface {

    String URL = "http://alexchaps.com/bde/";

    @GET("verReportes.php")
    Call<ResponseReporte> verReportes(
            @Query("latitud") String latitud,
            @Query("longitud") String longitud);

    @FormUrlEncoded
    @POST("nuevoReporte.php")
    Call<Void> insertarReporte(
            @Field("hashtag") String hashtag,
            @Field("comentario") String comentario,
            @Field("latitud") String latitud,
            @Field("longitud") String longitud);

}
