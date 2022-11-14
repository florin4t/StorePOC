package com.management.store.rest.api.demo;

import com.management.store.rest.api.StoreProductManagementApi;

public abstract sealed class StoreManagementCertifiedImplementations implements StoreProductManagementApi
        permits FlorinImplementation, JohnImplementation {
};
