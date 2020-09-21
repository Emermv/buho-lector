package pe.edu.upc.moviles.buholector.registrousuario.interactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import pe.edu.upc.moviles.buholector.data.config.RetrofitConfig;
import pe.edu.upc.moviles.buholector.data.entity.request.RegistrarUsuarioRequest;
import pe.edu.upc.moviles.buholector.data.entity.response.RegistrarUsuarioResponse;
import pe.edu.upc.moviles.buholector.registrousuario.presenter.RegistroUsuarioPresenterImpl;
import pe.edu.upc.moviles.buholector.utils.Constantes;
import pe.edu.upc.moviles.buholector.utils.Utilitarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUsuarioInteractorImpl implements RegistroUsuarioInteractor {

  @Override
  public void registroUsuario(String usuario, String password, String email,
                              final RegistroUsuarioPresenterImpl.RegistroUsuarioResponseCallback callback) {

    String idTransaccion = Utilitarios.idTransaction;
    RegistrarUsuarioRequest request = RegistrarUsuarioRequest.builder()
        .nombreUsuario(usuario)
        .password(password)
        .mail(email)
        .build();

    Call<RegistrarUsuarioResponse> call = RetrofitConfig.getRetrofitInstance().registroUsuario(request, idTransaccion);

    call.enqueue(new Callback<RegistrarUsuarioResponse>() {
      @Override
      public void onResponse(Call<RegistrarUsuarioResponse> call, Response<RegistrarUsuarioResponse> response) {
        String msgError = "";
        if (response.isSuccessful()) {
          if (Constantes.COD_AUDITORIA_OK.equals(response.body().getAuditoria().getCodigoRespuesta())) {
            callback.onRegistroUsuarioSuccess(response.body());
          }
          msgError = response.body().getAuditoria().getDescripcionRespuesta();
        } else {
          try {
            Gson gson = new GsonBuilder().create();
            msgError = gson.fromJson(response.errorBody().string(), RegistrarUsuarioResponse.class)
                .getAuditoria().getDescripcionRespuesta();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        callback.onRegistroUsuarioError(msgError);
      }

      @Override
      public void onFailure(Call<RegistrarUsuarioResponse> call, Throwable t) {
        callback.onRegistroUsuarioError(Constantes.ERROR_DEFAULT);
      }
    });

  }
}
