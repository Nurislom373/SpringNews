package org.khasanof.hlsstream.service;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegLogCallback;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.hlsstream.service
 * @since 9/27/2025 7:04 PM
 */
@Service
public class HlsService {

    private final Path streamDir = Paths.get("streams/channel1");
    private final Path playlistFile = streamDir.resolve("stream.m3u8");
    private int segmentIndex = 0;

    public HlsService() throws IOException {
        if (!Files.exists(streamDir)) {
            Files.createDirectories(streamDir);
        }
        if (!Files.exists(playlistFile)) {
            List<String> init = new ArrayList<>();
            init.add("#EXTM3U");
            init.add("#EXT-X-VERSION:3");
            init.add("#EXT-X-TARGETDURATION:10");
            init.add("#EXT-X-MEDIA-SEQUENCE:0");
            Files.write(playlistFile, init);
        }
        FFmpegLogCallback.set();
        avutil.av_log_set_level(avutil.AV_LOG_INFO);
    }

    /**
     *
     * @param videoFile
     * @throws IOException
     */
    public synchronized void addSegment(File videoFile) throws IOException {
        String segmentName = "segment" + (segmentIndex++) + ".ts";
        Path segmentPath = streamDir.resolve(segmentName);

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFile)) {
            grabber.start();

            int width = grabber.getImageWidth() > 0 ? grabber.getImageWidth() : 640;
            int height = grabber.getImageHeight() > 0 ? grabber.getImageHeight() : 360;
            int audioChannels = grabber.getAudioChannels() > 0 ? grabber.getAudioChannels() : 2;

            try (FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(
                    segmentPath.toFile(),
                    width,
                    height,
                    audioChannels
            )) {
                recorder.setFormat("mpegts");
                recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
                recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
                recorder.setFrameRate(grabber.getFrameRate() > 0 ? grabber.getFrameRate() : 25);
                recorder.setVideoBitrate(1_000_000);
                recorder.setSampleRate(grabber.getSampleRate() > 0 ? grabber.getSampleRate() : 44100);

                recorder.start();

                while (true) {
                    var frame = grabber.grabFrame();
                    if (frame == null) break;
                    recorder.record(frame);
                }

                recorder.stop();
            }

            grabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Segment yaratishda xato", e);
        }

        // Playlist yangilash
        List<String> lines = Files.readAllLines(playlistFile);
        lines.add("#EXTINF:10,");
        lines.add(segmentName);
        Files.write(playlistFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
