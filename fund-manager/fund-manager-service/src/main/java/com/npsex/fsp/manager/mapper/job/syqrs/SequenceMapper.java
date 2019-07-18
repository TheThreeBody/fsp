package com.npsex.fsp.manager.mapper.job.syqrs;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by songhuiping on 2017/7/25.
 */
@Repository
public interface SequenceMapper  {

  int getSeqNo(@Param(value="seqName")String seqName);
}
