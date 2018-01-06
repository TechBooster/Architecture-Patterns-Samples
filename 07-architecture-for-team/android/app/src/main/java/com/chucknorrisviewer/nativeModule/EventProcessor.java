package com.chucknorrisviewer.nativeModule;


import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

public class EventProcessor {
    private final PublishProcessor<ReactEvent> eventProcessor = PublishProcessor.create();
    /**
     *  event processor
     */
    public FlowableProcessor<ReactEvent> getEventProcessor() {
        return eventProcessor;
    }
}
