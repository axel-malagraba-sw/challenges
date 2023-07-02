package com.smallworldfs.offer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledOffer {

    private Long offerId;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private BigDecimal amount;
}
