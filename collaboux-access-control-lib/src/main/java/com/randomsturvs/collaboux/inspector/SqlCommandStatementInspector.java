package com.randomsturvs.collaboux.inspector;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class SqlCommandStatementInspector implements StatementInspector {

    private  final Logger LOGGER = LoggerFactory
            .getLogger(
                    SqlCommandStatementInspector.class
            );

    private  final Pattern SQL_COMMENT_PATTERN = Pattern
            .compile(
                    "\\/\\*.*?\\*\\/\\s*"
            );

    @Override
    public String inspect(String sql) {
        LOGGER.debug("Executing SQL query: {}", sql);

        return SQL_COMMENT_PATTERN
                .matcher(sql)
                .replaceAll("");
    }
}