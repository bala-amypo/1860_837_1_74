@Service
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository,
                            PolicyRepository policyRepository) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }

    public Claim createClaim(Long policyId, Claim claim) {
        Policy policy = policyRepository.findById(policyId)
            .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));

        if (claim.getClaimAmount() <= 0)
            throw new IllegalArgumentException("Invalid claim amount");

        if (claim.getClaimDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Invalid claim date");

        claim.setPolicy(policy);
        return claimRepository.save(claim);
    }
}
