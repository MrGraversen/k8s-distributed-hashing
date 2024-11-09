package io.graversen.hashingapp;

import lombok.NonNull;
import lombok.Value;

@Value
public class Hash {
    @NonNull String hash;
    @NonNull HashType type;
    @NonNull Integer rounds;
}
