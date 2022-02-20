package lab.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import static lab.temporal.Namespace.TASK_QUEUE;
import static lab.temporal.Namespace.WORKFLOW_ID;

public class OnboardingStarter {

  private static final WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
  private static final WorkflowClient client = WorkflowClient.newInstance(service);
  private static final WorkerFactory factory = WorkerFactory.newInstance(client);

  public static void main(String[] args) {
    createWorker();

    WorkflowOptions parentWorkflowOptions =
        WorkflowOptions.newBuilder()
            .setWorkflowId(WORKFLOW_ID)
            .setTaskQueue(TASK_QUEUE)
            .build();
    OnboardingWorkflow parentWorkflowStub =
        client.newWorkflowStub(OnboardingWorkflow.class, parentWorkflowOptions);


    // Start parent workflow and wait for it to complete
    String outcome = parentWorkflowStub.createOnboardingWorkflows(WORKFLOW_ID);

    System.out.println("Outcome=>: " + outcome);

    System.exit(0);
  }

  private static void createWorker() {
    Worker worker = factory.newWorker(TASK_QUEUE);
    worker.registerWorkflowImplementationTypes(OnboardingWorkflowImpl.class);
    worker.registerWorkflowImplementationTypes(KycWorkflowImpl.class);
    worker.registerWorkflowImplementationTypes(SuitabilityWorkflowImpl.class);
    factory.start();
  }
}
