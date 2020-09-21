package pe.edu.upc.moviles.buholector.data.entity.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrarUsuarioRequest {
  private String nombreUsuario;
  private String password;
  private String mail;
}
