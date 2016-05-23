package eu.siacs.conversations.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eu.siacs.conversations.R;
import eu.siacs.conversations.entities.Content;
import eu.siacs.conversations.entities.ContentsWrapper;
import eu.siacs.conversations.entities.Message;
import eu.siacs.conversations.ui.adapter.ContentAdapter;

/**
 * Created by reza on 5/5/16.
 */
public class ContentBuilderActivity extends XmppActivity {
    final private List<Uri> mPendingImageUris = new ArrayList<>();

    EditMessage editMessage;
    ImageButton textSendButton;
    ListView contentView;
    List<Content> contentList = new ArrayList<>();
    ArrayAdapter<Content> contentArrayAdapter;
    String conversationUuid;
    public static final String ACTION_VIEW = "actionView";

    @Override
    public void onCreate(Bundle savedInstanceSttate){
        super.onCreate(savedInstanceSttate);
        setContentView(R.layout.content_builder);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.content_builder);

        textSendButton = (ImageButton) findViewById(R.id.textSendButton);
        textSendButton.setOnClickListener(new OnClickTextSendButton());
        contentView = (ListView) findViewById(R.id.messages_view);
        contentArrayAdapter = new ContentAdapter(this, contentList);
        contentView.setAdapter(contentArrayAdapter);
        editMessage = (EditMessage) findViewById(R.id.message_body);
        Intent intent = getIntent();
        conversationUuid = intent.getExtras().getString("uuid");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.content_builder, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void refreshUiReal() {

    }

    @Override
    void onBackendConnected() {

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_image:
                displayAddImage();
                break;
            case R.id.action_publish_content:
                publishContent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void publishContent() {
        Intent intent = new Intent(this,ConversationActivity.class);
        intent.putExtra(ConversationActivity.STATE_OPEN_CONVERSATION,conversationUuid);
        intent.putExtra(ConversationActivity.STATE_PANEL_OPEN,true);
        ContentsWrapper contentsWrapper = new ContentsWrapper(contentList);
        intent.putExtra(ConversationActivity.CONTENTS,contentsWrapper);
        startActivity(intent);
    }

    private void displayAddImage() {
        String fallbackPackageId = null;
        Boolean chooser = true;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            if (chooser) {
                startActivityForResult(
                        Intent.createChooser(intent, getString(R.string.perform_action_with)),
                        ConversationActivity.ATTACHMENT_CHOICE_CHOOSE_IMAGE);
        }
        }

    }

    private class OnClickTextSendButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Content content = new Content(java.util.UUID.randomUUID().toString(),editMessage.getText().toString(),Content.TYPE_TEXT,null);
            contentList.add(content);
            contentArrayAdapter.notifyDataSetChanged();
            editMessage.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPendingImageUris.clear();
        if (resultCode == RESULT_OK) {
            if (requestCode == ConversationActivity.ATTACHMENT_CHOICE_CHOOSE_IMAGE) {
                mPendingImageUris.clear();
                mPendingImageUris.addAll(ConversationActivity.extractUriFromIntent(data));
                    for (Iterator<Uri> i = mPendingImageUris.iterator(); i.hasNext(); i.remove()) {
                        attachImageToContent(i.next());
                    }
                }
        }
    }

    private void attachImageToContent(Uri uri) {
        Content content= new Content(java.util.UUID.randomUUID().toString(),"",Content.TYPE_IMAGE, xmppConnectionService.getFileBackend().getOriginalPath(uri));
        contentList.add(content);
        contentArrayAdapter.notifyDataSetChanged();
    }


}

