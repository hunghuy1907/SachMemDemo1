package com.hungth.sachmemdemo.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.hungth.sachmemdemo.R;

/**
 * Created by ngoth on 3/14/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static Boolean ISLOGIN = false;
    private int RC_SIGN_IN = 101;
    private EditText edtEmail;
    private EditText edtPassWord;
    private TextView btnLogin;
    private TextView btnFogotPassWord;
    private TextView btnRegister;
    private ImageView imgHidePassWord;
    private boolean showPassWord = false;


    private LoginButton mBtnLoginFacebook;
    private CallbackManager mCallbackManager;
    private GoogleSignInClient googleSignInClient;
    private SignInButton signInButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ISLOGIN) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        mBtnLoginFacebook = findViewById(R.id.btn_login_with_facebook);
        mCallbackManager = CallbackManager.Factory.create();
        initalizeCompoment();
        mBtnLoginFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, loginResult.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;

    }

    private void initalizeCompoment() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassWord = findViewById(R.id.edt_password);
        edtPassWord.setOnClickListener(this);
        imgHidePassWord = findViewById(R.id.img_hide_password_in_login);
        imgHidePassWord.setOnClickListener(this);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        btnFogotPassWord = findViewById(R.id.btn_forgotPassword);
        btnFogotPassWord.setOnClickListener(this);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
        signInButton = findViewById(R.id.btn_login_with_google);
        signInButton.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String email = account.getEmail();
            String id = account.getId();
            Toast.makeText(this, email + " " + id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private boolean chechLogin(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        } else if ((email.equalsIgnoreCase("sachmem@gmail.com") && password.equals("abc123456"))
                || (email.equalsIgnoreCase("noname@gmail.com") && password.equals("khongcopass"))) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_hide_password_in_login:
                if (imgHidePassWord.getVisibility() == View.VISIBLE) {
                    if (showPassWord) {
                        Log.d("demoo", "onClick: hide");
                        imgHidePassWord.setBackgroundResource(R.drawable.icon_hide_password);
                        showPassWord = false;
                        edtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT );
                    } else {
                        Log.d("demoo", "onClick: show");
                        imgHidePassWord.setBackgroundResource(R.drawable.icon_show_password);
                        showPassWord= true;
                        edtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                }
                break;
            case R.id.btn_login:
                if (chechLogin(edtEmail.getText().toString(),
                        edtPassWord.getText().toString())) {
                    ISLOGIN = true;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Đăng nhập thất bại. Xin vui lòng kiểm tra lại email hoặc mật khẩu", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_forgotPassword:
                showDialogFogotPassWord();
                break;
            case R.id.btn_register:
                Intent intent1 = new Intent();
                break;
            case R.id.btn_login_with_facebook:
                break;
            case R.id.btn_login_with_google:
                signInWithGoogle();
                break;
            default:
                break;
        }
    }

    private void showDialogFogotPassWord() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_fogot_password);
        final EditText edtEmailInFogotPassWord = dialog.findViewById(R.id.edt_email_forgot_password);
        TextView btnGetFogotPassWord = dialog.findViewById(R.id.btn_forgot_password_in_dialog);
        if (!edtEmail.getText().toString().isEmpty()) {
            edtEmailInFogotPassWord.setText(edtEmail.getText().toString());
        }
        btnGetFogotPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkEmail = false;
                int temp = 0;
                String email = edtEmailInFogotPassWord.getText().toString();
                for (int i = 0; i < email.length(); i++) {
                    if (email.charAt(i) == '@') {
                        temp = 1;
                        break;
                    }
                }
                if (email.endsWith(".com") && temp == 1) {
                    checkEmail = true;
                } else checkEmail = false;
                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Thông báo");
                if (checkEmail) {
                    //Todo
                    builder.setMessage("Chúng tôi đã gửi 1 email đến " + edtEmailInFogotPassWord.getText().toString()
                            + " vui lòng kiểm tra hòm thư đến để xác nhận lấy lại mật khẩu!");
                    builder.setPositiveButton("OK", null);
                    Dialog dialog1 = builder.show();
                    dialog1.show();
                    dialog.dismiss();
                } else {
                    builder.setMessage("Xin vui lòng kiểm tra lại địa chỉ email!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int which) {
                            dialog.show();
                        }
                    });
                    Dialog dialog1 = builder.show();
                    dialog1.show();
                    dialog.hide();
                }
            }
        });
        dialog.show();

    }
}
