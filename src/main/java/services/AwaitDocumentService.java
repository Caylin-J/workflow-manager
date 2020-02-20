package services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AwaitDocumentService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String absoluteFilePath = "c:\\temp\\" + "emailDocument" +".txt";
        File file = new File(absoluteFilePath);
        while (!file.exists()){
           // do nothing but wait
        }
        if (file.exists()) {
            delegateExecution.setVariable("documentReceived", "received");
        }
    }
}
