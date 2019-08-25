package com.app.shimmereffects;


import java.util.List;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {


    @POST("retrofit/POST/readcontacts.php")
    Call<List<Contacts>> getContacts();

    @FormUrlEncoded
    @POST("retrofit/POST/addcontact.php")
    public Call<Contacts> insertUser(
            @Field("name") String name,
            @Field("email") String email);

    @FormUrlEncoded
    @POST("retrofit/POST/editcontact.php")
    public Call<Contacts> editUser(
            @Field("id") String id,
            @Field("name") String name,
            @Field("email") String email);


    @FormUrlEncoded
    @POST("retrofit/POST/deletecontact.php")
    Call<Contacts> deleteUser(
            @Field("id") int id
    );



    @GET("retrofit/GET/getcontacts.php")
    Observable<List<Contacts>> getproductdata(
            @Query("item_type") String item_type,
            @Query("key") String keyword

    );


    //for live data search
//    @GET("retrofit/GET/getcontacts.php")
//    Observable<List<Contacts>> getContact(
//            @Query("item_type") String item_type,
//            @Query("key") String keyword
//    );

}