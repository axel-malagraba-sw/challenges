package com.smallworldfs.offer;

import static java.util.Comparator.comparing;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class OfferSchedule {

    private static final Duration OFFER_OVERLAP_MARGIN = Duration.ofSeconds(1);
    private static final Comparator<Offer> BY_START_DATE_ASC_AND_PRIORITY_DESC = comparing(Offer::getStartDate)
            .thenComparing(comparing(Offer::getPriority).reversed());

    private final Queue<Offer> queue = new PriorityQueue<>(BY_START_DATE_ASC_AND_PRIORITY_DESC);
    private final List<ScheduledOffer> scheduledOffers;

    private Offer lastOffer;
    private ScheduledOffer lastScheduledOffer;

    public OfferSchedule(List<Offer> offers) {
        Optional.ofNullable(offers).ifPresent(queue::addAll);
        scheduledOffers = new ArrayList<>(queue.size());
    }

    public List<ScheduledOffer> toList() {
        Offer offer;

        while ((offer = queue.poll()) != null) {
            processOffer(offer);
        }
        return scheduledOffers;
    }

    private void processOffer(Offer offer) {
        if (doesNotOverlap(offer)) {
            schedule(offer);
            return;
        }
        processOverlappingOffer(offer);
    }

    private boolean doesNotOverlap(Offer offer) {
        return lastOffer == null || lastOffer.getEndDate().isBefore(offer.getStartDate());
    }

    private void schedule(Offer offer) {
        lastOffer = offer;
        lastScheduledOffer = offer.toScheduled();
        scheduledOffers.add(lastScheduledOffer);
    }

    private void processOverlappingOffer(Offer offer) {
        if (isApplicableAfterLastOffer(offer)) {
            requeueOfferIfActiveAfter(lastOffer, offer.getEndDate());
            adjustLastOfferEndDate(offer);
            schedule(offer);
            return;
        }
        requeueOfferIfActiveAfter(offer, lastOffer.getEndDate());
    }

    private boolean isApplicableAfterLastOffer(Offer offer) {
        return offer.getPriority() > lastOffer.getPriority() || offer.getStartDate().equals(lastOffer.getEndDate());
    }

    private void requeueOfferIfActiveAfter(Offer offer, ZonedDateTime date) {
        if (offer.getEndDate().isAfter(date)) {
            queue.add(offer.withStartDate(date));
        }
    }

    private void adjustLastOfferEndDate(Offer nextOffer) {
        lastScheduledOffer.setEndDate(nextOffer.getStartDate().minus(OFFER_OVERLAP_MARGIN));
    }
}
