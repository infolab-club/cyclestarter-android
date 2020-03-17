package com.infolab.ecohack.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Интерфейс для взаимодействия с сервером.
 * @author Глеб Новиков
 */
public interface ApiService {
    // TODO: Изменить запрос "add_user" на "add_collaborator".
    /**
     * Метод добавления нового сотрудника в базу данных.
     * @param collaborator сотрудник офиса
     */
    @POST("add_user")
    Call<Collaborator> addCollaborator(@Body Collaborator collaborator);
}
