package lab.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import static lab.temporal.Namespace.KYC_WORKFLOW_ID;
import static lab.temporal.Namespace.TASK_QUEUE;

public class KycStarter {

  private static final WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
  private static final WorkflowClient client = WorkflowClient.newInstance(service);
  private static final WorkerFactory factory = WorkerFactory.newInstance(client);

  public static void main(String[] args) {
    createWorker();
    KycWorkflow kycWorkflow =
        client.newWorkflowStub(KycWorkflow.class, KYC_WORKFLOW_ID);

    System.out.println("kycStatus:" + kycWorkflow.kycStatus());
    kycWorkflow.kycComplete();

    System.exit(0);
  }

  private static void createWorker() {
    Worker worker = factory.newWorker(TASK_QUEUE);
    worker.registerWorkflowImplementationTypes(KycWorkflowImpl.class);
    factory.start();
  }
}
