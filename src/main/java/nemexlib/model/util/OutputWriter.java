package nemexlib.model.util;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.api.util.Logger;
import nemexlib.api.util.writer.AWriter;

public class OutputWriter extends AWriter {

    public OutputWriter(final FMLPreInitializationEvent event, final String fileName, final Logger logger) {
        super(event, fileName);
        this.setLogger(logger);
    }
}
