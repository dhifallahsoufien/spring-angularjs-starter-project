package org.spt.helper;

import java.util.Map;

import org.stringtemplate.v4.ST;

public final class TemplateHelper {

  private TemplateHelper() {
    throw new UnsupportedOperationException("cannot instantiate helper class.");
  }

  public static String merge(String template, Map<String, String> parameters, char delimiter) {
    final ST stringTemplate = new ST(template, delimiter, delimiter);
    parameters.entrySet().stream().forEach(e -> stringTemplate.add(e.getKey(), e.getValue()));
    return stringTemplate.render();
  }
}
