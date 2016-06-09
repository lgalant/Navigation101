package ar.edu.ort.navigation101;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Leandro on 6/9/2016.
 */
public class UserNameDialog extends DialogFragment {

    public UserNameDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_name, container);
        final EditText userName = (EditText) view.findViewById(R.id.txt_your_name);
        Button b = (Button) view.findViewById(R.id.confirmar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Click","OK");
                MainActivity mainActivity  = (MainActivity) getActivity();
                mainActivity.userName=userName.getText().toString();

            }
        });
        getDialog().setTitle("Ingrese nomre de usuario");

        return view;
    }

}