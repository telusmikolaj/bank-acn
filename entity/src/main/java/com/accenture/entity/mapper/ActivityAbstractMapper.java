package com.accenture.entity.mapper;

import com.accenture.api.form.ActivityForm;
import com.accenture.api.form.CallForm;
import com.accenture.api.form.MeetingForm;
import com.accenture.api.form.OfferForm;
import com.accenture.entity.model.activity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityAbstractMapper {

    private final MeetingMapper meetingMapper;

    private final CallMapper callMapper;

    private final OfferMapper offerMapper;


    public Activity toActivity(ActivityForm activityForm) {
        if (activityForm == null) {
            return null;
        }

        if (activityForm instanceof MeetingForm) {
            return meetingMapper.toMeeting((MeetingForm) activityForm);
        } else if (activityForm instanceof CallForm) {
            return callMapper.toCall((CallForm) activityForm);
        } else if (activityForm instanceof OfferForm) {
            return offerMapper.toOffer((OfferForm) activityForm);
        } else {

            return null;
        }
    }
}
