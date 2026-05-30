package nemexlib.api.util.writer;

import nemexlib.api.util.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("ResultOfMethodCallIgnored")
public abstract class AWriter implements IWriter {

    protected Logger logger;
    protected File file;

    private FileWriter writer;

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
    // ToDo Event

    @Override public boolean write(String text) {
        if (this.file == null || writer == null) return false;
        try {
            writer.write(text);
        } catch (IOException e) {
            logError(e);
            return false;
        }
        // ToDo Finish
        return true;
    }

    @Override public boolean close() {
        try {
            writer.close();
            return true;
        } catch (IOException e) {
            logError(e);
            return false;
        }
    }

    @Override public Logger getLogger() {
        return logger;
    }
    @Override public boolean setLogger(Logger logger) {
        if (logger == null) return false;
        this.logger = logger;
        return true;
    }

    @Override public File getFile() {
        return file;
    }

    @Override public boolean setFile(File file) {
        if (file == null || !file.isFile()) return false;
        this.file = file;
        try {
            file.createNewFile();
            this.writer = new FileWriter(file);
        } catch (Exception e) {
            logError(e);
            return false;
        }
        return true;
    }
    @Override public boolean setFile(File folder, String fileName) {
        if (folder == null || !folder.isDirectory() || fileName == null || fileName.isEmpty()) return false;
        return this.setFile(new File(folder, fileName));
    }

    @Override public boolean logError(Exception e) {
        if (logger == null) return false;
        logger.info("Exception in Writer : ".concat(e.getClass().getSimpleName()).concat(", ").concat(e.getMessage()));
        return true;
    }
}
