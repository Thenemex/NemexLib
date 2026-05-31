package nemexlib.api.util.writer;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.api.util.Logger;
import nemexlib.config.AConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("ResultOfMethodCallIgnored")
public abstract class AWriter implements IWriter {

    protected Logger logger;
    protected File file;

    protected FileWriter writer;
    private boolean started = false;

    public AWriter() {}
    public AWriter(Logger logger) {
        this.setLogger(logger);
    }
    public AWriter(File file) {
        this.setFile(file);
    }
    public AWriter(File file, Logger logger) {
        this.setFile(file);
        this.setLogger(logger);
    }
    public AWriter(FMLPreInitializationEvent event, String fileName) {
        File folders = new File(new File(event.getModConfigurationDirectory(), AConfig.tnmxDir), "output");
        folders.mkdirs();
        setFile(new File(folders, fileName.concat(".output")));
    }

    @Override public boolean write(String text) {
        if (file == null || writer == null) return false;
        try {
            if (started)
                for (int i = 0; i < 3; i++)
                    writer.write(System.lineSeparator());
            writer.write(text);
            started = true;
        } catch (IOException e) {
            logError("Cannot write to file", e);
            return false;
        }
        return true;
    }
    @Override public boolean close() {
        try {
            writer.close();
            return true;
        } catch (IOException e) {
            logError("Cannot close writer", e);
            return false;
        }
    }

    @Override public File getFile() {
        return file;
    }
    @Override public boolean setFile(File file) {
        // Initial check
        try {
            file.createNewFile();
            if (!file.isFile()) return false;
        } catch (Exception e) {
            logError("Cannot create output file", e);
            return false;
        }
        // Set Data
        this.file = file;
        try {
            file.createNewFile();
            this.writer = new FileWriter(file);
        } catch (Exception e) {
            logError("Exception in Writer instantiation", e);
            return false;
        }
        return true;
    }
    @Override public boolean setFile(File folder, String fileName) {
        if (folder == null || !folder.isDirectory() || fileName == null || fileName.isEmpty()) return false;
        return this.setFile(new File(folder, fileName));
    }

    @Override public Logger getLogger() {
        return logger;
    }
    @Override public boolean setLogger(Logger logger) {
        if (logger == null) return false;
        this.logger = logger;
        return true;
    }

    @Override public boolean logError(String message, Exception e) {
        if (logger == null) return false;
        logger.info(message.concat(" : ").concat(e.getClass().getSimpleName()).concat(", ").concat(e.getMessage()));
        return true;
    }
}
