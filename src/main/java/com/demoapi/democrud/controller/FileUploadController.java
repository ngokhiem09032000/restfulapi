package com.demoapi.democrud.controller;

import com.demoapi.democrud.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("image") MultipartFile file) {
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

            // Trả về URL của file đã upload
            String fileUrl = "http://localhost:8080/uploads/" + fileName;

            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);

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

}
