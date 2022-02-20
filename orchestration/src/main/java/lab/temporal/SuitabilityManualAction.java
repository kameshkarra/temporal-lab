package lab.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;

import static lab.temporal.Namespace.*;

public class SuitabilityManualAction {

  private static final WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
  private static final WorkflowClient client = WorkflowClient.newInstance(service);

  public static void main(String[] args) {
    WorkflowStub parentWorkflow
        = client.newUntypedWorkflowStub(SUITABILITY_WORKFLOW_ID);
    queryStatus(parentWorkflow);
    parentWorkflow.signal(Namespace.SIGNAL_SUITABILITY_COMPLETE);
    System.exit(0);
  }

  private static void queryStatus(WorkflowStub parentWorkflow) {
    Boolean suitabilityStatus = parentWorkflow.query(QUERY_SUITABILITY_STATUS, Boolean.class, (Object) null);
    System.out.println("suitabilityStatus:" + suitabilityStatus);
  }
}
