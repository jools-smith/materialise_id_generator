package com.flexnet.external.webservice.idgenerator;

import org.apache.commons.lang3.RandomUtils;

public class StringGenerator {

  public static StringGenerator create() {
    return new StringGenerator();
  }

  private StringGenerator() {}

  public static String numeric = "0123456789";
  public static String hex = numeric + "abcdef";
  public static String alpha = "abcdefghijklmnopqrstuvwxyz";
  public static String alpha_numeric = alpha + numeric;
  public static String alpha_non_hex = "ghijklmnopqrstuvwxyz";

  class Group {
    public String separator;
    public int size;

    public boolean assertValid() {
      return separator != null && !separator.isEmpty() && size > 0;
    }
  }

  int length = 0;
  String charset = null;
  Group group = null;

  public StringGenerator withCharset(final String value) {
    this.charset = value;
    return this;
  }

  public StringGenerator withLength(final int value) {
    if (value <= 0) {
      throw new RuntimeException("invalid length specified | " + value);
    }
    this.length = value;
    return this;
  }

  public StringGenerator withGroup(final String groupSeparator, final int groupSize) {
    this.group = new Group() {
      {
        this.separator = groupSeparator;
        this.size = groupSize;
      }
    };

    if (!this.group.assertValid()) {
      throw new RuntimeException(String.format("invalid group definition | %s | %d", this.group.separator, this.group.size));
    }

    return this;
  }

  public String build() {

    if (length <= 0) {
      throw new RuntimeException("length is not defined");
    }

    final RandomUtils rnd = RandomUtils.secureStrong();

    final StringBuilder bfr = new StringBuilder();

    for (int i = 0; i < this.length; i++) {
      bfr.append(this.charset.charAt(rnd.randomInt(0, this.charset.length())));
    }
    final String raw = bfr.toString();

    if (this.group != null) {
      final String regex = String.format("(?<=\\G.{%s})", this.group.size);

      return String.join(this.group.separator, raw.split(regex));
    }
    else {
      return raw;
    }
  }
}
