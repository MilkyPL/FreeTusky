package com.thechiefmeat.freetusky.interfaces;

public interface PermissionRequester {
    void onRequestPermissionsResult(String[] permissions, int[] grantResults);
}