package com.example.gyana.activitytofragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    TextView textView;


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_blank, container, false);
        //MainActivity.fragmentManager.beginTransaction().replace(R.id.container,new BlankFragment()).addToBackStack(null).commit();
        textView = (TextView) view.findViewById(R.id.fname);

       ;

        Bundle bundle = getArguments();
        String myStr = bundle.getString("firstName");
        textView.setText(myStr);
        return view;

    }

}
