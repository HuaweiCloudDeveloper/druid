
/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.pool.vendor;

import com.alibaba.druid.pool.ValidConnectionChecker;
import com.alibaba.druid.pool.ValidConnectionCheckerAdapter;
import com.alibaba.druid.util.StringUtils;

import java.io.Serializable;
import java.sql.Connection;

/**
 * @author Acewuye
 *
 * Notes: Original code of this class based on com.alibaba.druid.pool.vendor.PGValidConnectionChecker
 */
public class GaussDBValidConnectionChecker extends ValidConnectionCheckerAdapter implements ValidConnectionChecker, Serializable {
    private static final long serialVersionUID = -2227528634302168877L;

    private String defaultValidateQuery = "SELECT 'x'";

    public GaussDBValidConnectionChecker() {
        configFromProperties(System.getProperties());
    }

    public boolean isValidConnection(Connection conn,
                                     String validateQuery,
                                     int validationQueryTimeout) throws Exception {
        if (conn.isClosed()) {
            return false;
        }

        if (StringUtils.isEmpty(validateQuery)) {
            validateQuery = this.defaultValidateQuery;
        }

        return ValidConnectionCheckerAdapter.execValidQuery(conn, validateQuery, validationQueryTimeout);
    }
}
