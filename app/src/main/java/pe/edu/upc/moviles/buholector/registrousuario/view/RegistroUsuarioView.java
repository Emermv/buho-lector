package pe.edu.upc.moviles.buholector.registrousuario.view;

import pe.edu.upc.moviles.buholector.base.BaseView;

public interface RegistroUsuarioView extends BaseView {
  void registroSuccess(String msg);
  void showError(String errorMessage);
}
