package com.lambda.cucumber.utils;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorderUtil {

	private static boolean recording = false;
	private static ScreenRecorder screenRecorder;

	public static void startRecording(String scenarioName) throws Exception {

		if (!recording) {
			File file = new File("target/videos");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle captureSize = new Rectangle(screenSize);

			GraphicsConfiguration gc =
					GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getDefaultScreenDevice()
					.getDefaultConfiguration();

			screenRecorder = new ScreenRecorder(gc, captureSize,
					new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
					new Format(MediaTypeKey, MediaType.VIDEO,
							EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							DepthKey, 24,
							FrameRateKey, Rational.valueOf(15),
							QualityKey, 1.0f,
							KeyFrameIntervalKey, 15 * 60),
					new Format(MediaTypeKey, MediaType.VIDEO, FrameRateKey, Rational.valueOf(30)),
					null, file);

			screenRecorder.start();
			recording = true;
		}
	}
	
	public static File getLatestVideo() {
	    File dir = new File("target/videos");
	    File[] files = dir.listFiles();
	    return files[files.length - 1];
	}

	public static void stopRecording() throws Exception {
		if (recording) {
	        screenRecorder.stop();
	        recording = false;
	    }
	}
}