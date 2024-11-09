package io.graversen.hashingapp;

import lombok.NonNull;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class HashingService {
    public Hash createHash(@NonNull String text, @NonNull HashType type, int rounds) {
        rounds = Math.max(rounds, 1);
        String hash = text;

        final var startedAt = Instant.now();
        for (int i = 0; i < rounds; i++) {
            hash = applyHash(hash, type);
        }
        final var latency = Duration.between(startedAt, Instant.now());
        return new Hash(hash, type, rounds, latency);
    }

    private String applyHash(@NonNull String text, HashType hash) {
        return switch (hash) {
            case MD5 -> DigestUtils.md5Hex(text);
            case SHA1 -> DigestUtils.sha1Hex(text);
            case SHA256 -> DigestUtils.sha256Hex(text);
            case SHA512 -> DigestUtils.sha512Hex(text);
            default -> throw new IllegalArgumentException("Unsupported hash type: " + hash);
        };
    }
}
