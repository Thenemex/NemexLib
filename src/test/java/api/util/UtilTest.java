package api.util;

import nemexlib.api.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("FieldCanBeLocal")
class UtilTest {

    private String n4, n5;
    private String[] tab, tab1to3, tab1to4, tab1to5, tab4to5;

    @BeforeEach void setUp() {
        n4 = "4";
        n5 = "5";
        tab1to3 = new String[]{"1", "2", "3"};
        tab1to4 = new String[]{"1", "2", "3", "4"};
        tab1to5 = new String[]{"1", "2", "3", "4", "5"};
        tab = new String[0];
        tab4to5 = new String[]{"4", "5"};
    }

    @Test void contains() {}

    @Test void deepCopyAndRemove() {}

    @Test void deepCopyTabAndAdd() {}

    @Test void removeIndex() {}

    @Test void addEntries() {
        assertArrayEquals(tab1to4,
                Util.addEntries(tab1to3, n4));
        assertArrayEquals(tab4to5,
                Util.addEntries(tab, n4, n5));
    }
}