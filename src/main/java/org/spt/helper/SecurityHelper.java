package org.spt.helper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;


public final class SecurityHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHelper.class);

  private static final String ENCRYPT_KEY_PATH = "keystore";
  private static final String ENCRYPT_KEY = loadKey();

  static {
    try {
      Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
      field.setAccessible(true);
      field.set(null, java.lang.Boolean.FALSE);
    } catch (Exception e) {
      LOGGER.error("error occured when trying to load SecurityHelper class.", e);
    }
  }

  private SecurityHelper() {
    // private constructor to avoid instantiation
  }

  public static String generateSalt() {
    return KeyGenerators.string().generateKey();
  }

  public static String encrypt(String password, String salt) {
    return Encryptors.text(ENCRYPT_KEY, salt).encrypt(password);
  }

  public static String decrypt(String encryptedPassword, String salt) {
    return Encryptors.text(ENCRYPT_KEY, salt).decrypt(encryptedPassword);
  }

  private static String loadKey() {
    String encoded = null;
    try {
      encoded = FileHelper.loadContentFileAsString(ENCRYPT_KEY_PATH);
    } catch (IOException e) {
      throw new ExceptionInInitializerError(e);
    }
    byte[] decoded = Base64.getDecoder().decode(encoded);
    return new String(decoded, Charset.defaultCharset());
  }
}
