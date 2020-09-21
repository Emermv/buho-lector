package pe.edu.upc.moviles.buholector.data.entity.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Auditoria {

  private String idTransaccion;
  private String metodo;
  private String fecha;
  private String codigoRespuesta;
  private String descripcionRespuesta;

}
