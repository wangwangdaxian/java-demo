package com.shizk.demo.java.tools.s3;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

public class S3Demo {
    public static void main(String[] args) {
        String filePath = "/tmp/blockedlist.csv";
        String bucketName = "";
        String key = "haweb-test/confignew/blacklistfile.csv";

        ClientConfiguration opts = new ClientConfiguration();
        opts.setSignerOverride("AWSS3V4SignerType");
        S3ClientOptions options = new S3ClientOptions();
        options.setPathStyleAccess(true);
        BasicAWSCredentials credentials = new BasicAWSCredentials("hypers", "hypersadmin");
        AmazonS3Client client = new AmazonS3Client(credentials, opts);
        client.setS3ClientOptions(options);
        client.setEndpoint("s3-test.hypers.cc");
        //        System.out.println(client.getRegion());
        //        client.listBuckets().forEach(System.out::println);

        client.getObject("haweb-test", "confignew/blacklistfile.csv");
        //        GetObjectRequest request = new GetObjectRequest(bucketName, key);
        //        Date now = Date.from(Instant.now());
        //        request.setModifiedSinceConstraint(now);
        //        request.setUnmodifiedSinceConstraint(now);
        //将对象存在文件中，并返回对象的元数据
        //        ObjectMetadata meta = client.getObject(request, new File(filePath));
        client.shutdown();
    }
}
