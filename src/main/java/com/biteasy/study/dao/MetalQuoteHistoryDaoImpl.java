package com.biteasy.study.dao;

import com.biteasy.study.domain.MetalQuoteLogger;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaoxia on 16/3/13.
 */
@Repository
public class MetalQuoteHistoryDaoImpl extends GenericJpaDao <MetalQuoteLogger> implements MetalQuoteHistoryDao {

}
