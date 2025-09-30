package org.khasanof.mediamtx.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.mediamtx.service
 * @since 9/30/2025 8:24 AM
 */
@Component
public class DefaultVideosInitializer implements InitializingBean {

    private final MediaMTXRecorderService mediaMTXRecorderService;

    public DefaultVideosInitializer(MediaMTXRecorderService mediaMTXRecorderService) {
        this.mediaMTXRecorderService = mediaMTXRecorderService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Resource resource1 = getResource("videos/26ccc9e7808532e9503dc9ffcb523bff.mp4");
        Resource resource2 = getResource("videos/e55dca519f2a030bdbb890f172b6413f.mp4");
        mediaMTXRecorderService.record(resource1.getFile(), "rtsp://localhost:8554/video1");
        mediaMTXRecorderService.record(resource2.getFile(), "rtsp://localhost:8554/video2");
    }

    private Resource getResource(String path) {
        return new FileSystemResource(path);
    }
}
