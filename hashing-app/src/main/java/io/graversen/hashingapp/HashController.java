package io.graversen.hashingapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HashController {
    private final HashingService hashingService;

    @PostMapping("/hash")
    public ResponseEntity<CreateHashResponseDTO> hashString(@RequestBody CreateHashRequestDTO request) {
        final var hash = hashingService.createHash(request.getText(), request.getType(), request.getRounds());

        final var response = new CreateHashResponseDTO(
                hash.getHash(),
                hash.getType(),
                hash.getRounds(),
                hash.getLatency()
        );

        return ResponseEntity.ok(response);
    }
}
