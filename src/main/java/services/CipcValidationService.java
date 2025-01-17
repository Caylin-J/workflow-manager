package services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * A mocked out service to call CIPC validation...
 */
@Service
public class CipcValidationService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //Call CIPC service and delegate validity..
        delegateExecution.setVariable("fraudValidation", "fail");
    }
}
