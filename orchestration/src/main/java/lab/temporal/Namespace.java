package lab.temporal;

public class Namespace {

  public static final int TICKET = 5;
  public static final String TASK_QUEUE = "Ticket-" + TICKET + "-Queue";
  public static final String WORKFLOW_ID = "Ticket-" + TICKET;
  public static final String KYC_WORKFLOW_ID = WORKFLOW_ID + "/Kyc";
  public static final String SUITABILITY_WORKFLOW_ID = WORKFLOW_ID + "/Suitability";
  public static final String SIGNAL_KYC_COMPLETE = "kycComplete";
  public static final String QUERY_SUITABILITY_STATUS = "suitabilityStatus";
  public static final String QUERY_KYC_STATUS = "kycStatus";
  public static final String SIGNAL_SUITABILITY_COMPLETE = "suitabilityComplete";
}
