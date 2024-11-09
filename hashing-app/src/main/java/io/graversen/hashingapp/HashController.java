package io.graversen.hashingapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class HashController {
    private final HashingService hashingService;

    @PostMapping("/hash")
    public ResponseEntity<CreateHashResponseDTO> hashString(@RequestBody CreateHashRequestDTO request) {
        final var startedAt = Instant.now();
        final var hash = hashingService.createHash(request.getText(), request.getType(), request.getRounds());
        final var latency = Duration.between(startedAt, Instant.now());

        final var response = new CreateHashResponseDTO(
                hash.getHash(),
                hash.getType(),
                hash.getRounds(),
                latency,
                Utils.getHostname()
        );

        return ResponseEntity.ok(response);
    }
}
