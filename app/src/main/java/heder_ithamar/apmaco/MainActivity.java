package heder_ithamar.apmaco;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import heder_ithamar.apmaco.ui.ActividadPrincipal;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView resultado = (TextView) findViewById(R.id.key);

        //Check si alquien ya se loego anteriormente
        if (AccessToken.getCurrentAccessToken() != null && com.facebook.Profile.getCurrentProfile() != null){

            Profile profile = Profile.getCurrentProfile();
            //Profile profile= com.facebook.Profile.getCurrentProfile();
            String name = profile.getFirstName();

            // App code
            Intent intent = new Intent(MainActivity.this,ActividadPrincipal.class);
            intent.putExtra("NOMBRE",name);
            startActivity(intent);
            finish();
        }

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            private ProfileTracker mProfileTracker;

            @Override
            public void onSuccess(LoginResult loginResult) {
                if(Profile.getCurrentProfile() == null) {
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                            // profile2 is the new profile
                            //Log.v("facebook - profile", profile2.getFirstName());
                            Intent appPrincipal = new Intent(MainActivity.this,ActividadPrincipal.class);
                            //appPrincipal.putExtra("NOMBRE", nombre);
                            mProfileTracker.stopTracking();
                            startActivity(appPrincipal);
                            finish();
                        }
                    };
                    // no need to call startTracking() on mProfileTracker
                    // because it is called by its constructor, internally.
                }
                else {
                    Profile profile = Profile.getCurrentProfile();
                    //Log.v("facebook - profile", profile.getFirstName());
                    Intent appPrincipal = new Intent(MainActivity.this,ActividadPrincipal.class);
                    //appPrincipal.putExtra("NOMBRE", nombre);
                    startActivity(appPrincipal);
                    finish();
                }
            }

            @Override
            public void onCancel() {
                resultado.setText("Se cancelo");
            }

            @Override
            public void onError(FacebookException error) {
                resultado.setText("Error");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
