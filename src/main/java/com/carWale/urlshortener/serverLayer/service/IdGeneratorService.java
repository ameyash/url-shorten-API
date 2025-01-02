package com.carWale.urlshortener.serverLayer.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class IdGeneratorService {
	
	private final SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1);
	
	private String generateUniqueId() {
		return generator.generateUniqueId();
	}
	
	public String getShortUrl() {
		return Base62Encoder.encode(Long.parseLong(generateUniqueId()));
	}
}


class Base62Encoder {

    private static final String BASE62_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Encode a given number into Base62 format.
     *
     * @param number the number to encode
     * @return the Base62 encoded string
     */
    public static String encode(long number) {
        StringBuilder encoded = new StringBuilder();
        while (number > 0) {
            int remainder = (int) (number % 62);
            encoded.insert(0, BASE62_ALPHABET.charAt(remainder));
            number /= 62;
        }
        return encoded.toString();
    }
}


class SnowflakeIdGenerator {
    private static final long EPOCH = 1630000000000L; // Custom epoch (start time)
    private static final long MACHINE_ID_BITS = 10L; // Machine ID bits (can be adjusted)
    private static final long SEQUENCE_BITS = 12L; // Sequence bits (12 bits)
    private static final long MACHINE_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    private final long machineId; // Unique machine ID
    private final AtomicLong sequence = new AtomicLong(0); // Sequence number (to handle multiple IDs in the same millisecond)
    private long lastTimestamp = -1L; // Last timestamp when an ID was generated

    /**
     * Constructor.
     *
     * @param machineId
     */
    public SnowflakeIdGenerator(long machineId) {
        if (machineId < 0 || machineId >= (1L << MACHINE_ID_BITS)) {
            throw new IllegalArgumentException("Machine ID must be between 0 and " + ((1L << MACHINE_ID_BITS) - 1));
        }
        this.machineId = machineId;
    }

    
    /**
     * Generate unique id.
     *
     * @return unique id as string
     */
    public synchronized String generateUniqueId() {
        long timestamp = System.currentTimeMillis();

        // If we're in the same millisecond, increment the sequence number
        if (timestamp == lastTimestamp) {
            long seq = sequence.incrementAndGet() & SEQUENCE_MASK;
            if (seq == 0) {
                // Sequence overflow, wait for the next millisecond
                while (timestamp == lastTimestamp) {
                    timestamp = System.currentTimeMillis();
                }
            }
        } else {
            // Reset sequence for the new millisecond
            sequence.set(0);
        }

        lastTimestamp = timestamp;

        // Calculate the unique ID using the timestamp, machine ID, and sequence number
        long id = ((timestamp - EPOCH) << TIMESTAMP_SHIFT) | (machineId << MACHINE_ID_SHIFT) | sequence.get();

        return Long.toString(id);
    }
}
