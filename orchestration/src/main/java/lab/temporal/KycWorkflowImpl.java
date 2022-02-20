package lab.temporal;

import io.temporal.workflow.Workflow;

public class KycWorkflowImpl implements KycWorkflow {
  private boolean kycComplete;

  @Override
  public void kycComplete() {
    System.out.println("KYC Complete Signal Received.");
    this.kycComplete = true;
    printStatus();
  }

  @Override
  public String startKyc() {
    System.out.println("Kyc workflow started");
    Workflow.await(() -> kycComplete);
    return "KYC Complete";
  }

  @Override
  public boolean kycStatus() {
    return kycComplete;
  }

  private void printStatus() {
    System.out.println("kycComplete: " + kycComplete);
  }
}
