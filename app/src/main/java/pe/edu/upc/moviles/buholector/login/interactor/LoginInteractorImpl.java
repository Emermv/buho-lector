package pe.edu.upc.moviles.buholector.login.interactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import pe.edu.upc.moviles.buholector.data.config.RetrofitConfig;
import pe.edu.upc.moviles.buholector.data.entity.request.LoginRequest;
import pe.edu.upc.moviles.buholector.data.entity.response.LoginResponse;
import pe.edu.upc.moviles.buholector.login.presenter.LoginPresenterImpl;
import pe.edu.upc.moviles.buholector.utils.Constantes;
import pe.edu.upc.moviles.buholector.utils.Utilitarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor {

  @Override
  public void login(String userName, String password, final LoginPresenterImpl.LoginResponseCallback callback) {

    String idTransaccion = Utilitarios.idTransaction;
    LoginRequest loginRequest = LoginRequest.builder()
        .userName(userName).password(password).build();

    Call<LoginResponse> call = RetrofitConfig.getRetrofitInstance().login(loginRequest, idTransaccion);

    call.enqueue(new Callback<LoginResponse>() {
      @Override
      public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

        String msgError = "";

        if (response.isSuccessful()) {
          if (Constantes.COD_AUDITORIA_OK.equals(response.body().getAuditoria().getCodigoRespuesta())) {
            callback.onLoginResponseSuccess(response.body());
          }
          msgError = response.body().getAuditoria().getDescripcionRespuesta();
        } else {
          try {
            Gson gson = new GsonBuilder().create();
            msgError = gson.fromJson(response.errorBody().string(), LoginResponse.class)
                .getAuditoria().getDescripcionRespuesta();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        callback.onLoginResponseError(msgError);
      }

      @Override
      public void onFailure(Call<LoginResponse> call, Throwable t) {
        callback.onLoginResponseError(Constantes.ERROR_DEFAULT);
      }
    });

  }
}
