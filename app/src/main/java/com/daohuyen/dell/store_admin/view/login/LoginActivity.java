package com.daohuyen.dell.store_admin.view.login;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.daohuyen.dell.store_admin.R;
import com.daohuyen.dell.store_admin.common.Constants;
import com.daohuyen.dell.store_admin.custom.LoadingDialog;
import com.daohuyen.dell.store_admin.model.body.UserBody;
import com.daohuyen.dell.store_admin.presenter.login.LoginPresenter;
import com.daohuyen.dell.store_admin.presenter.login.LoginPresenterIpl;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {
    EditText etEmail;
    EditText etPass;
    Button btLogin;
    TextView tvRegister;
    private LoadingDialog loadingDialog;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.et_email);
        etPass = findViewById(R.id.et_pass);
        btLogin = findViewById(R.id.bt_login);
        tvRegister = findViewById(R.id.txt_register);
        loginPresenter= new LoginPresenterIpl(this,this);
        loadingDialog = new LoadingDialog(this);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserBody userBody= new UserBody(etEmail.getText().toString(), etPass.getText().toString());
                loginPresenter.fetchLogin(userBody);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        String email = getIntent().getStringExtra(Constants.KEY_EMAIL);
        if (email != null) {
            etEmail.setText(email);
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showProgress() {
//        loadingDialog.show();
    }


    @Override
    public void hideProgress() {
        loadingDialog.hide();

    }

    @Override
    public void showEmailInputError(String message) {

    }

    @Override
    public void showPasswordInputError(String message) {

    }

    @Override
    public void backToHomeScreen(int resultCode) {
        setResult(resultCode);
        finish();
    }




}

