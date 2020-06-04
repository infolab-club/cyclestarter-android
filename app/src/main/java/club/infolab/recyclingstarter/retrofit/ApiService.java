package club.infolab.recyclingstarter.retrofit;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Интерфейс для взаимодействия с сервером.
 */
public interface ApiService {
    /**
     * Метод добавления нового сотрудника в базу данных.
     * @param collaborator сотрудник офиса
     */
    @POST("add_user")
    Call<ResponseBody> addCollaborator(@Body Collaborator collaborator);

    @POST("get_box")
    Call<ResponseBody> getBox(@Body HashMap hashMap);

    @POST("fill_box")
    Call<ResponseBody> fillBox(@Body HashMap hashMap);

    @POST("fill_box")
    Call<ResponseBody> clearBox(@Body HashMap hashMap);
}
