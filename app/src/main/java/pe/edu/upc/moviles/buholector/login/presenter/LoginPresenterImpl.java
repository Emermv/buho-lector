package pe.edu.upc.moviles.buholector.login.presenter;

import pe.edu.upc.moviles.buholector.data.entity.response.LoginResponse;
import pe.edu.upc.moviles.buholector.login.interactor.LoginInteractorImpl;
import pe.edu.upc.moviles.buholector.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginInteractorImpl interactor;
    private LoginView view;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl();
    }

    @Override
    public void sendDataToLogin(String userName, String password) {
        view.showLoading();
        interactor.login(userName, password, new LoginResponseCallback() {
            @Override
            public void onLoginResponseSuccess(LoginResponse loginResponse) {
                view.hideLoading();
                view.loginSuccess(loginResponse.getEmail());

            }

            @Override
            public void onLoginResponseError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }

    public interface LoginResponseCallback {
        void onLoginResponseSuccess(LoginResponse loginResponse);
        void onLoginResponseError(String message);
    }
}
