package com.accenture.entity.mapper;

import com.accenture.api.form.ActivityForm;
import com.accenture.api.form.CallForm;
import com.accenture.api.form.MeetingForm;
import com.accenture.api.form.OfferForm;
import com.accenture.entity.model.activity.Activity;
import com.accenture.entity.model.activity.Call;
import com.accenture.entity.model.activity.Meeting;
import com.accenture.entity.model.activity.Offer;
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

        if (activityForm instanceof MeetingForm meetingForm) {
            return meetingMapper.toMeeting(meetingForm);
        } else if (activityForm instanceof CallForm callForm) {
            return callMapper.toCall(callForm);
        } else if (activityForm instanceof OfferForm offerForm) {
            return offerMapper.toOffer(offerForm);
        } else {

            return null;
        }
    }

    public void updateFields(ActivityForm activityForm, Activity activity) {
        if (activityForm instanceof MeetingForm meetingForm) {
            meetingMapper.updateMeeting(meetingForm, (Meeting) activity);
        } else if (activityForm instanceof CallForm callForm) {
            callMapper.updateCall(callForm, (Call) activity);
        } else if (activityForm instanceof OfferForm offerForm) {
            offerMapper.updateOffer(offerForm, (Offer) activity);
        }
    }
}
