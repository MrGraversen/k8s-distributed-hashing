package io.graversen.hashingapp;

import lombok.NonNull;
import lombok.Value;

import java.time.Duration;

@Value
public class Hash {
    @NonNull String hash;
    @NonNull HashType type;
    @NonNull Integer rounds;
    @NonNull Duration latency;
}
