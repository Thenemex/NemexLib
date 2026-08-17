package nemexlib.api.util;

public class ColorUtils {

    private static final float[][] intenseDyeColors = new float[][] {
            {1.00F, 1.00F, 1.00F}, // 0 white
            {0.98F, 0.50F, 0.11F}, // 1 orange   (~#F9801D)
            {0.78F, 0.31F, 0.74F}, // 2 magenta  (~#C74EBD)
            {0.23F, 0.70F, 0.85F}, // 3 lightBlue (~#3AB3DA)
            {0.99F, 0.85F, 0.24F}, // 4 yellow   (~#FED83D)
            {0.50F, 0.78F, 0.12F}, // 5 lime     (~#80C71F)
            {0.95F, 0.55F, 0.67F}, // 6 pink     (~#F38BAA)
            {0.28F, 0.31F, 0.32F}, // 7 gray
            {0.62F, 0.62F, 0.59F}, // 8 lightGray / silver
            {0.09F, 0.61F, 0.61F}, // 9 cyan     (~#169C9C)
            {0.54F, 0.20F, 0.72F}, // 10 purple  (~#8932B8)
            {0.24F, 0.27F, 0.67F}, // 11 blue    (~#3C44AA)
            {0.51F, 0.33F, 0.20F}, // 12 brown
            {0.37F, 0.49F, 0.09F}, // 13 green
            {0.69F, 0.18F, 0.15F}, // 14 red     (~#B02E26)
            {0.11F, 0.11F, 0.13F}  // 15 black
    };

    public static float[] getIntenseDyeColors(int dyeMeta) {
        return intenseDyeColors[dyeMeta >= 0 && dyeMeta < 16 ? dyeMeta : 0];
    }
}
