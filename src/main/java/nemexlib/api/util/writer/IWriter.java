package nemexlib.api.util.writer;

import nemexlib.api.util.Logger;

import java.io.File;

@SuppressWarnings("UnusedReturnValue")
public interface IWriter {

    boolean write(final String text);
    boolean close();

    File getFile();
    boolean setFile(final File file);
    boolean setFile(final File folder, final String fileName);

    Logger getLogger();
    boolean setLogger(final Logger logger);

    boolean logError(final String message, final Exception e);
}
