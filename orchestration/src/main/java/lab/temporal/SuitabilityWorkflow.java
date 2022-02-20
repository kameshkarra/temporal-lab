package lab.temporal;

import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface SuitabilityWorkflow {

  @WorkflowMethod
  public String startSuitability();

  @QueryMethod
  boolean suitabilityStatus();


  @SignalMethod
  void suitabilityComplete();

}
