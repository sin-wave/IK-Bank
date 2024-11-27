package com.pratik.loans.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("LOANS_MS");
//        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
