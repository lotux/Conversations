package eu.siacs.conversations.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.siacs.conversations.R;

/**
 * Created by reza on 5/7/16.
 */
public class TestFragment extends Fragment {
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.test_fragment, container, false);
        return view;
    }
}