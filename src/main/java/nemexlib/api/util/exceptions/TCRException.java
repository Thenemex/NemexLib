package nemexlib.api.util.exceptions;

import static nemexlib.NemexLib.modID;

public abstract class TCRException extends RuntimeException {

    public TCRException(String text) {
        super(modID.concat(" : ").concat(text));
    }
}
