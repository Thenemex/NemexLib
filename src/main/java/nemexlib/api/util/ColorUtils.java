package nemexlib.api.util;

import java.util.Arrays;

public class ColorUtils {

    private static final float[][] intenseDyeColors = new float[][] {
            {1.0F, 1.0F, 1.0F},          // White       #ffffff
            {0.9412F, 0.3922F, 0.1961F}, // Orange      #f06532
            {0.698F, 0.298F, 0.851F},    // Magenta     #b24cd9
            {0.4F, 0.6F, 0.851F},        // Light Blue  #6699d9
            {0.902F, 0.902F, 0.2F},      // Yellow      #e6e633
            {0.502F, 0.8F, 0.102F},      // Lime        #80cc1a
            {0.949F, 0.502F, 0.651F},    // Pink        #f280a6
            {0.298F, 0.298F, 0.298F},    // Gray        #4c4c4c
            {0.6667F, 0.6667F, 0.6667F}, // Light Gray  #aaaaaa
            {0.0F, 0.502F, 0.6F},        // Cyan        #008099
            {0.3569F, 0.1804F, 0.5216F}, // Purple      #5b2e85
            {0.1176F, 0.1176F, 0.6863F}, // Blue        #1e1eaf
            {0.4F, 0.298F, 0.0F},        // Brown       #664c00
            {0.1176F, 0.3922F, 0.1176F}, // Green       #1e641e
            {0.7843F, 0.1176F, 0.1176F}, // Red         #c81e1e
            {0.0392F, 0.0392F, 0.0392F}  // Black       #0a0a0a
    };

    public static float[] getIntenseDyeColors(int dyeMeta) {
        return Arrays.copyOf(intenseDyeColors[dyeMeta >= 0 && dyeMeta < 16 ? dyeMeta : 0], 3);
    }
}
