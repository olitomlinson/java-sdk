/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */

package io.dapr.examples.tracing;

import io.dapr.springboot.DaprApplication;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

/**
 * Main method to invoke DemoService to test tracing.
 *
 * <p>Instrumentation is handled in {@link io.dapr.springboot.OpenTelemetryInterceptor}.
 *
 * <p>1. Build and install jars:
 * mvn clean install
 * 2. Run in server mode:
 * dapr run --components-path ./examples/components --app-id tracingdemo --app-port 3000 --port 3005 \
 *   -- java -jar examples/target/dapr-java-sdk-examples-exec.jar \
 *   io.dapr.examples.tracing.TracingDemoService -p 3000
 */
public class TracingDemoService {

  /**
   * Starts the service.
   * @param args Expects the port: -p PORT
   * @throws Exception If cannot start service.
   */
  public static void main(String[] args) throws Exception {
    Options options = new Options();
    options.addRequiredOption("p", "port", true, "Port to listen to.");

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);

    // If port string is not valid, it will throw an exception.
    int port = Integer.parseInt(cmd.getOptionValue("port"));

    DaprApplication.start(port);
  }
}