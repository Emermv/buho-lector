package pe.edu.upc.moviles.buholector.registrousuario.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pe.edu.upc.moviles.buholector.base.BaseActivity;
import pe.edu.upc.moviles.buholector.databinding.ActivityRegistrousuarioBinding;
import pe.edu.upc.moviles.buholector.login.view.LoginActivity;
import pe.edu.upc.moviles.buholector.registrousuario.presenter.RegistroUsuarioPresenter;
import pe.edu.upc.moviles.buholector.registrousuario.presenter.RegistroUsuarioPresenterImpl;

public class RegistroUsuarioActivity extends BaseActivity implements RegistroUsuarioView {

  private ActivityRegistrousuarioBinding binding;
  private RegistroUsuarioPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityRegistrousuarioBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    presenter = new RegistroUsuarioPresenterImpl(this);

    binding.btnRegistro.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.sendDataToRegistroUsuario(binding.edtxUsuario.getText().toString(),
            binding.edtxPassword.getText().toString(), binding.edtxCorreo.getText().toString());
      }
    });

    binding.tvIniciosesion.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        goTo(getApplicationContext(), LoginActivity.class);
      }
    });
  }

  @Override
  public void registroSuccess(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    goTo(this, LoginActivity.class);
  }

  @Override
  public void showError(String errorMessage) {
    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
  }
}