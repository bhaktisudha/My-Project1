package com.example.gyana.activitytofragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    TextView textView,textView1;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_blank, container, false);

        textView = (TextView) view.findViewById(R.id.fname);
        textView1 = (TextView)view.findViewById(R.id.lname);
        Bundle bundle = getArguments();
        String myStr = bundle.getString("firstName");
        textView.setText(myStr);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transection=getFragmentManager().beginTransaction();
                FragmentSecond mfragment=new FragmentSecond();
                String newstr = textView.getText().toString();
                Bundle bundle=new Bundle();
                bundle.putString("email",newstr);
                mfragment.setArguments(bundle); //data being send to SecondFragment
                transection.replace(R.id.container, mfragment);
                transection.commit();
            }
        });

        return view;

    }


}
