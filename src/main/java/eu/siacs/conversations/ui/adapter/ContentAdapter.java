package eu.siacs.conversations.ui.adapter;

import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import eu.siacs.conversations.R;
import eu.siacs.conversations.entities.Account;
import eu.siacs.conversations.entities.Conversation;
import eu.siacs.conversations.entities.Message;
import eu.siacs.conversations.ui.XmppActivity;

/**
 * Created by reza on 5/6/16.
 */
public class ContentAdapter extends ArrayAdapter<Message> {
    List<Message> messages;
    XmppActivity activity;
    DisplayMetrics metrics;
    public ContentAdapter(XmppActivity activity,List<Message> messages){
        super(activity,0,messages);
        this.messages = messages;
        this.activity = activity;
        metrics =  getContext().getResources().getDisplayMetrics();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Message message = getItem(position);
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = activity.getLayoutInflater().inflate(R.layout.content_row,parent,false);
            viewHolder.text= (TextView) view.findViewById(R.id.text);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if(message.getType() == Message.TYPE_IMAGE){

            String absolutePath = activity.xmppConnectionService.getFileBackend().getOriginalPath(Uri.parse(message.getRelativeFilePath()));
            InputStream in;
            try {
                in = new FileInputStream(absolutePath);
                Bitmap bitmap = BitmapFactory.decodeStream(in,null,null);

                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                double target = metrics.density * 288;
                int scalledW;
                int scalledH;
                if (width <= height) {
                    scalledW = (int) (width / ((double) height / target));
                    scalledH = (int) target;
                } else {
                    scalledW = (int) target;
                    scalledH = (int) (height / ((double) width / target));
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(scalledW, scalledH);
                layoutParams.setMargins(0, (int) (metrics.density * 4), 0, (int) (metrics.density * 4));
                viewHolder.image.setLayoutParams(layoutParams);
                viewHolder.image.setImageURI(Uri.parse(absolutePath));
                viewHolder.text.setVisibility(View.GONE);
                viewHolder.image.setVisibility(View.VISIBLE);
            }catch (Exception e){
              e.printStackTrace();
            }
        }else if(message.getType() == Message.TYPE_TEXT){
            viewHolder.text.setText(message.getBody());
            viewHolder.image.setVisibility(View.GONE);
            viewHolder.text.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }


    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public class ViewHolder{
        TextView text;
        ImageView image;
    }
}
