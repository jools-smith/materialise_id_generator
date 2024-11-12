package com.flexnet.external.webservice.idgenerator;

import com.flexnet.external.type.*;
import com.flexnet.external.utils.Diagnostics.Token;
import com.flexnet.external.utils.Log;
import com.flexnet.external.utils.Utils;
import com.flexnet.external.webservice.ServiceBase;

import javax.jws.WebService;
import java.time.Instant;

@WebService(
        endpointInterface = "com.flexnet.external.webservice.idgenerator.IdGeneratorServiceInterface",
        wsdlLocation = "WEB-INF/wsdl/schema/IdGeneratorService.wsdl"
)
public class IdGeneratorServiceImpl extends ServiceBase implements IdGeneratorServiceInterface {

  private static final GuidGenerator idg = new GuidGenerator();

  private static Id generate(final String value) {
   return new Id() {
     {
       this.id = value;
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
      return generate(StringGenerator.create()
              .withCharset(StringGenerator.alpha_non_hex.toUpperCase())
              .withLength(32)
              .withGroup("-", 4)
              .build());
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
      return generate(idg.guidInFours());
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
      return generate(idg.guidInFours());
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
      return generate(idg.guidInFours());
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
      return generate("FID_" + idg.guidWithUnderscore());
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
      return generate("CID_" + idg.guidWithUnderscore());
    }
    catch (final Throwable t) {
      throw new IdGeneratorException(t.getMessage(), this.serviceException.apply(t));
    }
    finally {
      token.commit();
    }
  }
}
