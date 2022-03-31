package com.jfeat.am.module.eavTest.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.am.module.eavTest.services.gen.persistence.model.Test;
import com.jfeat.am.module.eavTest.services.gen.persistence.dao.TestMapper;
import com.jfeat.am.module.eavTest.services.gen.crud.service.CRUDTestService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDTestService
 * @author Code generator
 * @since 2021-03-24
 */

@Service
public class CRUDTestServiceImpl  extends CRUDServiceOnlyImpl<Test> implements CRUDTestService {





        @Resource
        protected TestMapper testMapper;

        @Override
        protected BaseMapper<Test> getMasterMapper() {
                return testMapper;
        }







}


