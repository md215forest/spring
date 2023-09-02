package jp.co.practice.config;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jp.co.practice.util.PropertyUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

/**
 * Elasticsearch Java Client Configuration
 */
@Configuration
public class ElasticsearchConfig {

    /** リリースレベル **/
    @Value("${release.env}")
    private String releaseEnv;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        // create the API client
        return new ElasticsearchClient(getTransport());
    }

    @Bean
    public ElasticsearchAsyncClient elasticsearchAsyncClient() {
        // create the Async API client
        return new ElasticsearchAsyncClient(getTransport());
    }

    private ElasticsearchTransport getTransport() {
        String fingerprint = PropertyUtil.getApplicationValue("elasticsearch.fingerprint");

        SSLContext sslContext = TransportUtils.sslContextFromCaFingerprint(fingerprint);

        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials(PropertyUtil.getApplicationValue("elasticsearch.username"), PropertyUtil.getApplicationValue("elasticsearch.password"))
        );

        // Create the low-level client
        RestClient restClient = RestClient
                .builder(new HttpHost(PropertyUtil.getApplicationValue("elasticsearch.host"), PropertyUtil.getApplicationIntValueOrElseThrow("elasticsearch.port"), PropertyUtil.getApplicationValue("elasticsearch.schema")))
                .setHttpClientConfigCallback(hc -> {
                    hc
                            .setSSLContext(sslContext)
                            .setDefaultCredentialsProvider(credsProv);
                    if (ReleaseEnvironment.of(releaseEnv).isInspection()) {
                        // HOST名のチェックの無効化
                        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
                        hc.setSSLHostnameVerifier(hostnameVerifier);
                    }
                    return hc;
                })
                .build();

        // Create the transport with a Jackson mapper
        return new RestClientTransport(restClient, getJsonpMapper());
    }

    private static JacksonJsonpMapper getJsonpMapper() {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        return new JacksonJsonpMapper(objectMapper);
    }
}
