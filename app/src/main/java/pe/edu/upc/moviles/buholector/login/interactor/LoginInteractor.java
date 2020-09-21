package pe.edu.upc.moviles.buholector.login.interactor;

import pe.edu.upc.moviles.buholector.login.presenter.LoginPresenterImpl;

public interface LoginInteractor {
    void login(String userName, String password, LoginPresenterImpl.LoginResponseCallback callback);
}
