package club.infolab.recyclingstarter.retrofit;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import club.infolab.recyclingstarter.registration.RegistrationCallback;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTransactions {
    /** Ссылка на сервер. **/
    private static final String BASE_URL = "https://daniilor.pythonanywhere.com/";
    private static RetrofitTransactions retrofitTransactions;

    private RetrofitTransactions() {

    }

    public static RetrofitTransactions getInstance() {
        if (retrofitTransactions == null) {
            retrofitTransactions = new RetrofitTransactions();
        }
        return retrofitTransactions;
    }

    public void addCollaborator(Collaborator collaborator, RegistrationCallback callBack) {
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<ResponseBody> call = service.addCollaborator(collaborator);

        call.enqueue(new Callback<ResponseBody>() {
            /* Метод, вызываемый при успешной отправки данных. */
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.d("retrofit", response.body() != null ?
                                response.body().string() : "null");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    getBox(collaborator.getPhoneNumber());
                    callBack.onRegistrationResult(true);
                }
            }

            /* Метод, вызываемый при неудачной отправки данных. */
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                callBack.onRegistrationResult(false);
            }
        });
    }

    public void getBox(String phone) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HashMap params = new HashMap();
        params.put("phone", phone);

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> callGetBox = apiService.getBox(params);
        callGetBox.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.d("retrofit", response.body() != null ?
                                response.body().string() : "null");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void fillBox(int boxId, Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HashMap params = new HashMap();
        params.put("box_id", boxId);

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> callFillBox = apiService.fillBox(params);
        callFillBox.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.d("retrofit", response.body() != null ?
                                response.body().string() : "null");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Dialog dialog = new Dialog(context);
                    dialog.setTitle("Контейнер будет очищен");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void clearBox(int boxId, Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HashMap params = new HashMap();
        params.put("box_id", boxId);

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> callFillBox = apiService.clearBox(params);
        callFillBox.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.d("retrofit", response.body() != null ?
                                response.body().string() : "null");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Dialog dialog = new Dialog(context);
                    dialog.setTitle("Результаты отправлены");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
