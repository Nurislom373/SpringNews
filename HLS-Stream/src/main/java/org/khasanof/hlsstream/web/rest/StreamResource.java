package org.khasanof.hlsstream.web.rest;

import lombok.RequiredArgsConstructor;
import org.khasanof.hlsstream.service.HlsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Nurislom
 * @see org.khasanof.hlsstream.web.rest
 * @since 9/27/2025 7:07 PM
 */
@RestController
@RequestMapping("/stream")
public class StreamResource {

    private final HlsService hlsService;

    public StreamResource(HlsService hlsService) {
        this.hlsService = hlsService;
    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        File temp = File.createTempFile("upload", ".mp4");
        file.transferTo(temp);
        hlsService.addSegment(temp);
        return ResponseEntity.ok("Segment added");
    }
}
