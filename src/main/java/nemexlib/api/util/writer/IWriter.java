package nemexlib.api.util.writer;

import nemexlib.api.util.Logger;

import java.io.File;

@SuppressWarnings("UnusedReturnValue")
public interface IWriter {

    boolean write(String text);
    boolean close();

    Logger getLogger();
    boolean setLogger(Logger logger);

    File getFile();
    boolean setFile(File file);
    boolean setFile(File folder, String fileName);

    boolean logError(Exception e);
}
