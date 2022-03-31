package com.jfeat.am.module.eavTest.services.domain.service.impl;
import com.jfeat.am.module.eavTest.services.domain.service.TestService;
import com.jfeat.am.module.eavTest.services.gen.crud.service.impl.CRUDTestServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("testService")
public class TestServiceImpl extends CRUDTestServiceImpl implements TestService {

    @Override
    protected String entityName() {
        return "Test";
    }


                            }
