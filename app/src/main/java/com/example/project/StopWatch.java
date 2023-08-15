package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StopWatch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StopWatch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;
    View view ;
    private boolean wasRunning;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StopWatch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StopWatch.
     */
    // TODO: Rename and change types and number of parameters
    public static StopWatch newInstance(String param1, String param2) {
        StopWatch fragment = new StopWatch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (savedInstanceState != null) {

            // Get the previous state of the stopwatch
            // if the activity has been
            // destroyed and recreated.
            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_stop_watch, container, false);
        view.findViewById(R.id.start_button).setOnClickListener(v -> {

            running = true;

        });
        view.findViewById(R.id.reset_button).setOnClickListener(v -> {
            running = false;
            seconds = 0;
        });
        view.findViewById(R.id.stop_button).setOnClickListener(v -> {
            running = false;
        });

        return view;
    }
    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState)
    {
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    // If the activity is paused,
    // stop the stopwatch.
    @Override
    public void onPause()
    {
        super.onPause();
        wasRunning = running;
    }

    // If the activity is resumed,
    // start the stopwatch
    // again if it was running previously.
    @Override
    public void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    // Start the stopwatch running
    // when the Start button is clicked.
    // Below method gets called
    // when the Start button is clicked.
//    public void onClickStart(View view)
//    {
//        running = true;
//    }

    // Stop the stopwatch running
    // when the Stop button is clicked.
    // Below method gets called
    // when the Stop button is clicked.
//    public void onClickStop(View view)
//    {
//        running = false;
//    }

    // Reset the stopwatch when
    // the Reset button is clicked.
    // Below method gets called
    // when the Reset button is clicked.
//    public void onClickReset(View view)
//    {
//        running = false;
//        seconds = 0;
//    }

    // Sets the NUmber of seconds on the timer.
    // The runTimer() method uses a Handler
    // to increment the seconds and
    // update the text view.
    private void runTimer()
    {
        TextView timeView;
        // Get the text view.
        try {
              timeView = view.findViewById(
                    R.id.time_view);

        }catch ( NullPointerException ignored){

        }
        // Creates a new Handler
        final Handler handler
                = new Handler();

        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);
                Log.e("timme",time);
                // Set the text view text.
                try {
                    TextView timeView = view.findViewById(R.id.time_view);
                    timeView.setText(time);
                }catch (Exception ignored){

                }


                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds++;
                }else{
                    handler.removeCallbacks(this);
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }
}