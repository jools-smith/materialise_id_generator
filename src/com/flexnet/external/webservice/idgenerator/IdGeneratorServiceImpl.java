package com.flexnet.external.webservice.idgenerator;

import com.flexnet.external.type.*;
import com.flexnet.external.utils.Diagnostics.Token;
import com.flexnet.external.utils.Log;
import com.flexnet.external.utils.Utils;
import com.flexnet.external.webservice.ServiceBase;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.RandomUtils;

import javax.jws.WebService;
import java.time.Instant;

class StringGenerator {

  public static String numeric = "0123456789";
  public static String hex = numeric + "abcdef";
  public static String HEX = hex.toUpperCase();
  public static String alpha = "abcdefghjklmnpqrstuvwxyz";
  public static String alpha_oi = alpha + "oi";
  public static String alpha_numeric = alpha_oi + numeric;
  public static String ALPHA_NUMERIC = alpha_numeric.toUpperCase();
  public static String ALPHA = alpha.toUpperCase();
  public static String ALPHA_OI = alpha_oi.toUpperCase();
  public static String base58 = numeric + alpha + ALPHA;
  public static String base62 = numeric + alpha_oi + ALPHA_OI;

  class Group {
    public String separator;
    public int size;
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
        if (groupSeparator == null || groupSeparator.isEmpty() || groupSize <= 0) {
          throw new RuntimeException(String.format("invalid group definition | %s | %d", groupSeparator, groupSize));
        }
        this.separator = groupSeparator;
        this.size = groupSize;
      }
    };
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


@WebService(
        endpointInterface = "com.flexnet.external.webservice.idgenerator.IdGeneratorServiceInterface",
        wsdlLocation = "WEB-INF/wsdl/schema/IdGeneratorService.wsdl"
)
public class IdGeneratorServiceImpl extends ServiceBase implements IdGeneratorServiceInterface {

  private static final StringGenerator generator = new StringGenerator();

  private static Id generate(final String prefix) {
    return new Id() {
      {
        this.id = prefix + generator.withCharset(StringGenerator.hex)
                .withLength(32)
                .withGroup("-",4)
                .build();
      }
    };
  }

  @Override
  public PingResponse ping(final PingRequest payload) throws IdGeneratorException {
    super.logger.in();
    super.logger.yaml(Log.Level.trace, payload);
    final Token token = createDiagnosticsToken();

    try {
      return new PingResponse() {
        {
          this.str = getBuildVersion();
          this.processedTime = Instant.now().toString();
          this.info = Utils.safeSerializeYaml(getDiagnostics().serialize());
        }
      };
    }
    catch (final Throwable t) {
      throw new IdGeneratorException(t.getMessage(), this.serviceException.apply(t));
    }
    finally {
      token.commit();
    }
  }

  @Override
  public Id generateEntitlementID(final Entitlement payload) throws IdGeneratorException {
    super.logger.in();
    super.logger.yaml(Log.Level.trace, payload);
    final Token token = createDiagnosticsToken();

    try {
      return generate("ent-");
    }
    catch (final Throwable t) {
      throw new IdGeneratorException(t.getMessage(), this.serviceException.apply(t));
    }
    finally {
      token.commit();
    }
  }
  
  @Override
  public Id generateLineItemID(final EntitlementLineItem payload) throws IdGeneratorException {
    super.logger.in();
    super.logger.yaml(Log.Level.trace, payload);
    final Token token = createDiagnosticsToken();
    try {
      return generate("act-");
    }
    catch (final Throwable t) {
      throw new IdGeneratorException(t.getMessage(), this.serviceException.apply(t));
    }
    finally {
      token.commit();
    }
  }
  
  @Override
  public Id generateWebRegKey(final BulkEntitlement payload) throws IdGeneratorException {
    super.logger.in();
    super.logger.yaml(Log.Level.trace, payload);
    final Token token = createDiagnosticsToken();
    try {
      return generate("web-");
    }
    catch (final Throwable t) {
      throw new IdGeneratorException(t.getMessage(), this.serviceException.apply(t));
    }
    finally {
      token.commit();
    }
  }
  
  @Override
  public Id generateMaintenanceItemID(final MaintenanceItem payload) throws IdGeneratorException {
    super.logger.in();
    super.logger.yaml(Log.Level.trace, payload);
    final Token token = createDiagnosticsToken();
    try {
      return generate("mnt-");
    }
    catch (final Throwable t) {
      throw new IdGeneratorException(t.getMessage(), this.serviceException.apply(t));
    }
    finally {
      token.commit();
    }
  }
  
  @Override
  public Id generateFulfillmentID(final FulfillmentRecord payload) throws IdGeneratorException {
    super.logger.in();
    super.logger.yaml(Log.Level.trace, payload);
    final Token token = createDiagnosticsToken();
    try {
      return generate("fid-");
    }
    catch (final Throwable t) {
      throw new IdGeneratorException(t.getMessage(), this.serviceException.apply(t));
    }
    finally {
      token.commit();
    }
  }
  
  @Override
  public Id generateConsolidatedLicenseID(final ConsolidatedLicenseRecord payload) throws IdGeneratorException {
    super.logger.in();
    super.logger.yaml(Log.Level.trace, payload);
    final Token token = createDiagnosticsToken();
    try {
      return generate("cid-");
    }
    catch (final Throwable t) {
      throw new IdGeneratorException(t.getMessage(), this.serviceException.apply(t));
    }
    finally {
      token.commit();
    }
  }
}
