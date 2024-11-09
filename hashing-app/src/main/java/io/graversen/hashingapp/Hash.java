package io.graversen.hashingapp;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class Hash {
    private final @NonNull String hash;
    private final @NonNull HashType type;
    private final @NonNull Integer rounds;
}
