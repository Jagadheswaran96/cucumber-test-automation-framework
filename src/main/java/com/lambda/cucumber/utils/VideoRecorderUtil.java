package com.lambda.cucumber.utils;

import java.io.File;

import com.automation.remarks.video.RecorderFactory;
import com.automation.remarks.video.enums.RecorderType;
import com.automation.remarks.video.recorder.IVideoRecorder;

public class VideoRecorderUtil {

    private static IVideoRecorder recorder;
    private static String scenarioName;

    public static void startRecording(String name) {
        scenarioName = name.replace(" ", "_");
        recorder = RecorderFactory.getRecorder(RecorderType.MONTE);
        recorder.start();
    }

    public static void stopRecording() {
        if (recorder != null) {
            recorder.stopAndSave(scenarioName);
        }
    }
    
    public static File getLatestVideo() {
        File dir = new File("video");
        File[] files = dir.listFiles();
        return files[files.length - 1];
    }
}