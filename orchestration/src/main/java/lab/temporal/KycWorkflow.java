package lab.temporal;

import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface KycWorkflow {

  @WorkflowMethod
  String startKyc();

  @QueryMethod
  boolean kycStatus();

  @SignalMethod
  void kycComplete();
}
