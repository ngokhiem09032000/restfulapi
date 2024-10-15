package com.demoapi.democrud.controller;

import com.demoapi.democrud.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private static final String UPLOAD_DIR = "uploads/";

    @NonFinal
    @Value("${host-address}")
    protected String hostAddress;

    @NonFinal
    @Value("${host-port}")
    protected String hostPort;

    @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("image") MultipartFile file, @RequestParam("image2") MultipartFile file2, @RequestParam("image3") MultipartFile file3) {
        // Tạo thư mục lưu trữ với đường dẫn tuyệt đối
        String absoluteUploadDir = Paths.get(UPLOAD_DIR).toAbsolutePath().toString();
        File uploadDir = new File(absoluteUploadDir);

        // Tạo thư mục nếu chưa tồn tại
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs(); // Tạo thư mục
            if (!created) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Failed to create upload directory."));
            }
        }

        try {
            // Lưu file vào thư mục
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(absoluteUploadDir, fileName);
            file.transferTo(filePath.toFile()); // Lưu file

            String fileName2 = System.currentTimeMillis() + "2_" + file2.getOriginalFilename();
            Path filePath2 = Paths.get(absoluteUploadDir, fileName2);
            file2.transferTo(filePath2.toFile()); // Lưu file

            String fileName3 = System.currentTimeMillis() + "3_" + file3.getOriginalFilename();
            Path filePath3 = Paths.get(absoluteUploadDir, fileName3);
            file3.transferTo(filePath3.toFile()); // Lưu file

            // Trả về URL của file đã upload

            String fileUrl = hostAddress + hostPort + "/" + UPLOAD_DIR + fileName;
            String fileUrl2 = hostAddress + hostPort + "/" + UPLOAD_DIR + fileName2;
            String fileUrl3 = hostAddress + hostPort + "/" + UPLOAD_DIR + fileName3;

            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);
            response.put("url2", fileUrl2);
            response.put("url3", fileUrl3);

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to upload file: " + e.getMessage()));
        }
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> serveFile(@PathVariable String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        File file = filePath.toFile();
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Xác định loại MIME của file
        String mimeType = null;
        try {
            mimeType = Files.probeContentType(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .body(new FileSystemResource(file)); // Sử dụng FileSystemResource để trả về file
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        File file = filePath.toFile();

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "File not found"));
        }

        try {
            if (file.delete()) {
                return ResponseEntity.ok(Map.of("message", "File deleted successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Failed to delete the file"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while deleting the file: " + e.getMessage()));
        }
    }

}
