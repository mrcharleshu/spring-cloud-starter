package com.charles.springcloud.supplier.db.hateoas;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect {
    private static final String TABLE_TYPE_WITH_UTF8 = " ENGINE=InnoDB DEFAULT CHARSET=utf8";

    @Override
    public String getTableTypeString() {
        return TABLE_TYPE_WITH_UTF8;
    }
}
