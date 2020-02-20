package caylinsPrototypes.fnb;

import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;
import services.AwaitDocumentService;
import services.CipcValidationService;
import services.GenerateReceiptService;

import javax.sql.DataSource;

@Configuration
public class ServiceApplicationContext {
    @Bean
    public SpringProcessEngineConfiguration engineConfiguration(DataSource dataSource,
                                                                PlatformTransactionManager transactionManager,
                                                                @Value("classpath*:*.bpmn") Resource[] deploymentResources)
    {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setProcessEngineName("engine");
        configuration.setHistoryLevel(HistoryLevel.HISTORY_LEVEL_FULL);
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setJobExecutorActivate(false);
        configuration.setDeploymentResources(deploymentResources);

        return configuration;
    }


    @Bean
    public CipcValidationService retrieveValidation() {
        return new CipcValidationService();
    }

    @Bean
    public GenerateReceiptService generateReceiptService() { return new GenerateReceiptService();
    }

    @Bean
    public AwaitDocumentService awaitDocumentService() {
        return new AwaitDocumentService();
    }
}
