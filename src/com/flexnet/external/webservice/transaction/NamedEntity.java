package com.flexnet.external.webservice.transaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class NamedEntity extends TimedEntity {

  protected final String id = UUID.randomUUID().toString();

  protected final String name;

  /**
   *
   * @param name
   */
  protected NamedEntity(final String name) {
    this.name = name;
  }
}
