package io.graversen.hashingapp;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CreateHashRequestDTO {
    private final String text;
    private final HashType type;
    private final int rounds;
}
