package org.spt.helper;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.Objects.isNull;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public final class FileHelper {

  private FileHelper() {
    throw new UnsupportedOperationException("cannot instantiate helper class.");
  }

  public static String loadContentFileAsString(String path) throws IOException {
    try (InputStream stream = FileHelper.class.getClassLoader().getResourceAsStream(path);) {
      if (isNull(stream)) {
        throw new IllegalArgumentException("invalid received path : " + path);
      }
      return IOUtils.toString(stream, defaultCharset());
    }
  }
}
