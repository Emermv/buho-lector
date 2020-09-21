package pe.edu.upc.moviles.buholector.data.config;


import pe.edu.upc.moviles.buholector.data.entity.request.LoginRequest;
import pe.edu.upc.moviles.buholector.data.entity.request.RegistrarUsuarioRequest;
import pe.edu.upc.moviles.buholector.data.entity.response.LoginResponse;
import pe.edu.upc.moviles.buholector.data.entity.response.RegistrarUsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface HttpDomain {

    @POST("/seguridad/login")
    Call<LoginResponse> login( @Body LoginRequest loginRequest, @Header("idTransaccion") String idTransaction);

    @POST("/seguridad/usuario")
    Call<RegistrarUsuarioResponse> registroUsuario(@Body RegistrarUsuarioRequest request, @Header("idTransaccion") String idTransaction);

}
