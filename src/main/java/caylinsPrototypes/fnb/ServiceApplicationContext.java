package caylinsPrototypes.fnb;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.PlatformTransactionManager;
import services.AwaitDocumentService;
import services.CipcValidationService;
import services.GenerateReceiptService;
import services.RetrieveUserRequestService;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ServiceApplicationContext {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private static DmnEngine dmnEngine;
    private Resource resource;


    @Bean
    public SpringProcessEngineConfiguration engineConfiguration(DataSource dataSource,
                                                                PlatformTransactionManager transactionManager,
                                                                @Value("classpath*:*.bpmn, *.dmn") Resource[] deploymentResources)
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

    @Bean
    public RetrieveUserRequestService retrieveUserRequest() { return new RetrieveUserRequestService(); }

    @Bean
    public DmnEngine dmnEngine() {
        // create default DMN engine configuration
        DmnEngineConfiguration configuration = DmnEngineConfiguration
                .createDefaultDmnEngineConfiguration();

        // build a new DMN engine
        DmnEngine dmnEngine = configuration.buildEngine();
        return dmnEngine;
    }

    @Bean
    public DmnDecision routeDecision() {
        Resource resource = resourceLoader.getResource("classpath:/routeDecision.dmn");
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dmnEngine.parseDecision("decision", is);
    }

}
