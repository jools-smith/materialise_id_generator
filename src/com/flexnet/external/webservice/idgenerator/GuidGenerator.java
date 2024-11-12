package com.flexnet.external.webservice.idgenerator;

import java.util.UUID;

public class GuidGenerator {

  String guidRaw() {
    return UUID.randomUUID().toString();
  }

  String guid() {
    return guidRaw().replace("-","");
  }

  String guidWithUnderscore() {
    return guidRaw().replace("-","_");
  }

  String inFours(final String guid) {
    return String.join("-",guid.split("(?<=\\G.{4})"));
  }

  String guidInFours() {
    return inFours(guid());
  }
}
