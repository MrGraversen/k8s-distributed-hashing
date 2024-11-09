package io.graversen.hashingapp;

import lombok.NonNull;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HashingService {
    @Cacheable(value = "hashes", key = "#text + '-' + #type + '-' + #rounds")
    public Hash createHash(@NonNull String text, @NonNull HashType type, int rounds) {
        rounds = Math.max(rounds, 1);
        String hash = text;

        for (int i = 0; i < rounds; i++) {
            hash = applyHash(hash, type);
        }

        return new Hash(hash, type, rounds);
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
