package services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * A mocked out service to fetch document image to be displayed on frontend for user to confirm
 * validity.
 */
@Service
public class FetchDocumentsService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //fetch and store images to be consumed by front end
    }
}
