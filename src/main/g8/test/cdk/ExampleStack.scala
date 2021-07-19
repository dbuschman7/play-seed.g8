package cdk

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.Bucket;

class ExampleStack(val scope: Construct, val id: String, val props: StackProps = null) extends Stack(scope, id, props) { 

    Bucket
        .Builder
        .create(this, "MyFirstBucket")
        .versioned(true)
        .build();

}

