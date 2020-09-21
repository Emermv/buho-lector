package pe.edu.upc.moviles.buholector.data.entity.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrarUsuarioResponse {
  private Auditoria auditoria;
  private Integer idUsuario;
}
