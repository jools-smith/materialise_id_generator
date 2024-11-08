package com.flexnet.external.webservice;

import com.flexnet.external.type.SvcException;
import com.flexnet.external.utils.Diagnostics;
import com.flexnet.external.utils.Diagnostics.Token;
import com.flexnet.external.utils.Log;
import com.flexnet.external.utils.Utils;

import java.util.function.Function;

public abstract class ServiceBase {

  static final String build = "0012";

  static final String version = "2024.11.08";

  static final Diagnostics diagnostics = new Diagnostics();

  public static String getVersion() {
    return version;
  }

  public static String getBuild() {
    return build;
  }

  public static String getBuildVersion() {
    return String.format("version | %s | %s", getVersion(), getBuild());
  }

  public static Diagnostics getDiagnostics() {
    return diagnostics;
  }

  // debuggery
  static {
    // TODO: we can reduce this potentially -- once levels have been assessed
    Log.setLoggingLevel(Log.Level.trace);

    Log.create(ServiceBase.class).log(Log.Level.info, getBuildVersion());
  }

  protected final Log logger = Log.create(this.getClass());

  protected ServiceBase() {
    this.logger.in();
  }



  protected Token createDiagnosticsToken() {
    final StackTraceElement frame = Thread.currentThread().getStackTrace()[2];
    
    return diagnostics.getToken(this.getClass(), frame.getMethodName());
  }
  
  public Function<Throwable, SvcException> serviceException = (throwable) -> new SvcException() {
    {
      this.setMessage(Utils.frameDetails(Thread.currentThread().getStackTrace()[3]));
      
      this.setName(throwable.getClass().getName());
    }
  };
}