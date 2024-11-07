package com.flexnet.external.webservice.idgenerator;

import com.flexnet.external.type.*;
import com.flexnet.external.utils.Diagnostics.Token;
import com.flexnet.external.utils.Log;
import com.flexnet.external.webservice.ServiceBase;
import org.apache.commons.lang3.NotImplementedException;

import javax.jws.WebService;

@WebService(
        endpointInterface = "com.flexnet.external.webservice.idgenerator.IdGeneratorServiceInterface",
        wsdlLocation = "WEB-INF/wsdl/schema/IdGeneratorService.wsdl"
)
public class IdGeneratorServiceImpl extends ServiceBase implements IdGeneratorServiceInterface {

  @Override
  public PingResponse ping(final PingRequest payload) throws IdGeneratorException {
    super.logger.in();
    super.logger.yaml(Log.Level.trace, payload);
    final Token token = createDiagnosticsToken();

    try {
     throw new NotImplementedException("ping");
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
      throw new NotImplementedException("generateEntitlementID");
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
      throw new NotImplementedException("generateLineItemID");
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
      throw new NotImplementedException("generateWebRegKey");
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
      throw new NotImplementedException("generateMaintenanceItemID");
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
      throw new NotImplementedException("generateFulfillmentID");
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
      throw new NotImplementedException("generateConsolidatedLicenseID");
    }
    catch (final Throwable t) {
      throw new IdGeneratorException(t.getMessage(), this.serviceException.apply(t));
    }
    finally {
      token.commit();
    }
  }
}
