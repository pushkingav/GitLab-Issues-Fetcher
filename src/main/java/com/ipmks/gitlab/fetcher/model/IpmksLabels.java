package com.ipmks.gitlab.fetcher.model;

public enum IpmksLabels {
    QA_APPROVED ("qa: approved"),
    STATUS_WONTFIX ("status: wont-fix"),
    STATUS_INVALID ("status: invalid"),
    STATUS_REJECT ("status: reject"),
    TEST_AUTO ("test: auto"),
    TEST_DO_NOT_TEST ("test: do-not-test");

    private String title;

    IpmksLabels(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
