package lab.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import java.util.Optional;

import static lab.temporal.Namespace.KYC_WORKFLOW_ID;
import static lab.temporal.Namespace.TASK_QUEUE;

public class KycManualAction {

  private static final WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
  private static final WorkflowClient client = WorkflowClient.newInstance(service);
  private static final WorkerFactory factory = WorkerFactory.newInstance(client);

  private static void createWorker() {
    Worker worker = factory.newWorker(TASK_QUEUE);
    factory.start();
  }
  public static void main(String[] args) {
    createWorker();
    WorkflowStub kycWorkflow
        = client.newUntypedWorkflowStub(KYC_WORKFLOW_ID, Optional.empty(),Optional.of("KycWorkflow"));
    Boolean kycStatus = kycWorkflow.query(Namespace.QUERY_KYC_STATUS, Boolean.class, null);
    System.out.println("kycStatus:" + kycStatus);
    kycWorkflow.signal(Namespace.SIGNAL_KYC_COMPLETE);
    System.exit(0);
  }

}
