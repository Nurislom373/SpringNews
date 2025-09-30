package org.khasanof.mediamtx.service;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Nurislom
 * @see org.khasanof.mediamtx.service
 * @since 9/30/2025 8:14 AM
 */
@Service
public class MediaMTXRecorderService {

    /**
     *
     */
    public void record(File file, String rtsp) {
        FFmpegLogCallback.set();
        FFmpegFrameGrabber grabber = null;
        FFmpegFrameRecorder recorder = null;

        try {
            grabber = new FFmpegFrameGrabber(file.toURI().toString());
            grabber.setFormat("mp4");
            grabber.start();

            recorder = getFmpegFrameRecorder(grabber, rtsp);

            Frame frame;
            while ((frame = grabber.grab()) != null) {
                recorder.record(frame);
            }

        } catch (Exception e) {
            throw new RuntimeException("Video stream failed", e);
        } finally {
            try {
                if (recorder != null) recorder.stop();
                if (grabber != null) grabber.stop();
            } catch (Exception ignore) {}
        }
    }

    private FFmpegFrameRecorder getFmpegFrameRecorder(FFmpegFrameGrabber grabber, String rtsp) throws FFmpegFrameRecorder.Exception {
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(
                rtsp,
                grabber.getImageWidth(),
                grabber.getImageHeight(),
                grabber.getAudioChannels()
        );
        recorder.setFormat("rtsp");
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        recorder.setFrameRate(grabber.getFrameRate() > 0 ? grabber.getFrameRate() : 25);
        recorder.setVideoBitrate(1_000_000);
        recorder.setSampleRate(grabber.getSampleRate() > 0 ? grabber.getSampleRate() : 44100);
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.start();
        return recorder;
    }

}
