//package caylinsPrototypes.fnb;
//
//import org.camunda.bpm.dmn.engine.DmnDecision;
//import org.camunda.bpm.dmn.engine.DmnEngine;
//import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//@Configuration
//public class DecisionStarter {
//
//    @Autowired
//    private ResourceLoader resourceLoader;
//    @Autowired
//    private DmnEngine dmnEngine;
//    private Resource resource;
//
//    @Bean
//    public DmnEngine createDmnEngine() {
//        // create default DMN engine configuration
//        DmnEngineConfiguration configuration = DmnEngineConfiguration
//                .createDefaultDmnEngineConfiguration();
//
//        // build a new DMN engine
//        DmnEngine dmnEngine = configuration.buildEngine();
//        return dmnEngine;
//    }
//
//    @Bean(name = "routeDecision")
//    public DmnDecision createDecisionRouteDecision() {
//        Resource resource = resourceLoader.getResource("classpath:/routeDecision.dmn");
//        InputStream is = null;
//        try {
//            is = resource.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dmnEngine.parseDecision("decision", is);
//    }
//}
