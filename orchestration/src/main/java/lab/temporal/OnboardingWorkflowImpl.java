package lab.temporal;

import io.temporal.api.enums.v1.ParentClosePolicy;
import io.temporal.workflow.*;

import static lab.temporal.Namespace.KYC_WORKFLOW_ID;
import static lab.temporal.Namespace.SUITABILITY_WORKFLOW_ID;

public class OnboardingWorkflowImpl implements OnboardingWorkflow {

  @Override
  public String createOnboardingWorkflows(String ticketId) {
    System.out.println("workflowId: " + ticketId);
    ChildWorkflowOptions kycWorkflowOptions =
        ChildWorkflowOptions.newBuilder()
            .setWorkflowId(KYC_WORKFLOW_ID)
            .setParentClosePolicy(ParentClosePolicy.PARENT_CLOSE_POLICY_ABANDON)
            .build();

    ChildWorkflowOptions suitabilityWorkflowOptions =
        ChildWorkflowOptions.newBuilder()
            .setWorkflowId(SUITABILITY_WORKFLOW_ID)
            .setParentClosePolicy(ParentClosePolicy.PARENT_CLOSE_POLICY_ABANDON)
            .build();

    ChildWorkflowStub kycWorkflow = Workflow.newUntypedChildWorkflowStub("KycWorkflow", kycWorkflowOptions);
    Promise<String> kycComplete = Async.function(() -> kycWorkflow.execute(String.class, (Object) null));

    ChildWorkflowStub suitabilityWorkflow = Workflow.newUntypedChildWorkflowStub("SuitabilityWorkflow", suitabilityWorkflowOptions);
    Promise<String> suitabilityComplete = Async.function(() -> suitabilityWorkflow.execute(String.class, (Object) null));

    Promise.allOf(kycComplete, suitabilityComplete).get();
    return "Onboarded";
  }
}
