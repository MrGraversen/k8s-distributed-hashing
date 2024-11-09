package io.graversen.hashingapp;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@Getter
@RequiredArgsConstructor
public class CreateHashResponseDTO {
    private final @NonNull String hash;
    private final @NonNull HashType type;
    private final @NonNull Integer rounds;
    private final @NonNull Duration latency;
}
