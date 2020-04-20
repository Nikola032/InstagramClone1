package com.example.instagramclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {
    private EditText edtProfileName,edtProfileBio,edtProfileProfession,edtProfileHobby,edtProfileFavSport;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileHobby = view.findViewById(R.id.edtProfileHobby);
        edtProfileProfession = view.findViewById(R.id.edtProfileProffesion);
        edtProfileFavSport = view.findViewById(R.id.edtProfileFavSport);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

         final ParseUser parseUser = ParseUser.getCurrentUser();

         if (parseUser.get("profileName") == null ) {
             edtProfileName.setText("");
         }else {
             edtProfileName.setText(parseUser.get("profileName").toString());
         }

         if (parseUser.get("profileBio") == null) {
             edtProfileBio.setText("");
         }else {
             edtProfileBio.setText(parseUser.get("profileBio").toString());

         }
         if (parseUser.get("profileProfession") == null) {
             edtProfileProfession.setText("");
         }else{
             edtProfileProfession.setText(parseUser.get("profileProfession").toString());

         }
         if (parseUser.get("profileHobby") ==  null){
             edtProfileHobby.setText("");
         }else {
             edtProfileHobby.setText(parseUser.get("profileHobby").toString());

         }
         if(parseUser.get("profileFavSport") == null){
             edtProfileFavSport.setText("");
         }else{
             edtProfileFavSport.setText(parseUser.get("profileFavSport").toString());

         }



         btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName",edtProfileName.getText() + "");
                parseUser.put("profileBio",edtProfileBio.getText() + "");
                parseUser.put("profileProfession",edtProfileProfession.getText() + "");
                parseUser.put("profileFavSport",edtProfileFavSport.getText() + "");
                parseUser.put("profileHobby",edtProfileHobby.getText() + "");

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(getContext(), "info is updated", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                        }
                        else {
                            FancyToast.makeText
                                    (getContext(),e.getMessage(),
                                            FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });

        return view;





    }

}
