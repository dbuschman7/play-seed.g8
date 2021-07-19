package cdk 

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

import org.scalatest.funsuite.AnyFunSuite

class CdkSynth extends AnyFunSuite {

  test("main") {
    val app = new App()

    new ExampleStack(app, "ExampleStack", StackProps.builder()
//            .env(Environment.builder()
//                        .account(System.getenv("CDK_DEFAULT_ACCOUNT"))
//                        .region(System.getenv("CDK_DEFAULT_REGION"))
//                        .build()
//                )
            .build())

    app.synth()        

  }
}