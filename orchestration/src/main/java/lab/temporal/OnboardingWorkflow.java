package lab.temporal;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OnboardingWorkflow {
  @WorkflowMethod
  String createOnboardingWorkflows(String ticketId);
}
