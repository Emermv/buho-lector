package pe.edu.upc.moviles.buholector.data.entity.request;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    @SerializedName("nombreUsuario")
    private String userName;
    @SerializedName("password")
    private String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
