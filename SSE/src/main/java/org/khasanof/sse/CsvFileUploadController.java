package org.khasanof.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Nurislom
 * @see org.khasanof.sse
 * @since 10/17/2023 2:28 PM
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/upload")
public class CsvFileUploadController {

    private final Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @GetMapping("/stream-sse-mvc")
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; i <= 20; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data("SSE MVC - " + LocalTime.now())
                            .id(String.valueOf(i))
                            .name("sse event - mvc");
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    @PostMapping("/csv")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a CSV file to upload.");
        }

        String transactionId = "TXN-" + System.currentTimeMillis();

        //1. Start asynchronous processing and return a CompletableFuture
        CompletableFuture.runAsync(() -> asyncProcessCsv(file, transactionId));

        //2. Return a 202 (Accepted) response with the transaction ID
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionId);
    }

    @GetMapping("/sse/{transactionId}")
    public SseEmitter getSseEmitter(@PathVariable String transactionId) {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitters.put(transactionId, sseEmitter);
        return sseEmitter;
    }

    private void asyncProcessCsv(MultipartFile file, String transactionId) {
        CompletableFuture<Void> processingFuture = CompletableFuture.runAsync(() -> {
            // Your CSV processing logic here
            try (BufferedReader csvReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // Process CSV rows here
                // ...

                long count = csvReader.lines().count();
                System.out.println("count = " + count);
                // Send progress updates via SSE
                for (int i = 1; i <= count; i++) {
                    String progressMessage = "Processing row " + i;
                    sendProgressUpdate(transactionId, progressMessage);
                    log.info("Send Process file event : {}", i);
                }

                // Send completion message via SSE
                sendCompletionMessage(transactionId, "CSV processing completed.");
            } catch (Exception e) {
                // Handle exceptions during processing
                sendErrorMessage(transactionId, "Error during processing: " + e.getMessage());
            } finally {
                sseEmitters.remove(transactionId);
            }
        });

        // Handle any exceptions that occur during processing
        processingFuture.exceptionally(ex -> {
            sendErrorMessage(transactionId, "Error during processing: " + ex.getMessage());
            return null;
        });
    }

    private void sendProgressUpdate(String transactionId, String message) {
        SseEmitter sseEmitter = sseEmitters.get(transactionId);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(SseEmitter.event().name("progress").data(message));
            } catch (IOException e) {
                // Handle exceptions when sending SSE updates
                e.printStackTrace();
            }
        }
    }


    private void sendCompletionMessage(String transactionId, String message) {
        SseEmitter sseEmitter = sseEmitters.get(transactionId);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(SseEmitter.event().name("complete").data(message));
                sseEmitter.complete(); // Close the SSE connection
            } catch (IOException e) {
                // Handle exceptions when sending SSE updates
                e.printStackTrace();
            }
        }
    }


    private void sendErrorMessage(String transactionId, String message) {
        SseEmitter sseEmitter = sseEmitters.get(transactionId);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(SseEmitter.event().name("error").data(message));
                sseEmitter.completeWithError(new RuntimeException(message)); // Complete with an error
            } catch (IOException e) {
                // Handle exceptions when sending SSE updates
                e.printStackTrace();
            }
        }
    }

}
