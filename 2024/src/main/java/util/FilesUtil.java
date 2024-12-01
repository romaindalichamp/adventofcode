package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FilesUtil {
  private static final Logger LOGGER = Logger.getLogger(FilesUtil.class.getName());


  public static Stream<String> loadInputFile(String fileName) {
    Stream<String> lines = Stream.empty();
    try {
      lines = Files.lines(Paths.get(fileName));
      LOGGER.info("Input file loaded");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }
}
