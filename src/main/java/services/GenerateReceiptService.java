package services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class GenerateReceiptService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String absoluteFilePath = "c:\\temp\\" + "generatedReceipt-"+ (new java.util.Date()).getTime() +".txt";
        File file = new File(absoluteFilePath);
        if (file.createNewFile()) {
            System.out.println(absoluteFilePath + " File Created");
            delegateExecution.setVariable("receiptGenerated", "generated");
        }
    }
}
