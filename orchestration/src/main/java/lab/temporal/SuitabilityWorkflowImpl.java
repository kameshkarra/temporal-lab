package lab.temporal;

import io.temporal.workflow.Workflow;

public class SuitabilityWorkflowImpl implements SuitabilityWorkflow {
  private boolean suitabilityComplete;

  @Override
  public String startSuitability() {
    System.out.println("Suitability workflow started");
    Workflow.await(() -> suitabilityComplete);
    return "Suitability Complete";
  }

  @Override
  public boolean suitabilityStatus() {
    return suitabilityComplete;
  }

  @Override
  public void suitabilityComplete() {
    System.out.println("Suitability Complete Signal Received.");
    this.suitabilityComplete = true;
    printStatus();
  }

  private void printStatus() {
    System.out.println("suitabilityComplete: " + suitabilityComplete);
  }
}
