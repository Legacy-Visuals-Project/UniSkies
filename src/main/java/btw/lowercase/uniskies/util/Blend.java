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

    static Blend byName(String input) {
        return Arrays.stream(Blend.values()).filter(blend -> blend.name.equals(input)).findFirst().orElse(ADD);
    }
}
