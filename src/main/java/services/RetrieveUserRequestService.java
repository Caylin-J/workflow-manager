package services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class RetrieveUserRequestService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //for now we just set these variables manually here, but this request should be created elsewhere and received here
        delegateExecution.setVariable("ucn",true);
        delegateExecution.setVariable("riskRating", 6);

    }
}
