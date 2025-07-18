package nemexlib.api.thaumcraft.aspects;

import nemexlib.api.util.exceptions.ParameterArraysSizeException;
import nemexlib.api.util.exceptions.ParameterValueIsNegativeOrZero;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * Class extending <code>AspectList</code> from Thaumcraft 4 API
 * <p>Adding easier way to initialize an AspectList</p>
 */
public class Aspects extends AspectList {

    /**
     * Basic constructor, with only one aspect
     * @param aspect The aspect
     * @param amount The amount (1 = 1)
     * @throws ParameterValueIsNegativeOrZero If amount is zero or negative
     */
    public Aspects(Aspect aspect, int amount) {
        if (amount <= 0) throw new ParameterValueIsNegativeOrZero(amount);
        super.add(aspect, amount);
    }

    /**
     * Constructor with multiple aspects
     * <p>Each slot of the array will match the same slot from the other</p>
     * <p>Exemple : <code>aspects[i]</code> will have <code>amounts[i]</code> as quantity</p>
     * @param aspects The aspect array
     * @param amounts The amount aspect
     * @throws ParameterArraysSizeException If the two array sizes are different
     * @throws ParameterValueIsNegativeOrZero If one of the amounts is zero or negative
     */
    public Aspects(Aspect[] aspects, int ... amounts) {
        if (aspects.length != amounts.length) throw new ParameterArraysSizeException(aspects.length, amounts.length);
        for (int i = 0; i < aspects.length; i++) {
            if (amounts[i] <= 0) throw new ParameterValueIsNegativeOrZero(amounts[i]);
            super.add(aspects[i], amounts[i]);
        }
    }
}
