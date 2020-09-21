package pe.edu.upc.moviles.buholector.data.entity.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LoginResponse {

    private Auditoria auditoria;

    @SerializedName("mail")
    private String email;

    private boolean isPerfilCreado;

}
