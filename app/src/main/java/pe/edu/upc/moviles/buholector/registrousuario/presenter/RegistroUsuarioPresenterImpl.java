package pe.edu.upc.moviles.buholector.registrousuario.presenter;

import pe.edu.upc.moviles.buholector.data.entity.response.RegistrarUsuarioResponse;
import pe.edu.upc.moviles.buholector.registrousuario.interactor.RegistroUsuarioInteractor;
import pe.edu.upc.moviles.buholector.registrousuario.interactor.RegistroUsuarioInteractorImpl;
import pe.edu.upc.moviles.buholector.registrousuario.view.RegistroUsuarioView;

public class RegistroUsuarioPresenterImpl implements RegistroUsuarioPresenter {

  private RegistroUsuarioInteractor interactor;
  private RegistroUsuarioView view;

  public RegistroUsuarioPresenterImpl(RegistroUsuarioView view) {
    this.view = view;
    interactor = new RegistroUsuarioInteractorImpl();
  }

  @Override
  public void sendDataToRegistroUsuario(String userName, String password, String email) {
    view.showLoading();

    interactor.registroUsuario(userName, password, email, new RegistroUsuarioResponseCallback() {
      @Override
      public void onRegistroUsuarioSuccess(RegistrarUsuarioResponse response) {
        view.hideLoading();
        view.registroSuccess(response.getAuditoria().getDescripcionRespuesta());
      }

      @Override
      public void onRegistroUsuarioError(String message) {
        view.hideLoading();
        view.showError(message);
      }
    });

  }

  public interface RegistroUsuarioResponseCallback {
    void onRegistroUsuarioSuccess(RegistrarUsuarioResponse response);
    void onRegistroUsuarioError(String message);
  }

}
