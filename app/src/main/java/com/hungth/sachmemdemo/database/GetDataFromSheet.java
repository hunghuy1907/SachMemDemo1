package com.hungth.sachmemdemo.database;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.hungth.sachmemdemo.model.Data;
import com.hungth.sachmemdemo.model.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class GetDataFromSheet
        implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = "demooo: ";
    GoogleAccountCredential mCredential;

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;

    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = {SheetsScopes.SPREADSHEETS_READONLY};
    private Activity activity;
    private ArrayList<Question> valueQuestions;
    private ArrayList<String> stringDataQuestion;
    private List<Data> datas;

    public GetDataFromSheet(Activity activity) {
        this.activity = activity;
        mCredential = GoogleAccountCredential.usingOAuth2(
                activity, Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());
        valueQuestions = new ArrayList<>();
        stringDataQuestion = new ArrayList<>();
        datas = new ArrayList<>();
    }

    public List<Data> getDatas() {
        getResultsFromApi();
        return datas;
    }

    public ArrayList<Question> getData() {
        getResultsFromApi();
        return valueQuestions;
    }

    public ArrayList<String> getStringDataQuestion() {
        Log.d("ghg", "getStringDataQuestion: ");
        getResultsFromApi();
        return stringDataQuestion;
    }

    /**
     * Attempt to call the API, after verifying that all the preconditions are
     * satisfied. The preconditions are: Google Play Services installed, an
     * account was selected and the device currently has online access. If any
     * of the preconditions are not satisfied, the app will prompt the user as
     * appropriate.
     */
    private void getResultsFromApi() {
        if (!isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else if (!isDeviceOnline()) {
            Toast.makeText(activity, "No network connection available.", Toast.LENGTH_LONG).show();
        } else {
            Log.d(TAG, "getResultsFromApi: ");
            new MakeRequestTask(mCredential).execute();
        }
    }

    /**
     * Attempts to set the account used with the API credentials. If an account
     * name was previously saved it will use that one; otherwise an account
     * picker dialog will be shown to the user. Note that the setting the
     * account to use with the credentials object requires the app to have the
     * GET_ACCOUNTS permission, which is requested here if it is not already
     * present. The AfterPermissionGranted annotation indicates that this
     * function will be rerun automatically whenever the GET_ACCOUNTS permission
     * is granted.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseAccount() {
        Log.d(TAG, "chooseAccount: ");
        if (EasyPermissions.hasPermissions(activity, Manifest.permission.GET_ACCOUNTS)) {
            String accountName = activity.getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                mCredential.setSelectedAccountName(accountName);
                Log.d(TAG, "da co");
                getResultsFromApi();
            } else {
                Log.d(TAG, "da co 2");

                final ArrayList<String> gUsernameList = new ArrayList<String>();
                AccountManager accountManager = AccountManager.get(activity);
                Account[] accounts = accountManager.getAccountsByType("com.google");

                gUsernameList.clear();
                for (Account account : accounts) {
                    gUsernameList.add(account.name);
                }

                Log.d(TAG, "show dialog ");
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Choose you gmail-account");


                ListView lv = new ListView(activity);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (activity, android.R.layout.simple_list_item_1, android.R.id.text1,
                                gUsernameList);

                lv.setAdapter(adapter);
                Log.d(TAG, "khong show duoc");
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String accountName = gUsernameList.get(position);
                        Log.d(TAG, "chọn");
                        if (accountName != null) {
                            SharedPreferences settings =
                                    activity.getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString(PREF_ACCOUNT_NAME, accountName);
                            editor.apply();
                            getResultsFromApi();
                        }
                    }
                });
                builder.setView(lv);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });

                final Dialog dialog = builder.create();
                dialog.show();
            }
        } else {
            Log.d(TAG, "chua co");
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(
                    activity,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Do nothing.
    }

    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(activity);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }


    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(activity);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
        }
    }


    private class MakeRequestTask extends AsyncTask<Void, Void, List<String>> {
        private com.google.api.services.sheets.v4.Sheets mService = null;
        private Exception mLastError = null;

        MakeRequestTask(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.sheets.v4.Sheets.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("Google Sheets API Android Quickstart")
                    .build();
        }

        /**
         * Background task to call Google Sheets API.
         *
         * @param params no parameters needed for this task.
         */
        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                return getDataFromApi();
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                return null;
            }
        }

        private List<String> getDataFromApi() throws IOException {
//            Log.d(TAG, "getDataFromApi: ");
            String spreadsheetId = "1CyD40d9rfdB5Q2Gk38Iv9eNffue1A5pWCz15mZV210A";
            String range = "VD!A2:B";
            List<String> results = new ArrayList<String>();
            ValueRange response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
//            Log.d(TAG, "Loi");
            List<List<Object>> values = response.getValues();
            if (values != null) {
//                Log.d(TAG,"size" +values.size());
                for (List row : values) {
//                    Log.d(TAG, "sao sao sao ");
//                    String tempChiaLop = row.get(5).toString();
//                    String chiaLop = tempChiaLop.trim();
//                    String tempTypeQuestion = row.get(9).toString();
//                    Log.d(TAG, "tempTypeQuestion: "+tempTypeQuestion);
//                    String typeQuestion = tempTypeQuestion.trim();
//                    String tempValue = row.get(7).toString();
//                    stringDataQuestion.add(tempValue);
//                    Log.d("ghghg", "size "+ stringDataQuestion.size());

                    String data = row.get(0).toString().trim();
                    String note = row.get(1).toString().trim();
                    Data data1 = new Data(data, note);
                    datas.add(data1);
//                    int x = tempValue.indexOf('{');
//                    String valueQuestion = "";
//                    for (int i = 0; i < x; i++) {
//                        valueQuestion += tempValue.charAt(i);
//                    }
//                    x++;
////                    Log.d(TAG, "temp Value: "+ tempValue);
//                    String resultQuestion = "";
////                    Log.d(TAG, "truoc while ");
//                    while (tempValue.charAt(x) != '}') {
//                        resultQuestion += tempValue.charAt(x);
//                        x++;
//                    }
//                    Log.d(TAG, "wao trong for");
                    String temp = "data: " + data + "note:" + note;
                    Log.d("dataSheet", temp);

//                    valueQuestions.add(new Question(chiaLop, typeQuestion, valueQuestion, resultQuestion));
//                    results.add(temp);
                }
            }else {
                Log.d(TAG, "nulll");
            }
            return results;
        }


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(List<String> output) {

        }

        @Override
        protected void onCancelled() {

        }
    }
}