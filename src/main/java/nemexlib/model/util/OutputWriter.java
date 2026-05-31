package nemexlib.model.util;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.api.util.Logger;
import nemexlib.api.util.writer.AWriter;

public class OutputWriter extends AWriter {

    public OutputWriter(FMLPreInitializationEvent event, String fileName, Logger logger) {
        super(event, fileName);
        this.setLogger(logger);
    }
}
