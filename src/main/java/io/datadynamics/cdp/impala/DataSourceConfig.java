package io.datadynamics.cdp.impala;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DataSourceConfig {

    @Value("${connection.url}")
    private String connectionURL;

    @Value("${connection.username}")
    private String userName;

    @Value("${connection.password}")
    private String password;

    @Bean
    public DataSource dataSource() throws IOException {
/*
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "true");
        System.setProperty("java.security.krb5.conf", "/Users/amarendra/IdeaProjects/spring-boot-impala/src/main/resources/krb5.conf");
        System.setProperty("java.security.auth.login.config","/Users/amarendra/IdeaProjects/spring-boot-impala/src/main/resources/jaas.conf");
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.set("hadoop.security.authentication", "kerberos");
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation.loginUserFromKeytab("impala/quickstart.cloudera@CLOUDERA", "/Users/amarendra/IdeaProjects/spring-boot-impala/src/main/resources/impala.keytab");
*/

        com.cloudera.impala.jdbc.DataSource dataSource = new com.cloudera.impala.jdbc.DataSource();
        dataSource.setURL(connectionURL);
        if (!StringUtils.isEmpty(userName)) dataSource.setUserID(userName);
        if (!StringUtils.isEmpty(password)) dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcStream(dataSource);
    }
}
