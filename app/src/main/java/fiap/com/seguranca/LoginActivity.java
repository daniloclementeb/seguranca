package fiap.com.seguranca;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.ContactsContract.Profile;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {
    private static final String[] DUMMY_CREDENTIALS = new String[]{"foo@example.com:hello", "bar@example.com:world"};
    private static final int REQUEST_READ_CONTACTS = 0;
    private UserLoginTask mAuthTask = null;
    private AutoCompleteTextView mEmailView;
    private View mLoginFormView;
    private EditText mPasswordView;
    private View mProgressView;

    class C09231 implements OnEditorActionListener {
        C09231() {
        }

        public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
            if (id != R.id.login && id != 0) {
                return false;
            }
            LoginActivity.this.attemptLogin();
            return true;
        }
    }

    class C09242 implements OnClickListener {
        C09242() {
        }

        public void onClick(View view) {
            LoginActivity.this.attemptLogin();
        }
    }

    class C09253 implements OnClickListener {
        C09253() {
        }

        @TargetApi(23)
        public void onClick(View v) {
            LoginActivity.this.requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, 0);
        }
    }

    private interface ProfileQuery {
        public static final int ADDRESS = 0;
        public static final int IS_PRIMARY = 1;
        public static final String[] PROJECTION = new String[]{"data1", "is_primary"};
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            this.mEmail = email;
            this.mPassword = password;
        }

        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
                for (String credential : LoginActivity.DUMMY_CREDENTIALS) {
                    String[] pieces = credential.split(":");
                    if (pieces[0].equals(this.mEmail)) {
                        return Boolean.valueOf(pieces[1].equals(this.mPassword));
                    }
                }
                return Boolean.valueOf(true);
            } catch (InterruptedException e) {
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean success) {
            LoginActivity.this.mAuthTask = null;
            LoginActivity.this.showProgress(false);
            if (success.booleanValue()) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this.getBaseContext(), MainActivity.class));
                return;
            }
            LoginActivity.this.mPasswordView.setError(LoginActivity.this.getString(R.string.error_incorrect_password));
            LoginActivity.this.mPasswordView.requestFocus();
        }

        protected void onCancelled() {
            LoginActivity.this.mAuthTask = null;
            LoginActivity.this.showProgress(false);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_login);
        this.mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();
        this.mPasswordView = (EditText) findViewById(R.id.password);
        this.mPasswordView.setOnEditorActionListener(new C09231());
        ((Button) findViewById(R.id.email_sign_in_button)).setOnClickListener(new C09242());
        this.mLoginFormView = findViewById(R.id.login_form);
        this.mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {

    }

    private boolean mayRequestContacts() {
            return true;

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0 && grantResults.length == 1 && grantResults[0] == 0) {
            populateAutoComplete();
        }
    }

    private void attemptLogin() {
        if (this.mAuthTask == null) {
            this.mEmailView.setError(null);
            this.mPasswordView.setError(null);
            String email = this.mEmailView.getText().toString();
            String password = this.mPasswordView.getText().toString();
            boolean cancel = false;
            View focusView = null;
            if (!(TextUtils.isEmpty(password) || isPasswordValid(password))) {
                this.mPasswordView.setError(getString(R.string.error_invalid_password));
                focusView = this.mPasswordView;
                cancel = true;
            }
            if (TextUtils.isEmpty(email)) {
                this.mEmailView.setError(getString(R.string.error_field_required));
                focusView = this.mEmailView;
                cancel = true;
            } else if (!isEmailValid(email)) {
                this.mEmailView.setError(getString(fiap.com.seguranca.R.string.error_invalid_email));

                       // R.string.error_invalid_email));
                focusView = this.mEmailView;
                cancel = true;
            }
            if (cancel) {
                focusView.requestFocus();
                return;
            }
            showProgress(true);
            this.mAuthTask = new UserLoginTask(email, password);
            this.mAuthTask.execute(new Void[]{(Void) null});
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, Uri.withAppendedPath(Profile.CONTENT_URI, "data"), ProfileQuery.PROJECTION, "mimetype = ?", new String[]{"vnd.android.cursor.item/email_v2"}, "is_primary DESC");
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        List<String> emails = new ArrayList();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(0));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        this.mEmailView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, emailAddressCollection));
    }
}
