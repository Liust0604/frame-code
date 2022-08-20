package service;

import com.mori.config.SpringConfiguration;
import com.mori.domain.Account;
import com.mori.service.AccountService;
import com.mori.service.impl.AccountServiceImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * 测试通过@Configuration配置类创建的QueryRunner对象是否单例
 */
public class QueryRunnerTest {

    ApplicationContext ac;

    @Before
    public void init() {
        //1、获取容器
        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    }


    @Test
    public void test() {
        QueryRunner queryRunner = ac.getBean("runner", QueryRunner.class);
        QueryRunner queryRunner1 = ac.getBean("runner", QueryRunner.class);
        System.out.println(queryRunner == queryRunner1);
    }
}
