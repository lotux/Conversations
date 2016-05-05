package eu.siacs.conversations.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.siacs.conversations.Const;
import eu.siacs.conversations.R;

/**
 * Created by reza on 4/28/16.
 */
public class VerificationCodeActivity extends XmppActivity {
    private EditText verificationCodeText;
    private Button verifyCodeButton;
    private Button resendVerifictionCodeButton;

    @Override
    protected void refreshUiReal() {

    }

    @Override
    void onBackendConnected() {

    }

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setContentView(R.layout.verification_code);

        verificationCodeText = (EditText) findViewById(R.id.verification_code);
        verifyCodeButton = (Button) findViewById(R.id.verify_code);
        resendVerifictionCodeButton = (Button) findViewById(R.id.resend_verification_code);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificationCodeText.getText().toString().trim().length() == 0) {
                    verificationCodeText.setError(getString(R.string.verification_code_required));
                    verificationCodeText.requestFocus();
                    return;
                }

                String sentCode = xmppConnectionService.fetchFromPreferences(Const.VERIFICATION_CODE);
                if (sentCode != null && sentCode.equalsIgnoreCase(verificationCodeText.getText().toString())) {
                    xmppConnectionService.saveInPreferences(Const.VERIFICATION_CODE_DONE, "1");
                    Intent conversationActivityIntent = new Intent(VerificationCodeActivity.this, ConversationActivity.class);
                    startActivity(conversationActivityIntent);
                    finish();
                } else {
                    Toast.makeText(VerificationCodeActivity.this, getString(R.string.verification_code_is_not_valid), Toast.LENGTH_LONG).show();
                }
            }
        };
        verifyCodeButton.setOnClickListener(onClickListener);
    }
}
