package com.naisacul.challengeeventsmicroservice.repositories;

import com.naisacul.challengeeventsmicroservice.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
