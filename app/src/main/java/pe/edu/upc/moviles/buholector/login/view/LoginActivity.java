package pe.edu.upc.moviles.buholector.login.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pe.edu.upc.moviles.buholector.MainActivity;
import pe.edu.upc.moviles.buholector.PrincipalActivity;
import pe.edu.upc.moviles.buholector.base.BaseActivity;
import pe.edu.upc.moviles.buholector.databinding.ActivityLoginBinding;
import pe.edu.upc.moviles.buholector.login.presenter.LoginPresenter;
import pe.edu.upc.moviles.buholector.login.presenter.LoginPresenterImpl;
import pe.edu.upc.moviles.buholector.registrousuario.view.RegistroUsuarioActivity;

public class LoginActivity extends BaseActivity implements LoginView {

  private ActivityLoginBinding binding;
  private LoginPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    presenter = new LoginPresenterImpl(this);

    binding.button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.sendDataToLogin(binding.edtxUserName.getText().toString(),
            binding.edtxPassword.getText().toString());
      }

    });

    binding.tvRegistrarme.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        goTo(getApplicationContext(), RegistroUsuarioActivity.class);
      }
    });

  }



  @Override
  public void loginSuccess(String email) {
    Toast.makeText(this, "Bienvenido "+email, Toast.LENGTH_SHORT).show();
    goTo(this, MainActivity.class);
  }

  @Override
  public void showError(String errorMessage) {
    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
  }

}