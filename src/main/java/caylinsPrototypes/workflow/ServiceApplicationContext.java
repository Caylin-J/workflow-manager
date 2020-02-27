package caylinsPrototypes.workflow;

import listeners.TaskListener;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.PlatformTransactionManager;
import services.*;

import javax.sql.DataSource;
/**
 * Stores Camunda engine configuration and initializes services as beans
*/

@Configuration
public class ServiceApplicationContext {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private static DmnEngine dmnEngine;
    private Resource resource;

    /**
     * Camunda Engine Configuration
     */
    @Bean
    public SpringProcessEngineConfiguration engineConfiguration(DataSource dataSource,
                                                                PlatformTransactionManager transactionManager,
                                                                @Value("classpath*:*mn") Resource[] deploymentResources) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setProcessEngineName("engine");
        configuration.setHistoryLevel(HistoryLevel.HISTORY_LEVEL_FULL);
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setJobExecutorActivate(false);
        configuration.setDeploymentResources(deploymentResources);
        //dmn configuration
        configuration.setDmnEnabled(true);
        DefaultDmnEngineConfiguration defaultDmnEngineConfiguration = (DefaultDmnEngineConfiguration) DmnEngineConfiguration.createDefaultDmnEngineConfiguration();
        configuration.setDmnEngineConfiguration(defaultDmnEngineConfiguration);
        return configuration;
    }

    /**
     * Service Beans
     */
    @Bean
    public CipcValidationService retrieveValidation() {
        return new CipcValidationService();
    }

    @Bean
    public GenerateReceiptService generateReceiptService() { return new GenerateReceiptService(); }

    @Bean
    public AwaitDocumentService awaitDocumentService() {
        return new AwaitDocumentService();
    }

    @Bean
    public RetrieveUserRequestService retrieveUserRequest() { return new RetrieveUserRequestService(); }

    @Bean
    public FetchDocumentsService fetchDocuments() { return new FetchDocumentsService(); }

    /**
     * Listener Beans
     */
    @Bean
    public TaskListener taskAssignListener() { return new TaskListener(); }
}
