package listeners;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.impl.context.Context;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tasklistener is triggered whenever a 'Task' has been created
 * We could load a pool of eligible users (e.g. from LDAP) and manage task assignments here
 */
@Component
public class TaskListener implements org.camunda.bpm.engine.delegate.TaskListener {

    private static final Logger log = Logger.getLogger(TaskListener.class.getName()); //log implementation

    private static final String USER_ID = "Caylin";

    @Override
    public void notify(DelegateTask delegateTask) {
        log.log(Level.ALL, "Task received!");
        IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
        identityService.newUser(USER_ID); // for now lets just add a user manually, these users should come from a pool we derive from active directory
        delegateTask.setAssignee(USER_ID); //When a new task appears, for now we auto-assign to Caylin
        log.log(Level.ALL, "Task: " + delegateTask.getId() + " has been assigned to user: " + USER_ID);
    }
}
