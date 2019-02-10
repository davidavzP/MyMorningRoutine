package com.example.mymorningroutine;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TaskService extends JobService {
    private static final String TAG = "CurrentTask";
    private boolean jobCancelled = false;


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job Started");
        doBackGroundWork(params);
        return true;
    }

    private void doBackGroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10;i++){
                    Log.d(TAG, "run: " + i);
                    if(jobCancelled){
                        return;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                Log.d(TAG, "Job Finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job Cancelled before completetion");
        jobCancelled = true;
        return false;
    }
}
