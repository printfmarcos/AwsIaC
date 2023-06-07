package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

import java.util.Arrays;

public class WsInfraAwsApp {
    public static void main(final String[] args) {
        App app = new App();
        AluraVpcStack vpcStack = new AluraVpcStack(app, "Vpc");
        AluraClusterStack clusterStack = new AluraClusterStack(app, "Cluster", vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        AluraRdsStack aluraRdsStack = new AluraRdsStack(app, "Rds", vpcStack.getVpc());
        aluraRdsStack.addDependency(vpcStack);

        AluraServiceStack aluraServiceStack = new AluraServiceStack(app, "Service", clusterStack.getCluster());
        aluraServiceStack.addDependency(clusterStack);
        aluraServiceStack.addDependency(aluraRdsStack);
        app.synth();
    }
}

