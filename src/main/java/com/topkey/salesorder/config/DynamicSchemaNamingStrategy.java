package com.topkey.salesorder.config;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 動態從 config server 取得 JPA Schema
 */
@Component
public class DynamicSchemaNamingStrategy extends CamelCaseToUnderscoresNamingStrategy  {

	@Value("${jde-table.schema:defaultSchema}")
    private String schemaName;

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
        return name != null ? Identifier.toIdentifier(schemaName) : null;
    }
}
