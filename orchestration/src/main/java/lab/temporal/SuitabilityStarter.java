package lab.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.Async;
import io.temporal.workflow.Promise;

import static lab.temporal.Namespace.SUITABILITY_WORKFLOW_ID;
import static lab.temporal.Namespace.TASK_QUEUE;

public class SuitabilityStarter {

  private static final WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
  private static final WorkflowClient client = WorkflowClient.newInstance(service);
  private static final WorkerFactory factory = WorkerFactory.newInstance(client);

  public static void main(String[] args) {
    createWorker();
    SuitabilityWorkflow suitabilityWorkflow =
        client.newWorkflowStub(SuitabilityWorkflow.class, SUITABILITY_WORKFLOW_ID);

    System.out.println("suitabilityStatus:" + suitabilityWorkflow.suitabilityStatus());

    suitabilityWorkflow.suitabilityComplete();

    System.exit(0);
  }

  private static void createWorker() {
    Worker worker = factory.newWorker(TASK_QUEUE);
    worker.registerWorkflowImplementationTypes(SuitabilityWorkflowImpl.class);
    factory.start();
  }
}
