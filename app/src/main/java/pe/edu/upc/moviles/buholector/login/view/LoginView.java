package pe.edu.upc.moviles.buholector.login.view;

import pe.edu.upc.moviles.buholector.base.BaseView;

public interface LoginView extends BaseView {
    void loginSuccess(String email);
    void showError(String errorMessage);
}
