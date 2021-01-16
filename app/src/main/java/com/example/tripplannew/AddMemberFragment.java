package com.example.tripplannew;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tripplannew.data.webservice.Share;
import com.example.tripplannew.viewmodels.ShareViewModel;
import com.example.tripplannew.viewmodels.TripListViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddMemberFragment extends Fragment {
    private Button buttonView;
    private Button btnTripSubmit;
    private LinearLayout parentLayout;
    private int hint=0;
    private ShareViewModel mShareViewModel;
    private TripListViewModel mTripListViewModel;
    List<EditText> allEds;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mShareViewModel = new ViewModelProvider(getActivity()).get(ShareViewModel.class);
        mTripListViewModel = new ViewModelProvider(getActivity()).get(TripListViewModel.class);

        return inflater.inflate(R.layout.fragment_addmember,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buttonView = (Button) getActivity().findViewById(R.id.btnAddMember);
        parentLayout = (LinearLayout)getActivity().findViewById(R.id.parentLayout);
        allEds= new ArrayList<EditText>();//luu mang editText
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                createEditTextView();
            }
        });
        btnTripSubmit =(Button)getActivity().findViewById(R.id.btnTripSubmit);
        btnTripSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShareViewModel.addShare(new Share(mShareViewModel.getTripId(), mTripListViewModel.getUserId(),mTripListViewModel.getUserName()));


                for(int i = 0; i < allEds.size(); i++)
                {
                    mShareViewModel.addShare(new Share(mShareViewModel.getTripId(),"" ,allEds.get(i).getText().toString()));
                }


                Navigation.findNavController(view).navigate(R.id.action_addMemberFragment_to_listTripFragment);
            }
        });
    }
    protected void createEditTextView() {


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0,10,0,10);
        EditText edittTxt = new EditText(getContext());
        int maxLength = 20;
        hint++;
        edittTxt.setHint("Username"+hint);
        edittTxt.setLayoutParams(params);
        // edtTxt.setBackgroundColor(Color.WHITE);
        edittTxt.setInputType(InputType.TYPE_CLASS_TEXT);
        edittTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        edittTxt.setId(hint);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        edittTxt.setFilters(fArray);
        allEds.add(edittTxt);
        parentLayout.addView(edittTxt);

    }
}
