package com.smallworldfs.offer;

import static java.time.ZonedDateTime.parse;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class OfferScheduleTest {

    @Test
    void returns_empty_when_offers_is_null() {
        assertThat(schedule(null)).isEmpty();
    }

    @Test
    void returns_empty_when_offers_is_empty() {
        assertThat(schedule(List.of())).isEmpty();
    }

    @Test
    void returns_offer_when_only_one_applies() {
        List<Offer> offers = List.of(
                offer(1, 0, "2023-06-05T15:00:00Z", "2023-07-05T15:00:00Z", 10));

        assertThat(schedule(offers)).containsExactly(
                scheduled(1, "2023-06-05T15:00:00Z", "2023-07-05T15:00:00Z", 10));
    }

    @Test
    void returns_offers_with_no_overlap() {
        List<Offer> offers = List.of(
                offer(1, 0, "2023-06-05T15:00:00Z", "2023-06-25T15:00:00Z", 10),
                offer(2, 0, "2023-06-29T15:00:00Z", "2023-07-05T15:00:00Z", 11));

        assertThat(schedule(offers)).containsExactly(
                scheduled(1, "2023-06-05T15:00:00Z", "2023-06-25T15:00:00Z", 10),
                scheduled(2, "2023-06-29T15:00:00Z", "2023-07-05T15:00:00Z", 11));
    }

    @Test
    void selects_offer_with_higher_priority_when_overlapping() {
        List<Offer> offers = List.of(
                offer(1, 0, "2023-06-05T15:00:00Z", "2023-07-05T15:00:00Z", 10),
                offer(2, 1, "2023-07-03T16:00:00Z", "2023-07-06T15:00:00Z", 9));

        assertThat(schedule(offers)).containsExactly(
                scheduled(1, "2023-06-05T15:00:00Z", "2023-07-03T15:59:59Z", 10),
                scheduled(2, "2023-07-03T16:00:00Z", "2023-07-06T15:00:00Z", 9));
    }

    @Test
    void selects_offer_with_higher_priority_when_offer_lasts_a_subset_of_another_offer_time() {
        List<Offer> offers = List.of(
                offer(1, 0, "2023-06-05T15:00:00Z", "2023-07-05T15:00:00Z", 10),
                offer(2, 1, "2023-06-25T16:00:00Z", "2023-07-01T15:00:00Z", 9));

        assertThat(schedule(offers)).containsExactly(
                scheduled(1, "2023-06-05T15:00:00Z", "2023-06-25T15:59:59Z", 10),
                scheduled(2, "2023-06-25T16:00:00Z", "2023-07-01T14:59:59Z", 9),
                scheduled(1, "2023-07-01T15:00:00Z", "2023-07-05T15:00:00Z", 10));
    }

    @Test
    void selects_offer_with_higher_priority_when_multiple_offers_overlap() {
        List<Offer> offers = List.of(
                offer(1, 0, "2023-06-05T15:00:00Z", "2023-07-05T15:00:00Z", 10),
                offer(2, 2, "2023-06-25T16:00:00Z", "2023-06-29T15:00:00Z", 9),
                offer(3, 1, "2023-06-15T16:00:00Z", "2023-07-01T15:00:00Z", 11));

        assertThat(schedule(offers)).containsExactly(
                scheduled(1, "2023-06-05T15:00:00Z", "2023-06-15T15:59:59Z", 10),
                scheduled(3, "2023-06-15T16:00:00Z", "2023-06-25T15:59:59Z", 11),
                scheduled(2, "2023-06-25T16:00:00Z", "2023-06-29T14:59:59Z", 9),
                scheduled(3, "2023-06-29T15:00:00Z", "2023-07-01T14:59:59Z", 11),
                scheduled(1, "2023-07-01T15:00:00Z", "2023-07-05T15:00:00Z", 10));
    }

    @Test
    void selects_offer_with_lower_priority_when_offer_with_higher_priority_ends() {
        List<Offer> offers = List.of(
                offer(1, 0, "2023-06-05T15:00:00Z", "2023-07-05T15:00:00Z", 10),
                offer(2, 0, "2023-06-01T16:00:00Z", "2023-07-01T15:00:00Z", 9),
                offer(3, 1, "2023-06-15T16:00:00Z", "2023-05-29T15:00:00Z", 11));

        assertThat(schedule(offers)).containsExactly(
                scheduled(2, "2023-06-01T16:00:00Z", "2023-06-15T15:59:59Z", 9),
                scheduled(3, "2023-06-15T16:00:00Z", "2023-05-29T14:59:59Z", 11),
                scheduled(2, "2023-05-29T15:00:00Z", "2023-07-01T14:59:59Z", 9),
                scheduled(1, "2023-07-01T15:00:00Z", "2023-07-05T15:00:00Z", 10));
    }

    private Offer offer(int id, int priority, String startDate, String endDate, int amount) {
        return Offer.builder()
                .offerId((long) id)
                .priority(priority)
                .startDate(parse(startDate))
                .endDate(parse(endDate))
                .amount(new BigDecimal(amount))
                .build();
    }

    private ScheduledOffer scheduled(int id, String startDate, String endDate, int amount) {
        return ScheduledOffer.builder()
                .offerId((long) id)
                .startDate(parse(startDate))
                .endDate(parse(endDate))
                .amount(new BigDecimal(amount))
                .build();
    }

    private List<ScheduledOffer> schedule(List<Offer> offers) {
        return new OfferSchedule(offers).toList();
    }
}
