package pe.edu.upc.moviles.buholector.base;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements BaseView {

  @Override
  public void showLoading() {

  }

  @Override
  public void hideLoading() {

  }

  protected void goTo(Context context, Class classToGo) {
    Intent intent = new Intent(context, classToGo);
    startActivity(intent);
    finish();
  }

  private void backTo() {

  }
}
