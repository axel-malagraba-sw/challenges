package com.smallworldfs.offer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    private Long offerId;
    private Integer priority;
    @With
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private BigDecimal amount;

    public ScheduledOffer toScheduled() {
        return ScheduledOffer.builder()
                .offerId(offerId)
                .amount(amount)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
