package accumuwinner.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.accumuwinnerbettingtips.R;

import java.util.Date;

/**
 * Used to hold
 */
public class NewsFeed extends Fragment {


    private OnFragmentInteractionListener mListener;

    /**
     * Creates a new instance of the NewsFeed fragment for the horizontal scroll view.
     *
     * @return A new instance of fragment NewsFeed.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFeed newInstance() {
        NewsFeed fragment = new NewsFeed();

        //Can be used for passing through required params to new fragment
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }
    public NewsFeed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_news_feed, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.test);
        textView.setText(String.valueOf(new Date().toString()));

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
