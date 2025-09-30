package org.khasanof.mediamtx;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author Nurislom
 * @see org.khasanof.mediamtx
 * @since 9/29/2025 9:18 PM
 */
@Controller
public class WebRtcResource {

    private final SimpMessagingTemplate messagingTemplate;

    public WebRtcResource(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/offer")
    public void handleOffer(String sdpOffer, @Header("simpSessionId") String sessionId) throws IOException {
        String[] videos = {
                "rtsp://localhost:8554/videos/video1.mp4",
                "rtsp://localhost:8554/videos/video2.mp4"
        };

        String webrtcApiUrl = "http://localhost:8888/api/webrtc";

        for (String videoUrl : videos) {
            String sdpAnswer = sendOfferToMediaMtx(videoUrl, sdpOffer, webrtcApiUrl);
            messagingTemplate.convertAndSend("/topic/answer", sdpAnswer);
            try { Thread.sleep(7000); } catch (InterruptedException ignored) {}
        }
    }

    private String sendOfferToMediaMtx(String videoUrl, String sdpOffer, String apiUrl) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl + "?url=" + videoUrl).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(sdpOffer.getBytes(StandardCharsets.UTF_8));
        }

        try (InputStream is = conn.getInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
