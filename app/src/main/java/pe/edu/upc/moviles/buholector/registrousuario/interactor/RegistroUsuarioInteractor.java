package pe.edu.upc.moviles.buholector.registrousuario.interactor;

import pe.edu.upc.moviles.buholector.registrousuario.presenter.RegistroUsuarioPresenterImpl;

public interface RegistroUsuarioInteractor {
  void registroUsuario(String usuario, String password, String email,
                       RegistroUsuarioPresenterImpl.RegistroUsuarioResponseCallback callback);
}
