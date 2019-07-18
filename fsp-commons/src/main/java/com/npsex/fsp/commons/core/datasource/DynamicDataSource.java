package com.npsex.fsp.commons.core.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * Created by dongwen on 2017/7/4.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getCustomerType();
    }

    //重置默认数据源
//    protected Object resetDefaultTargetDataSource() {return setTargetDataSources(this.defaultT);}

}
