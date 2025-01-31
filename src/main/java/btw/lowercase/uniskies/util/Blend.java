package btw.lowercase.uniskies.util;

import java.util.Arrays;

public enum Blend {
    ADD("add"),
    SUBTRACT("subtract"),
    MULTIPLY("multiply"),
    DODGE("dodge"),
    BURN("burn"),
    SCREEN("screen"),
    REPLACE("replace"),
    OVERLAY("overlay"),
    ALPHA("alpha");

    private final String name;

    Blend(String name) {
        this.name = name;
    }

    public static Blend byName(String name) {
        return Arrays.stream(Blend.values()).filter(blend -> blend.name.equals(name)).findFirst().orElse(ADD);
    }
}
