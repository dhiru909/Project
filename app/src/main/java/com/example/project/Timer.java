package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Timer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Timer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CountDownTimer cdt;
    private int seconds=0;
    private int minutes=0;
    private int hours=0;
    private int overAllSeconds = 0;
    AppCompatEditText tvHr,tvMin,tvSec;
    Button startButton, pauseButton;
    public Timer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Timer.
     */
    // TODO: Rename and change types and number of parameters
    public static Timer newInstance(String param1, String param2) {
        Timer fragment = new Timer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
//    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//        @Override
//        public void handleOnBackPressed() {
//            if(getArguments() != null && getArguments().getBoolean("notification") /* coming from notification */) {
//                startActivity(new Intent(getActivity(),DashBoardMain.class));
//                requireActivity().finish();
//            } else {
//                Navigation.findNavController(requireActivity(), R.id.trackernav).navigate(R.id.action_waterTrackerFragment_to_dashBoardFragment);
//            }
//        }
//    };
//    requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
         tvHr = (AppCompatEditText) view.findViewById(R.id.hr);
         tvMin = (AppCompatEditText) view.findViewById(R.id.min);
         tvSec = (AppCompatEditText)view.findViewById(R.id.sec);
         startButton =  view.findViewById(R.id.startButton);
         pauseButton = view.findViewById(R.id.pauseButton);
         startButton.setOnClickListener(
                 v -> {
                     runTimer(null);
                 }
         );
         pauseButton.setOnClickListener(
                 v -> {
                     if(pauseButton.getText().toString().equals("Pause")){
                         pauseButton.setText("Resume");
                         cdt.cancel();
                     }else{
                         pauseButton.setText("Pause");
                            runTimer("resume");
                     }

                 }
         );
//        view.findViewById(R.id.)
//        MaterialTimePicker timePickerDialog = new  MaterialTimePicker.Builder().setNegativeButtonText("Cancel")
//                .setPositiveButtonText("Confirm").build();
//        timePickerDialog.addOnPositiveButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hours = timePickerDialog.getHour();
//                minutes = timePickerDialog.getMinute();
//
//            }
//        });
//        timePickerDialog.show(getParentFragmentManager(),"t");

        return view;
    }

    private void runTimer(String defaul) {
        if(startButton.getText().toString().equals("Start") || defaul!=null){
            pauseButton.setVisibility(View.VISIBLE);
            startButton.setText("Stop");
            tvHr.setFocusable(false);
            tvSec.setFocusable(false);
            tvMin.setFocusable(false);
            try {
                if(Objects.requireNonNull(tvHr.getText()).toString().equals("")){
                    hours = 0;
                }else{
                    hours = Integer.parseInt(Objects.requireNonNull(tvHr.getText()).toString());
                }
                if(Objects.requireNonNull(tvMin.getText()).toString().equals("")){
                    minutes = 0;
                }else{
                    minutes = Integer.parseInt(Objects.requireNonNull(tvMin.getText()).toString());
                }
                if(Objects.requireNonNull(tvSec.getText()).toString().equals("")){
                    seconds = 0;
                }else{
                    seconds = Integer.parseInt(Objects.requireNonNull(tvSec.getText()).toString());
                }

                overAllSeconds = hours* 3600 + minutes*60 +seconds;
                Log.e("overall",String.valueOf(overAllSeconds));
                cdt = new CountDownTimer(overAllSeconds* 1000L,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        overAllSeconds = (int) (millisUntilFinished/1000);
                        hours = overAllSeconds/3600;
                        minutes = (overAllSeconds%3600)/60;
                        seconds = overAllSeconds-hours*3600 - minutes*60;
                        tvHr.setText(String.valueOf(hours));
                        tvSec.setText(String.valueOf(seconds));
                        tvMin.setText(String.valueOf(minutes));
                    }

                    @Override
                    public void onFinish() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                        builder.setCancelable(true);
                        builder.setTitle("Times UPP");
                        builder.setMessage("Completed");
                        builder.show();
                        startButton.setText("Start");
                        pauseButton.setText("Pause");
                        pauseButton.setVisibility(View.INVISIBLE);
                        tvHr.setFocusable(true);
                        tvSec.setFocusable(true);
                        tvMin.setFocusable(true);
                        tvHr.setFocusableInTouchMode(true);
                        tvMin.setFocusableInTouchMode(true);
                        tvSec.setFocusableInTouchMode(true);
                    }
                };
                cdt
                        .start();
            }catch (Exception ignored){
                Toast.makeText(requireContext(),"Please enter without preceding zeroes",Toast.LENGTH_LONG).show();

            }
        }else{
            tvHr.setFocusable(true);
            tvSec.setFocusable(true);
            tvMin.setFocusable(true);
            tvHr.setFocusableInTouchMode(true);
            tvMin.setFocusableInTouchMode(true);
            tvSec.setFocusableInTouchMode(true);
            startButton.setText("Start");
            tvHr.setText(null);
            tvMin.setText(null);
            tvSec.setText(null);
            if(cdt!=null)
            cdt.cancel();
            pauseButton.setVisibility(View.INVISIBLE);
        }


    }
}